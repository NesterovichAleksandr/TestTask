package net.cabinssolitude.cabins.services;

import net.cabinssolitude.cabins.model.User;
import net.cabinssolitude.cabins.repositorys.dto.UserRQ;

import java.util.List;
import java.util.Optional;

public interface UserService {
    //Optional<User> save(User user);

    User create(User user);
    void delete(long id);
    User getByName(String name);
    User edit(User user);
    List<User> findAll();

}
