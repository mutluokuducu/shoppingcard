package com.bjss.shoppingcard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCardTests {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ShoppingCardService shoppingCardService;


    @Test
    public void testSpecialOffers() {
        Product apple = new Product();
        apple.setId("Apples");
        apple.setPrice(1.00);
        apple.setDiscount(0.1d);

        Product milk = new Product();
        milk.setId("Milk");
        milk.setPrice(1.30d);

        Product bread = new Product();
        bread.setId("Bread");
        bread.setPrice(0.8D);

        List<String> list = new ArrayList<>();
        list.add("Apples");
        list.add("Milk");
        list.add("Bread");

        when(productRepository.findById("Apples")).thenReturn(apple);
        when(productRepository.findById("Milk")).thenReturn(milk);
        when(productRepository.findById("Bread")).thenReturn(bread);

        String result = shoppingCardService.checkout(list);

        StringBuilder expectedResult = new StringBuilder();

        expectedResult.append("Subtotal: £ 3.10\n");
        expectedResult.append("Apples 10% off: -10.0p\n");
        expectedResult.append("Total: £ 3.00");

        Assert.assertEquals(expectedResult.toString(), result);


    }

    @Test
    public void testNoSpecialOffers() {
        Product apple = new Product();
        apple.setId("Apples");
        apple.setPrice(1.00D);

        Product milk = new Product();
        milk.setId("Milk");
        milk.setPrice(1.30D);

        Product bread = new Product();
        bread.setId("Bread");
        bread.setPrice(0.8D);

        when(productRepository.findById("Milk")).thenReturn(milk);


        List<String> list = new ArrayList<>();
        list.add("Milk");
        String result = shoppingCardService.checkout(list);

        StringBuilder expectedResult = new StringBuilder();

        expectedResult.append("Subtotal: £ 1.30\n");
        expectedResult.append("(no offers available)\n");
        expectedResult.append("Total: £ 1.30");

        Assert.assertEquals(expectedResult.toString(), result);

    }

    @Test
    public void testSpecialBuyMoreThan2SoupOffers() {
        Product apple = new Product();
        apple.setId("Soup");
        apple.setPrice(0.65);

        Product milk = new Product();
        milk.setId("Milk");
        milk.setPrice(1.30d);

        Product bread = new Product();
        bread.setId("Bread");
        bread.setPrice(0.8D);
        List<String> list = new ArrayList<>();
        list.add("Soup");
        list.add("Soup");
        list.add("Milk");
        list.add("Bread");

        when(productRepository.findById("Soup")).thenReturn(apple);
        when(productRepository.findById("Milk")).thenReturn(milk);
        when(productRepository.findById("Bread")).thenReturn(bread);


        String result = shoppingCardService.checkout(list);

        StringBuilder expectedResult = new StringBuilder();

        expectedResult.append("Subtotal: £ 3.40\n");
        expectedResult.append("Bread 50% off: -40.0p\n");
        expectedResult.append("Total: £ 3.00");

        Assert.assertEquals(expectedResult.toString(), result);


    }

    @Test
    public void testBuy2BagApplesSpecialOffers() {
        Product apples = new Product();
        apples.setId("Apples");
        apples.setPrice(1.00);
        apples.setDiscount(0.1d);

        Product milk = new Product();
        milk.setId("Milk");
        milk.setPrice(1.30d);

        Product bread = new Product();
        bread.setId("Bread");
        bread.setPrice(0.8D);
        List<String> list = new ArrayList<>();
        list.add("Apples");
        list.add("Apples");
        list.add("Milk");
        list.add("Bread");

        when(productRepository.findById("Apples")).thenReturn(apples);
        when(productRepository.findById("Milk")).thenReturn(milk);
        when(productRepository.findById("Bread")).thenReturn(bread);

        String result = shoppingCardService.checkout(list);

        StringBuilder expectedResult = new StringBuilder();

        expectedResult.append("Subtotal: £ 4.10\n");
        expectedResult.append("Apples 10% off: -20.0p\n");
        expectedResult.append("Total: £ 3.90");

        Assert.assertEquals(expectedResult.toString(), result);


    }


}
