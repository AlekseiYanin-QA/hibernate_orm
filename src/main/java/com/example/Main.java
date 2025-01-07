package com.example;
import com.example.model.Contact;
import com.example.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private ContactService contactService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        populateDatabase();


        List<Contact> contacts = contactService.findAll();
        System.out.println("Contacts in the database:");
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    private void populateDatabase() {

        Contact contact1 = new Contact("Иван", "Иванов", "1234567890", "ivan.ivanov@example.com");
        Contact contact2 = new Contact("Мария", "Иванова", "0987654321", "maria.ivanova@example.com");
        Contact contact3 = new Contact("Алексей", "Петров", "1122334455", "alexey.petrov@example.com");
        Contact contact4 = new Contact("Анна", "Сидорова", "2233445566", "anna.sidorova@example.com");
        Contact contact5 = new Contact("Сергей", "Морозов", "3344556677", "sergey.morozov@example.com");
        Contact contact6 = new Contact("Ольга", "Кузнецова", "4455667788", "olga.kuznetsova@example.com");
        Contact contact7 = new Contact("Дмитрий", "Федоров", "5566778899", "dmitry.fedorov@example.com");

        contactService.save(contact1);
        contactService.save(contact2);
        contactService.save(contact3);
        contactService.save(contact4);
        contactService.save(contact5);
        contactService.save(contact6);
        contactService.save(contact7);

        System.out.println("Sample contacts added to the database.");
    }
}