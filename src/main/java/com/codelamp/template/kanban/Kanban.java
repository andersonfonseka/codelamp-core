package com.codelamp.template.kanban;

import com.codelamp.template.dominio.campo.Campo;
import com.codelamp.template.dominio.campo.CampoEnum;
import com.codelamp.template.dominio.campo.CampoReferencia;
import com.codelamp.template.md.MestreDetalhe;

public class Kanban extends MestreDetalhe {

	private MestreDetalhe mestreDetalhe;

	private CampoReferencia campoReferencia;
	
	private CampoEnum campoStatus;
	
	private String[] atributos;

	public MestreDetalhe getMestreDetalhe() {
		return mestreDetalhe;
	}

	public void setMestreDetalhe(MestreDetalhe mestreDetalhe) {
		this.mestreDetalhe = mestreDetalhe;
		
		for(Campo campo: this.mestreDetalhe.getCampos()) {
			if (campo instanceof CampoReferencia) {
				this.campoReferencia = (CampoReferencia) campo;
			} else if (campo instanceof CampoEnum) {
				this.campoStatus = (CampoEnum) campo;
			}
		}
	}
	
	public CampoReferencia getCampoReferencia() {
		return campoReferencia;
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
