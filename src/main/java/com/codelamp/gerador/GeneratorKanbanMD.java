package com.codelamp.gerador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import com.codelamp.template.dominio.Projeto;
import com.codelamp.template.kanban.Kanban;

public class GeneratorKanbanMD extends Generator {
	
	private String path = "c:/temp/codigofonte/" + getProjeto().getNomePasta() + "/frontend/";
	private String pathBknd = "c:/temp/codigofonte/" + getProjeto().getNomePasta() + "/backend/";

	
	public GeneratorKanbanMD(Projeto projeto) {
		super(projeto);
		// TODO Auto-generated constructor stub
	}

	public void gerarKanban(VelocityContext context, Kanban kanban) throws IOException {

		Template template = getEngine().getTemplate("template/kanban/md/kanban.vm");

		context.put("entidade", kanban);
		context.put("mestre", kanban);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = this.path + kanban.getNomePasta() + "/";
		new File(path).mkdirs();

		FileWriter fw = new FileWriter(new File(path + kanban.getNomePasta() + ".jsp"));
		fw.write(stWriter.toString().replace("*", "$"));
		fw.close();

	}

	public void gerarController(VelocityContext context, Kanban kanban) throws IOException {

		Template template = getEngine().getTemplate("template/kanban/md/controllerKanban.vm");

		context.put("entidade", kanban);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = this.pathBknd + kanban.getNomePasta() + "/";
		new File(path).mkdirs();

		FileWriter fw = new FileWriter(new File(path + kanban.getClasse() + "Controller.java"));
		fw.write(stWriter.toString());
		fw.close();

	}
	
	public void gerarService(VelocityContext context, Kanban kanban) throws IOException {

		Template template = getEngine().getTemplate("template/kanban/md/serviceKanban.vm");

		context.put("entidade", kanban);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		String path = this.pathBknd + kanban.getNomePasta() + "/";
		new File(path).mkdirs();

		FileWriter fw = new FileWriter(new File(path + kanban.getClasse() + "Service.java"));
		fw.write(stWriter.toString());
		fw.close();

	}

	

}
