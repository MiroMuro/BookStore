package com.example.Bookstore.web;

import java.nio.file.Paths;
import java.nio.file.Path;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Luokka kuvien näyttämiseen selaimessa. Selaimelle pitää näyttää
//hakemisto missä kuvat sijaitsevat.
@Configuration
public class MvcConfig  implements WebMvcConfigurer{
	
	@Override
	//Resurssin käsittelijä joka saa pääsyn kuvahakemistoon.
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		exposeDirectory("user-photos",registry);
	}
	
	private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
		//Luodaan uusi Path tyyppiä oleva muuttuja ja annetaan sille
		//kuvatiedoston sijainti.
		Path uploadDir = Paths.get(dirName);
		String uploadPath = uploadDir.toFile().getAbsolutePath();
		//Siistitään polku helpommin luettavaksi.
		if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
		registry.addResourceHandler("/"+dirName+"/**").addResourceLocations("file:/"+uploadPath+"/");
		
	}
}
