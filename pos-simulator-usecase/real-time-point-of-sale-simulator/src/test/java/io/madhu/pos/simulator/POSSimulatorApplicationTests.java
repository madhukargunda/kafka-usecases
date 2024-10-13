package io.madhu.pos.simulator;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.madhu.pos.simulator.beans.PosInvoice;
import io.madhu.pos.simulator.generator.InvoiceGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Slf4j
class POSSimulatorApplicationTests {

    @Test
    void contextLoads() {
        InvoiceGenerator invoiceGenerator = InvoiceGenerator.getInstance();
        PosInvoice posInvoice = invoiceGenerator.getNextInvoice();
        JsonSerializer jsonSerializer = new JsonSerializer();
        byte[] serialized = jsonSerializer.serialize("pos-invoice", posInvoice);
        log.info(String.format("---> %s",new String(serialized)));
        JsonDeserializer deserializer = new JsonDeserializer<>(PosInvoice.class);
        PosInvoice posInvoice1 = (PosInvoice) deserializer.deserialize("pos-invoice",serialized);
        log.info(String.format("Deserialized Object %s",posInvoice1));
    }
}
