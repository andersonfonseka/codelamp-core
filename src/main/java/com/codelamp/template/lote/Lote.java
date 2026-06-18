package com.codelamp.template.lote;

import com.codelamp.template.dominio.IValidador;
import com.codelamp.template.dominio.ValidadorResultado;
import com.codelamp.template.dominio.campo.Campo;
import com.codelamp.template.md.MestreDetalhe;

public class Lote extends MestreDetalhe implements IValidador {
	
	private Campo campoRotulo;
	
	private Campo campoValor;

	public Lote(String titulo, String classe, Campo campoMestre) {
		super(titulo, classe, campoMestre);
	}
	
	public Campo getCampoRotulo() {
		return campoRotulo;
	}

	public void setCampoRotulo(Campo campoRotulo) {
		this.campoRotulo = campoRotulo;
		this.adicionar(campoRotulo);
	}

	public Campo getCampoValor() {
		return campoValor;
	}

	public void setCampoValor(Campo campoValor) {
		this.campoValor = campoValor;
		this.adicionar(campoValor);
	}

	public ValidadorResultado validar() {
		
		ValidadorResultado resultado = new ValidadorResultado();
		
		if (super.validar().isValido()) {
			
			if (null == this.getCampoRotulo()) {
				resultado.addMensagem("Eh necessario informar o Campo Rotulo.");
				resultado.setValido(false);
			}
			
			if (null == this.getCampoValor()) {
				resultado.addMensagem("Eh necessario informar o Campo Valor.");
				resultado.setValido(false);
			}

			
		} else {
			resultado = super.validar();
		}
		
		return resultado;
	}

	
}
