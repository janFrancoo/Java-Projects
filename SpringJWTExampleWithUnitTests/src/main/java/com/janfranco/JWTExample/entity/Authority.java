package com.janfranco.JWTExample.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authorities")
@Data
@NoArgsConstructor
public class Authority {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "AUTH_NAME")
    private String name;

    @ManyToMany(mappedBy = "authorities")
    List<User> users;
}
