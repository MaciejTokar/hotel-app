package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private String mail;
    private String phone;
    private BigDecimal salary;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
