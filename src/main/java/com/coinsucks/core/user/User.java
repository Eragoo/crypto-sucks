package com.coinsucks.core.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "`user`")
public class User {
    private static final String GENERATOR = "user_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR)
    @SequenceGenerator(name = GENERATOR, sequenceName = "coin_sequence")
    private Long id;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserAuthType userAuthType = UserAuthType.GITHUB;
    private String password;
    private String username;

    public User(String email, String username, String password, UserAuthType userAuthType) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
