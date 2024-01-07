package com.example.Bookstore.domain;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
public interface KauppaRepository extends CrudRepository<Kauppa, Long> {
	
	Kauppa findByName(String name);
	
	
	
}
