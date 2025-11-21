package com.codelamp.template.dominio;

import java.util.ArrayList;
import java.util.List;

public class Projeto {
	
	private String titulo;
	
	private List<Modulo> modulos = new ArrayList<>();

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}
	
	

}
