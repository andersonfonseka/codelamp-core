package com.codelamp.template.crud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codelamp.template.dominio.Acao;
import com.codelamp.template.dominio.EntidadeReferencia;
import com.codelamp.template.dominio.Modulo;
import com.codelamp.template.dominio.campo.Campo;
import com.codelamp.template.kanban.Kanban;
import com.codelamp.template.md.MestreDetalhe;

public class Entidade {
	
	private Modulo modulo;
	
	private String classe;
	
	private List<Campo> campos = new ArrayList<Campo>();
	
	private Map<String, Campo> camposHash = new HashMap<String, Campo>();
	
	private List<Acao> acoes = new ArrayList<>();
	
	private String titulo;
	
	private boolean isMenuVisivel = false;
	
	public Entidade(String titulo, String classe) {
		super();
		this.classe = classe;
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getClasse() {
		return classe;
	}

	public List<Campo> getCampos() {
		return this.campos;
	}
	
	public Campo getCampo(String nome) {
		
		Campo campo = null;
		
		if (this.camposHash.containsKey(nome)) {
			campo = this.camposHash.get(nome);
		}
		
		return campo;
	}

	public void adicionar(Campo campos) {
		this.campos.add(campos);
		this.camposHash.put(campos.getNome(), campos);
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
	
	public void adicionarAcaoKanban(Acao acao) {
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
	
	public boolean isKanban() {
		return this instanceof Kanban;
	}
	
	public boolean isMenuVisivel() {
		return isMenuVisivel;
	}

	public void setMenuVisivel(boolean isMenuVisivel) {
		this.isMenuVisivel = isMenuVisivel;
	}
	
}
