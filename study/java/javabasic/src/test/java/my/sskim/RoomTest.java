package my.sskim;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoomTest {
    
    @Test
    void isFull() {
        Room room = new Room(100, 10);

        assertFalse(room.isFull());
    }
}
