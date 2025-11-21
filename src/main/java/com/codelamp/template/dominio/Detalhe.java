package com.codelamp.template.dominio;

import java.util.ArrayList;
import java.util.List;

import com.codelamp.template.dominio.campo.Campo;

public class Detalhe {
	
	private List<Campo> campos = new ArrayList<>();
	
	public List<Campo> getCampos() {
		return campos;
	}

	public void adicionar(Campo campos) {
		this.campos.add(campos);
	}

}
