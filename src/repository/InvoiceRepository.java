package repository;

import domain.invoice.Invoice;
import domain.invoice.Reseller;
import interfaces.IInvoiceRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InvoiceRepository implements IInvoiceRepository {
    private List<Invoice> invoices;

    public InvoiceRepository() {
        this.invoices = new ArrayList<>();
    }

    @Override
    public Optional<Invoice> findBy(Predicate<Invoice> predicate) {
        return invoices.stream()
                .filter(predicate)
                .findFirst();
    }

    @Override
    public List<Invoice> findAll(Predicate<Invoice> predicate) {
        return invoices.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public boolean add(Invoice invoice) {
        return invoices.add(invoice);
    }

    @Override
    public void removeAll(List<Invoice> invoices) {
        this.invoices.removeAll(invoices);
    }

    public Optional<Invoice> findByDate(Reseller reseller, LocalDateTime issueDate) {
        return findBy(invoice -> invoice.getReseller().equals(reseller)
                && invoice.getIssueDate().equals(issueDate));
    }

    public Optional<Invoice> findByValue(Reseller reseller, BigDecimal value) {
        return findBy(invoice -> invoice.getReseller().equals(reseller) &&
                invoice.getDesnecessaryTotalValue().equals(value));
    }

    public List<Invoice> findAllInvoices(Reseller reseller) {
        return findAll(invoice -> invoice.getReseller().equals(reseller));
    }
}
