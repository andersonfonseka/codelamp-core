package com.codelamp.template.dominio;

import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;

import com.codelamp.gerador.GeneratorCRUD;
import com.codelamp.gerador.GeneratorKanban;
import com.codelamp.gerador.GeneratorKanbanMD;
import com.codelamp.gerador.GeneratorLote;
import com.codelamp.gerador.GeneratorMD;
import com.codelamp.template.crud.Entidade;
import com.codelamp.template.kanban.Kanban;
import com.codelamp.template.lote.Lote;
import com.codelamp.template.md.MestreDetalhe;

public class Modulo implements IValidador {

	VelocityContext context = new VelocityContext();

	private Map<String, Entidade> entidades = new HashMap<>();
	
	private Projeto projeto;

	private String titulo;

	GeneratorCRUD generatorCrud;
	GeneratorMD generatorMD;
	GeneratorLote generatorLote;
	GeneratorKanban generatorKanban;
	GeneratorKanbanMD generatorKanbanMD;
	
	public Modulo() {}
	
	public Modulo(String titulo) {
		super();
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void adicionar(Entidade entidade) {
		entidade.setModulo(this);
		this.entidades.put(entidade.getClasse(), entidade);
	}

	public Map<String, Entidade> getEntidades() {
		return entidades;
	}
	
	public Entidade get(String key) {
		
		Entidade ent = null;
		
		if (this.entidades.containsKey(key)) {
			ent = this.entidades.get(key);
		}
		
		return ent;
	}

	public void gerar() {
		
		ValidadorResultado resultado = this.validar();
		
		if (!resultado.isValido()) {
			System.out.println(resultado.getMensagem());
			return;
		}

		try {
			
			for (Entidade entidade : entidades.values()) {

				if (entidade.getClass().getName().equals("com.codelamp.template.crud.Entidade")) {
					
					ValidadorResultado entResultado = entidade.validar();
					
					if (!entResultado.isValido()) {
						System.out.println(entResultado.getMensagem());
					}

					generatorCrud.gerarEntidade(context, entidade);
					generatorCrud.gerarDominio(context, entidade);
					generatorCrud.gerarController(context, entidade);
					generatorCrud.gerarService(context, entidade);
					generatorCrud.gerarRepository(context, entidade);
					generatorCrud.gerarList(context, entidade);
					generatorCrud.gerarForm(context, entidade);
					generatorCrud.gerarFormEditar(context, entidade);

				} else if (entidade.getClass().getName().equals("com.codelamp.template.md.MestreDetalhe")) {
					
					MestreDetalhe md = (MestreDetalhe) entidade;
					
					ValidadorResultado mdResultado = md.validar();
					
					if (!mdResultado.isValido()) {
						System.out.println(mdResultado.getMensagem());
					}

					generatorMD.gerarMasterDetail(context, md);
					generatorMD.gerarForm(context, md);
					generatorMD.gerarFormEditar(context, md);
					generatorMD.gerarDominio(context, md);
					generatorMD.gerarEntidade(context, md);
					generatorMD.gerarController(context, md);
					generatorMD.gerarService(context, md);
					generatorMD.gerarRepository(context, md);

				} else if (entidade.getClass().getName().equals("com.codelamp.template.lote.Lote")) {

					generatorLote.gerarLote(context, (Lote) entidade);
					generatorLote.gerarDominio(context, (Lote) entidade);
					generatorLote.gerarEntidade(context, (Lote) entidade);
					generatorLote.gerarEntidadeSaida(context, (Lote) entidade);
					generatorLote.gerarController(context, (Lote) entidade);
					generatorLote.gerarService(context, (Lote) entidade);
					generatorLote.gerarRepository(context, (Lote) entidade);
				
				} else if (entidade.getClass().getName().equals("com.codelamp.template.kanban.Kanban")) {

					Kanban kb = (Kanban) entidade;
					
					ValidadorResultado kbResultado = kb.validar();
					
					if (!kbResultado.isValido()) {
						System.out.println(kbResultado.getMensagem());
					}

					
					if (kb.isMenuVisivel()) {
						generatorKanban.gerarKanban(context, kb);
						generatorKanban.gerarController(context, kb);
						generatorKanban.gerarService(context, kb);
					} else {
						generatorKanbanMD.gerarKanban(context, kb);
						generatorKanbanMD.gerarController(context, kb);
						generatorKanbanMD.gerarService(context, kb);
					}
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	
		this.generatorCrud = new GeneratorCRUD(projeto);
		this.generatorKanban = new GeneratorKanban(projeto);
		this.generatorKanbanMD = new GeneratorKanbanMD(projeto);
		this.generatorLote = new GeneratorLote(projeto);
		this.generatorMD = new GeneratorMD(projeto);

	}

	@Override
	public ValidadorResultado validar() {
		
		ValidadorResultado resultado = new ValidadorResultado();
		
		if (this.entidades.isEmpty()) {
			resultado.addMensagem("O modulo deve ter ao menos uma entidade");
			resultado.setValido(false);
		}
		
		return resultado;
	}
	
}
