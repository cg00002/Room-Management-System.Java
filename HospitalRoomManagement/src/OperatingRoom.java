
public class OperatingRoom extends Room implements RoomOperations {

	private boolean isInUse; // If surgery is ongoing
    private String surgeryType;

    public OperatingRoom(int roomNumber) {
        super(roomNumber, RoomType.OR);
        this.isInUse = false;
        this.surgeryType = "";
    }
    
    public boolean isInUse() {
        return isInUse;
    }

    public String getSurgeryType() {
        return surgeryType;
    }

    public void startSurgery(String surgeryType) {
        if (status == RoomStatus.UnderMaintenance) {
            System.out.println("Operating Room " + roomNumber + " is under maintenance and cannot start a surgery.");
        } else if (isInUse) {
            System.out.println("Operating Room " + roomNumber + " is already in use for another surgery.");
        } else if (status == RoomStatus.Clean) {
            this.isInUse = true;
            this.surgeryType = surgeryType;
            status = RoomStatus.Occupied;
            System.out.println("Operating Room " + roomNumber + " is now in use for surgery: " + surgeryType);
        } else {
            System.out.println("Operating Room " + roomNumber + " is unavailable for surgery.");
        }
    }

    public void endSurgery() {
        if (isInUse) {
            isInUse = false;
            surgeryType = "";
            status = RoomStatus.BeingCleaned;
            System.out.println("Operating Room " + roomNumber + " surgery completed and ready for cleaning.");
        } else {
            System.out.println("No ongoing surgery to end in Operating Room " + roomNumber + ".");
        }
    }

    @Override
    public void sanitizeRoom() {
        if (status == RoomStatus.BeingCleaned) {
            status = RoomStatus.Clean;
            System.out.println("Operating Room " + roomNumber + " sanitized and ready for use.");
        } else if (status == RoomStatus.UnderMaintenance) {
            System.out.println("Operating Room " + roomNumber + " is under maintenance and cannot be sanitized.");
        } else {
            System.out.println("Operating Room " + roomNumber + " is not in a cleanable state.");
        }
    }

    @Override
    public void allocateRoom() {
        System.out.println("Operating Rooms are allocated based on surgical schedules.");
    }

    @Override
    public void reserveRoom() {
        System.out.println("Operating Rooms cannot be reserved. Use startSurgery instead.");
    }

    @Override
    public void markReady() {
        if (status == RoomStatus.UnderMaintenance) {
            status = RoomStatus.Clean;
            System.out.println("Operating Room " + roomNumber + " is now clean and ready after maintenance.");
        } else if (status == RoomStatus.BeingCleaned) {
            status = RoomStatus.Clean;
            System.out.println("Operating Room " + roomNumber + " is now clean and ready.");
        } else {
            System.out.println("Operating Room " + roomNumber + " is already ready or in an invalid state.");
        }
    }

    @Override
    public void markUnderMaintenance() {
        if (isInUse) {
            System.out.println("Operating Room " + roomNumber + " cannot be marked under maintenance while a surgery is ongoing.");
        } else if (status == RoomStatus.BeingCleaned || status == RoomStatus.Clean) {
            status = RoomStatus.UnderMaintenance;
            System.out.println("Operating Room " + roomNumber + " is now marked under maintenance.");
        } else {
            System.out.println("Operating Room " + roomNumber + " cannot be marked under maintenance in its current state.");
        }
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("In Use: " + (isInUse ? "Yes" : "No") + ", Current Surgery: " + (surgeryType.isEmpty() ? "None" : surgeryType));
    }
}
