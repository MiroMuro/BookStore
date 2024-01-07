package com.example.Bookstore.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.example.Bookstore.domain.Beer;


@RepositoryRestResource
public interface BeerRepository extends CrudRepository<Beer, Long> {
    List<Beer> findByName(String Name);
    Optional<Beer> findById(Long id);
    List<Beer> findByMaku(@Param("maku")String maku);
    
    
}