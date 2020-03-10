package com.example.mta.demo.services.serviceImpl;

import com.example.mta.demo.models.Contact;
import com.example.mta.demo.repositories.ContactRepository;
import com.example.mta.demo.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> getAll() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts;
    }

    @Override
    public Optional<Contact> getById(Long id) {
        return contactRepository.findById(id);
    }

    @Override
    public Contact create(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public void delete(Contact contact) {
        contactRepository.delete(contact);
    }

    @Override
    public Timestamp getTimestamp() {
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        return  ts;
    }
}
