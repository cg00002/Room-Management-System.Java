import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestER {

	@Test
    public void testAddPatientToQueue() {
        ERRoom erRoom = new ERRoom(201, 3);
        Patient patient = new Patient("John", 5);
        erRoom.addPatient(patient);
        assertEquals(1, erRoom.patientQueue.size());
    }

    @Test
    public void testTreatPatientByPriority() {
        ERRoom erRoom = new ERRoom(201, 3);
        erRoom.addPatient(new Patient("Joe", 2));
        erRoom.addPatient(new Patient("Bob", 5));
        erRoom.addPatient(new Patient("John", 3));

        erRoom.allocateRoom();
        erRoom.treatNextPatient();
        assertEquals(2, erRoom.patientQueue.size());
    }

    @Test
    public void testTreatAllPatients() {
        ERRoom erRoom = new ERRoom(201, 3);
        erRoom.addPatient(new Patient("Joe", 2));
        erRoom.addPatient(new Patient("Bob", 5));
        erRoom.addPatient(new Patient("John", 3));

        erRoom.allocateRoom();
        erRoom.treatNextPatient();
        erRoom.treatNextPatient();
        erRoom.treatNextPatient();

        assertTrue(erRoom.patientQueue.isEmpty());
    }
}
