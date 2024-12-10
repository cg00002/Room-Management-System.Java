import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestOR {

	private OperatingRoom operatingRoom;
	
	/**
	 * Creates a fresh OperatingRoom instance before each test. This
	 * avoids problems that arised before doing this.
	 */
	@BeforeEach
    public void setUp() {
        operatingRoom = new OperatingRoom(401);
    }
	
	@Test
    public void testStartSurgery() {
        operatingRoom.startSurgery("Heart Surgery");
        assertTrue(operatingRoom.isInUse(), "Operating room should be marked as in use.");
        assertEquals("Heart Surgery", operatingRoom.getSurgeryType(), "Surgery type should be 'Heart Surgery'.");
        assertEquals(RoomStatus.Occupied, operatingRoom.getStatus(), "Room status should be Occupied.");
    }

    @Test
    public void testEndSurgery() {
        operatingRoom.startSurgery("Heart Surgery");
        operatingRoom.endSurgery();
        assertFalse(operatingRoom.isInUse(), "Operating room should no longer be in use.");
        assertEquals("", operatingRoom.getSurgeryType(), "Surgery type should be reset after ending surgery.");
        assertEquals(RoomStatus.BeingCleaned, operatingRoom.getStatus(), "Room status should be BeingCleaned.");
    }

    @Test
    public void testMarkUnderMaintenance() {
        operatingRoom.markUnderMaintenance();
        assertEquals(RoomStatus.UnderMaintenance, operatingRoom.getStatus(), "Room should be under maintenance.");
    }

    @Test
    public void testCannotStartSurgeryWhenUnderMaintenance() {
        operatingRoom.markUnderMaintenance();
        operatingRoom.startSurgery("Heart Surgery");
        assertFalse(operatingRoom.isInUse(), "Surgery should not start when the room is under maintenance.");
        assertEquals("", operatingRoom.getSurgeryType(), "Surgery type should remain empty.");
        assertEquals(RoomStatus.UnderMaintenance, operatingRoom.getStatus(), "Room should remain under maintenance.");
    }

    @Test
    public void testSanitizeRoom() {
        operatingRoom.startSurgery("Heart Surgery");
        operatingRoom.endSurgery();
        operatingRoom.sanitizeRoom();
        assertEquals(RoomStatus.Clean, operatingRoom.getStatus(), "Room should be clean after sanitization.");
    }
}
