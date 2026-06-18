package com.codelamp.template.dominio;

import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.VelocityContext;

import com.codelamp.gerador.GeneratorCore;
import com.codelamp.gerador.GeneratorWeb;

public class Projeto implements IValidador {

	VelocityContext context = new VelocityContext();

	private String titulo;

	GeneratorWeb generatorWeb;
	GeneratorCore generatorCore;

	private List<Modulo> modulos = new ArrayList<>();
	
	public Projeto() {}
	
	public Projeto(String titulo) {
		super();
		this.titulo = titulo;
	}

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
		
		ValidadorResultado resultado = this.validar();
		
		if (!resultado.isValido()) {
			System.out.println(resultado.getMensagem());
			return;
		}
		
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

	@Override
	public ValidadorResultado validar() {

		ValidadorResultado resultado = new ValidadorResultado();
		
		if (this.modulos.isEmpty()) {
			resultado.addMensagem("O projeto deve ter ao menos um modulo");
			resultado.setValido(false);
		}
		
		return resultado;
	}
	
}
