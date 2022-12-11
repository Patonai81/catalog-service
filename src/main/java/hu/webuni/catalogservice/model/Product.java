package hu.webuni.catalogservice.model;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Audited
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

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
    private Category category;


}
