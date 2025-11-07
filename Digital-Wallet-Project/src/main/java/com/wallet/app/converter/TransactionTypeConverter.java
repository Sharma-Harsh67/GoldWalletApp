package com.wallet.app.converter;
import com.wallet.app.enums.TransactionType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TransactionTypeConverter implements AttributeConverter<TransactionType, String> {

    @Override
    public String convertToDatabaseColumn(TransactionType attribute) {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public TransactionType convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        switch (dbData) {
            case "Buy": return TransactionType.BUY;
            case "Sell": return TransactionType.SELL;
            case "Convert to Physical": return TransactionType.CONVERT_TO_PHYSICAL;
            default: throw new IllegalArgumentException("Unknown transaction type: " + dbData);
        }
    }
}