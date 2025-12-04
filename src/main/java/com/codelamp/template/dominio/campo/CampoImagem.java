package com.codelamp.template.dominio.campo;

public class CampoImagem extends Campo {

	public CampoImagem(String nome, String rotulo, int posicao) {
		super("String", nome, rotulo, posicao);
		this.setTipoView("img");
	}

}
