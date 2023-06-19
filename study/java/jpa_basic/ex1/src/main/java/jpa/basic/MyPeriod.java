package jpa.basic;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(this.startDate, this.endDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MyPeriod)) {
            return false;
        }
        MyPeriod other = (MyPeriod) obj;
        return Objects.equals(this.startDate, other.startDate) && Objects.equals(this.endDate, other.endDate);
    }

    
    
}
