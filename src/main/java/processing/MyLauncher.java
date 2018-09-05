package processing;

import java.io.File;

public class MyLauncher {

    public static void main(String[] args) throws Exception {
	File file = new File("/Users/gbuitrago/Desktop/Untitled.txt");
	FileProcessor fileProcessor = new BirthEventFileProcessor();
	fileProcessor.processFile(file);
    }

}
