package io.madhu.txsimulator;

import io.madhu.txsimulator.producer.TransactionTaskFixedUsersProducer;
import io.madhu.txsimulator.producer.TransactionTaskProducer;
import io.madhu.txsimulator.runner.TransactionTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CreditCardTxSimulatorApplication implements CommandLineRunner {

	@Autowired
	private TransactionTaskProducer transactionTaskProducer;

	@Autowired
	private TransactionTaskFixedUsersProducer fixedUsersProducer;

	public static void main(String[] args) {
		SpringApplication.run(CreditCardTxSimulatorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//transactionTaskProducer.produce();
		fixedUsersProducer.produce();
	}
}
