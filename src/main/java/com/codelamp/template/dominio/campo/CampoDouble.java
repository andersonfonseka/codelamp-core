package com.codelamp.template.dominio.campo;

public class CampoDouble extends Campo {

	public CampoDouble(String nome, String rotulo, int posicao) {
		super("double", nome.substring(0, 1).toLowerCase()+nome.substring(1), rotulo, posicao);
		this.setTipoView("number");
	}

}
