package hu.webuni.catalogservice.dto;

import hu.webuni.catalogservice.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;

@Data
@AllArgsConstructor
@ToString
public class ProductHistoryWrapper {

    Product product;
    DefaultRevisionEntity revEntity;
    RevisionType revType;
}