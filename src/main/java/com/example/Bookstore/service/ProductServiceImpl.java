package com.example.Bookstore.service;

import javax.transaction.Transactional;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Bookstore.domain.Beer;
import com.example.Bookstore.domain.BeerRepository;

@Service
@Transactional
public class ProductServiceImpl {
	
	private BeerRepository beerRepo;
	
	public ProductServiceImpl(BeerRepository repo) {
		this.beerRepo = repo;
	}
	
	public Iterable<Beer> getAllBeers(){
		return beerRepo.findAll();
	}
	
	public Beer getBeer(long id) {
		return beerRepo
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}
	
	public Beer save(Beer product) {
		return beerRepo.save(product);
	}
	
}
