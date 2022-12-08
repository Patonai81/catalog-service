package hu.webuni.catalogservice.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private  String name;
    private Long price;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Category category;


}
