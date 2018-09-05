package domain;

import java.time.ZoneId;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class BirthEventFileHeaderFieldSetMapper implements FieldSetMapper<BirthEventFileHeader> {

    public BirthEventFileHeader mapFieldSet(FieldSet fieldSet) throws BindException {
	BirthEventFileHeader birthEventFileHeader = new BirthEventFileHeader();
	birthEventFileHeader.setDay(fieldSet.readDate("day", "dd/MM/yyyy").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	birthEventFileHeader.setHour(fieldSet.readDate("hour", "HH:mm").toInstant().atZone(ZoneId.systemDefault()).toLocalTime());
	birthEventFileHeader.setRecords(fieldSet.readInt("records"));

	return birthEventFileHeader;
    }

}
