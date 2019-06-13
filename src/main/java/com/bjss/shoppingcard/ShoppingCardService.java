package com.bjss.shoppingcard;

import java.util.Collections;
import java.util.List;

public class ShoppingCardService {

    private ProductRepository productRepository;

    public ShoppingCardService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public String checkout(List<String> list) {

        double subTotal = 0;
        double discount = 0;
        StringBuilder result = new StringBuilder();

        int soupCount = Collections.frequency(list, "Soup");

        for (String item : list) {

            Product products = productRepository.findById(item);

            subTotal += products.getPrice();
            if (products.getId().equals("Apples")) {
                discount += products.getPrice() * products.getDiscount();


            }
            if (soupCount == 2 && products.getId().equals("Bread")) {
                discount += products.getPrice() / 2;

            }

        }

        result.append(String.format("Subtotal: \u00a3 %.2f\n", subTotal));
        if (discount == 0) {
            result.append("(no offers available)\n");
        }
        if (list.contains("Apples")){
            result.append("Apples 10% off: -" + discount * 100 + "p\n");

        }
        if(soupCount>=2){
            result.append("Bread 50% off: -" + discount * 100 + "p\n");
        }

        result.append(String.format("Total: \u00a3 %.2f", (subTotal - discount)));

        return result.toString();
    }
}


