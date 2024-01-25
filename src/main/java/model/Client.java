package model;

import lombok.*;
import model.enums.CardType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Client {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private String pesel;
    private String mail;
    private String phone;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Reservation> reservations;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Review> reviews;
    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Event event;
    @Enumerated(EnumType.STRING)
    private CardType cardType;
}
