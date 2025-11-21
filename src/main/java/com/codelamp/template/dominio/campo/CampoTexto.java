package com.codelamp.template.dominio.campo;

public class CampoTexto extends Campo {

	public CampoTexto(String nome, String rotulo, int posicao) {
		super("String", nome.substring(0, 1).toLowerCase()+nome.substring(1), rotulo, posicao);
	}

}
