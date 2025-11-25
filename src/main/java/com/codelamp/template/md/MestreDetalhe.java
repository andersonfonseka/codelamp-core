package com.codelamp.template.md;

import com.codelamp.template.crud.Entidade;
import com.codelamp.template.dominio.EntidadeReferencia;
import com.codelamp.template.dominio.campo.Campo;

public class MestreDetalhe extends Entidade {
	
	private EntidadeReferencia entidadeReferencia;
	
	private Campo campoMestre;
	
	public EntidadeReferencia getEntidadeReferencia() {
		return entidadeReferencia;
	}

	public void setEntidadeReferencia(EntidadeReferencia entidadeReferencia) {
		this.entidadeReferencia = entidadeReferencia;
	}

	public Campo getCampoMestre() {
		return campoMestre;
	}

	public void setCampoMestre(Campo campoMestre) {
		this.campoMestre = campoMestre;
	}

	@Override
	public String getNomePasta() {
		return getClasse().replace(" ", "").toLowerCase();
	}
	
}
