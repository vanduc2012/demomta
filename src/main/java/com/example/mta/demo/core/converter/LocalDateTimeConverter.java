package com.example.mta.demo.core.converter;

import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        if (attribute != null) {
            return java.sql.Timestamp.valueOf(attribute);
        }
        return null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        if (dbData != null) {
            return dbData.toLocalDateTime();
        }
        return null;
    }
}
