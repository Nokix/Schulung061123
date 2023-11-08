package db.schulung.Schulung061123.aop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class ShoppingCart {
    private Integer countOfItems = 0;
    private Integer sumOfPrices = 0;

    private List<Item> cart = new ArrayList<>();

    @TimeMe(ChronoUnit.MILLIS)
    public ShoppingCart addItem(Item item) {
        cart.add(item);
        return this;
    }

    @TimeMe
    public ShoppingCart removeItem(Item item) {
        cart.remove(item);
        return this;
    }

    void updateSumOfPrices() {
        sumOfPrices = cart.stream()
                .mapToInt(Item::getPrice)
                .sum();
    }

    void updateCountOfItems() {
        countOfItems = cart.size();
    }
}
