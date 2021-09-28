package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.User;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;

@RequestScoped
public class AuthenticationService {
    @Inject
    UserService userService;

    public User GenerateValidJwtToken(String username, String password) {
        if (!authenticationValid(username, password)) {
            throw new IllegalArgumentException("Username or password are wrong");
        }

        User user = userService.findByUsername(username);

        user.setToken(Jwt.issuer("https://zli.ch/issuer")
                .upn(username)
                .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                .claim(Claims.birthdate.name(), "2001-07-13")
                .expiresIn(Duration.ofHours(1))
                .sign());

        return user;
    }

    public boolean authenticationValid(String username, String password) {
        return userService.findByUsername(username).getPassword().equals(password);
    }

}
