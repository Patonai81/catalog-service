package hu.webuni.catalogservice;

import hu.webuni.catalogservice.dto.CategoryDTO;
import hu.webuni.catalogservice.dto.ProductDTO;
import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;

@SpringBootApplication
public class CatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogServiceApplication.class, args);
    }


    public static void main2(String[] args) {
        JSONObject json = new JSONObject();
        HashSet<ProductDTO> productDTOS = new HashSet<>();
        ProductDTO productDTO = new ProductDTO();
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("mycategory");

       productDTO.setCategory(categoryDTO);
        productDTO.setName("productname");
       productDTOS.add(productDTO);

        json.put("set", productDTOS);
      //  json.put("list", Arrays.asList("a", "b"));
        String jsonString = json.toString();

        System.out.println(jsonString);

     //   JSONObject afterParse = new JSONObject(jsonString);
     //   System.out.println(afterParse.toString());
    }

}

