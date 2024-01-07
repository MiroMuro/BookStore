package com.example.Bookstore.domain;

import java.awt.image.BufferedImage;
import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.Bookstore.domain.Kauppa;

@Entity
@Table(name="beer")
public class Beer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private double hinta;
	private String maku;
	private String koko;
	@Column(nullable = true, length = 64)
	private String photos;
	
	//Yhtä olutta voi olla monessa eri kaupassa. Liitetään taulut
	// (kauppa ja olut) kaupan id:llä.
	@ManyToOne
	@JoinColumn(name = "kauppaid")
	private Kauppa kauppa;
	
	
// private String author;
	public Beer(String name, double hinta, String maku, String koko, Kauppa kauppa, String photos) {
		super();
		this.name = name;
		this.hinta = hinta;
		this.maku = maku;
		this.koko = koko;
		this.kauppa = kauppa;
		this.photos = photos;
	}
	@Transient
	//Luodaan uusi getteri, jolla kuva saadaan näkyviin html:llä.
	public String getPhotosImagePath() {
		if (photos == null || id == null) return null;
		
		return "user-photos/"+photos;
	}
	public Beer() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Kauppa getKauppa() {
		return kauppa;
	}

	public void setKauppa(Kauppa kauppa) {
		this.kauppa = kauppa;
	}

	public double getHinta() {
		return hinta;
	}

	public void setHinta(double hinta) {
		this.hinta = hinta;
	}

	public String getMaku() {
		return maku;
	}

	public void setMaku(String maku) {
		this.maku = maku;
	}

	public String getKoko() {
		return koko;
	}

	public void setKoko(String koko) {
		this.koko = koko;
	}

	
	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}
	

}