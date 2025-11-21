package com.codelamp.template.dominio;

import com.codelamp.template.crud.Entidade;

public class EntidadeReferencia {
	
	private Entidade entidade;
	
	private String campo;

	public EntidadeReferencia(Entidade entidade, String campo) {
		super();
		this.entidade = entidade;
		this.campo = campo;
	}

	public String getClasse() {
		return this.entidade.getClasse();
	}

	public String getCampo() {
		return campo;
	}

	public Entidade getEntidade() {
		return entidade;
	}
	
}
