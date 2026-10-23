package com.example.placement;

/** Backend authentication contract. Implementations must never persist a raw password. */
public interface AuthenticationService {
    interface Callback { void onComplete(boolean success, String message); }
    void signIn(String emailOrStudentId, char[] password, Callback callback);
    void register(String fullName, String studentId, String universityEmail, char[] password, Callback callback);
    void requestPasswordReset(String universityEmail, Callback callback);
    void signOut();
}
