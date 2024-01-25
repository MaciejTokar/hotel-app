package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class Facility {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private String name;
    @ManyToMany(mappedBy = "facilities")
    private List<Room> rooms;
}
