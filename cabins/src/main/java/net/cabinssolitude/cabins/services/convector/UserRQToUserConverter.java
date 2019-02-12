package net.cabinssolitude.cabins.services.convector;

import lombok.RequiredArgsConstructor;
import net.cabinssolitude.cabins.model.User;
import net.cabinssolitude.cabins.services.dto.UserRQ;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRQToUserConverter implements Converter<UserRQ, User> {

    @Override
    public User convert(final UserRQ userRQ) {
        User user = new User();
        user.setName(userRQ.getName());
        user.setLastName(userRQ.getLastName());
        user.setEmail(userRQ.getEmail());
        user.setPassword(userRQ.getPassword());
        user.setRoles(userRQ.getRoles());
        return user;
    }
}
