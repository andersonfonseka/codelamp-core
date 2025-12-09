package com.codelamp.template.kanban;

import com.codelamp.template.dominio.campo.Campo;
import com.codelamp.template.dominio.campo.CampoEnum;
import com.codelamp.template.dominio.campo.CampoReferencia;
import com.codelamp.template.md.MestreDetalhe;

public class Kanban extends MestreDetalhe {

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
	}

	public void setCampoStatus(CampoEnum campoStatus) {
		this.campoStatus = campoStatus;
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
	
}
