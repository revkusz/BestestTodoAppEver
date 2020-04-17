package hu.elte.BestestTodoAppEver.Services;

import hu.elte.BestestTodoAppEver.entities.Authorities;
import hu.elte.BestestTodoAppEver.entities.Users;
import hu.elte.BestestTodoAppEver.repositories.AuthoritiesRepository;
import hu.elte.BestestTodoAppEver.repositories.UserRepository;
import hu.elte.BestestTodoAppEver.staticData.Roles;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthoritiesRepository authoritiesRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void registerUser(String username, String password) {
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        addRoles(user,Arrays.asList(Roles.USER));

    }

    public void setPassword(String username, String password) {
        userRepository.findById(username).get().setPassword(passwordEncoder.encode(password));
    }

    public void setEnableUser(String username,boolean enabled) {
        userRepository.findById(username).get().setEnabled(enabled);
    }


    public void addRoles(Users user, List<String> roles) {
        for (String role :
                roles) {
            Authorities authorities = new Authorities();
            authorities.setAuthority(getRoleDbName(role));
            authorities.setUsername(user);
            authoritiesRepository.save(authorities);

        }

    }

    private String getRoleDbName(String role) {
        return role;
    }
}
