package com.codelamp.gerador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.codelamp.beautifier.BeautifierPro;
import com.codelamp.template.dominio.Projeto;

public class Generator {

	private VelocityEngine engine;
	
	private Projeto projeto;

	public Generator() {

		this.engine = new VelocityEngine();

		this.engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		this.engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

		Properties props = new Properties();
		props.put("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.SimpleLog4JLogSystem");
		props.put("runtime.log.logsystem.log4j.category", "velocity");
		props.put("runtime.log.logsystem.log4j.logger", "velocity");

		this.engine.init(props);

	}
	
	public Generator(Projeto projeto) {
		this();
		this.projeto = projeto;
	}

	public VelocityEngine getEngine() {
		return engine;
	}


	public void writeToDisk(StringWriter stWriter, String path, String fileName) throws IOException {
		FileWriter fw = new FileWriter(new File(path + fileName));
		
		String valor = stWriter.toString().replace("*", "$");
		fw.write(BeautifierPro.beautify(valor));
		fw.close();
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	

}
