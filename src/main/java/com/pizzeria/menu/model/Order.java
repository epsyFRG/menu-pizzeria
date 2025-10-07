package com.pizzeria.menu.model;

import com.pizzeria.menu.enums.OrderState;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Order {
    private static int orderCounter = 1;

    private int orderNumber;
    private Table table;
    private List<Product> items;
    private OrderState state;
    private int numberOfCovers;
    private LocalDateTime orderTime;
    private double coverCharge;

    public Order(Table table, int numberOfCovers, double coverCharge) {
        this.orderNumber = orderCounter++;
        this.table = table;
        this.numberOfCovers = numberOfCovers;
        this.coverCharge = coverCharge;
        this.items = new ArrayList<>();
        this.state = OrderState.IN_CORSO;
        this.orderTime = LocalDateTime.now();
    }

    public void addItem(Product product) {
        items.add(product);
    }

    public double getTotalAmount() {
        double itemsTotal = items.stream()
                .mapToDouble(Product::getPrice)
                .sum();
        double coversTotal = numberOfCovers * coverCharge;
        return itemsTotal + coversTotal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        sb.append("\n╔════════════════════════════════════════════════════════════╗\n");
        sb.append(String.format("║  ORDINE #%d%s║\n",
                orderNumber, " ".repeat(48 - String.valueOf(orderNumber).length())));
        sb.append("╚════════════════════════════════════════════════════════════╝\n");
        sb.append(String.format("Tavolo: %d\n", table.getNumber()));
        sb.append(String.format("Stato: %s\n", state));
        sb.append(String.format("Numero coperti: %d\n", numberOfCovers));
        sb.append(String.format("Ora acquisizione: %s\n", orderTime.format(formatter)));
        sb.append("\n--- PRODOTTI ---\n");

        for (int i = 0; i < items.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, items.get(i)));
        }

        sb.append("\n--- TOTALI ---\n");
        double itemsTotal = items.stream().mapToDouble(Product::getPrice).sum();
        double coversTotal = numberOfCovers * coverCharge;

        sb.append(String.format("Subtotale prodotti: €%.2f\n", itemsTotal));
        sb.append(String.format("Coperti (%d x €%.2f): €%.2f\n",
                numberOfCovers, coverCharge, coversTotal));
        sb.append(String.format("\n**TOTALE: €%.2f**\n", getTotalAmount()));
        sb.append("════════════════════════════════════════════════════════════\n");

        return sb.toString();
    }
}