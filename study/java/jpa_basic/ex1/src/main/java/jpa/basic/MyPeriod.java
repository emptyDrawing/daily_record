package jpa.basic;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

@Embeddable
public class MyPeriod {
    
    private LocalDateTime startDate;
	private LocalDateTime endDate;
    /**
     * 
     */
    public MyPeriod() {
    }
    
    /**
     * @param startDate
     * @param endDate
     */
    public MyPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * @return the startDate
     */
    public LocalDateTime getStartDate() {
        return this.startDate;
    }
    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    /**
     * @return the endDate
     */
    public LocalDateTime getEndDate() {
        return this.endDate;
    }
    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    
}
