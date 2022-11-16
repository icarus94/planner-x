package rs.fon.plannerx.core.account.ports.out;

public interface DoesUserExist {
    boolean doesExistByEmail(String email);
}
