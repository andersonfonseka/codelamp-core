package com.codelamp.template.dominio.campo;

public class CampoAreaTexto extends Campo {

	public CampoAreaTexto(String nome, String rotulo, int posicao) {
		super("String", nome, rotulo, posicao);
		this.setTipoView("textarea");
	}

}
