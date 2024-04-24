package org.facultymanagementsystem.facultymanagementsystem.registration.token;

import lombok.RequiredArgsConstructor;
import org.facultymanagementsystem.facultymanagementsystem.model.User;
import org.facultymanagementsystem.facultymanagementsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VerificationTokenService implements IVerificationTokenService {
    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;

    @Override
    public String validateToken(String token) {
        Optional<VerificationToken> theToken = tokenRepository.findByToken(token);
        if (theToken.isEmpty()) {
            return "invalid";
        }
        User user = theToken.get().getUser();
        Calendar calendar = Calendar.getInstance();
        if ((theToken.get().getExpirationTime()
                .getTime() - calendar.getTime().getTime()) <= 0) {
            return "expired";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }

    @Override
    public void saveVerificationTokenForUser(User user, String token) {
        var verifivationToken = new VerificationToken(token, user);
        tokenRepository.save(verifivationToken);

    }

    @Override
    public Optional<VerificationToken> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public void deleteUserToken(Long id) {
        tokenRepository.deleteByUserId(id);
    }

}
