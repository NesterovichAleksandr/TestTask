package net.cabinssolitude.cabins.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "\"User\"")
public class User implements Serializable {

    private static final long serialVersionUID = -1552664944708088234L;

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

    @ManyToMany(cascade = {CascadeType.ALL})
    private Set<Role> roles;

}
