package repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import entities.client;

@Repository
public interface clientRepository extends JpaRepository<client, Long> {
	
	@Query("SELECT DISTINCT obj FROM Client obj WHERE "
			+ "obj.income >= :income")
	Page<client> findByIncome(Double income, Pageable pageable);
	
	@Query("SELECT obj FROM client obj WHERE UPPER(obj.nome) = UPPER(?1)")
	List<client>findFirstnameIgnoreCase(client name);
	
	@Query("SELECT obj FROM client obj WHERE UPPER(obj.birtDate) = UPPER(?1)")
	List<client>findBybirthDate(client birthDate);

	
}
