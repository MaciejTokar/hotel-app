package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.EventType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table
public class Event {
    @GeneratedValue
    @Id
    private Long id;
    private LocalDate date;
    private Integer personCount;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "event_types",
            joinColumns = @JoinColumn(name = "event_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private Set<EventType> events;
    @ManyToOne
    private Hotel hotel;
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private Set<Client> clients;
}
