package com.codelamp.template.dominio;

public class CampoAreaTexto extends Campo {

	public CampoAreaTexto(String nome, String rotulo, int posicao) {
		super("String", nome.substring(0, 1).toLowerCase()+nome.substring(1), rotulo, posicao);
		this.setTipoView("textarea");
	}

}
