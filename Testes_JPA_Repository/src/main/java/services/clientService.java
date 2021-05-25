package services;

import java.util.Optional;


import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import dto.clientDTO;
import entities.client;
import repositories.clientRepository;
import services.exceptions.dataBaseException;
import services.exceptions.resourceNotFoundException;

public class clientService {
	@Autowired
	private clientRepository repository;
	
	@Transactional(readOnly = true)
	public Page<clientDTO> findAllPaged(PageRequest pageRequest) {
		Page<client> list =  repository.findAll(pageRequest);
		return list.map(x -> new clientDTO(x));
	}
	
	@Transactional(readOnly = true)
	public Page<clientDTO> findByIncome(Double income, PageRequest pageRequest) {
		Page<client> list =  repository.findByIncome(income, pageRequest);
		return list.map(x -> new clientDTO(x));
	}
	
	@Transactional(readOnly = true)
	public clientDTO findById(Long id) {
		Optional<client> obj = repository.findById(id);
		client entity = obj.orElseThrow(() -> new resourceNotFoundException("Entity not found"));
		return new clientDTO(entity);
	}
	
	@Transactional
	public clientDTO insert(clientDTO dto) {
		client entity = dto.toEntity();
		entity = repository.save(entity);
		return new clientDTO(entity);
	}
	
	@Transactional
	public clientDTO update(Long id, clientDTO dto) {
		try {
			@SuppressWarnings("deprecation")
			client entity = repository.getOne(id);
			updateData(entity, dto);
			entity = repository.save(entity);
			return new clientDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new resourceNotFoundException("Id not found " + id);
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new resourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new dataBaseException("Integrity violation");
		}
	}

	private void updateData(client entity, clientDTO dto) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}

}
