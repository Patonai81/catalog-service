package hu.webuni.catalogservice.repository;

import hu.webuni.catalogservice.model.Product;
import hu.webuni.catalogservice.model.QProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.Iterator;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long>, QuerydslPredicateExecutor<Product>,
        QuerydslBinderCustomizer<QProduct> {

    @Override
    default void customize(QuerydslBindings bindings, QProduct product){

        bindings.bind(product.name).first( (path,value) -> path.likeIgnoreCase("%"+value.toLowerCase()+"%"));
        bindings.bind(product.category.name).first(((path, value) -> path.startsWithIgnoreCase(value.toLowerCase()+"%")));

        bindings.bind(product.price).all((path, values) ->{
            Iterator<? extends Long> iterator = values.iterator();
            if (values.size() == 2) {
                return Optional.of(path.between(iterator.next(), iterator.next()));
            }else if (values.size() == 1){
                return Optional.of(path.gt(iterator.next()));
            }
            return Optional.empty();

        } );



    }

    Optional<Product> findByNameIgnoreCase(String name);


}
