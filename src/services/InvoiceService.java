package services;

import domain.invoice.Invoice;
import domain.invoice.Lot;
import domain.invoice.Reseller;
import enums.InvoiceSearch;
import exceptions.InvoiceException;
import repository.InvoiceRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InvoiceService {

    private final InvoiceRepository repository;

    public InvoiceService() {
        this.repository = new InvoiceRepository();
    }

    public Invoice createInvoice(Set<Lot> lots) {
        return new Invoice.InvoiceBuilder()
                .lots(lots)
                .issueDate(generateDate())
                .desnecessaryTotalValue(calculateTotalValue(lots))
                .description(receiveDescription())
                .build();
    }

    private LocalDateTime generateDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String aux = LocalDateTime.now().format(dtf);
        return LocalDateTime.parse(aux, dtf);
    }

    private BigDecimal calculateTotalValue(Set<Lot> lots) {
        return lots.stream()
                .map(Lot::getPopsiclesAndQuantities)
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .map(entry -> entry.getKey().getUnitPrice().multiply(new BigDecimal(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String receiveDescription() {
        System.out.println("Invoice description: ");
        return new Scanner(System.in).nextLine();
    }

    public boolean addInvoice(Invoice invoice) {
        return repository.add(invoice);
    }

    public void remove(List<Invoice> invoices) {
        repository.removeAll(invoices);
    }

    public Optional<Invoice> findInvoice(Reseller reseller) {
        InvoiceSearch search = ReadService.readEnum("Choose your invoice search option: ", InvoiceSearch.class);
        return switch (search) {
            case DATE -> repository.findByDate(reseller, receiveDate());
            case VALUE -> repository.findByValue(reseller, receiveValue());
        };
    }

    public LocalDateTime receiveDate() {

        System.out.println("Enter the date: (pattern: day/month/year hour:minute)");

        //Reading full line because the local date time pattern has white space
        String date = new Scanner(System.in).nextLine();
        if (!validDate(date)) throw new InvoiceException("Invalid date format!");

        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    private boolean validDate(String date) {
        return date.matches("^(0[1-9]|[12]\\d|3[01])/(0[1-9]|1[0-2])/(2[01]|1[6-9])\\d{2} (0[0-9]|1[0-9]|2[0-3]):[0-5]\\d$");
    }

    public BigDecimal receiveValue() {


        String value = ReadService.readString("Enter the total value: (with dot to separate decimal values!)");
        if (!validValue(value)) throw new InvoiceException("Invalid format!");

        if (!value.contains(".")) value += ".00";

        return new BigDecimal(value);
    }

    private boolean validValue(String value) {
        return value.matches("^[$]?[0-9]*(\\.)?[0-9]?[0-9]?$");
    }

    public List<Invoice> findInvoices(Reseller reseller) {
        return repository.findAllInvoices(reseller);
    }


}
