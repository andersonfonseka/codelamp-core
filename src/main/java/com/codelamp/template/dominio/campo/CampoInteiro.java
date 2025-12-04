package com.codelamp.template.dominio.campo;

public class CampoInteiro extends Campo {

	public CampoInteiro(String nome, String rotulo, int posicao) {
		super("int", nome, rotulo, posicao);
		this.setTipoView("number");
	}

}
