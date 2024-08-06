/**
 * Author: Madhu
 * User:madhu
 * Date:24/6/24
 * Time:1:52 PM
 * Project: real-time-point-of-sale-simulator
 */

package io.madhu.pos.simulator.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.madhu.pos.simulator.beans.LineItem;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Random;

@Slf4j
public class ProductGenerator {

    private static final ProductGenerator INSTANCE = new ProductGenerator();
    private final Random random = new Random();
    private final Random qty = new Random();
    private LineItem[] products; //alias name of LineItem

    private ProductGenerator() {
        String DATAFILE = "src/main/resources/data/products.json";
        ObjectMapper mapper = new ObjectMapper();
        try {
            products = mapper.readValue(new File(DATAFILE), LineItem[].class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ProductGenerator getInstance() {
        return INSTANCE;
    }

    private int getIndex() {
        return random.nextInt(100);
    }

    private int getQuantity() {
        return qty.nextInt(2) + 1;
    }

    public LineItem getNextProduct() {
        LineItem lineItem = this.products[getIndex()];
        lineItem.setItemQty(getQuantity());
        lineItem.setTotalValue(lineItem.getItemPrice() * lineItem.getItemQty());
        return lineItem;
    }
}
