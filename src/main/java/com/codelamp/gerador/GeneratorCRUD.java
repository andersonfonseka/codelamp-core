package com.codelamp.gerador;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import com.codelamp.template.crud.Entidade;
import com.codelamp.template.dominio.Projeto;
import com.codelamp.template.dominio.campo.Campo;

public class GeneratorCRUD extends Generator {

	private String path = "c:/temp/codigofonte/" + getProjeto().getNomePasta() + "/frontend/";
	private String pathBknd = "c:/temp/codigofonte/" + getProjeto().getNomePasta() + "/backend/";
	
	public GeneratorCRUD(Projeto projeto) {
		super(projeto);
	}

	public void gerarEntidade(VelocityContext context, Entidade entidade) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/crud/entidade.vm");

		context.put("entidade", entidade);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = this.pathBknd + entidade.getNomePasta() + "/";
		new File(path).mkdirs();

		writeToDisk(stWriter, path, entidade.getClasse() + ".java");

	}

	public void gerarDominio(VelocityContext context, Entidade entidade) throws Exception, IOException {

		for (Campo campo : entidade.getCampos()) {

			if (campo.getTipo().equals("Enum")) {

				Template template = getEngine().getTemplate("template/dominio.vm");

				context.put("campo", campo);

				StringWriter stWriter = new StringWriter();
				template.merge(context, stWriter);

				String path = this.pathBknd + entidade.getNomePasta() + "/";
				new File(path).mkdirs();

				writeToDisk(stWriter, path, "Dominio" + campo.getNome() + ".java");

			}
		}

	}

	public void gerarController(VelocityContext context, Entidade entidade) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/crud/controller.vm");

		context.put("entidade", entidade);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = this.pathBknd + entidade.getNomePasta() + "/";
		new File(path).mkdirs();

		writeToDisk(stWriter, path, entidade.getClasse() + "Controller.java");

	}

	public void gerarService(VelocityContext context, Entidade entidade) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/crud/service.vm");

		context.put("entidade", entidade);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = this.pathBknd + entidade.getNomePasta() + "/";
		new File(path).mkdirs();
		
		writeToDisk(stWriter, path, entidade.getClasse() + "Service.java");

	}
	
	public void gerarRepository(VelocityContext context, Entidade entidade) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/crud/repository.vm");

		context.put("entidade", entidade);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = this.pathBknd + entidade.getNomePasta() + "/";
		new File(path).mkdirs();
		
		writeToDisk(stWriter, path, entidade.getClasse() + "Repository.java");

	}

	public void gerarList(VelocityContext context, Entidade entidade) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/crud/list.vm");

		context.put("entidade", entidade);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = this.path + entidade.getNomePasta() + "/";
		new File(path).mkdirs();

		writeToDisk(stWriter, path, "list.jsp");
	}

	public void gerarForm(VelocityContext context, Entidade entidade) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/crud/form.vm");

		context.put("entidade", entidade);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = this.path + entidade.getNomePasta() + "/";
		new File(path).mkdirs();

		writeToDisk(stWriter, path, "form.jsp");
	}

	public void gerarFormEditar(VelocityContext context, Entidade entidade) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/crud/formeditar.vm");

		context.put("entidade", entidade);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = this.path + entidade.getNomePasta() + "/";
		new File(path).mkdirs();

		writeToDisk(stWriter, path, "formeditar.jsp");
	}
	
}
