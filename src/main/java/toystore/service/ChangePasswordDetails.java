package toystore.service;

public interface ChangePasswordDetails {
    public boolean changePassword(Long customerid, String newPassword);
}
