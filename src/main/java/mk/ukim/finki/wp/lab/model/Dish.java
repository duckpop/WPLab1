package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.lab.model.enums.Rank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
    private String dishId;
    private String name;
    private String cuisine;
    private int preparationTime;
    private Rank rank;
    private double rating;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne
    private Chef chef;

    public Dish(String dishId, String name, String cuisine, int preparationTime,Rank rank,double rating) {
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
        this.rank = rank;
        this.rating = rating;
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
