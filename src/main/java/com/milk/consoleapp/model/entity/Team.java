package com.milk.consoleapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jack Milk
 */
@Entity
@Table(name = "team")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "developers")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tm_dev",
            joinColumns = { @JoinColumn(name = "tm_id") },
            inverseJoinColumns = { @JoinColumn(name = "dev_id") })
    private Set<Developer> developers = new HashSet<>();

    public Team(String name, Set<Developer> developers) {
        this(null, name, developers);
    }

    @Override
    public String toString() {
        return "Team => |" + " id:" + id + " " + name + " | Developers => " + developers;
    }

}
