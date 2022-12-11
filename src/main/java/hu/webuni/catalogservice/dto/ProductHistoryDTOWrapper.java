package hu.webuni.catalogservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;


@Data
@AllArgsConstructor
@ToString
public class ProductHistoryDTOWrapper {

    ProductDTO productDTO;
    DefaultRevisionEntity revEntity;
    RevisionType revType;
}
