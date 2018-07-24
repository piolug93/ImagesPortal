package exception;
import Enum.RegistrationErrors;

public class ErrorRegistrationException extends Exception {
    RegistrationErrors error;

    public ErrorRegistrationException(RegistrationErrors error) {
        this.error = error;
    }

    public RegistrationErrors getTypeError() {
        return error;
    }
}
