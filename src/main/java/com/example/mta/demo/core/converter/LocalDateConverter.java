package com.example.mta.demo.core.converter;

import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.time.LocalDate;
import java.sql.Date;

@Component
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {
    @Override
    public Date convertToDatabaseColumn(LocalDate attribute) {
        if (attribute != null) {
            return java.sql.Date.valueOf(attribute);
        }
        return null;
    }

    @Override
    public LocalDate convertToEntityAttribute(Date dbData) {
        if (dbData != null) {
            return dbData.toLocalDate();
        }
        return null;
    }
}
