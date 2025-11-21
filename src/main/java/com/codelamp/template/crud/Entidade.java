package com.codelamp.template.crud;

import java.util.ArrayList;
import java.util.List;

import com.codelamp.template.dominio.Acao;
import com.codelamp.template.dominio.EntidadeReferencia;
import com.codelamp.template.dominio.Modulo;
import com.codelamp.template.dominio.campo.Campo;
import com.codelamp.template.md.MestreDetalhe;

public class Entidade {
	
	private Modulo modulo;
	
	private String classe;
	
	private List<Campo> campos = new ArrayList<>();
	
	private List<Acao> acoes = new ArrayList<>();
	
	private String titulo;
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public List<Campo> getCampos() {
		return campos;
	}

	public void adicionar(Campo campos) {
		this.campos.add(campos);
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	
	public boolean isCampoPresente(String campoNome) {
		
		boolean isPresent = false;
		
		isPresent = this.campos.stream().filter(x -> x.getNome().equals(campoNome)).findFirst().isPresent();

		if(!isPresent && this instanceof MestreDetalhe) {
			MestreDetalhe mestre = (MestreDetalhe) this;
			isPresent = (mestre.getCampoMestre() != null);
		}
		return isPresent;
	}
	
	public void adicionarAcao(Acao acao) {
		acao.getMestre().setEntidadeReferencia(new EntidadeReferencia(this, acao.getCampo()));
		this.acoes.add(acao);
	}
	
	public List<Acao> getAcoes() {
		return acoes;
	}

	public String getNomePasta() {
		return this.classe.replace(" ", "").toLowerCase();
	}
	
	public boolean isMestre() {
		return this instanceof MestreDetalhe;
	}
	
}
