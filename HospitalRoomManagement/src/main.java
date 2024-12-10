import java.util.ArrayList;
import java.util.List;

public class main {

	public static void main(String[] args) {
		testFunctionalityICURoom();
		
		System.out.println("____________________________________________________________");
		System.out.println("==                                                        ==");
		System.out.println("==                                                        ==");
		System.out.println("==                                                        ==");
		System.out.println("____________________________________________________________");
		
		testFunctionalityERRoom();
		
		System.out.println("____________________________________________________________");
		System.out.println("==                                                        ==");
		System.out.println("==                                                        ==");
		System.out.println("==                                                        ==");
		System.out.println("____________________________________________________________");
		
		testFunctionalityERRoomWithPriority();
		
		System.out.println("____________________________________________________________");
		System.out.println("==                                                        ==");
		System.out.println("==                                                        ==");
		System.out.println("==                                                        ==");
		System.out.println("____________________________________________________________");
		
		testFunctionalityJanitorialRoom();
		
		System.out.println("____________________________________________________________");
		System.out.println("==                                                        ==");
		System.out.println("==                                                        ==");
		System.out.println("==                                                        ==");
		System.out.println("____________________________________________________________");
		
		testFunctionalityOperatingRoom();
		
		System.out.println("____________________________________________________________");
		System.out.println("==                                                        ==");
		System.out.println("==                                                        ==");
		System.out.println("==                                                        ==");
		System.out.println("____________________________________________________________");
		
		testFunctionalityReportGenerator();
    }
	
	static void testFunctionalityICURoom() {
		ICURoom icuRoom1 = new ICURoom(101, true);

        icuRoom1.displayDetails(); // Display room details

        // Allocate room and enable life support
        icuRoom1.allocateRoom();
        icuRoom1.enableLifeSupport();

        icuRoom1.handleCriticalPatient(); // Handle critical patient

        icuRoom1.markUnderMaintenance(); // Mark room under maintenance (should fail)

        // Discharge critical patient and mark room ready
        icuRoom1.releaseCriticalPatient();
        icuRoom1.markReady();

        icuRoom1.reserveRoom(); // Reserve room
	}
	
	static void testFunctionalityERRoom() {
	    ERRoom erRoom1 = new ERRoom(201, 3); // Emergency Level 3

	    erRoom1.displayDetails(); // Display room details

	    erRoom1.allocateRoom(); // Allocate room

	    erRoom1.escalateEmergency(5); // Escalate emergency level

	    erRoom1.markUnderMaintenance(); // Try to mark room under maintenance (should fail)

	    erRoom1.markReady(); // Mark room ready after occupancy ends

	    erRoom1.reserveRoom(); // Reserve room

	    erRoom1.sanitizeRoom(); // Sanitize room
	}
	
	static void testFunctionalityERRoomWithPriority() {
	    ERRoom erRoom = new ERRoom(201, 3);

	    // Add patients to the room
	    erRoom.addPatient(new Patient("Joe", 2));
	    erRoom.addPatient(new Patient("Bob", 5));
	    erRoom.addPatient(new Patient("John", 3));

	    erRoom.displayDetails(); // Display room details

	    erRoom.allocateRoom(); // Allocate room

	    // Treat patients based on priority
	    erRoom.treatNextPatient(); // Should treat Bob (highest priority: 5)
	    erRoom.treatNextPatient(); // Should treat John (priority: 3)
	    erRoom.treatNextPatient(); // Should treat Joe (lowest priority: 2)

	    erRoom.treatNextPatient(); // Try treating with no patients left
	}

	static void testFunctionalityJanitorialRoom() {
	    JanitorialRoom janitorialRoom1 = new JanitorialRoom(301);

	    janitorialRoom1.displayDetails(); // Display initial room details

	    janitorialRoom1.startTask(TaskType.Cleaning); // Start a cleaning task

	    janitorialRoom1.sanitizeRoom(); // Try to sanitize while busy

	    janitorialRoom1.completeTask(); // Complete the cleaning task

	    janitorialRoom1.markUnderMaintenance(); // Mark room under maintenance

	    janitorialRoom1.startTask(TaskType.Maintenance); // Try to start a task while under maintenance

	    // Mark room ready and start a maintenance task
	    janitorialRoom1.markReady();
	    janitorialRoom1.startTask(TaskType.Maintenance);

	    
	    janitorialRoom1.completeTask(); // Complete the maintenance task
	}
	
	static void testFunctionalityOperatingRoom() {
		OperatingRoom operatingRoom1 = new OperatingRoom(401);

	    operatingRoom1.displayDetails(); // Display initial room details

	    operatingRoom1.markUnderMaintenance(); // Attempt to mark room under maintenance (should succeed since it's clean)

	    operatingRoom1.markReady(); // Mark room ready for use after maintenance

	    operatingRoom1.startSurgery("Heart Surgery"); // Start a surgery

	    operatingRoom1.markUnderMaintenance(); // Attempt to mark room under maintenance during surgery (should fail)

	    operatingRoom1.endSurgery(); // End the surgery and prepare the room for cleaning

	    operatingRoom1.sanitizeRoom(); // Sanitize the room after cleaning

	    operatingRoom1.startSurgery("Knee Replacement Surgery"); // Attempt to start another surgery

	    operatingRoom1.displayDetails(); // Display final room details
	}
	
	static void testFunctionalityReportGenerator() {
	    // Create list of rooms
	    List<Room> rooms = new ArrayList<>();
	    rooms.add(new ICURoom(101, true));
	    rooms.add(new ERRoom(201, 3));
	    rooms.add(new JanitorialRoom(301));
	    rooms.add(new OperatingRoom(401));

	    // Set statuses for rooms
	    rooms.get(0).setStatus(RoomStatus.Occupied);  // ICU Room - Occupied
	    rooms.get(1).setStatus(RoomStatus.Clean);     // ER Room - Clean
	    rooms.get(2).setStatus(RoomStatus.Avaliable); // Janitorial Room - Available
	    rooms.get(3).setStatus(RoomStatus.UnderMaintenance); // Operating Room - Under Maintenance

	    ReportGenerator reportGenerator = new ReportGenerator(rooms);

	    // Generate and display reports
	    System.out.println("=== Testing Report Generator Functionality ===");
	    System.out.println("____________________________________________________________");
	    reportGenerator.generateRoomStatusReport();
	    System.out.println("____________________________________________________________");
	    reportGenerator.generateOccupancyReport();
	    System.out.println("____________________________________________________________");
	    reportGenerator.generateAvailabilityReport();
	    System.out.println("____________________________________________________________");
	    reportGenerator.generateGroupedStatusReport();
	    System.out.println("____________________________________________________________");
	}
}
