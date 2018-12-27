package dev.gabrieljbo.poc.processing;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BirthEventFileProcessor extends FileProcessor {
    private static Logger logger = LoggerFactory.getLogger(BirthEventFileProcessor.class);

    @Autowired
    BirthEventLineProcessor lineProcessor;

    public BirthEventFileProcessor() {
	super();
    }

    @Override
    public void processFile(File file) throws Exception {
	this.errorCount = 0;

	logger.info("Processing file -> [{}]", file.getAbsolutePath());

	LineIterator lineIterator = FileUtils.lineIterator(file, "UTF-8");
	try {
	    int lineNumber = 0;

	    while (lineIterator.hasNext()) {
		++lineNumber;

		String line = lineIterator.nextLine();
		lineProcessor.processLine(line, lineNumber);
		if (lineProcessor.hasErrors()) {
		    this.errorCount++;
		}
	    }
	} finally {
	    lineIterator.close();
	}
    }

}
