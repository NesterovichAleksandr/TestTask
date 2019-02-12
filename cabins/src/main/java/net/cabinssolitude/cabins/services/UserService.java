package net.cabinssolitude.cabins.services;

import net.cabinssolitude.cabins.model.User;

import java.util.List;

public interface UserService {

    User create(User user);
    void delete(long id);
    User getByName(String name);
    User edit(User user);
    List<User> findAll();
    User findOne(long id);
}
