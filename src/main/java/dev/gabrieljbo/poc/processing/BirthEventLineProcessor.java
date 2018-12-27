package dev.gabrieljbo.poc.processing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.stereotype.Component;

import dev.gabrieljbo.poc.domain.BirthEvent;
import dev.gabrieljbo.poc.domain.BirthEventFieldSetMapper;
import dev.gabrieljbo.poc.domain.BirthEventFileHeader;
import dev.gabrieljbo.poc.domain.BirthEventFileHeaderFieldSetMapper;
import dev.gabrieljbo.poc.exception.FileProcessingErrorCode;
import dev.gabrieljbo.poc.exception.SystemException;

@Component
public class BirthEventLineProcessor {
    private final static Logger logger = LoggerFactory.getLogger(BirthEventLineProcessor.class);

    private boolean hasErrors = false;

    private FixedLengthTokenizer fileHeaderTokenizer;
    private DefaultLineMapper<BirthEventFileHeader> fileHeaderLineMapper;

    private FixedLengthTokenizer fileRecordTokenizer;
    private DefaultLineMapper<BirthEvent> fileRecordLineMapper;

    public BirthEventLineProcessor() {
	fileHeaderTokenizer = new FixedLengthTokenizer();
	fileHeaderTokenizer.setNames(new String[] { "day", "hour", "records" });
	fileHeaderTokenizer.setColumns(new Range[] { new Range(1, 10), new Range(11, 15), new Range(16, 17) });

	fileHeaderLineMapper = new DefaultLineMapper<BirthEventFileHeader>();
	fileHeaderLineMapper.setFieldSetMapper(new BirthEventFileHeaderFieldSetMapper());
	fileHeaderLineMapper.setLineTokenizer(fileHeaderTokenizer);

	fileRecordTokenizer = new FixedLengthTokenizer();
	fileRecordTokenizer.setNames(new String[] { "id", "name", "gender", "day", "hour", "alive" });
	fileRecordTokenizer.setColumns(new Range[] { new Range(1, 2), new Range(3, 17), new Range(18, 18), new Range(19, 28), new Range(29, 33), new Range(34, 34) });

	fileRecordLineMapper = new DefaultLineMapper<BirthEvent>();
	fileRecordLineMapper.setFieldSetMapper(new BirthEventFieldSetMapper());
	fileRecordLineMapper.setLineTokenizer(fileRecordTokenizer);
    }

    public void processLine(String line, int lineNumber) {
	hasErrors = false;

	logger.info("Line {} -> [{}]", lineNumber, line);

	if (lineNumber > 1) { // line is a RECORD
	    if (34 != line.length()) {
		hasErrors = true;
		throw new SystemException(FileProcessingErrorCode.RECORD_LENGTH_ERROR);
	    }

	    BirthEvent birthEvent = null;
	    try {
		birthEvent = fileRecordLineMapper.mapLine(line, lineNumber);
		logger.info("Mapped BEAN is -> {}", birthEvent.toString());
	    } catch (Exception e) {
		hasErrors = true;
		throw new SystemException(FileProcessingErrorCode.RECORD_STRUCTURE_ERROR);
	    }
	} else { // line is the HEADER
	    if (17 != line.length()) {
		hasErrors = true;
		throw new SystemException(FileProcessingErrorCode.HEADER_LENGTH_ERROR);
	    }

	    BirthEventFileHeader fileHeader = null;
	    try {
		fileHeader = fileHeaderLineMapper.mapLine(line, lineNumber);
		logger.info("Mapped HEADER is -> {}", fileHeader.toString());
	    } catch (Exception e) {
		hasErrors = true;
		throw new SystemException(FileProcessingErrorCode.HEADER_STRUCTURE_ERROR);
	    }
	}
    }

    public boolean hasErrors() {
	return hasErrors;
    }

}
