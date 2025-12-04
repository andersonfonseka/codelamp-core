package com.codelamp.template.dominio.campo;

public class CampoDouble extends Campo {

	public CampoDouble(String nome, String rotulo, int posicao) {
		super("double", nome, rotulo, posicao);
		this.setTipoView("number");
	}

}
