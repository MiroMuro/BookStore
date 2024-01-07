package com.example.Bookstore.web;

import java.io.IOException;
import java.io.*;
import java.nio.file.*;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	//Tämä luokka vastaa tiedostohakemiston luomisesta, mikäli sitä ei ole, sekä
	//kuvan kopioinnista sinne.
	public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {		
		Path uploadPath = Paths.get(uploadDir);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			//Tiedostosijainnin lukeminen parametristä.
			Path filePath = uploadPath.resolve(fileName);
			//Kopioidaan tiedosto.
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save image file: " + fileName, ioe);

		}
	}
}
