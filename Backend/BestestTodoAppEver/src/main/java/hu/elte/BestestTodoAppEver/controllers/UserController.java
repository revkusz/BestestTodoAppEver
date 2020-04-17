package hu.elte.BestestTodoAppEver.controllers;

import hu.elte.BestestTodoAppEver.Services.UserService;
import hu.elte.BestestTodoAppEver.dataTransferObjects.UserDto;
import hu.elte.BestestTodoAppEver.entities.Users;
import hu.elte.BestestTodoAppEver.repositories.UserRepository;
import hu.elte.BestestTodoAppEver.staticData.Roles;
import org.h2.engine.Role;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("user")
public class UserController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/all")
    @Secured({Roles.ADMIN})
    public ResponseEntity<List<UserDto>> getAll() {
        ResponseEntity<List<UserDto>> res = new ResponseEntity<>(StreamSupport.stream(userRepository.findAll().spliterator(), false).map(p -> new UserDto(p)).collect(Collectors.toList()),HttpStatus.OK);
        return res;
    }

    /*@GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getuser(@PathVariable String id, Principal principal) {
        ResponseEntity<UserDto> res = new ResponseEntity<UserDto>(new UserDto(userRepository.findById(id).get()),HttpStatus.OK);
        return res;
    }*/ //nincsen még mit lekérni

    @GetMapping("/enable/{id}")
    @Secured({Roles.ADMIN})
    public ResponseEntity disableUser(@PathVariable String id, @RequestParam boolean enabled) {
        userService.setEnableUser(id,enabled);
        return new ResponseEntity(HttpStatus.OK);
    }

   /* @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String id , @RequestBody Users user) {
        Users updatedUser = userRepository.save(user);
        ResponseEntity<UserDto> res = new ResponseEntity<>(new UserDto(updatedUser),HttpStatus.OK);
        return res;

    }
*/ // még nincs semmi amit lehetne updatelni
    @PostMapping("/create")
    @Secured({Roles.ADMIN})
    public ResponseEntity addUser(@RequestBody Users user) {
        userService.registerUser(user.getUsername(),user.getPassword());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/changePass")
    public ResponseEntity changePass( @RequestParam String newPass, @RequestParam String username,Principal principal) {
        if (principal.equals(username) || userInRole(Roles.ADMIN)) {
            userService.setPassword(username,newPass);
            return new ResponseEntity(HttpStatus.OK);
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
