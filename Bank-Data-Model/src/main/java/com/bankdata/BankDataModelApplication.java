package com.bankdata;

import com.bankdata.fakedata.DummyDataGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankDataModelApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankDataModelApplication.class, args);

		DummyDataGenerator dummyDataGenerator = new DummyDataGenerator();
		dummyDataGenerator.dummyPesonalData();
	}

}
