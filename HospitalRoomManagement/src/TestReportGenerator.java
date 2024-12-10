import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestReportGenerator {

	@Test
    public void testGenerateRoomStatusReport() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new ICURoom(101, true));
        rooms.add(new ERRoom(201, 3));
        rooms.add(new JanitorialRoom(301));
        rooms.add(new OperatingRoom(401));

        ReportGenerator reportGenerator = new ReportGenerator(rooms);
        assertDoesNotThrow(() -> reportGenerator.generateRoomStatusReport());
    }

    @Test
    public void testGenerateOccupancyReport() {
        List<Room> rooms = new ArrayList<>();
        ICURoom icuRoom = new ICURoom(101, true);
        icuRoom.setStatus(RoomStatus.Occupied);
        rooms.add(icuRoom);
        rooms.add(new ERRoom(201, 3));

        ReportGenerator reportGenerator = new ReportGenerator(rooms);
        assertDoesNotThrow(() -> reportGenerator.generateOccupancyReport());
    }

    @Test
    public void testGenerateAvailabilityReport() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new JanitorialRoom(301));
        rooms.get(0).setStatus(RoomStatus.Avaliable);

        ReportGenerator reportGenerator = new ReportGenerator(rooms);
        assertDoesNotThrow(() -> reportGenerator.generateAvailabilityReport());
    }

    @Test
    public void testGenerateGroupedStatusReport() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new ICURoom(101, true));
        rooms.add(new ERRoom(201, 3));
        rooms.add(new JanitorialRoom(301));
        rooms.add(new OperatingRoom(401));

        ReportGenerator reportGenerator = new ReportGenerator(rooms);
        assertDoesNotThrow(() -> reportGenerator.generateGroupedStatusReport());
    }
}
