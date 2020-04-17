package hu.elte.BestestTodoAppEver.controllers;

import hu.elte.BestestTodoAppEver.dataTransferObjects.TodoItemDto;
import hu.elte.BestestTodoAppEver.entities.TodoItem;
import hu.elte.BestestTodoAppEver.repositories.CategoryRepository;
import hu.elte.BestestTodoAppEver.repositories.TodoItemRepository;
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

import java.security.Principal;

@RestController
@RequestMapping("todo")
public class TodoItemController {

    @Autowired
    TodoItemRepository todoItemRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/all",produces = "application/json")
    @Secured(Roles.ADMIN)
    public ResponseEntity<Iterable<TodoItem>> getAll() {
        ResponseEntity<Iterable<TodoItem>> res = new ResponseEntity<Iterable<TodoItem>>(todoItemRepository.findAll(), HttpStatus.OK);
        return res;
    }

    @GetMapping(path = "/all/{username}",produces = "application/json")
    public ResponseEntity<Iterable<TodoItem>> getAllForUser(@PathVariable String username, Principal principal) {
        System.out.println(username);
        System.out.println(principal.getName());
        if (principal.getName().equals(username) || userInRole(Roles.ADMIN)) {
            ResponseEntity<Iterable<TodoItem>> res = new ResponseEntity<Iterable<TodoItem>>(todoItemRepository.findAllForUser(username), HttpStatus.OK);
            return res;
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(path = "/all/{username}/{category}",produces = "application/json")
    public ResponseEntity<Iterable<TodoItem>> getAllForUser(@PathVariable String username, Principal principal,@PathVariable String category) {
        if (principal.getName().equals(username) || userInRole(Roles.ADMIN)) {
            ResponseEntity<Iterable<TodoItem>> res = new ResponseEntity<Iterable<TodoItem>>(todoItemRepository.findAllForUserbyCategory(username,category), HttpStatus.OK);
            return res;
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(path = "{id}", produces = "application/json")
    public ResponseEntity<TodoItem> getTodoItem(@PathVariable Long id,Principal principal) {
        if (userInRole(Roles.ADMIN)) {
            ResponseEntity<TodoItem> res = new ResponseEntity<TodoItem>(todoItemRepository.findById(id).get(),HttpStatus.OK);
            return res;
        }
        ResponseEntity<TodoItem> res = new ResponseEntity<TodoItem>(todoItemRepository.findByidforUser(principal.getName(),id),HttpStatus.OK);
        return res;
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteTodoItem(@PathVariable Long id,Principal principal) {
        if (userInRole(Roles.ADMIN)) {
            todoItemRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            if (todoItemRepository.findById(id).get().getOwner().getUsername().equals(principal.getName())) {
                todoItemRepository.deleteById(id);
                return new ResponseEntity(HttpStatus.OK);
            }
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);

    }

    @PutMapping(path = "{id}",consumes = "application/json")
    public ResponseEntity<TodoItem> updateTodoItem(@PathVariable Long id , @RequestBody TodoItemDto todoItem, Principal principal) {
        TodoItem newTodoItem = new TodoItem();
        newTodoItem.setId(id);
        newTodoItem.setCategory(categoryRepository.findById(todoItem.getCategor_id()).get());
        newTodoItem.setDeleted(false);
        newTodoItem.setDone(todoItem.isDone());
        newTodoItem.setMessage(todoItem.getMessage());
        newTodoItem.setOwner(userRepository.findById(todoItem.getOwner_id()).get());
        if (userInRole(Roles.ADMIN) || todoItem.getOwner_id().equals(principal.getName())) {
            TodoItem updatedTodoitem = todoItemRepository.save(newTodoItem);
            ResponseEntity<TodoItem> res = new ResponseEntity<TodoItem>(updatedTodoitem,HttpStatus.OK);
            return res;
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);

    }

    @PostMapping(path = "" , consumes = "application/json")
    public ResponseEntity<TodoItem> addTodoItem( @RequestBody TodoItemDto todoItem,Principal principal) {
        TodoItem newTodoItem = new TodoItem();
        newTodoItem.setCategory(categoryRepository.findById(todoItem.getCategor_id()).get());
        newTodoItem.setDeleted(false);
        newTodoItem.setDone(todoItem.isDone());
        newTodoItem.setMessage(todoItem.getMessage());
        newTodoItem.setOwner(userRepository.findById(todoItem.getOwner_id()).get());
        if (userInRole(Roles.ADMIN) || todoItem.getOwner_id().equals(principal.getName())) {
            TodoItem updatedTodoitem = todoItemRepository.save(newTodoItem);
            ResponseEntity<TodoItem> res = new ResponseEntity<TodoItem>(updatedTodoitem,HttpStatus.OK);
            return res;
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
