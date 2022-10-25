package test.sskim.junit;


public class Study {

    StudyStatus status = StudyStatus.DRAFT;
    
    private int limit = 0;



    
    public StudyStatus getStatus() {
        return status;
    }

    public int getLimit() {
        return limit;
    }
    
}