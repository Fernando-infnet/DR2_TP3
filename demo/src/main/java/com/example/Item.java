package com.example;

import java.util.Objects;

public final class Item {

    private final String sku;       
    private final int quantity;     
    private final double price;      

    public Item(String sku, int quantity, double price) {
        if (sku == null || sku.isEmpty()) {
            throw new IllegalArgumentException("SKU cannot be null or empty");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        this.sku = sku;
        this.quantity = quantity;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public Item updateQuantity(int newQuantity) {
        return new Item(this.sku, newQuantity, this.price);
    }

    public Item updatePrice(double newPrice) {
        return new Item(this.sku, this.quantity, newPrice);
    }

    public double totalValue() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return String.format("Item[sku=%s, quantity=%d, price=%.2f]", sku, quantity, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return quantity == item.quantity && Double.compare(item.price, price) == 0 && sku.equals(item.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, quantity, price);
    }
}
