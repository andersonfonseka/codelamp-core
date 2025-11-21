package com.codelamp.gerador;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import com.codelamp.template.dominio.Modulo;

public class GeneratorWeb extends Generator {

	private String path = "c:/temp/codigofonte/frontend/";


	public void gerarSideBar(VelocityContext context, Modulo projeto) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/web/sidebar.vm");

		context.put("projeto", projeto);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		new File(path).mkdirs();

		writeToDisk(stWriter, path, "sidebar.jsp");
	}

	public void gerarIndex(VelocityContext context, Modulo projeto) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/web/index.vm");

		context.put("projeto", projeto);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		new File(path).mkdirs();

		writeToDisk(stWriter, path, "index.jsp");
	}

	public void gerarLayout(VelocityContext context, Modulo projeto) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/web/layout.vm");

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		new File(path).mkdirs();

		writeToDisk(stWriter, path, "layout.jsp");
	}

}
