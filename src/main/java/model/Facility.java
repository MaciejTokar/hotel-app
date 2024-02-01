package model;

import lombok.*;

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
    @ManyToMany(mappedBy = "facilities", fetch = FetchType.EAGER)
    private List<Room> rooms;
}
