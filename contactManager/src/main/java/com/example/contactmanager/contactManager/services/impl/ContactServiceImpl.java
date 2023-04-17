package com.example.contactmanager.contactManager.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.contactmanager.contactManager.entities.Contact;
import com.example.contactmanager.contactManager.exceptions.ResourceNotFoundException;
import com.example.contactmanager.contactManager.payloads.ContactDto;
import com.example.contactmanager.contactManager.repositories.ContactRepository;
import com.example.contactmanager.contactManager.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ModelMapper modelmapper;

	@Autowired
	private ContactRepository contactrepo;

	@Override
	public ContactDto createContact(ContactDto contactdto) {

		Contact contact = this.contactdtoToContact(contactdto);

		Contact savedContact = contactrepo.save(contact);

		ContactDto contactToContactdto = this.contactToContactdto(savedContact);

		return contactToContactdto;
	}

	@Override
	public ContactDto updateContact(ContactDto contactdto, int contactId) {

		Contact contact = contactrepo.findById(contactId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact", "Contact ID", contactId));

		contact.setFirst_name(contactdto.getFirst_name());
		contact.setLast_name(contact.getLast_name());
		contact.setEmail(contactdto.getEmail());
		contact.setPhone_number(contactdto.getPhone_number());

		Contact updatedContact = contactrepo.save(contact);

		return this.contactToContactdto(updatedContact);
	}

	@Override
	public ContactDto getContactById(Integer contactId) {
		Contact contact = contactrepo.findById(contactId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact", "Contact ID", contactId));
		return this.contactToContactdto(contact);
	}

	@Override
	public List<ContactDto> getAllContacts() {
		List<Contact> contacts = contactrepo.findAll();

		List<ContactDto> collectedContacts = contacts.stream().map(con -> this.contactToContactdto(con))
				.collect(Collectors.toList());

		return collectedContacts;
	}

	@Override
	public void deleteContactById(Integer contactId) {

		Contact contact = contactrepo.findById(contactId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact", "Contact ID", contactId));
		contactrepo.delete(contact);

	}

	@Override
	public ContactDto getContactByFirstName(String contactFirstName) {
		Contact contactByFirstName = this.contactrepo.getContactByFirstName(contactFirstName);
		if (contactByFirstName == null) {
			throw new ResourceNotFoundException("Contact", contactFirstName);
		}
		return this.contactToContactdto(contactByFirstName);
	}

	@Override
	public ContactDto getContactByLastName(String contactLastName) {
		Contact contactByLastName = this.contactrepo.getContactByLastName(contactLastName);
		if (contactByLastName == null) {
			throw new ResourceNotFoundException("Contact", contactLastName);
		}
		return this.contactToContactdto(contactByLastName);
	}

	@Override
	public List<ContactDto> getContactByEmail(String getContactByEmail) {
		List<Contact> searchByEmail = this.contactrepo.searchByEmail(getContactByEmail);
		if (searchByEmail.isEmpty()) {
			throw new ResourceNotFoundException("Contact", getContactByEmail);
		}
		List<ContactDto> collect = searchByEmail.stream().map(con -> this.contactToContactdto(con))
				.collect(Collectors.toList());

		return collect;
	}

	public Contact contactdtoToContact(ContactDto contactdto) {
		Contact contact = this.modelmapper.map(contactdto, Contact.class);
		return contact;
	}

	public ContactDto contactToContactdto(Contact contact) {
		ContactDto contactdto = this.modelmapper.map(contact, ContactDto.class);
		return contactdto;
	}

}
