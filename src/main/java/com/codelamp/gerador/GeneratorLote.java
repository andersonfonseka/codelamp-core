package com.codelamp.gerador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import com.codelamp.template.dominio.campo.Campo;
import com.codelamp.template.lote.Lote;

public class GeneratorLote extends GeneratorCRUD {

	public void gerarLote(VelocityContext context, Lote mestre) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/lote/lote.vm");

		context.put("entidade", mestre);
		context.put("mestre", mestre);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = "c:/temp/codigofonte/frontend/" + mestre.getNomePasta() + "/";
		new File(path).mkdirs();
		
		writeToDisk(stWriter, path, mestre.getNomePasta() + ".jsp");

	}

	public void gerarEntidade(VelocityContext context, Lote mestre) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/lote/entidadeLote.vm");

		context.put("entidade", mestre);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = "c:/temp/codigofonte/backend/" + mestre.getNomePasta() + "/";
		new File(path).mkdirs();
		
		writeToDisk(stWriter, path, mestre.getClasse() + ".java");

	}
	
	
	public void gerarEntidadeSaida(VelocityContext context, Lote mestre) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/lote/entidadeLoteSaida.vm");

		context.put("entidade", mestre);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = "c:/temp/codigofonte/backend/" + mestre.getNomePasta() + "/";
		new File(path).mkdirs();
		
		writeToDisk(stWriter, path, mestre.getClasse() + "Saida.java");

	}

	public void gerarController(VelocityContext context, Lote mestre) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/lote/controllerLote.vm");

		context.put("entidade", mestre);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = "c:/temp/codigofonte/backend/" + mestre.getNomePasta() + "/";
		new File(path).mkdirs();

		writeToDisk(stWriter, path, mestre.getClasse() + "Controller.java");
		
	}

	public void gerarService(VelocityContext context, Lote mestre) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/lote/serviceLote.vm");

		context.put("entidade", mestre);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = "c:/temp/codigofonte/backend/" + mestre.getNomePasta() + "/";
		new File(path).mkdirs();
		
		writeToDisk(stWriter, path, mestre.getClasse() + "Service.java");

	}
	
	public void gerarRepository(VelocityContext context, Lote mestre) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/lote/repositoryLote.vm");

		context.put("entidade", mestre);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = "c:/temp/codigofonte/backend/" + mestre.getNomePasta() + "/";
		new File(path).mkdirs();
		
		writeToDisk(stWriter, path, mestre.getClasse() + "Repository.java");

	}

	public void gerarDominio(VelocityContext context, Lote mestre) throws Exception, IOException {

		for (Campo campo : mestre.getCampos()) {

			if (campo.getTipo().equals("Enum")) {

				Template template = getEngine().getTemplate("template/dominio.vm");

				context.put("campo", campo);

				StringWriter stWriter = new StringWriter();
				template.merge(context, stWriter);

				String path = "c:/temp/codigofonte/backend/" + mestre.getNomePasta() + "/";
				new File(path).mkdirs();

				FileWriter fw = new FileWriter(new File(path + "Dominio" + campo.getNome() + ".java"));
				fw.write(stWriter.toString());
				fw.close();

			}
		}

	}

}
