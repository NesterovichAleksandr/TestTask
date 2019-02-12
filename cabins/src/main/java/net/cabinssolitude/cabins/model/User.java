package net.cabinssolitude.cabins.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "\"User\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email
    @NotBlank
    @Size(min = 1, max = 200)
    @Column
    private String email;

    @NotBlank
    @Size(min = 1, max = 100)
    @Column
    private String name;

    @NotBlank
    @Size(min = 1, max = 100)
    @Column
    private String lastName;

    @NotBlank
    @Size(min = 1, max = 100)
    @Column
    private String password;

    //@Size(min = 1, max = 100)
    @ManyToMany(cascade = {CascadeType.ALL})
    private Set<Role> roles;

    //private LocalDateTime lastVisit;
    //private String locale;

}
