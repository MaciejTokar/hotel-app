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
    @ManyToMany(mappedBy = "facilities")
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "room_facility",
//            joinColumns = @JoinColumn(name = "room_id"),
//            inverseJoinColumns = @JoinColumn(name = "facility_id")
//    )
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
    private List<Room> rooms;
}
