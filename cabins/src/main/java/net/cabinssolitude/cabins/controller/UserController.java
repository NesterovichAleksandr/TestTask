package net.cabinssolitude.cabins.controller;

import lombok.RequiredArgsConstructor;
import net.cabinssolitude.cabins.model.User;
import net.cabinssolitude.cabins.repositorys.dto.ApiResponse;
import net.cabinssolitude.cabins.repositorys.dto.UserRQ;
import net.cabinssolitude.cabins.services.AuthenticationFacadeService;
import net.cabinssolitude.cabins.services.UserService;
import net.cabinssolitude.cabins.services.convector.UserRQToUserConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
class UserController {

    private final UserService userService;
    private final ConversionService conversionService;
    private final UserRQToUserConverter userRQToUserConverter;

    @PostMapping(value = "/common/registration")
    public void userRegistration(@Valid @RequestBody UserRQ userRQ) {
        User user = userRQToUserConverter.convert(userRQ);
        userService.create(user);
    }

    @GetMapping(value="/all")
    public List listUser(){
        return userService.findAll();
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable(value = "id") Long id){
        userService.delete(id);
        return "success";
    }
}