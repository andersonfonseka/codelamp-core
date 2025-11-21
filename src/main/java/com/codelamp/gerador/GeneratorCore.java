package com.codelamp.gerador;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import com.codelamp.template.dominio.Modulo;

public class GeneratorCore extends Generator {

	private String path = "c:/temp/codigofonte/backend/";

	public GeneratorCore() {}
	
	public void gerarApplication(VelocityContext context, Modulo projeto) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/core/Application.vm");

		context.put("projeto", projeto);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		new File(path).mkdirs();

		writeToDisk(stWriter, path, "Application.java");
	}

	public void gerarWebConfig(VelocityContext context, Modulo projeto) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/core/WebConfig.vm");

		context.put("projeto", projeto);

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		new File(path).mkdirs();

		writeToDisk(stWriter, path, "WebConfig.java");
	}
	
	public void gerarViewLogging(VelocityContext context, Modulo projeto) throws Exception, IOException {

		Template template = getEngine().getTemplate("template/core/ViewLoggingInterceptor.vm");

		StringWriter stWriter = new StringWriter();
		template.merge(context, stWriter);

		new File(path).mkdirs();

		writeToDisk(stWriter, path, "ViewLoggingInterceptor.java");
	}
	
}
