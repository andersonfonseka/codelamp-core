package com.codelamp.template.lote;

import com.codelamp.template.dominio.campo.Campo;
import com.codelamp.template.md.MestreDetalhe;

public class Lote extends MestreDetalhe {
	
	private Campo campoRotulo;
	
	private Campo campoValor;

	public Campo getCampoRotulo() {
		return campoRotulo;
	}

	public void setCampoRotulo(Campo campoRotulo) {
		this.campoRotulo = campoRotulo;
	}

	public Campo getCampoValor() {
		return campoValor;
	}

	public void setCampoValor(Campo campoValor) {
		this.campoValor = campoValor;
	}
	
}
