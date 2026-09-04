class Item {
    String itemName;
    int stock;

    // Resolving parameter clash using this
    public Item(String itemName, int stock) {
        this.itemName = itemName;
        this.stock = stock;
    }

    // Resolving parameter clash using this
    public void restock(int stock) {
        this.stock += stock;
    }
}

public class CanteenRestockTest {
    public static void main(String[] args) {
        Item[] items = {
            new Item("Samosa", 15),
            new Item("Tea Powder", 40),
            new Item("Bread", 8),
            new Item("Biscuit Packs", 25)
        };

        for (Item item : items) {
            item.restock(20);
            System.out.println(item.itemName + " | Final Stock: " + item.stock);
        }
    }
}