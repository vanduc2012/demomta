package com.example.mta.demo.services;

import com.example.mta.demo.models.Contact;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ContactService {
    List<Contact> getAll();
    Optional<Contact> getById(Long id);
    Contact create(Contact contact);
    Contact update(Contact contact);
    void delete(Contact contact);
    Contact save(Contact contact);
}
