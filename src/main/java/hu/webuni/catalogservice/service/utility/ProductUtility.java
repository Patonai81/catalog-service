package hu.webuni.catalogservice.service.utility;

import com.querydsl.core.types.Predicate;
import hu.webuni.catalogservice.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.querydsl.QuerydslPredicateArgumentResolver;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import java.lang.reflect.Method;

@RequiredArgsConstructor
@Service
public class ProductUtility {

    private final PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver;
    private final QuerydslPredicateArgumentResolver querydslPredicateArgumentResolver;


    public Predicate getPredicate(String methodName, int paramIndex, NativeWebRequest nativeWebRequest) {

        Method method = null;
        try {
            method = this.getClass().getMethod(methodName, Predicate.class);
            MethodParameter methodParameter= new MethodParameter(method,paramIndex);
            return (Predicate) querydslPredicateArgumentResolver.resolveArgument(methodParameter,null,nativeWebRequest,null);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Pageable getPageable(String methodName, int paramIndex, NativeWebRequest nativeWebRequest) {
        Method method = null;
        try {
            method = this.getClass().getMethod(methodName, Pageable.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        MethodParameter methodParameter= new MethodParameter(method,paramIndex);
        Pageable pageable=pageableHandlerMethodArgumentResolver.resolveArgument(methodParameter,null,nativeWebRequest,null);
        return pageable;
    }

    public void configPageable(@SortDefault("id") Pageable pageable){}

    public void configPredicate(@QuerydslPredicate(root = Product.class) Predicate predicate){}


}
