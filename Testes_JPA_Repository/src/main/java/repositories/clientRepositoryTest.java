package repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import entities.client;

public class clientRepositoryTest {

	clientRepository repository;
	
	@Test
	public void testNameExistingIngoreCase() {
		Optional<client> result = repository.findAllBy(name);
		result.get().setName("Joao Algusto");
		repository.findFirstnameIgnoreCase(result.get());
		Assertions.assertSame(result.get().getName(), "Joao Pedro Alves");
	}
	
	@Test
	public void testDateOfBirthGreaterThanThatProvided() {
		Optional<client> result = repository.findBybirthDate(birthDate); 
		result.get().getBirthDate();
		repository.findBybirthDate(result.get());
		Assertions.assertSame(result.get().getBirthDate(), "24-11-2005");
	}
	
	@Test
	public void updateShouldChangeAndPersistData() {
		Optional<client> result = repository.findById(id);
		result.get().setName("Joao Algusto");
		repository.save(result.get());
		Assertions.assertSame(result.get().getName(), "Paulo Algusto");
	}
}