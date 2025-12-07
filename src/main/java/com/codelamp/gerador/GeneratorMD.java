package com.codelamp.gerador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import com.codelamp.template.dominio.campo.Campo;
import com.codelamp.template.md.MestreDetalhe;

public class GeneratorMD extends Generator {

	public void gerarMasterDetail(VelocityContext context, MestreDetalhe mestre) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/md/masterdetail.vm");

		context.put("entidade", mestre);
		context.put("mestre", mestre);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = "c:/temp/codigofonte/frontend/" + mestre.getNomePasta() + "/";
		new File(path).mkdirs();
		
		writeToDisk(stWriter, path, mestre.getNomePasta() + ".jsp");

	}
	
	public void gerarForm(VelocityContext context, MestreDetalhe mestre) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/md/form.vm");

		context.put("entidade", mestre);
		context.put("mestre", mestre);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = "c:/temp/codigofonte/frontend/" + mestre.getNomePasta() + "/";
		new File(path).mkdirs();
		
		writeToDisk(stWriter, path, "form.jsp");

	}

	public void gerarEntidade(VelocityContext context, MestreDetalhe mestre) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/md/entidadeMD.vm");

		context.put("entidade", mestre);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = "c:/temp/codigofonte/backend/" + mestre.getNomePasta() + "/";
		new File(path).mkdirs();
		
		writeToDisk(stWriter, path, mestre.getClasse() + ".java");

	}

	public void gerarController(VelocityContext context, MestreDetalhe mestre) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/md/controllerMD.vm");

		context.put("entidade", mestre);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = "c:/temp/codigofonte/backend/" + mestre.getNomePasta() + "/";
		new File(path).mkdirs();

		writeToDisk(stWriter, path, mestre.getClasse() + "Controller.java");
		
	}

	public void gerarService(VelocityContext context, MestreDetalhe mestre) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/md/serviceMD.vm");

		context.put("entidade", mestre);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = "c:/temp/codigofonte/backend/" + mestre.getNomePasta() + "/";
		new File(path).mkdirs();
		
		writeToDisk(stWriter, path, mestre.getClasse() + "Service.java");

	}
	
	public void gerarRepository(VelocityContext context, MestreDetalhe mestre) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/md/repositoryMD.vm");

		context.put("entidade", mestre);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = "c:/temp/codigofonte/backend/" + mestre.getNomePasta() + "/";
		new File(path).mkdirs();
		
		writeToDisk(stWriter, path, mestre.getClasse() + "Repository.java");

	}

	public void gerarDominio(VelocityContext context, MestreDetalhe mestre) throws Exception, IOException {

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
