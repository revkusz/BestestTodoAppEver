package hu.elte.BestestTodoAppEver.controllers;

import hu.elte.BestestTodoAppEver.dataTransferObjects.CategoryDto;
import hu.elte.BestestTodoAppEver.entities.Category;
import hu.elte.BestestTodoAppEver.entities.Users;
import hu.elte.BestestTodoAppEver.repositories.CategoryRepository;
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
@RequestMapping("category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/all",produces = "application/json")
    @Secured(Roles.ADMIN)
    public ResponseEntity<Iterable<Category>> getAll() {
        ResponseEntity<Iterable<Category>> res = new ResponseEntity<Iterable<Category>>(categoryRepository.findAll(), HttpStatus.OK);
        return res;
    }
    @GetMapping(path = "/all/{username}",produces = "application/json")
    public ResponseEntity<Iterable<Category>> getAll(@PathVariable String username, Principal principal) {
        if (userInRole(Roles.ADMIN) || username.equals(principal.getName())) {
            ResponseEntity<Iterable<Category>> res = new ResponseEntity<Iterable<Category>>(categoryRepository.findAllForUser(username), HttpStatus.OK);
            return res;
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);

    }

    @GetMapping(path = "{id}", produces = "application/json")
    public ResponseEntity<Category> getCategory(@PathVariable Long id, Principal principal) {
        Category category = categoryRepository.findById(id).get();
        if (userInRole(Roles.ADMIN) || category.getOwner().getUsername().equals(principal.getName())) {
            ResponseEntity<Category> res = new ResponseEntity<>(category, HttpStatus.OK);
            return res;
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id, Principal principal) {
        Category category = categoryRepository.findById(id).get();
        if (userInRole(Roles.ADMIN) || category.getOwner().getUsername().equals(principal.getName())) {
            categoryRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping(path = "{id}",consumes = "application/json")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id , @RequestBody CategoryDto category,Principal principal) {
        Category newCategory = new Category();
        newCategory.setId(id);
        newCategory.setColor(category.getColor());
        newCategory.setName(category.getName());
        if (category.getOwner_id().equals("GOD") && userInRole(Roles.ADMIN)){
            newCategory.setShowAll(true);
        } else {
            newCategory.setShowAll(false);
        }
        newCategory.setOwner(userRepository.findById(category.getOwner_id()).get());
        newCategory.setDeleted(false);
        if (userInRole(Roles.ADMIN) || category.getOwner_id().equals(principal.getName())) {
            Category updatedCategory = categoryRepository.save(newCategory);
            ResponseEntity<Category> res = new ResponseEntity<Category>(updatedCategory,HttpStatus.OK);
            return res;
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(path = "" , consumes = "application/json")
    public ResponseEntity<Category> addCategory( @RequestBody CategoryDto category,Principal principal) {
        Category newCategory = new Category();
        newCategory.setColor(category.getColor());
        newCategory.setName(category.getName());
        if (category.getOwner_id().equals("GOD") && userInRole(Roles.ADMIN)){
            newCategory.setShowAll(true);
        } else {
            newCategory.setShowAll(false);
        }
        newCategory.setOwner(userRepository.findById(category.getOwner_id()).get());
        newCategory.setDeleted(false);
        if (userInRole(Roles.ADMIN) || category.getOwner_id().equals(principal.getName())) {
            Category updatedCategory = categoryRepository.save(newCategory);
            ResponseEntity<Category> res = new ResponseEntity<Category>(updatedCategory,HttpStatus.OK);
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
