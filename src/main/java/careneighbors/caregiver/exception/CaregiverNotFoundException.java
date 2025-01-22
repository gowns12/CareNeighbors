package careneighbors.caregiver.exception;

public class CaregiverNotFoundException extends RuntimeException {

    public CaregiverNotFoundException(String message) {
        super(message);
    }

    public CaregiverNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
