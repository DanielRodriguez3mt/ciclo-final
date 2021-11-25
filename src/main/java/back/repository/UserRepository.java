/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back.repository;

import back.entities.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Override
    List<User> findAll();
    
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
}
