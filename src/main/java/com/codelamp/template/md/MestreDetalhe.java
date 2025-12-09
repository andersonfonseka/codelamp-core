package com.codelamp.template.md;

import com.codelamp.template.crud.Entidade;
import com.codelamp.template.dominio.EntidadeReferencia;
import com.codelamp.template.dominio.campo.Campo;

public class MestreDetalhe extends Entidade {
	
	private EntidadeReferencia entidadeReferencia;
	
	private Campo campoMestre;
	
	public MestreDetalhe(String titulo, String classe) {
		super(titulo, classe);
	}
	
	public MestreDetalhe(String titulo, String classe, Campo campoMestre) {
		super(titulo, classe);
		this.campoMestre = campoMestre;
	}
	
	public EntidadeReferencia getEntidadeReferencia() {
		return entidadeReferencia;
	}

	public void setEntidadeReferencia(EntidadeReferencia entidadeReferencia) {
		this.entidadeReferencia = entidadeReferencia;
	}

	public Campo getCampoMestre() {
		return campoMestre;
	}

	@Override
	public String getNomePasta() {
		return getClasse().replace(" ", "").toLowerCase();
	}
	
}
