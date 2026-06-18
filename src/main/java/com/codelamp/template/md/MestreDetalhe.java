package com.codelamp.template.md;

import com.codelamp.template.crud.Entidade;
import com.codelamp.template.dominio.EntidadeReferencia;
import com.codelamp.template.dominio.IValidador;
import com.codelamp.template.dominio.ValidadorResultado;
import com.codelamp.template.dominio.campo.Campo;

public class MestreDetalhe extends Entidade implements IValidador {
	
	private EntidadeReferencia entidadeReferencia;
	
	private Campo campoMestre;
	
	public MestreDetalhe(String titulo, String classe) {
		super(titulo, classe);
	}
	
	public MestreDetalhe(String titulo, String classe, Campo campoMestre) {
		super(titulo, classe);
		this.campoMestre = campoMestre;
	}
	
	public static MestreDetalhe mestreDetalhe(String titulo) {
		return new MestreDetalhe(titulo, titulo);
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
	
	public MestreDetalhe mestre(Campo campoMestre) {
		this.campoMestre = campoMestre;
		return this;
	}
	
	public MestreDetalhe detalhe() {
		return this;
	}


	@Override
	public String getNomePasta() {
		return getClasse().replace(" ", "").toLowerCase();
	}
	
	public ValidadorResultado validar() {
		
		ValidadorResultado resultado = new ValidadorResultado();
		
		if (super.validar().isValido()) {
			if (!this.getModulo().getEntidades().containsKey(getCampoMestre().getTipo())) {
				resultado.addMensagem("A entidade [" + this.campoMestre.getTipo() +  "] nao existe.");
				resultado.setValido(false);
			}
		} else {
			resultado = super.validar();
		}
		
		return resultado;
	}
	
}
