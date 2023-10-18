package hitit.hit.moderator;

import hitit.hit.dto.request.HitRequest;
import hitit.hit.spring.entity.DailyHits;
import hitit.hit.spring.entity.Hit;
import hitit.hit.type.WorkerType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HitWorker implements Runnable{
    private final Logger log = LoggerFactory.getLogger(HitWorker.class);
    private static HitWorker instance;
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private WorkerType workerType;


    public HitWorker(final EntityManager entityManager,final WorkerType workerType){
        this.entityManager = entityManager;
        this.workerType = workerType;
    }



    @Override
    public void run() {

        HitRequest nowTask = HitModerator.getAssignment();
        if(nowTask!=null) {
            HitModerator.assignmentCountMinus();

            if(workerType.getWorkerNum()==1){
              EntityTransaction transaction = entityManager.getTransaction();
              try {
                  transaction.begin();
                  Hit hit = entityManager.find(Hit.class, nowTask.getUrlId());
                  if(hit==null) {
                      hit = Hit.initAllHit(nowTask.getUrlId());
                  }
                  DailyHits newDailyHits = DailyHits.of(hit.getUrlId(), HitModerator.getServerTime(), hit.getHit());
                  hit = Hit.initHit(hit);
                  entityManager.merge(hit);
                  entityManager.persist(newDailyHits);
                  transaction.commit();
              }catch (Exception e){
                  transaction.rollback();
              }finally {
                  entityManager.close();
              }
            }else {
                    EntityTransaction transaction = entityManager.getTransaction();
                try {
                    transaction.begin();
                    Hit hit = entityManager.find(Hit.class, nowTask.getUrlId());
                    if(hit==null){
                        hit = Hit.initAllHit(nowTask.getUrlId());
                    }
                    Hit newHit = Hit.addHit(hit);
                    entityManager.merge(newHit);
                    transaction.commit();
                } catch (Exception e) {
                    transaction.rollback();
                }finally {
                    entityManager.close();
                }
            }
        }
    }


    public synchronized static HitWorker of(WorkerType workerType){
        if(instance==null) {
            return new HitWorker(HitModerator.getEntityManagerFactory()
                        .createEntityManager(),workerType);
        }
        return instance;
    }

}
