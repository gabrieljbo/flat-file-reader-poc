package dev.gabrieljbo.poc.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BirthEventFileHeader implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDate day;
    private LocalTime hour;
    private int records;

    public LocalDate getDay() {
	return day;
    }

    public void setDay(LocalDate day) {
	this.day = day;
    }

    public LocalTime getHour() {
	return hour;
    }

    public void setHour(LocalTime hour) {
	this.hour = hour;
    }

    public int getRecords() {
	return records;
    }

    public void setRecords(int records) {
	this.records = records;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
