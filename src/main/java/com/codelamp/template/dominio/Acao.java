package com.codelamp.template.dominio;

import com.codelamp.template.crud.Entidade;
import com.codelamp.template.md.MestreDetalhe;

public class Acao {

	private MestreDetalhe mestre;

	private String titulo;
	
	private String campo;

	public Acao(MestreDetalhe mestre, String titulo, String campo) {
		super();
		this.mestre = mestre;
		this.titulo = titulo;
		this.campo = campo;
	}

	public MestreDetalhe getMestre() {
		return mestre;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getCampo() {
		return campo;
	}

}
