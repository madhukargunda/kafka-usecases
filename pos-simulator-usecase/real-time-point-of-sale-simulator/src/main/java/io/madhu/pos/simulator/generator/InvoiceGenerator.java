/**
 * Author: Madhu
 * User:madhu
 * Date:24/6/24
 * Time:4:06 PM
 * Project: real-time-point-of-sale-simulator
 */

package io.madhu.pos.simulator.generator;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.madhu.pos.simulator.beans.DeliveryAddress;
import io.madhu.pos.simulator.beans.LineItem;
import io.madhu.pos.simulator.beans.PosInvoice;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class InvoiceGenerator {

    private static final InvoiceGenerator INSTANCE = new InvoiceGenerator();
    private final Random invoiceIndex;
    private final Random invoiceNumber;
    private final Random numberOfItems;
    private PosInvoice[] posInvoices;

    private InvoiceGenerator() {
        String DATAFILE = "src/main/resources/data/Invoice.json";
        invoiceIndex = new Random();
        invoiceNumber = new Random();
        numberOfItems = new Random();
        ObjectMapper mapper = new ObjectMapper();
        try {
            posInvoices = mapper.readValue(new File(DATAFILE), PosInvoice[].class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static InvoiceGenerator getInstance() {
        return INSTANCE;
    }

    private int getIndex() {
        return invoiceIndex.nextInt(100);
    }

    private int getNewInvoiceNumber() {
        return invoiceNumber.nextInt(99999999) + 99999;
    }

    private int getNoOfItems() {
        return numberOfItems.nextInt(4) + 1;
    }

    public PosInvoice getNextInvoice() {
        PosInvoice invoice = posInvoices[getIndex()];
        invoice.setInvoiceNumber(Integer.toString(getNewInvoiceNumber()));
        invoice.setCreatedTime(Instant.now().toEpochMilli());
        if ("HOME-DELIVERY".equalsIgnoreCase(invoice.getDeliveryType())) {
            DeliveryAddress deliveryAddress = AddressLineGenerator.getInstance().getNextAddress();
            invoice.setDeliveryAddress(deliveryAddress);
        }
        int itemCount = getNoOfItems();
        Double totalAmount = 0.0;
        List<LineItem> items = new ArrayList<>();
        ProductGenerator productGenerator = ProductGenerator.getInstance();
        for (int i = 0; i < itemCount; i++) {
            LineItem item = productGenerator.getNextProduct();
            totalAmount = totalAmount + item.getTotalValue();
            items.add(item);
        }
        invoice.setNumberOfItems(itemCount);
        invoice.setInvoiceLineItems(items);
        invoice.setTotalAmount(totalAmount);
        invoice.setTaxableAmount(totalAmount);
        invoice.setCGST(totalAmount * 0.025);
        invoice.setSGST(totalAmount * 0.025);
        invoice.setCESS(totalAmount * 0.00125);
        return invoice;
    }
}
