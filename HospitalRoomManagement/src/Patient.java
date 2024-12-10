
public class Patient {

	private String name;
    private int emergencyLevel;

    public Patient(String name, int emergencyLevel) {
        this.name = name;
        this.emergencyLevel = emergencyLevel;
    }

    public String getName() {
        return name;
    }

    public int getEmergencyLevel() {
        return emergencyLevel;
    }

    @Override
    public String toString() {
        return "Patient{Name='" + name + "', EmergencyLevel=" + emergencyLevel + "}";
    }
}
