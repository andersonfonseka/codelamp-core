package com.codelamp.template.dominio;

import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;

import com.codelamp.gerador.GeneratorCRUD;
import com.codelamp.gerador.GeneratorCore;
import com.codelamp.gerador.GeneratorLote;
import com.codelamp.gerador.GeneratorMD;
import com.codelamp.gerador.GeneratorWeb;
import com.codelamp.template.crud.Entidade;
import com.codelamp.template.lote.Lote;
import com.codelamp.template.md.MestreDetalhe;

public class Modulo {

	VelocityContext context = new VelocityContext();

	GeneratorWeb generatorWeb = new GeneratorWeb();
	GeneratorCore generatorCore = new GeneratorCore();
	GeneratorCRUD generatorCrud = new GeneratorCRUD();
	GeneratorMD generatorMD = new GeneratorMD();
	GeneratorLote generatorLote = new GeneratorLote();

	private Map<String, Entidade> entidades = new HashMap<>();

	private String titulo;

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

	public void gerar() {

		try {

			generatorWeb.gerarSideBar(context, this);
			generatorWeb.gerarIndex(context, this);
			generatorWeb.gerarLayout(context, this);
			
			generatorCore.gerarApplication(context, this);
			generatorCore.gerarViewLogging(context, this);
			generatorCore.gerarWebConfig(context, this);
			
			for (Entidade entidade : entidades.values()) {

				if (entidade.getClass().getName().equals("com.codelamp.template.crud.Entidade")) {

					generatorCrud.gerarEntidade(context, entidade);
					generatorCrud.gerarDominio(context, entidade);
					generatorCrud.gerarController(context, entidade);
					generatorCrud.gerarService(context, entidade);
					generatorCrud.gerarList(context, entidade);
					generatorCrud.gerarForm(context, entidade);
					generatorCrud.gerarFormEditar(context, entidade);

				} else if (entidade.getClass().getName().equals("com.codelamp.template.md.MestreDetalhe")) {

					generatorMD.gerarMasterDetail(context, (MestreDetalhe) entidade);
					generatorMD.gerarDominio(context, (MestreDetalhe) entidade);
					generatorMD.gerarEntidade(context, (MestreDetalhe) entidade);
					generatorMD.gerarController(context, (MestreDetalhe) entidade);
					generatorMD.gerarService(context, (MestreDetalhe) entidade);

				} else if (entidade.getClass().getName().equals("com.codelamp.template.lote.Lote")) {

					generatorLote.gerarLote(context, (Lote) entidade);
					generatorLote.gerarDominio(context, (Lote) entidade);
					generatorLote.gerarEntidade(context, (Lote) entidade);
					generatorLote.gerarController(context, (Lote) entidade);
					generatorLote.gerarService(context, (Lote) entidade);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
