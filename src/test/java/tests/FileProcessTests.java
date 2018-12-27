package tests;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import dev.gabrieljbo.poc.processing.AppConfig;
import dev.gabrieljbo.poc.processing.FileProcessor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class FileProcessTests {
    @Autowired
    private FileProcessor fileProcessor;

    @Test
    public void test() throws Exception {
	File file = new File("/Users/gbuitrago/Desktop/flat_file_example_copy1.csv"); // pass full path to flat file to process
	fileProcessor.processFile(file);

	Assert.isTrue(fileProcessor.getErrorCount() == 0, "Error count must be 0");
    }
}
