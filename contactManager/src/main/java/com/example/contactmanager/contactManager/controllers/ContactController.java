package com.example.contactmanager.contactManager.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.contactmanager.contactManager.payloads.ContactDto;
import com.example.contactmanager.contactManager.services.ContactService;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

	@Autowired
	private ContactService contactservice;

	// Create contact

	@PostMapping("/")
	public ResponseEntity<ContactDto> createContact(@RequestBody ContactDto contactdto) {
		ContactDto createdcontactDto = contactservice.createContact(contactdto);
		return new ResponseEntity<>(createdcontactDto, HttpStatus.CREATED);
	}

	// update user
	@PutMapping("/{contactId}")
	public ResponseEntity<ContactDto> updateContact(@RequestBody ContactDto contactdto,
			@PathVariable Integer contactId) {
		ContactDto updatedContact = this.contactservice.updateContact(contactdto, contactId);
		return new ResponseEntity<>(updatedContact, HttpStatus.OK);

	}

	// get contact by ID
	@GetMapping("/id/{contactId}")
	public ResponseEntity<ContactDto> getContactById(@PathVariable Integer contactId) {
		return ResponseEntity.ok(this.contactservice.getContactById(contactId));
	}

	// search contact by Name
	@RequestMapping(value="/name/fname/{first_name}" ,method = RequestMethod.GET)
	public ResponseEntity<ContactDto> getContactByFirstName(@PathVariable("first_name") String contactFirstName) {
		return ResponseEntity.ok(this.contactservice.getContactByFirstName(contactFirstName));
	}

	@RequestMapping(value="/name/lname/{last_name}" ,method = RequestMethod.GET)
	public ResponseEntity<ContactDto> getContactByLastName(@PathVariable("last_name") String contactLastName) {
		return ResponseEntity.ok(this.contactservice.getContactByLastName(contactLastName));
	}
	
	@RequestMapping(value="/email/{email}" ,method = RequestMethod.GET)
	public ResponseEntity<List<ContactDto>> getContactByEmail(@PathVariable("email") String contactEmail) {
		return ResponseEntity.ok(this.contactservice.getContactByEmail(contactEmail));
	}
	
	
	
	// get all contacts
	@GetMapping("/")
	public ResponseEntity<List<ContactDto>> getAllContacts() {
		return ResponseEntity.ok(this.contactservice.getAllContacts());
	}

	// delete contact by id

	@DeleteMapping("/{contactId}")
	public ResponseEntity<?> deleteContactById(@PathVariable Integer contactId) {
		this.contactservice.deleteContactById(contactId);
		return ResponseEntity.ok(Map.of("message", "Contact got deleted"));
	}

}
