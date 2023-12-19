package enums;

import java.math.BigDecimal;

public enum PriceConstant {
    PRICE_PER_FLAVOR(new BigDecimal("0.50"));

    private final BigDecimal value;

    PriceConstant(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}

