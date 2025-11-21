package com.codelamp.template.dominio;

public class CampoImagem extends Campo {

	public CampoImagem(String nome, String rotulo, int posicao) {
		super("String", nome.substring(0, 1).toLowerCase()+nome.substring(1), rotulo, posicao);
		this.setTipoView("img");
	}

}
