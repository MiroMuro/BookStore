package com.example.Bookstore.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="kauppa")
public class Kauppa {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@JsonIgnore
	//Kerrotaan databaselle että kaupalla on yhden suhde moneen
	// yhteys olut-tauluun.
	@OneToMany(cascade = CascadeType.ALL,mappedBy="kauppa")
	private List<Beer> beers;
	
	public Kauppa(){}
	public Kauppa(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Beer> getBeers() {
		return beers;
	}

	public void setBeers(List<Beer> beers) {
		this.beers = beers;
	}
	@Override
	public int hashCode() {
		return Objects.hash(beers, id, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kauppa other = (Kauppa) obj;
		return Objects.equals(beers, other.beers) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

}
