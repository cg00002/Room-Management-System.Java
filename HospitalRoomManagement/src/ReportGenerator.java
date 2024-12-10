import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportGenerator {

	List<Room> rooms;
	
	public ReportGenerator(List<Room> rooms) {
		this.rooms = rooms;
	}
	
	public void generateRoomStatusReport() {
		System.out.println("=== Current Room Status Report ===");
		for (Room room: rooms) {
			room.displayDetails();
		}
	}
	
	public void generateOccupancyReport() {
        System.out.println("=== Room Occupancy Report === ");
        long occupiedCount = rooms.stream().filter(room -> room.getStatus() == RoomStatus.Occupied).count();
        long totalRooms = rooms.size();
        System.out.println("Total Rooms: " + totalRooms);
        System.out.println("Occupied Rooms: " + occupiedCount);
        System.out.println("Occupancy Rate: " + (occupiedCount * 100.0 / totalRooms) + "%");
    }

    public void generateAvailabilityReport() {
        System.out.println("=== Room Availability Report ===");
        long availableCount = rooms.stream().filter(room -> room.getStatus() == RoomStatus.Avaliable).count();
        System.out.println("Available Rooms: " + availableCount);

        // Only list available rooms
        rooms.stream()
                .filter(room -> room.getStatus() == RoomStatus.Avaliable)
                .forEach(room -> {
                    System.out.println("Room Number: " + room.roomNumber + ", Type: " + room.type);
                });
    }

    // Group Rooms by Status
    public void generateGroupedStatusReport() {
        System.out.println("=== Rooms Grouped by Status ===");
        Map<RoomStatus, List<Room>> groupedByStatus = rooms.stream()
                .collect(Collectors.groupingBy(room -> room.getStatus()));

        for (Map.Entry<RoomStatus, List<Room>> entry : groupedByStatus.entrySet()) {
            System.out.println("Status: " + entry.getKey());
            for (Room room : entry.getValue()) {
                System.out.println("  Room Number: " + room.roomNumber + ", Type: " + room.type);
                System.out.println("");
            }
        }
    }
}
