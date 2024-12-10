import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestJanitorial {

	@Test
    public void testStartTask() {
        JanitorialRoom janitorialRoom = new JanitorialRoom(301);
        janitorialRoom.startTask(TaskType.Cleaning);
        assertEquals(RoomStatus.Occupied, janitorialRoom.getStatus());
    }

    @Test
    public void testCompleteTask() {
        JanitorialRoom janitorialRoom = new JanitorialRoom(301);
        janitorialRoom.startTask(TaskType.Maintenance);
        janitorialRoom.completeTask();
        assertEquals(RoomStatus.Avaliable, janitorialRoom.getStatus());
    }

    @Test
    public void testMarkUnderMaintenance() {
        JanitorialRoom janitorialRoom = new JanitorialRoom(301);
        janitorialRoom.markUnderMaintenance();
        assertEquals(RoomStatus.UnderMaintenance, janitorialRoom.getStatus());
    }
}
