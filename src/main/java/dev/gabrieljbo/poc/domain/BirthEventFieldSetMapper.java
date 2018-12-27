package dev.gabrieljbo.poc.domain;

import java.time.ZoneId;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class BirthEventFieldSetMapper implements FieldSetMapper<BirthEvent> {

    public BirthEvent mapFieldSet(FieldSet fieldSet) throws BindException {
	BirthEvent birthEvent = new BirthEvent();
	birthEvent.setId(fieldSet.readInt("id"));
	birthEvent.setName(fieldSet.readString("name"));
	birthEvent.setGender(fieldSet.readString("gender"));
	birthEvent.setDay(fieldSet.readDate("day", "dd/MM/yyyy").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	birthEvent.setHour(fieldSet.readDate("hour", "HH:mm").toInstant().atZone(ZoneId.systemDefault()).toLocalTime());
	birthEvent.setAlive(fieldSet.readBoolean("alive", "1"));

	return birthEvent;
    }

}
