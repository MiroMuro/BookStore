package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import com.example.Bookstore.domain.Beer;
import com.example.Bookstore.domain.BeerRepository;
import com.example.Bookstore.domain.KauppaRepository;
import com.example.Bookstore.web.BeerController;

@SpringBootTest
class BeerstoreApplicationTests {
	 Logger logger=Logger.getLogger("global");
	BufferedImage bufferedImage = new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);
	@Autowired
	private KauppaRepository krepository;
	@Autowired
	private BeerController controller;
	@Autowired
	private BeerRepository repository;
	@Test
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	@Test
	public void BeerListLoads() throws Exception {
		assertThat(controller.beerListRest()).isNotNull();
	}
	@Test
	public void FindBeerLoads() throws Exception {
		assertThat(controller.findbeerRest((long) 6)).isNotNull();
	}
	@Test
	public void TestCreatingBeer() throws Exception {
		Beer beer = new Beer("Sandels",1.30,"4/5","0.5L can",krepository.findByName("Prisma"),null);
		repository.save(beer);
		assertThat(beer.getId()).isNotNull();
	}
	@Test
	public void TestDeletingBeer() throws Exception {
		List<Beer> kirja = repository.findByName("Harry Potter");
		Beer beer = kirja.get(0); // Kirjan id on 4.
		repository.delete(beer);
		assertThat(repository.findById((long) 4)).isEmpty();
	}
	@Test
	public void TestSearches() throws Exception {
		assertThat(repository.findByName("Pokemon")).isNull();
		assertThat(repository.findById((long) 5 )).isNotNull();
		assertThat(repository.findByMaku("Jack Daniels")).isNull();
		assertThat(krepository.findByName("Fantasy")).isNull();
	}
	@Test
	void contextLoads() {
	}

}
