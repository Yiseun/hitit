package hitit.hit.moderator;

import hitit.hit.dto.request.HitRequest;
import hitit.hit.entity.DailyHits;
import hitit.hit.entity.Hit;
import hitit.hit.type.WorkerType;
import hitit.post.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import java.awt.*;
import java.util.List;

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

        //이거 operation 2에 있어야할듯
        HitRequest nowTask = HitModerator.getAssignment();
        HitModerator.assignmentCountMinus();

        //nowTask가 비어서 initializer가 진입하지못함

            if(workerType.getWorkerNum()==1){
              EntityTransaction transaction = entityManager.getTransaction();
              try {
                transaction.begin();
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                CriteriaQuery<Hit> criteriaQuery = criteriaBuilder.createQuery(Hit.class);
                Root<Hit> hit = criteriaQuery.from(Hit.class);
                criteriaQuery.select(hit);
                TypedQuery<Hit> query = entityManager.createQuery(criteriaQuery);
                List<Hit> hits = query.getResultList();
                hits.forEach(hitt -> {
                    entityManager.persist(DailyHits.from(hitt));
                    entityManager.merge(Hit.initHit(hitt));
                        }
                );
                  transaction.commit();
              }catch (Exception e){
                  transaction.rollback();
              }finally {
                  entityManager.close();
              }
            }else {
              if(nowTask!=null) {
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
