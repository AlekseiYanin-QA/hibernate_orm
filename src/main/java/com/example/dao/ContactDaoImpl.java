package com.example.dao;

import com.example.model.Contact;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ContactDaoImpl {

    private static final Logger logger = LoggerFactory.getLogger(ContactDaoImpl.class);
    private final ContactRepository contactRepository;

    @Autowired
    public ContactDaoImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Contact> findById(Long id) {
        return contactRepository.findById(id);
    }

    @Transactional
    public void save(Contact contact) {
        contactRepository.save(contact);
        logger.info("Контакт {} успешно добавлен.", contact);
    }

    @Transactional
    public void updatePhoneNumber(Long id, String newPhoneNumber) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()) {
            Contact contact = optionalContact.get();
            contact.setPhoneNumber(newPhoneNumber);
            contactRepository.save(contact);
            logger.info("Телефонный номер контакта с ID {} обновлён.", id);
        } else {
            logger.warn("Контакт с ID {} не найден для обновления номера.", id);
            throw new EntityNotFoundException("Контакт с ID " + id + " не найден.");
        }
    }

    @Transactional
    public void updateEmail(Long id, String newEmail) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()) {
            Contact contact = optionalContact.get();
            contact.setEmail(newEmail);
            contactRepository.save(contact);
            logger.info("Email контакта с ID {} обновлён.", id);
        } else {
            logger.warn("Контакт с ID {} не найден для обновления email.", id);
            throw new EntityNotFoundException("Контакт с ID " + id + " не найден.");
        }
    }

    @Transactional
    public void deleteById(Long id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            logger.info("Контакт с ID {} успешно удалён.", id);
        } else {
            logger.warn("Контакт с ID {} не найден для удаления.", id);
            throw new EntityNotFoundException("Контакт с ID " + id + " не найден.");
        }
    }

}
