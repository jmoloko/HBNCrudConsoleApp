package com.milk.consoleapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jack Milk
 */

@Entity
@Table(name = "skill")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "developers")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "skills")
    private Set<Developer> developers = new HashSet<>();

    public Skill(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Skill(String name) {
        this(null, name);
    }

    @Override
    public String toString() {
        return "id:" + id + " " + name + " |";
    }
}
