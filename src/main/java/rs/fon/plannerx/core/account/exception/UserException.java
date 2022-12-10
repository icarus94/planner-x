package rs.fon.plannerx.core.account.exception;

import rs.fon.plannerx.core.exception.CoreDomainException;

public class UserException extends CoreDomainException {
    private UserException(String message) {
        super(message);
    }

    public static UserException emailIsAlreadyInUse() {
        return new UserException(UserExceptionMessages.EMAIL_IS_ALREADY_USED);
    }

    public static UserException passwordDoesNotMatch() {
        return new UserException(UserExceptionMessages.PASSWORD_DOES_NOT_MATCH);
    }

    public static UserException newPasswordIsBlank() {
        return new UserException(UserExceptionMessages.NEW_PASSWORD_IS_BLANK);
    }

    public static UserException newPasswordsDoNotMatch() {
        return new UserException(UserExceptionMessages.NEW_PASSWORDS_DO_NOT_MATCH);
    }

    public static UserException invitedUserIsAlreadyRegistered() {
        return new UserException(UserExceptionMessages.INVITED_USER_IS_ALREADY_REGISTERED);
    }
}
