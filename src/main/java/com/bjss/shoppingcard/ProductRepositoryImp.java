package com.bjss.shoppingcard;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImp implements ProductRepository {
    @Override
    public Product findById(String id) {

        return productsList.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("This product not found!"));
    }


    private static List<Product> productsList = new ArrayList<>();

    static {
        Product soup = new Product();
        soup.setId("Soup");
        soup.setPrice(6.5);

        Product apple = new Product();
        apple.setId("Apples");
        apple.setPrice(1.00);
        apple.setDiscount(0.1);

        Product milk = new Product();
        milk.setId("Milk");
        milk.setPrice(1.30);

        Product bread = new Product();
        bread.setId("Bread");
        bread.setPrice(0.8);

        productsList.add(apple);
        productsList.add(milk);
        productsList.add(bread);
        productsList.add(soup);


    }

}
