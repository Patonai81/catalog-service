package hu.webuni.catalogservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Audited
@EqualsAndHashCode
@Data
@Entity
public class Category {

    @Id
    @Column(name = "name", nullable = false)
    @EqualsAndHashCode.Include
    private String name;


    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private Set<Product> products= new HashSet<>();


}
