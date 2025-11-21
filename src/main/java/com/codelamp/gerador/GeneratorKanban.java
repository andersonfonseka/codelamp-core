package com.codelamp.gerador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import com.codelamp.template.kanban.Kanban;
import com.codelamp.template.md.MestreDetalhe;

public class GeneratorKanban extends Generator {

	public void gerarKanban(VelocityContext context, Kanban kanban) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/kanban/kanban.vm");

		context.put("entidade", kanban);
		context.put("mestre", kanban);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = "c:/temp/codigofonte/frontend/" + kanban.getNomePasta() + "/";
		new File(path).mkdirs();

		FileWriter fw = new FileWriter(new File(path + kanban.getNomePasta() + ".jsp"));
		fw.write(stWriter.toString().replace("*", "$"));
		fw.close();

	}

	public void gerarController(VelocityContext context, Kanban kanban) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/kanban/controllerKanban.vm");

		context.put("entidade", kanban);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = "c:/temp/codigofonte/backend/" + kanban.getNomePasta() + "/";
		new File(path).mkdirs();

		FileWriter fw = new FileWriter(new File(path + kanban.getClasse() + "Controller.java"));
		fw.write(stWriter.toString());
		fw.close();

	}
	
	public void gerarService(VelocityContext context, Kanban kanban) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/kanban/serviceKanban.vm");

		context.put("entidade", kanban);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = "c:/temp/codigofonte/backend/" + kanban.getNomePasta() + "/";
		new File(path).mkdirs();

		FileWriter fw = new FileWriter(new File(path + kanban.getClasse() + "Service.java"));
		fw.write(stWriter.toString());
		fw.close();

	}

	

}
