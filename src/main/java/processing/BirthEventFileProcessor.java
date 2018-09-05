package processing;

import java.io.File;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;

import domain.BirthEvent;
import domain.BirthEventFieldSetMapper;
import domain.BirthEventFileHeader;
import domain.BirthEventFileHeaderFieldSetMapper;

public class BirthEventFileProcessor extends FileProcessor {
    private static final Logger logger = Logger.getLogger(BirthEventFileProcessor.class.getName());

    private FixedLengthTokenizer fileHeaderTokenizer;
    private DefaultLineMapper<BirthEventFileHeader> fileHeaderLineMapper;

    private FixedLengthTokenizer fileRecordTokenizer;
    private DefaultLineMapper<BirthEvent> fileRecordLineMapper;

    public BirthEventFileProcessor() {
	super();

	fileHeaderTokenizer = new FixedLengthTokenizer();
	fileHeaderTokenizer.setNames(new String[] { "day", "hour", "records" });
	fileHeaderTokenizer.setColumns(new Range[] { new Range(1, 10), new Range(11, 15), new Range(16, 17) });

	fileHeaderLineMapper = new DefaultLineMapper<BirthEventFileHeader>();
	fileHeaderLineMapper.setFieldSetMapper(new BirthEventFileHeaderFieldSetMapper());
	fileHeaderLineMapper.setLineTokenizer(fileHeaderTokenizer);

	fileRecordTokenizer = new FixedLengthTokenizer();
	fileRecordTokenizer.setNames(new String[] { "id", "name", "gender", "day", "hour", "alive" });
	fileRecordTokenizer.setColumns(new Range[] { new Range(1, 2), new Range(3, 17), new Range(18, 18),  new Range(19, 28), new Range(29, 33), new Range(34, 34) });

	fileRecordLineMapper = new DefaultLineMapper<BirthEvent>();
	fileRecordLineMapper.setFieldSetMapper(new BirthEventFieldSetMapper());
	fileRecordLineMapper.setLineTokenizer(fileRecordTokenizer);

    }

    @Override
    public void processFile(File file) throws Exception {
	BirthEventFileHeader fileHeader = null;

	LineIterator lineIterator = FileUtils.lineIterator(file, "UTF-8");
	try {
	    int counter = 1;

	    while (lineIterator.hasNext()) {
		String line = lineIterator.nextLine();
		logger.info("LINE IS -> [" + line + "]");

		if (counter > 1) { // line is a RECORD
		    BirthEvent birthEvent = fileRecordLineMapper.mapLine(line, counter);
		    logger.info("BIRTH EVENT IS -> " + birthEvent.toString());
		} else { // line is the HEADER
		    fileHeader = fileHeaderLineMapper.mapLine(line, counter);
		    logger.info("FILE HEADER IS -> " + fileHeader.toString());
		}

		counter++;
	    }
	} finally {
	    lineIterator.close();
	}
    }

}
