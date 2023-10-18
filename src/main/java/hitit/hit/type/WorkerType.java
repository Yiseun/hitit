package hitit.hit.type;

public enum WorkerType {
    INITIALIZER(1),
    NONINITIALIZER(2);

    private int workerNum;
    WorkerType(int workerNum){
        this.workerNum = workerNum;
    }

    public int getWorkerNum(){
        return workerNum;
    }
}
