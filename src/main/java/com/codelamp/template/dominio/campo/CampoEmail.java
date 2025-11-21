package com.codelamp.template.dominio.campo;

public class CampoEmail extends CampoTexto {

	public CampoEmail(String nome, String rotulo, int posicao) {
		super(nome, rotulo, posicao);
		this.setTipoView("email");
	}

}
