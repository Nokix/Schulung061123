package db.schulung.Schulung061123.aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ShoppingCartTest {
    @Autowired
    ShoppingCart shoppingCart;

    Item item0 = new Item("Cola", 100);
    Item item1 = new Item("Apfel", 50);

    @Test
    void emptyCartTest() {
        assertEquals(0, shoppingCart.getCountOfItems());
        assertEquals(0, shoppingCart.getSumOfPrices());
    }

    @Test
    void addItemTest() {
        shoppingCart.addItem(item0);
        assertEquals(1, shoppingCart.getCountOfItems());
        assertEquals(100, shoppingCart.getSumOfPrices());
    }

    @Test
    void addMultipleItems() {
        shoppingCart.addItem(item0);
        shoppingCart.addItem(item0);
        shoppingCart.addItem(item0);
        assertEquals(3, shoppingCart.getCountOfItems());
        assertEquals(300, shoppingCart.getSumOfPrices());
    }

    @Test
    void addDifferentItems() {
        shoppingCart.addItem(item0);
        shoppingCart.addItem(item1);
        assertEquals(2, shoppingCart.getCountOfItems());
        assertEquals(150, shoppingCart.getSumOfPrices());
    }

    @Test
    void removeEmpty() {
        shoppingCart.removeItem(item0);
        assertEquals(0, shoppingCart.getCountOfItems());
        assertEquals(0, shoppingCart.getSumOfPrices());
    }

    @Test
    void removeExistingItem() {
        shoppingCart.addItem(item0);
        shoppingCart.removeItem(item0);
        shoppingCart.addItem(item0);
        assertEquals(1, shoppingCart.getCountOfItems());
        assertEquals(100, shoppingCart.getSumOfPrices());
    }

    @Test
    void disallowAddingOfEmptyItem() {
        shoppingCart.addItem(new Item("", 100));
        assertEquals(0, shoppingCart.getCountOfItems());
        assertEquals(0, shoppingCart.getSumOfPrices());
    }
}