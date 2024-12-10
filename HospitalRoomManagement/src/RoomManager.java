import java.util.ArrayList;
import java.util.List;

public class RoomManager {

	private List<Room> rooms;
    private List<JanitorialRoom> janitorialRooms;
    private List<RoomOperations> operationalRooms;

    public RoomManager() {
        rooms = new ArrayList<>();
        janitorialRooms = new ArrayList<>();
        operationalRooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);

        // Add to janitorialRooms list if it is a JanitorialRoom
        if (room instanceof JanitorialRoom) {
            janitorialRooms.add((JanitorialRoom) room);
        }

        // Add to operationalRooms list if it implements RoomOperations
        if (room instanceof RoomOperations) {
            operationalRooms.add((RoomOperations) room);
        }
    }

    public void updateRoomStatuses() {
        for (Room room : rooms) {
            room.updateStatus();
        }
    }

    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.getStatus() == RoomStatus.Avaliable) {
                room.displayDetails();
            }
        }
    }

    public void reserveOperationalRooms() {
        System.out.println("Reserving operational rooms...");
        for (RoomOperations roomOps : operationalRooms) {
            roomOps.reserveRoom();
        }
    }

    public void performTaskOnRoom(int roomNumber, TaskType task) {
        // Find an available janitorial room
        for (JanitorialRoom janitorialRoom : janitorialRooms) {
            if (janitorialRoom.getStatus() == RoomStatus.Avaliable) {
                janitorialRoom.startTask(task);

                // Find & update the room needing the task
                for (Room room : rooms) {
                    if (room.roomNumber == roomNumber) {
                        if (task == TaskType.Cleaning) {
                            room.setStatus(RoomStatus.BeingCleaned);
                        } else if (task == TaskType.Maintenance) {
                            room.setStatus(RoomStatus.UnderMaintenance);
                        }
                        System.out.println("Task '" + task + "' started on Room " + roomNumber);
                        return;
                    }
                }
            }
        }
        System.out.println("No available janitorial rooms to perform the task.");
    }

    public void completeTaskInJanitorialRoom(int janitorialRoomNumber) {
        for (JanitorialRoom janitorialRoom : janitorialRooms) {
            if (janitorialRoom.roomNumber == janitorialRoomNumber) {
                janitorialRoom.completeTask();
                return;
            }
        }
        System.out.println("Janitorial Room " + janitorialRoomNumber + " not found.");
    }
}
