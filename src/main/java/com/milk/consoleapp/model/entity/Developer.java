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
@Table(name = "developer")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "skills")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "dev_skill",
            joinColumns = { @JoinColumn(name = "dev_id") },
            inverseJoinColumns = { @JoinColumn(name = "sk_id") })
    private Set<Skill> skills = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "developers")
    private Set<Team> teams = new HashSet<>();

    public Developer(Integer id, String firstName, String lastName, Set<Skill> skills) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
    }

    public Developer(String firstName, String lastName, Set<Skill> skills) {
        this(null, firstName, lastName, skills);
    }

    @Override
    public String toString() {
        return " id:" + id + " " + firstName + " " + lastName + " | Skills => " + skills;
    }

}
