package com.example.contactmanager.contactManager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.contactmanager.contactManager.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
	
	@Query("select c from Contact c where c.first_name = :first_name")
	public Contact getContactByFirstName(@Param("first_name") String first_name);
	
	@Query("select c from Contact c where c.last_name = :last_name")
	public Contact getContactByLastName(@Param("last_name") String last_name);
	
	@Query("from Contact as c where c.email like %:email%")
	public List<Contact> searchByEmail(@Param("email")String email);
	

}
