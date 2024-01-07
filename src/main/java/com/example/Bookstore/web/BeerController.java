package com.example.Bookstore.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Bookstore.domain.Beer;
import com.example.Bookstore.domain.BeerRepository;
import com.example.Bookstore.domain.KauppaRepository;

import org.springframework.stereotype.Controller;

@Controller
public class BeerController {

	@Autowired
	private BeerRepository repository;
	@Autowired
	private KauppaRepository krepository;

	// Metodi palauttaa yhden olue json muodossa REST palveluna.
	@RequestMapping(value = "/beer/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Beer> findbeerRest(@PathVariable("id") Long beerId) {
		return repository.findById(beerId);
	}

	// REST palvelulle kaikki oluet json muodossa palauttava metodi
	@RequestMapping(value = "/beers", method = RequestMethod.GET)
	public @ResponseBody List<Beer> beerListRest() {
		return (List<Beer>) repository.findAll();
	}

	// Ns. kotisivu. Listaa kaikki databasessa olevat oluet
	@RequestMapping(value = "/beerlist")
	public String beerList(Model model) {
		model.addAttribute("beers", repository.findAll());
		return "beerlist";
	}

	// Oluen lisäys databaseen sivu. Model saa attribuutekseen oluen ja
	// kaikki kaupat jotka on tallennettu databaseen.
	
	@RequestMapping(value = "/addbeer")
	public String addbeer(Model model) {
		model.addAttribute("beer", new Beer());
		model.addAttribute("stores", krepository.findAll());
		return "addbeer";
	}

	// Tallennetaan olut. Luodaan parametristä "image" MultipartFile.
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Beer beer, @RequestParam("image") MultipartFile multipartFile) throws IOException {
		
		System.out.println(beer.getName()+" "+beer.getPhotosImagePath());
		// Annetaan tallennettavan Beer-objektin photos attribuutille
		// arvoksi tiedoston sijainti.
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		beer.setPhotos(fileName);

		
		//Käytetään FileUploadUtil luokan saveFile metodia kuvan tallentamiseen.
		//Databasen taulussa on vain kuvan nimi, oikea kuva on tiedostojärjetelmässä
		//koneella.
		
		String uploadDir = "user-photos/null";
		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

		repository.save(beer);

		return "redirect:beerlist";

	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String deletebeer(@PathVariable("id") Long beerId, Model model) {
		repository.deleteById(beerId);
		return "redirect:../beerlist";

	}

	@RequestMapping(value = "/editbeer/{id}")
	public String editbeer(@PathVariable("id") Long beerId, Model model, Beer beer) {
		model.addAttribute("beer", repository.findById(beerId));
		model.addAttribute("stores", krepository.findAll());
		

		return "editbeer";
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/logout")
	public String logout() {
		return "logout";
	}

}
