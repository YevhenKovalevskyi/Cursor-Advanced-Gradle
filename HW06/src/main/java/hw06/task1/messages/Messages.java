package hw06.task1.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The Messages Enum represents text messages for use and localization(if it needed)
 */
@AllArgsConstructor
public enum Messages {
    PRODUCT_NOT_FOUND("Product ID[{}] not found!", "Product ID[%s] not found!"),
    COUNTRY_NOT_FOUND("Country ID[{}] not found!", "Country ID[%s] not found!"),

    ;
    
    @Getter private final String logMessage;
    @Getter private final String outMessage;
}
