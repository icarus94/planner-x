package rs.fon.plannerx.core.exception;

public class CoreDomainException extends RuntimeException implements ExceptionMessages {
    public CoreDomainException(String message) {
        super(message);
    }
}
