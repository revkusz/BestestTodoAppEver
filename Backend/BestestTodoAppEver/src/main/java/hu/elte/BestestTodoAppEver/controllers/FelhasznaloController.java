package hu.elte.BestestTodoAppEver.controllers;

import hu.elte.BestestTodoAppEver.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("user")
public class FelhasznaloController {

    @GetMapping("")
    public ResponseEntity<Iterable<User>> getAll() {
        ArrayList<User> users  = new ArrayList<>();
        users.add(new User());
        ResponseEntity<Iterable<User>> res = new ResponseEntity<Iterable<User>>(users,HttpStatus.OK);
        return res;
    }
}
