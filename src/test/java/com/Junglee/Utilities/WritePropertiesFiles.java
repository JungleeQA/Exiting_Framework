package com.Junglee.Utilities;

import java.io.File;
import java.io.IOException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class WritePropertiesFiles {

	public void WritePropertiesFile(String Filepath, String Configname, String Configvalue)
			throws IOException, ConfigurationException {
		File file = new File(Filepath);

		boolean exists = file.exists();

		if (!exists) {
			file.createNewFile();
			
		}

		PropertiesConfiguration config = new PropertiesConfiguration(Filepath);
		config.setProperty(Configname, Configvalue);
		config.save();

	}

}
