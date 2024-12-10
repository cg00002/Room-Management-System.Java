
public abstract class Room {

	protected int roomNumber;
	protected RoomType type;
	protected RoomStatus status;
	
	public Room(int roomNumber, RoomType type) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.status = RoomStatus.Clean;
    }
	
	public abstract void allocateRoom();
	public abstract void markReady();
	public abstract void markUnderMaintenance();
	
	public void updateStatus() {
        if (status == RoomStatus.Clean) {
            status = RoomStatus.Avaliable;
        }
    }
	
	public void displayDetails() {
		System.out.println("Room Number: " + roomNumber + ", Type: " + type + ", Status: " + status);
	}
	
	public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }
}
