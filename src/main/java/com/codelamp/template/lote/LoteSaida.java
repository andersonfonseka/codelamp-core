package com.codelamp.template.lote;

import com.codelamp.template.crud.Entidade;

public class LoteSaida extends Entidade {
	
	private Lote lote;

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}
	
}
