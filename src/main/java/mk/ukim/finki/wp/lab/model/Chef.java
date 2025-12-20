package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
    @OneToMany(mappedBy = "chef", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dish> dishes;

    public  Chef(String firstName, String lastName, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        dishes = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Chef{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bio='" + bio + '\'' +
                '}';     // do NOT print dishes
    }
}
