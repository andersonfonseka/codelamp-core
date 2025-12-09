package com.codelamp.template.dominio;

import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.VelocityContext;

import com.codelamp.gerador.GeneratorCore;
import com.codelamp.gerador.GeneratorWeb;

public class Projeto {

	VelocityContext context = new VelocityContext();

	private String titulo;

	GeneratorWeb generatorWeb;
	GeneratorCore generatorCore;

	private List<Modulo> modulos = new ArrayList<>();

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Modulo> getModulos() {
		return modulos;
	}

	public void addModulos(Modulo modulo) {
		modulo.setProjeto(this);
		this.modulos.add(modulo);
	}

	public void gerar() {

		try {

			this.generatorWeb = new GeneratorWeb(this);
			
			generatorWeb.gerarSideBar(context, this);
			generatorWeb.gerarIndex(context, this);
			generatorWeb.gerarLayout(context, this);

			this.generatorCore = new GeneratorCore(this);
			
			generatorCore.gerarApplication(context, this);
			generatorCore.gerarViewLogging(context, this);
			generatorCore.gerarWebConfig(context, this);

			for (Modulo modulo : modulos) {
				modulo.gerar();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String getNomePasta() {
		return this.titulo.replace(" ", "").toLowerCase();
	}

}
