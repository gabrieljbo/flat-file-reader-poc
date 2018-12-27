package dev.gabrieljbo.poc.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BirthEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String gender;
    private LocalDate day;
    private LocalTime hour;
    private boolean alive;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getGender() {
	return gender;
    }

    public void setGender(String gender) {
	this.gender = gender;
    }

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

    public boolean isAlive() {
	return alive;
    }

    public void setAlive(boolean alive) {
	this.alive = alive;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
