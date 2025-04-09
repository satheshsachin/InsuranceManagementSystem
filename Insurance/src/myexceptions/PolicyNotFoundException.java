package myexceptions;

public class PolicyNotFoundException extends RuntimeException {
	public PolicyNotFoundException(String message) {
        super(message);
    }

}
