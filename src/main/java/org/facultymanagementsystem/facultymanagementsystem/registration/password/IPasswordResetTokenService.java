package org.facultymanagementsystem.facultymanagementsystem.registration.password;

import org.facultymanagementsystem.facultymanagementsystem.model.User;

import java.util.Optional;

public interface IPasswordResetTokenService {
    String validatePasswordResetToken(String theToken);

    Optional<User> findUserByPasswordResetToken(String theToken);

    void resetPassword(User user, String password);

    void createPasswordResetTokenForUser(User user, String passwordResetToken);
}
