package test.sskim.junit;


public class Study {

    StudyStatus status = StudyStatus.DRAFT;
    
    private int limit = 0;


    public Study(int limit) {
        if ( limit < 0 ) {
            throw new IllegalArgumentException("limit 는 0보다 커야된다.");
        }
        this.limit = limit;
    }

    public StudyStatus getStatus() {
        return status;
    }

    public int getLimit() {
        return limit;
    }
    
}



