package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
    private String dishId;
    private String name;
    private String cuisine;
    private int preparationTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne
    private Chef chef;

    public Dish(String dishId, String name, String cuisine, int preparationTime) {
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + ID +
                ", dishId='" + dishId + '\'' +
                ", name='" + name + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", preparationTime=" + preparationTime +
                '}';
    }
}
