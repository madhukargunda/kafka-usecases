package io.madhu.pos.simulator;

import io.madhu.pos.simulator.engine.PosInvoiceSimulatorEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class POSSimulatorApplication implements CommandLineRunner {

    @Autowired
    private PosInvoiceSimulatorEngine engine;

    public static void main(String[] args) {
        SpringApplication.run(POSSimulatorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        engine.start();
    }
}
