package test.sskim.junit;


public class Study {

    StudyStatus status = StudyStatus.DRAFT;
    
    private int limit = 0;

    private String name ="";


    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    
    @Override
    public String toString() {
        return "Study [limit=" + limit + ", name=" + name + "]";
    }

    /**
     * @param limit
     * @param name
     */
    public Study(int limit, String name) {
        this.limit = limit;
        this.name = name;
    }

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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
}



