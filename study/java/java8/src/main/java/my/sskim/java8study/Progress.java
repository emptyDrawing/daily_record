package my.sskim.java8study;

import java.time.Duration;

public class Progress {
    
    private Duration studyDuration;
    private boolean finished;
    /**
     * @param studyDuration
     * @param finished
     */
    public Progress(Duration studyDuration, boolean finished) {
        this.studyDuration = studyDuration;
        this.finished = finished;
    }
    /**
     * @return the studyDuration
     */
    public Duration getStudyDuration() {
        return studyDuration;
    }
    /**
     * @param studyDuration the studyDuration to set
     */
    public void setStudyDuration(Duration studyDuration) {
        this.studyDuration = studyDuration;
    }
    /**
     * @return the finished
     */
    public boolean isFinished() {
        return finished;
    }
    /**
     * @param finished the finished to set
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    

}
