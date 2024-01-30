package model;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Room {
    @Id
    @GeneratedValue
    private Long id;
    private String number;
    private String type;
    private Integer personCount;
    private Boolean bathroom;
    @Column(name = "price_per_night")
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Reservation> reservations;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "room_facility",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
//    @ManyToMany(mappedBy = "rooms")
    private List<Facility> facilities;
}
