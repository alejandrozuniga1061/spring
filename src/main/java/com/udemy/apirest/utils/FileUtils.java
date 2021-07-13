package com.udemy.apirest.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import com.udemy.apirest.utils.Constantes.Multimedia;

public class FileUtils {
	public FileUtils() {
		
	}
	
	public static Path obtenerRutaArchivo(String url, String nombreArchivo) {
		return Paths.get(url).resolve(nombreArchivo).toAbsolutePath();
	}
	
	public static String construirNombreArchivo(String nombreArchivo) {
		return UUID.randomUUID().toString()+"_"+nombreArchivo.replace(" ", "");
	}
	
	public static String guardarArchivo(MultipartFile archivo, String url) throws IOException{
		String nombreArchivo = construirNombreArchivo(archivo.getOriginalFilename());
		Path rutaArchivo = obtenerRutaArchivo(url, nombreArchivo);
		Files.copy(archivo.getInputStream(), rutaArchivo);
		return nombreArchivo;
	}
	
	public static boolean eliminarArchivo(String nombreArchivo, String url) {
		Path rutaArchivo = obtenerRutaArchivo(url, nombreArchivo);
		File archivo = rutaArchivo.toFile();
		if(archivo.exists() && archivo.canRead()) {
			return archivo.delete();
		}
		return false;
	}
	
	public static Resource obtenerArchivo(String nombreArchivo, String url) throws MalformedURLException {
		Path ruta = obtenerRutaArchivo(url, nombreArchivo);
		return new UrlResource(ruta.toUri()); 
	}
}
