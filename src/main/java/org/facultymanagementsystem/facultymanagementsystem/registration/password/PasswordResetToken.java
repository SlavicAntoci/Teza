package org.facultymanagementsystem.facultymanagementsystem.registration.password;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.facultymanagementsystem.facultymanagementsystem.model.User;
import org.facultymanagementsystem.facultymanagementsystem.registration.token.TokenExpirationTime;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date exirationTime;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public PasswordResetToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.exirationTime = TokenExpirationTime.getExperationTime();
    }
}
