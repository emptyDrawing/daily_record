package my.sskim.test;

import java.util.Objects;

public class OnClass {
   
    
    private Integer id;
    private String title;
    private boolean closed;

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
