package com.example.mta.demo.controllers;


import com.example.mta.demo.models.Contact;
import com.example.mta.demo.services.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api(value = "Contact Management System", description = "Test API contact")
public class ContactController {


    public static Logger logger = LoggerFactory.getLogger(ContactController.class);
    @Autowired
    private ContactService contactService;

    @ApiOperation(value = "View a list of available contact")

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public ResponseEntity<List<Contact>> findAllContact(){
        List<Contact> contactList = contactService.getAll();
        if (contactList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Contact>>(contactList, HttpStatus.OK);
    }

    @ApiOperation(value = "View contact by id")
    @RequestMapping(value = "/contact/{id}", method = RequestMethod.GET)
    public ResponseEntity<Contact> findContact(@PathVariable("id") Long id) {
        Optional<Contact> contact= contactService.getById(id);
        if(!contact.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contact.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "create a contact")
    @RequestMapping(value = "/contact", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> createContact(@Valid @RequestBody Contact contact, UriComponentsBuilder builder){
        contact.setCreatedAt(contactService.getTimestamp().toLocalDateTime());
        contact.setUpdatedAt(contactService.getTimestamp().toLocalDateTime());
        contactService.create(contact);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/contact}")
                .buildAndExpand(contact.getId()).toUri());
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    @ApiOperation(value = "update a contact")
    @RequestMapping(value = "/contact/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Contact> updateContact(@PathVariable(value = "id") Long id,
                                                 @Valid @RequestBody Contact contact) {

        Optional<Contact> currentContact = contactService.getById(id);
        if (!currentContact.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        currentContact.get().setId(currentContact.get().getId());
        currentContact.get().setName(contact.getName());
        currentContact.get().setDob(contact.getDob());
        currentContact.get().setAge(contact.getAge());
        currentContact.get().setEmail(contact.getEmail());
        currentContact.get().setUpdatedAt(contactService.getTimestamp().toLocalDateTime());
        contactService.update(currentContact.get());
        return new ResponseEntity<>(currentContact.get(), HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "delete a contact")
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
