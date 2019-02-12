package net.cabinssolitude.cabins.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "\"Role\"")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private RoleType name;
}
