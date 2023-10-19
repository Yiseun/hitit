package hitit.hit.moderator;

import hitit.hit.dto.request.HitRequest;
import hitit.hit.type.WorkerType;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class HitModerator {
    private static final Logger log = LoggerFactory.getLogger(HitModerator.class);
    private static ExecutorService ex;
    private static final int DB_WORKER = 8;
    private static final int WAIT_MILLISECONDS = 1000;
    private static volatile AtomicInteger assignmentCount;
    private static LocalDate serverTime;
    private static Queue<HitRequest> assignment;
    private static EntityManagerFactory entityManagerFactory;



    static{
        entityManagerFactory = Persistence.createEntityManagerFactory("hit");
        assignment = new ConcurrentLinkedQueue<>();
        ex = Executors.newFixedThreadPool(DB_WORKER);
        assignmentCount = new AtomicInteger();
        serverTime = LocalDate.now();
    }

    private synchronized static void detectChange(){
//        LocalDate now = LocalDate.now();
        LocalDate now = LocalDate.now().plusDays(1);
        if(Objects.equals(serverTime, now)){
            return;
        }
        serverTime = now;
        ex.execute(HitWorker.of(WorkerType.INITIALIZER));

    }


    public static void assignmentCountPlus(){
        assignmentCount.getAndIncrement();
    }
    public static void assignmentCountMinus(){
        if(assignmentCount.get()>0){
            assignmentCount.getAndDecrement();
            return;
        }
        assignmentCount.set(0);
    }

    public static void assignTask(HitRequest hitRequest){
        assignment.add(hitRequest);
        assignmentCountPlus();
    }

    public static EntityManagerFactory getEntityManagerFactory(){
        return entityManagerFactory;
    }

    public static HitRequest getAssignment(){
        return assignment.poll();
    }



    public static LocalDate getServerTime(){
        return serverTime;
    }


    public static void run(){
        new Thread(()->{
            log.info("Initialized Moderator");
            while(true){
                detectChange();
                if(assignmentCount.get()>0) {
                    ex.execute(HitWorker.of(WorkerType.NONINITIALIZER));
                    continue;
                }
                try {
                    Thread.sleep(WAIT_MILLISECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
