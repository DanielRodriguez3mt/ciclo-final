package back.controller;

import back.entities.User;
import back.service.UserService;
import java.util.List;
import java.util.Optional;

import net.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", 
             methods = {RequestMethod.GET,
                        RequestMethod.POST,
                        RequestMethod.PUT,
                        RequestMethod.DELETE})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/{email}")
    public Boolean getUserByEmail(@PathVariable("email") String userEmail) {
        return userService.getUserByEmail(userEmail).isPresent();
    }

    @GetMapping("/{email}/{password}")
    public Optional<User> getUserByEmailAndPassword(
            @PathVariable("email") String userEmail,
            @PathVariable("password") String userPass) {
        if (userService.getUserByEmailAndPassword(userEmail, userPass).isPresent())
            return userService.getUserByEmailAndPassword(userEmail, userPass);
        else {
            User u = new User();
            u.setEmail(userEmail);
            u.setPassword(userPass);
            u.setName("NO DEFINIDO");
            return Optional.of(u);
        }
    }
    
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User user){
        return userService.save(user);
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public User update(@RequestBody User user){
        return userService.save(user);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int userID) {
        return userService.deleteUser(userID);
    }
}
