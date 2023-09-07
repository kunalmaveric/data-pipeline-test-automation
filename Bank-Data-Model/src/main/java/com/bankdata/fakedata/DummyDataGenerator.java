package com.bankdata.fakedata;

import com.bankdata.model.CustomerFeedBack;
import com.github.javafaker.Faker;
import com.bankdata.model.ContactInfomation;
import com.bankdata.model.EmploymentInfo;
import com.bankdata.model.PersonalInfo;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DummyDataGenerator {
    Faker faker = new Faker();
    Random random = new Random();
    public void dummyPesonalData() {
        // Create a Faker instance

        // Create a list to store the generated data
        List<PersonalInfo> personalInfoList = new ArrayList<>();

        // Generate 100 instances of fake data
        for (int i = 0; i < 500; i++) {
            // Generate dummy data for PersonalInfo
            PersonalInfo personalInfo = new PersonalInfo();
            personalInfo.setCustomerId(Long.parseLong(faker.number().digits(6)));
            personalInfo.setFirstName(faker.name().firstName());
            personalInfo.setLastName(faker.name().lastName());

            // Set Date of Birth to be null for some instances
            if (random.nextBoolean()) {
                personalInfo.setDob(faker.date().birthday());
            } else {
                personalInfo.setDob(null);
            }

            // Set Pan Number to be empty for some instances
            if (random.nextBoolean()) {
                personalInfo.setPanNumber(faker.idNumber().valid());
            } else {
                personalInfo.setPanNumber("");
            }

            // Generate dummy data for ContactInformation
            ContactInfomation contactInfo = new ContactInfomation();

            // Set Address to be null for some instances
            if (random.nextBoolean()) {
                contactInfo.setAddress(faker.address().fullAddress());
            } else {
                contactInfo.setAddress(null);
            }

            // Set Phone Number to be null for some instances
            if (random.nextBoolean()) {
                contactInfo.setPhoneNo(faker.number().randomNumber());
            } else {
                contactInfo.setPhoneNo(null);
            }

            // Set Email to be empty for some instances
            if (random.nextBoolean()) {
                contactInfo.setEmail(faker.internet().emailAddress());
            } else {
                contactInfo.setEmail("");
            }

            // Generate dummy data for EmploymentInfo
            EmploymentInfo employmentInfo = new EmploymentInfo();
            employmentInfo.setEmployer(faker.company().name());
            employmentInfo.setJobTitle(faker.job().title());

            // Set PersonalInfo's ContactInformation and EmploymentInfo
            personalInfo.setContactInfomation(contactInfo);
            personalInfo.setEmploymentInfo(employmentInfo);

            // Set status and dependents fields
            personalInfo.setStatus(random.nextBoolean() ? "Single" : "Married");
            personalInfo.setDependents(random.nextInt(4)); // Random number of dependents (0 to 4)


            personalInfo.setAccount_No(Long.parseLong(faker.number().digits(12)));

            // Add the PersonalInfo object to the list
            personalInfoList.add(personalInfo);
        }

        // Print the generated dummy data
        for (PersonalInfo personalInfo : personalInfoList) {
            System.out.println(personalInfo);
            System.out.println();
        }

        for (int i = 0; i < 50; i++) {
            personalInfoList.add(personalInfoList.get(i));
        }

        // Specify the path to the CSV file
        String csvFilePath = "C:\\Users\\pushkarg\\Documents\\hackathon\\dummyData.csv";

        // Write the data to the CSV file
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFilePath))) {
            // Write the header row
            String[] header = {
                    "Customer ID",
                    "First Name",
                    "Last Name",
                    "Date of Birth",
                    "Pan Number",
                    "Address",
                    "Phone Number",
                    "Email",
                    "Employer",
                    "Job Title",
                    "Status",
                    "Dependents",
                    "Account_No"
            };
            csvWriter.writeNext(header);

            // Write the data rows
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            for (PersonalInfo personalInfo : personalInfoList) {
                String dob = personalInfo.getDob() != null ? dateFormat.format(personalInfo.getDob()) : "";
                String[] data = {
                        String.valueOf(personalInfo.getCustomerId()),
                        personalInfo.getFirstName(),
                        personalInfo.getLastName(),
                        dob,
                        personalInfo.getPanNumber(),
                        personalInfo.getContactInfomation().getAddress() != null ? personalInfo.getContactInfomation().getAddress() : "",
                        personalInfo.getContactInfomation().getPhoneNo() != null ? personalInfo.getContactInfomation().getPhoneNo().toString() : "",
                        personalInfo.getContactInfomation().getEmail(),
                        personalInfo.getEmploymentInfo().getEmployer(),
                        personalInfo.getEmploymentInfo().getJobTitle(),
                        personalInfo.getStatus(),
                        String.valueOf(personalInfo.getDependents()),
                        String.valueOf(personalInfo.getAccount_No())
                };
                csvWriter.writeNext(data);
            }

            System.out.println("Data has been written to " + csvFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
