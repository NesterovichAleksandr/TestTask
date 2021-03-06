package net.cabinssolitude.cabins.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import net.cabinssolitude.cabins.model.Role;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserRQ {
    private final static String messageForLength = "must be 8 - 100 chars";

    @JsonProperty("name")
    @Size(min = 1, max = 100, message = messageForLength)
    @NotBlank
    private String name;

    @JsonProperty("lastName")
    @Length(min = 1, max = 100, message = messageForLength)
    @NotBlank
    private String lastName;

    @JsonProperty("email")
    @Email
    @NotBlank
    @Size(min = 1, max = 200)
    private String email;

    @JsonProperty("password")
    @NotBlank
    @Size(min = 1, max = 200)
    private String password;

    @JsonProperty("roles")
    private Set<Role> roles;

}
