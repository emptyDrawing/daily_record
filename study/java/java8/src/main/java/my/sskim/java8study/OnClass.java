package my.sskim.java8study;

import java.util.Objects;
import java.util.Optional;

public class OnClass {
   
    
    private Integer id;
    private String title;
    private boolean closed;
    private Progress progress;


    
    /**
     * @param id
     * @param title
     * @param closed
     * @param progress
     */
    public OnClass(Integer id, String title, boolean closed, Progress progress) {
        this.id = id;
        this.title = title;
        this.closed = closed;
        this.progress = progress;
    }

    /**
     * @return the progress
     */
    public Optional<Progress> getProgress() {
        return Optional.ofNullable(progress);
    }

    /**
     * @param progress the progress to set
     */
    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public OnClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return this.closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public OnClass id(Integer id) {
        setId(id);
        return this;
    }

    public OnClass title(String title) {
        setTitle(title);
        return this;
    }

    public OnClass closed(boolean closed) {
        setClosed(closed);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OnClass)) {
            return false;
        }
        OnClass onClass = (OnClass) o;
        return Objects.equals(id, onClass.id) && Objects.equals(title, onClass.title) && closed == onClass.closed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, closed);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", closed='" + isClosed() + "'" +
            "}";
    }


}
