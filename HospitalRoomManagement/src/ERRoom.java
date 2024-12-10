import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Emergency room provides immediate care for patients with urgent
 * life-threatening medical issues. Once stabilized, patients are
 * then moved to either the ICU or OR.
 */
public class ERRoom extends Room implements RoomOperations {

	private int emergencyLevel; // Emergency levels range from 1-5. 1 being the lowest and 5 being the highest.
	protected PriorityQueue<Patient> patientQueue;
	
	public ERRoom(int roomNumber, int emergencyLevel) {
        super(roomNumber, RoomType.ER);
        this.emergencyLevel = emergencyLevel;
        
        // Order patients by emergency level (highest level first, lowest level last)
        this.patientQueue = new PriorityQueue<>(
                Comparator.comparingInt(patient -> ((Patient) patient).getEmergencyLevel()).reversed()
            );
    }
	
	public void addPatient(Patient patient) {
        patientQueue.add(patient);
        System.out.println(patient + " added to ER Room " + roomNumber + ".");
    }
	
	public void treatNextPatient() {
        if (status == RoomStatus.Occupied) {
            if (!patientQueue.isEmpty()) {
                Patient nextPatient = patientQueue.poll(); // Get the next highest-priority patient in the queue
                System.out.println("Treating " + nextPatient + " in ER Room " + roomNumber + ".");
            } else {
                System.out.println("No patients left to treat in ER Room " + roomNumber + ".");
            }
        } else {
            System.out.println("ER Room " + roomNumber + " is not occupied and cannot treat patients.");
        }
    }
	
	public void escalateEmergency(int newLevel) {
        if (newLevel > emergencyLevel) {
            emergencyLevel = newLevel;
            System.out.println("Emergency level of ER Room " + roomNumber + " escalated to " + emergencyLevel);
        }
    }

	@Override
    public void allocateRoom() {
        if (status == RoomStatus.Clean || status == RoomStatus.Reserved) {
            status = RoomStatus.Occupied;
            System.out.println("ER Room " + roomNumber + " allocated.");
        } else {
            System.out.println("ER Room not available for allocation.");
        }
    }

    @Override
    public void markReady() {
        if (status == RoomStatus.UnderMaintenance) {
            status = RoomStatus.BeingCleaned;
            System.out.println("ER Room " + roomNumber + " is now ready for cleaning.");
        } else if (status == RoomStatus.BeingCleaned) {
            status = RoomStatus.Clean;
            System.out.println("ER Room " + roomNumber + " is now clean and ready.");
        } else if (status == RoomStatus.Occupied) {
            status = RoomStatus.BeingCleaned;
            System.out.println("ER Room " + roomNumber + " is now ready for cleaning.");
        } else {
            System.out.println("ER Room " + roomNumber + " is already ready or in use.");
        }
    }

    @Override
    public void markUnderMaintenance() {
        status = RoomStatus.UnderMaintenance;
        System.out.println("ER Room " + roomNumber + " marked under maintenance.");
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Emergency Level: " + emergencyLevel);

        // Display patients in descending order of emergency level
        List<Patient> sortedPatients = new ArrayList<>(patientQueue);
        sortedPatients.sort(Comparator.comparingInt(patient -> ((Patient) patient).getEmergencyLevel()).reversed());
        System.out.println("Patients in Queue: " + sortedPatients);
    }
    
    /**
     * Recursion is used only for the ER room because it has more complex state transitions
     * than the ICU or Janitorial rooms. This is because the ER involves intermediate states.
     * 
     * Recursion is used here to handle intermediate state transitions. So if the room
     * is in the Reserved state, it transitions the room to BeingCleaned and then calls
     * itself to complete the sanitization. This works here because the state transitions
     * are finite, so it ensures that it will eventually terminate (Reserved -> BeingCleaned -> Clean).
     */
    @Override
    public void sanitizeRoom() {
        if (status == RoomStatus.BeingCleaned) {
            status = RoomStatus.Clean;
            System.out.println("ER Room " + roomNumber + " sanitized and ready.");
        } else if (status == RoomStatus.Reserved) {
            System.out.println("ER Room " + roomNumber + " is reserved. Transitioning to cleaning...");
            status = RoomStatus.BeingCleaned;
            sanitizeRoom(); // recursively sanitize after transitioning is complete
        } else {
            System.out.println("ER Room " + roomNumber + " is not in a cleanable state.");
        }
    }

    /**
     * Recursion is used only for the ER room because it has more complex state transitions
     * than the ICU or Janitorial rooms. This is because the ER involves intermediate states.
     * 
     * This ensures proper transitions between states. If the room is
     * BeingCleaned, it transitions the room to Clean and then calls itself to complete
     * the reservation. This works because the transitions are deterministic (BeingCleaned -> Clean -> Reserved).
     * This means that the order of the state transitions is pre-determined, so it will
     * terminate once it reaches the Reserved state.
     */
    @Override
    public void reserveRoom() {
        if (status == RoomStatus.Clean) {
            status = RoomStatus.Reserved;
            System.out.println("ER Room " + roomNumber + " reserved.");
        } else if (status == RoomStatus.BeingCleaned) {
            System.out.println("ER Room " + roomNumber + " is being cleaned. Reserving once cleaned...");
            status = RoomStatus.Clean;
            reserveRoom(); // recursively reserve after transitioning is complete
        } else {
            System.out.println("ER Room " + roomNumber + " cannot be reserved in its current state.");
        }
    }
}
