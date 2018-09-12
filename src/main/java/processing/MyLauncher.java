package processing;

import java.io.File;

public class MyLauncher {

    public static void main(String[] args) throws Exception {
	File file = new File(args[0]); // pass full path to flat file to process

	FileProcessor fileProcessor = new BirthEventFileProcessor();
	fileProcessor.processFile(file);
    }

}
