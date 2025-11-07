package com.wallet.app.converter;

import com.wallet.app.enums.TransactionStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TransactionStatusConverter implements AttributeConverter<TransactionStatus, String> {

    @Override
    public String convertToDatabaseColumn(TransactionStatus attribute) {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public TransactionStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        switch (dbData) {
            case "Success": return TransactionStatus.SUCCESS;
            case "Failed": return TransactionStatus.FAILED;
            default: throw new IllegalArgumentException("Unknown transaction status: " + dbData);
        }
    }
}