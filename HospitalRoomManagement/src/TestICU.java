import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestICU {

	@Test
    public void testAllocateRoom() {
        ICURoom icuRoom = new ICURoom(101, true);
        icuRoom.allocateRoom();
        assertEquals(RoomStatus.Occupied, icuRoom.getStatus());
    }

    @Test
    public void testMarkUnderMaintenanceWhenOccupied() {
        ICURoom icuRoom = new ICURoom(101, true);
        icuRoom.allocateRoom();
        icuRoom.markUnderMaintenance();
        assertEquals(RoomStatus.Occupied, icuRoom.getStatus()); // Maintenance shouldn't change the status
    }

    @Test
    public void testMarkReadyAfterMaintenance() {
        ICURoom icuRoom = new ICURoom(101, true);
        icuRoom.markUnderMaintenance();
        icuRoom.markReady();
        assertEquals(RoomStatus.Clean, icuRoom.getStatus());
    }
}
