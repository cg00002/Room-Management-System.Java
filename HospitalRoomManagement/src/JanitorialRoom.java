/**
 * Janitorial rooms service and provide maintenance to all other rooms
 * in the hospital. These rooms are critical to the ongoing functioning
 * of all other rooms.
 */
public class JanitorialRoom extends Room implements RoomOperations {
	
	/**
	 * Indicates if the Janitorial room is currently in use.
	 * If there are no available janitorial rooms, then no other rooms can be cleaned or maintained.
	 * We must wait for a janitorial room to become available.
	 */
	private boolean isBusy;
    private TaskType currentTask;

    public JanitorialRoom(int roomNumber) {
        super(roomNumber, RoomType.Janitorial);
        this.isBusy = false;
        this.status = RoomStatus.Avaliable;
    }

    public void startTask(TaskType task) {
        if (status == RoomStatus.UnderMaintenance) {
            System.out.println("Janitorial Room " + roomNumber + " is under maintenance and cannot start a new task.");
            return;
        }
        if (!isBusy && status == RoomStatus.Avaliable) {
            isBusy = true;
            currentTask = task;
            status = RoomStatus.Occupied;
            System.out.println("Janitorial Room " + roomNumber + " is now performing task: " + task);
        } else {
            System.out.println("Janitorial Room " + roomNumber + " is busy or unavailable.");
        }
    }
    
    public void completeTask() {
        if (isBusy) {
            isBusy = false;
            currentTask = null;
            status = RoomStatus.Avaliable;
            System.out.println("Janitorial Room " + roomNumber + " has completed its task and is now available.");
        }
    }

    @Override
    public void allocateRoom() {
        System.out.println("Janitorial rooms cannot be allocated to patients.");
    }

    @Override
    public void markReady() {
        if (status == RoomStatus.UnderMaintenance) {
            status = RoomStatus.Avaliable;
            isBusy = false;
            System.out.println("Janitorial Room " + roomNumber + " is now available and ready.");
        } else if (status == RoomStatus.Occupied && isBusy) {
            status = RoomStatus.Avaliable;
            isBusy = false;
            System.out.println("Janitorial Room " + roomNumber + " has been reset and is now available.");
        } else {
            System.out.println("Janitorial rooms are always ready when available.");
        }
    }

    @Override
    public void markUnderMaintenance() {
        if (!isBusy) {
            status = RoomStatus.UnderMaintenance;
            System.out.println("Janitorial Room " + roomNumber + " marked under maintenance.");
        } else {
            System.out.println("Janitorial Room " + roomNumber + " cannot be marked under maintenance while busy.");
        }
    }
    
    @Override
    public void sanitizeRoom() {
        if (!isBusy && status == RoomStatus.Avaliable) {
            System.out.println("Janitorial Room " + roomNumber + " is sanitized and ready.");
        } else if (isBusy) {
            System.out.println("Janitorial Room " + roomNumber + " is busy and cannot sanitize itself now.");
        } else {
            System.out.println("Janitorial Room " + roomNumber + " is not in a cleanable state.");
        }
    }

    @Override
    public void reserveRoom() {
        if (status == RoomStatus.Avaliable) {
            status = RoomStatus.Reserved;
            System.out.println("Janitorial Room " + roomNumber + " reserved.");
        } else {
            System.out.println("Janitorial Room " + roomNumber + " cannot be reserved in its current state.");
        }
    }
}
