package back.service;

import back.entities.User;
import back.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(int id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserByEmailAndPassword(String email, String pass) {
        return userRepository.findByEmailAndPassword(email, pass);
    }

    public User save(User user) {
        if (user.getId()== null) {
            return userRepository.save(user);
        } else {
            Optional<User> mot = userRepository.findById(user.getId());
            if (mot.isPresent()) {
                return userRepository.save(user);
            } else {
                return user;
            }
        }
    }

    public User update(User user) {
        if (user.getId()!= null) {
            Optional<User> userOpt = userRepository.findById(user.getId());
            if (!userOpt.isPresent()) {
                if (user.getName() != null) userOpt.get().setName(user.getName());
                if (user.getEmail() != null) userOpt.get().setEmail(user.getEmail());
                if (user.getPassword()!= null) userOpt.get().setPassword(user.getPassword());
                userRepository.save(userOpt.get());
                return userOpt.get();
            } else {
                return user;
            }
        } else {
            return user;
        }

    }

    public boolean deleteUser(int id) {
        Boolean userBoolean = userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
        return userBoolean;
    }
    
}
