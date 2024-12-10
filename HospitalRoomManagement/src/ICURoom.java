/**
 * Intensive Car Unit provides critical care and life support for
 * ill or injured patients. These patients often require constant
 * monitoring.
 */
public class ICURoom extends Room implements RoomOperations {

	private boolean hasLifeSupport; // If the room has life support or not
	private boolean isCriticalPatient; // If the room is currently occupied by a critical patient
	
	public ICURoom(int roomNumber, boolean hasLifeSupport) {
        super(roomNumber, RoomType.ICU);
        this.hasLifeSupport = hasLifeSupport;
    }

	@Override
    public void allocateRoom() {
        if (status == RoomStatus.Clean || status == RoomStatus.Avaliable) {
            status = RoomStatus.Occupied;
            System.out.println("ICU Room " + roomNumber + " allocated.");
        } else {
            System.out.println("ICU Room " + roomNumber + " is not available for allocation.");
        }
    }

    @Override
    public void markReady() {
        if (status == RoomStatus.BeingCleaned || status == RoomStatus.UnderMaintenance) {
            status = RoomStatus.Clean;
            System.out.println("ICU Room " + roomNumber + " is now clean and ready.");
        } else if (status == RoomStatus.Occupied && !isCriticalPatient) {
            status = RoomStatus.BeingCleaned; // Transition to BeingCleaned after occupancy ends
            System.out.println("ICU Room " + roomNumber + " is now ready for cleaning.");
        } else {
            System.out.println("ICU Room " + roomNumber + " is already ready or in use.");
        }
    }

    @Override
    public void markUnderMaintenance() {
        if (status != RoomStatus.Occupied) {
            status = RoomStatus.UnderMaintenance;
            System.out.println("ICU Room " + roomNumber + " marked under maintenance.");
        } else {
            System.out.println("Cannot mark ICU Room " + roomNumber + " under maintenance while occupied.");
        }
    }

    @Override
    public void sanitizeRoom() {
        if (status == RoomStatus.BeingCleaned) {
            status = RoomStatus.Clean;
            System.out.println("ICU Room " + roomNumber + " sanitized and ready.");
        } else {
            System.out.println("ICU Room " + roomNumber + " is not in a cleanable state.");
        }
    }

    @Override
    public void reserveRoom() {
        if (status == RoomStatus.Clean || status == RoomStatus.Avaliable) {
            status = RoomStatus.Reserved;
            System.out.println("ICU Room " + roomNumber + " reserved.");
        } else if (status == RoomStatus.BeingCleaned) {
            System.out.println("ICU Room " + roomNumber + " is being cleaned. Reserving once cleaned...");
            status = RoomStatus.Clean;
            reserveRoom(); // Call recursively to reserve the room after cleaning
        } else {
            System.out.println("ICU Room " + roomNumber + " cannot be reserved in its current state.");
        }
    }
    
    public void enableLifeSupport() {
        if (hasLifeSupport && status == RoomStatus.Occupied) {
            System.out.println("Life support enabled in ICU Room " + roomNumber + ".");
        } else if (!hasLifeSupport) {
            System.out.println("ICU Room " + roomNumber + " does not have life support equipment.");
        } else {
            System.out.println("ICU Room " + roomNumber + " is not occupied, cannot enable life support.");
        }
    }

    public void handleCriticalPatient() {
        if (status == RoomStatus.Occupied) {
            isCriticalPatient = true;
            System.out.println("ICU Room " + roomNumber + " is now handling a critical patient.");
        } else {
            System.out.println("ICU Room " + roomNumber + " must be occupied to handle a critical patient.");
        }
    }

    public void releaseCriticalPatient() {
        if (isCriticalPatient) {
            isCriticalPatient = false;
            System.out.println("Critical patient has been discharged from ICU Room " + roomNumber + ".");
        } else {
            System.out.println("ICU Room " + roomNumber + " is not currently handling a critical patient.");
        }
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Life Support: " + (hasLifeSupport ? "Available" : "Not Available"));
        System.out.println("Handling Critical Patient: " + (isCriticalPatient ? "Yes" : "No"));
    }
}
