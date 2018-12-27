package dev.gabrieljbo.poc.processing;

import java.io.File;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyLauncher {

    public static void main(String[] args) throws Exception {
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
	ctx.register(AppConfig.class);
	ctx.refresh();

	File file = new File("/Users/gbuitrago/Desktop/flat_file_example_copy1.csv"); // pass full path to flat file to process

	FileProcessor fileProcessor = new BirthEventFileProcessor();
	fileProcessor.processFile(file);
    }

}
