package com.codelamp.template.kanban;

import com.codelamp.template.dominio.IValidador;
import com.codelamp.template.dominio.ValidadorResultado;
import com.codelamp.template.dominio.campo.Campo;
import com.codelamp.template.dominio.campo.CampoEnum;
import com.codelamp.template.dominio.campo.CampoReferencia;
import com.codelamp.template.md.MestreDetalhe;

public class Kanban extends MestreDetalhe implements IValidador {

	private MestreDetalhe mestreDetalhe;
	
	private CampoReferencia campoReferencia;
	
	private CampoEnum campoStatus;
	
	private Campo campoMestre;
	
	private String[] atributos;
	
	public Kanban(String titulo, String classe) {
		super(titulo, classe);
	}
	
	public Kanban(String titulo, String classe, Campo campoMestre) {
		super(titulo, classe);
		this.campoMestre = campoMestre;
	}

	public CampoReferencia getCampoReferencia() {
		return campoReferencia;
	}
	
	public void setCampoReferencia(CampoReferencia campoReferencia) {
		this.campoReferencia = campoReferencia;
		super.adicionar(campoReferencia);
	}

	public void setCampoStatus(CampoEnum campoStatus) {
		this.campoStatus = campoStatus;
		super.adicionar(campoStatus);
	}

	public CampoEnum getCampoStatus() {
		return campoStatus;
	}

	public String[] getAtributos() {
		return atributos;
	}

	public void setAtributos(String[] atributos) {
		this.atributos = atributos;
	}

	public Campo getCampoMestre() {
		return campoMestre;
	}
	
	public ValidadorResultado validar() {
		
		ValidadorResultado resultado = new ValidadorResultado();
		
		if (super.validar().isValido()) {
			
			if (null == this.getEntidadeReferencia()) {
				resultado.addMensagem("Eh necessario informar uma entidade de Referencia.");
				resultado.setValido(false);
			}
			
			if (null == this.getCampoStatus()) {
				resultado.addMensagem("Eh necessario informar um Campo de Status.");
				resultado.setValido(false);
			}
			
			if (null == this.getAtributos()) {
				resultado.addMensagem("Eh necessario informar os Atributos.");
				resultado.setValido(false);
			}

			
		} else {
			resultado = super.validar();
		}
		
		return resultado;
	}

}
