package com.codelamp.template.dominio;

public class CampoURL extends CampoTexto {

	public CampoURL(String nome, String rotulo, int posicao) {
		super(nome, rotulo, posicao);
		this.setTipoView("url");
	}

}
