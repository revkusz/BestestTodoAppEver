package hu.elte.BestestTodoAppEver.controllers;

import hu.elte.BestestTodoAppEver.Services.UserService;
import hu.elte.BestestTodoAppEver.repositories.AuthoritiesRepository;
import hu.elte.BestestTodoAppEver.repositories.UserRepository;
import hu.elte.BestestTodoAppEver.staticData.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("role")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;

    @PutMapping("/add")
    @Secured(Roles.ADMIN)
    public ResponseEntity addRoles(@RequestBody List<String> roles,@RequestParam String username) {
        userService.addRoles(userRepository.findById(username).get(),roles);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    @Secured(Roles.ADMIN)
    public ResponseEntity<List<String>> getAllRoles() {
        return new ResponseEntity<List<String>>(authoritiesRepository.findAllRole(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<String>> getUserRoles(@PathVariable String id,Principal principal) {
        if (principal.equals(id) || userInRole(Roles.ADMIN)) {
            return new ResponseEntity<List<String>>(authoritiesRepository.findAllUserRole(id),HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);

    }

    private boolean userInRole(String role) {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null)
            return false;

        Authentication authentication = context.getAuthentication();
        if (authentication == null)
            return false;

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (role.equals(auth.getAuthority())) {
                return true;
            }
        }
        return false;
    }
}
