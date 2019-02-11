package net.cabinssolitude.cabins.services.convector;

import lombok.RequiredArgsConstructor;
import net.cabinssolitude.cabins.model.User;
import net.cabinssolitude.cabins.repositorys.dto.UserRQ;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRQToUserConverter implements Converter<UserRQ, User> {

    private static final String SALT = "abcdefghijklmnopqrstuvwxyz1234567890";
    private static final Integer SALT_LENGTH = 18;

    @Override
    public User convert(final UserRQ userRQ) {
        User user = new User();
        user.setName(userRQ.getName());
        user.setLastName(userRQ.getLastName());
        user.setEmail(userRQ.getEmail());
        user.setPassword(userRQ.getPassword());
        return user;
    }
}
