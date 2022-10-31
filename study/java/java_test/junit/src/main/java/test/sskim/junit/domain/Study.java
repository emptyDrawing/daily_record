package test.sskim.junit.domain;

import java.util.Date;

import test.sskim.junit.study.StudyStatus;

public class Study {

    StudyStatus status = StudyStatus.DRAFT;
    
    private int limit = 0;

    private String name ="";

    private Member owner;

    private Date openedDataTime;

    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    
    /**
     * @return the owner
     */
    public Member getOwner() {
        return owner;
    }

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

    public void setOwner(Member member) {
        this.owner = member;
    }

    public void open() {
        this.status = StudyStatus.OPEN;
        this.openedDataTime = new Date();

    }

    /**
     * @return the openedDataTime
     */
    public Date getOpenedDataTime() {
        return openedDataTime;
    }
    
}



