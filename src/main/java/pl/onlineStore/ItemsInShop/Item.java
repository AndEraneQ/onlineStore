package pl.onlineStore.ItemsInShop;

public class Item {
    private String name;
    private String category;
    private double price;
    private int quantity;
    public Item(Item i1){
        this.name=i1.name;
        this.quantity =i1.quantity;
        this.price=i1.price;
        this.category=i1.category;
    }
    public Item() {}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
