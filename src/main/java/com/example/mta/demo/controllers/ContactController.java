package com.example.mta.demo.controllers;


import com.example.mta.demo.models.Contact;
import com.example.mta.demo.services.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ContactController {

    public static Logger logger = LoggerFactory.getLogger(ContactController.class);
    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/contact/", method = RequestMethod.GET)
    public ResponseEntity<List<Contact>> findAllContact(){
        List<Contact> contactList = contactService.getAll();
        if (contactList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Contact>>(contactList, HttpStatus.OK);
    }

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.GET)
    public ResponseEntity<Contact> findContact(@PathVariable("id") long id) {
        Optional<Contact> contact= contactService.getById(id);
        if(!contact.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contact.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.POST)
    public ResponseEntity<Contact> createProduct(@RequestBody Contact contact, UriComponentsBuilder builder){
        contactService.create(contact);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/products/{id}")
                .buildAndExpand(contact.getId()).toUri());
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Contact> updateContact(@PathVariable(value = "id") Long id,
                                                 @Valid @RequestBody Contact contact) {
        Optional<Contact> currentContact = contactService.getById(id);

        if (!currentContact.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        currentContact.get().setId(contact.getId());
        currentContact.get().setName(contact.getName());
        currentContact.get().setDob(contact.getDob());
        currentContact.get().setAge(contact.getAge());
        currentContact.get().setEmail(contact.getEmail());

        contactService.update(currentContact.get());
        return new ResponseEntity<>(currentContact.get(), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Contact> deleteContact(@PathVariable(value = "id") Long id) {
        Optional<Contact> contact = contactService.getById(id);

        if (!contact.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        contactService.delete(contact.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
