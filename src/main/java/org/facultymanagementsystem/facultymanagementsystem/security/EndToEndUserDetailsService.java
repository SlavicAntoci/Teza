package org.facultymanagementsystem.facultymanagementsystem.security;

import lombok.RequiredArgsConstructor;
import org.facultymanagementsystem.facultymanagementsystem.model.User;
import org.facultymanagementsystem.facultymanagementsystem.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EndToEndUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    //    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return userRepository.findByEmail(email)
//                .map(EndToEndUserDetails::new)
//                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
//    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!user.isEnabled()) {
            throw new UsernameNotFoundException("User is disabled");
        }
        return new EndToEndUserDetails(user);
    }
}
