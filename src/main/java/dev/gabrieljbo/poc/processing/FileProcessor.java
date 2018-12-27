package dev.gabrieljbo.poc.processing;

import java.io.File;

public abstract class FileProcessor {

    protected int errorCount = 0;

    public int getErrorCount() {
	return errorCount;
    }

    public abstract void processFile(File file) throws Exception;

}
