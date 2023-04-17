package com.example.contactmanager.contactManager.services;

import java.util.List;

import com.example.contactmanager.contactManager.payloads.ContactDto;

public interface ContactService {
		
		ContactDto createContact(ContactDto contactdto);
		
		ContactDto updateContact(ContactDto contactdto,int contactId);
		
		ContactDto getContactById(Integer contactId);
		
		ContactDto getContactByFirstName(String contactFirstName);
		
		ContactDto getContactByLastName(String contactLastName);
		
		List<ContactDto> getContactByEmail(String getContactByEmail);
		
		
		List<ContactDto> getAllContacts();
		
		void deleteContactById(Integer contactId);
}
