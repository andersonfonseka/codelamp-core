package com.codelamp.template.dominio.campo;

public class CampoDate extends Campo {

	public CampoDate(String nome, String rotulo, int posicao) {
		super("Date", nome.substring(0, 1).toLowerCase()+nome.substring(1), rotulo, posicao);
	}

}
