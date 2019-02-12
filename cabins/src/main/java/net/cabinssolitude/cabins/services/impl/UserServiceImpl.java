package net.cabinssolitude.cabins.services.impl;

import net.cabinssolitude.cabins.model.Role;
import net.cabinssolitude.cabins.model.User;
import net.cabinssolitude.cabins.repositorys.UserRepository;
import net.cabinssolitude.cabins.repositorys.dto.UserRQ;
import net.cabinssolitude.cabins.services.UserService;
import net.cabinssolitude.cabins.services.convector.UserRQToUserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User edit(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        Set<GrantedAuthority> grantedAuthorities = getAuthorities(user);

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), grantedAuthorities);
    }
    private Set<GrantedAuthority> getAuthorities(User user) {
        Set<Role> roleByUserId = user.getRoles();
        final Set<GrantedAuthority> authorities = roleByUserId.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().toString().toUpperCase())).collect(Collectors.toSet());
        return authorities;
    }
}
