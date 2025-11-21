package com.codelamp.gerador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import com.codelamp.template.dominio.campo.Campo;
import com.codelamp.template.lote.Lote;
import com.codelamp.template.lote.LoteSaida;

public class GeneratorLote extends GeneratorCRUD {

	public void gerarLote(VelocityContext context, Lote lote) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/lote/lote.vm");

		context.put("entidade", lote);
		context.put("lote", lote);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = "c:/temp/codigofonte/frontend/" + lote.getNomePasta() + "/";
		new File(path).mkdirs();

		FileWriter fw = new FileWriter(new File(path + lote.getNomePasta() + ".jsp"));
		fw.write(stWriter.toString().replace("*", "$"));
		fw.close();

	}

	public void gerarEntidade(VelocityContext context, Lote lote) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/lote/entidadeLote.vm");

		context.put("entidade", lote);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = "c:/temp/codigofonte/backend/" + lote.getNomePasta() + "/";
		new File(path).mkdirs();

		FileWriter fw = new FileWriter(new File(path + lote.getClasse() + ".java"));
		fw.write(stWriter.toString());
		fw.close();
		
		gerarEntidade(context, lote, lote.getSaida());
	
	}
	
	public void gerarEntidade(VelocityContext context, Lote lote, LoteSaida loteSaida) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/lote/loteSaida.vm");

		context.put("entidadeBase", lote.getModulo().getEntidades().get(lote.getEntidadeBase()));
		context.put("entidade", lote);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = "c:/temp/codigofonte/backend/" + lote.getNomePasta() + "/";
		new File(path).mkdirs();

		FileWriter fw = new FileWriter(new File(path + loteSaida.getClasse() + ".java"));
		fw.write(stWriter.toString());
		fw.close();
	}


	public void gerarController(VelocityContext context, Lote lote) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/lote/controllerLote.vm");

		context.put("entidade", lote);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = "c:/temp/codigofonte/backend/" + lote.getNomePasta() + "/";
		new File(path).mkdirs();

		FileWriter fw = new FileWriter(new File(path + lote.getClasse() + "Controller.java"));
		fw.write(stWriter.toString());
		fw.close();

	}

	public void gerarService(VelocityContext context, Lote lote) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/lote/loteService.vm");

		context.put("entidade", lote);
		context.put("entidadeBase", lote.getModulo().getEntidades().get(lote.getEntidadeBase()));

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = "c:/temp/codigofonte/backend/" + lote.getNomePasta() + "/";
		new File(path).mkdirs();

		FileWriter fw = new FileWriter(new File(path + lote.getClasse() + "Service.java"));
		fw.write(stWriter.toString());
		fw.close();

	}

	public void gerarDominio(VelocityContext context, Lote lote) throws Exception, IOException {

		for (Campo campo : lote.getSaida().getCampos()) {

			if (campo.getTipo().equals("Enum")) {

				Template template = getEngine().getTemplate("template/dominio.vm");

				context.put("campo", campo);

				StringWriter stWriter = new StringWriter();
				template.merge(context, stWriter);

				String path = "c:/temp/codigofonte/backend/" + lote.getNomePasta() + "/";
				new File(path).mkdirs();

				FileWriter fw = new FileWriter(new File(path + "Dominio" + campo.getNome() + ".java"));
				fw.write(stWriter.toString());
				fw.close();

			}
		}

	}

}
