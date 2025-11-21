package com.codelamp.template.dominio.campo;

public class CampoInteiro extends Campo {

	public CampoInteiro(String nome, String rotulo, int posicao) {
		super("int", nome.substring(0, 1).toLowerCase()+nome.substring(1), rotulo, posicao);
		this.setTipoView("number");
	}

}
