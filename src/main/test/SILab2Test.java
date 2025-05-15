import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class SILab2Test {

    // Every Statement coverage
    @Test
    public void testEveryStatement() {
        // null list
        RuntimeException e1 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, "1234567890123456"));
        assertEquals("allItems list can't be null!", e1.getMessage());

        // invalid item name
        List<Item> items2 = List.of(new Item(null, 100, 0, 1));
        RuntimeException e2 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items2, "1234567890123456"));
        assertEquals("Invalid item!", e2.getMessage());

        // invalid card number (length != 16)
        List<Item> items3 = List.of(new Item("item", 100, 0, 1));
        RuntimeException e3 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items3, "123"));
        assertEquals("Invalid card number!", e3.getMessage());

        // valid data, no condition triggered
        List<Item> items4 = List.of(new Item("item", 100, 0, 1));
        assertEquals(100, SILab2.checkCart(items4, "1234567890123456"));

        // valid data, card contains invalid character
        List<Item> items5 = List.of(new Item("item", 100, 0, 1));
        RuntimeException e5 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items5, "12345678901234a6"));
        assertEquals("Invalid character in card number!", e5.getMessage());
    }

    // Multiple Condition coverage for: price > 300 || discount > 0 || quantity > 10
    @Test
    public void testMultipleConditionPriceDiscountQuantity() {
        String card = "1234567890123456";

        // 1: F F F
        assertEquals(100 * 5, SILab2.checkCart(List.of(new Item("item", 100, 0, 5)), card));

        // 2: T F F
        assertEquals(350 * 5 - 30, SILab2.checkCart(List.of(new Item("item", 350, 0, 5)), card));

        // 3: F T F
        assertEquals(-30 + 100 * 0.9 * 5, SILab2.checkCart(List.of(new Item("item", 100, 0.1, 5)), card));

        // 4: F F T
        assertEquals(100 * 15 - 30, SILab2.checkCart(List.of(new Item("item", 100, 0, 15)), card));

        // 5: T T F
        assertEquals(-30 + 350 * 0.9 * 5, SILab2.checkCart(List.of(new Item("item", 350, 0.1, 5)), card));

        // 6: T F T
        assertEquals(-30 + 400 * 20, SILab2.checkCart(List.of(new Item("item", 400, 0, 20)), card));

        // 7: F T T
        assertEquals(-30 + 200 * 0.9 * 20, SILab2.checkCart(List.of(new Item("item", 200, 0.1, 20)), card));

        // 8: T T T
        assertEquals(-30 + 500 * 0.8 * 25, SILab2.checkCart(List.of(new Item("item", 500, 0.2, 25)), card));
    }
}
