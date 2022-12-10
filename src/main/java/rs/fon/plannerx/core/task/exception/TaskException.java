package rs.fon.plannerx.core.task.exception;

import rs.fon.plannerx.core.exception.CoreDomainException;

public class TaskException extends CoreDomainException {
    private TaskException(String message) {
        super(message);
    }

    public static TaskException operationNotAllowed() {
        return new TaskException(TaskExceptionMessages.OPERATION_NOT_ALLOWED);
    }

    public static TaskException userNotFoundByEmail() {
        return new TaskException(TaskExceptionMessages.USER_NOT_FOUND);
    }
}
