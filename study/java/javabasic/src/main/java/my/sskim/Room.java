package my.sskim;

public class Room {
    
    int maxSzie;

    int nowSize;

    public boolean isFull() {
        if ( maxSzie == 0 ){
            return false;
        }
        
        if ( maxSzie > nowSize ) {
            return false;
        }

        return true;

    }

    public Room(int maxSzie, int nowSize) {
        this.maxSzie = maxSzie;
        this.nowSize = nowSize;
    }


    public int getMaxSzie() {
        return this.maxSzie;
    }

    public void setMaxSzie(int maxSzie) {
        this.maxSzie = maxSzie;
    }

    public int getNowSize() {
        return this.nowSize;
    }

    public void setNowSize(int nowSize) {
        this.nowSize = nowSize;
    }
    

}
