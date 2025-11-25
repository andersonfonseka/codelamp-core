package com.codelamp.template.lote;

import com.codelamp.template.crud.Entidade;
import com.codelamp.template.dominio.Filtro;

public class Lote extends Entidade {
	
	private String entidadeBase;
	
	private Filtro filtro = new Filtro();
	
	private LoteSaida saida = new LoteSaida();
	
	public Filtro getFiltro() {
		return filtro;
	}

	public void setFiltro(Filtro filtro) {
		this.filtro = filtro;
	}
	
	public String getEntidadeBase() {
		return entidadeBase;
	}

	public void setEntidadeBase(String entidadeBase) {
		this.entidadeBase = entidadeBase;
	}

	public LoteSaida getSaida() {
		saida.setClasse(super.getClasse()+"Saida");
		return saida;
	}

	public void setSaida(LoteSaida saida) {
		saida.setLote(this);
		this.saida = saida;
	}

	@Override
	public String getNomePasta() {
		return getClasse().replace(" ", "").toLowerCase();
	}
	
}
