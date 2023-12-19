package domain.invoice;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class Invoice {
    private Set<Lot> lots;
    private LocalDateTime issueDate;
    private BigDecimal desnecessaryTotalValue;
    private String description;

    private Invoice(Set<Lot> lots, LocalDateTime issueDate, BigDecimal desnecessaryTotalValue, String description) {
        this.lots = lots;
        this.issueDate = issueDate;
        this.desnecessaryTotalValue = desnecessaryTotalValue;
        this.description = description;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public String getIssueDateInString() {
        return issueDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public BigDecimal getDesnecessaryTotalValue() {
        return desnecessaryTotalValue;
    }

    public Reseller getReseller() {
        return lots.stream().findFirst().get().getReseller();
    }

    @Override
    public String toString() {
        return " ---- INVOICE ----" + "\n" +
                "Issue date:" + getIssueDateInString() + "\n" +
                "Reseller: " + getReseller().getName() + "\n" +
                "Purchases: " + lots + "\n" +
                "Total Value: " + desnecessaryTotalValue + "\n" +
                "Description: " + description;
    }

    public static final class InvoiceBuilder {
        private Set<Lot> lots;
        private LocalDateTime issueDate;
        private BigDecimal desnecessaryTotalValue;
        private String description;

        public InvoiceBuilder() {
        }

        public InvoiceBuilder lots(Set<Lot> lots) {
            this.lots = lots;
            return this;
        }

        public InvoiceBuilder issueDate(LocalDateTime issueDate) {
            this.issueDate = issueDate;
            return this;
        }

        public InvoiceBuilder desnecessaryTotalValue(BigDecimal desnecessaryTotalValue) {
            this.desnecessaryTotalValue = desnecessaryTotalValue;
            return this;
        }

        public InvoiceBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Invoice build() {
            return new Invoice(lots, issueDate, desnecessaryTotalValue, description);
        }
    }
}
