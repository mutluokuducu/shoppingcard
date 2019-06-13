package com.bjss.shoppingcard;

import java.util.Arrays;

public class ShoppingCard {

    public static void main(String[] args) {

        ProductRepository productRepository = new ProductRepositoryImp();

        ShoppingCardService shoppingCard = new ShoppingCardService(productRepository);

        System.out.println(shoppingCard.checkout(Arrays.asList(args)));
    }

}
