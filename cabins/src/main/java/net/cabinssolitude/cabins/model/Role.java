package net.cabinssolitude.cabins.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "\"Role\"")
public class Role implements Serializable {

    private static final long serialVersionUID = -4664485943115930724L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private RoleType name;
}
