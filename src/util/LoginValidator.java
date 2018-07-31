package util;

import exception.ErrorRegistrationException;
import model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import service.UserService;
import Enum.RegistrationErrors;

public class LoginValidator {
    public static boolean validate(String login) throws ErrorRegistrationException{
        UserService userService = new UserService();
        User user = null;
        try {
            user = userService.getUserByLogin(login);
        } catch(EmptyResultDataAccessException e) { }
        if(user != null) {
            throw new ErrorRegistrationException(RegistrationErrors.LOGIN_EXIST);
        } else
            return true;
    }
}
