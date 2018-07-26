package model;

import exception.ErrorRegistrationException;
import Enum.RegistrationErrors;

public class PasswordValidator {
    public static boolean validate(String password, String repassword) throws ErrorRegistrationException {
        if(!password.equals(repassword)) {
            throw new ErrorRegistrationException(RegistrationErrors.REPASSWORD_INCORRECT);
        } else
            return true;
    }
}
