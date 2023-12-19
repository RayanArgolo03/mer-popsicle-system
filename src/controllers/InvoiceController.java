package controllers;

import domain.invoice.Invoice;
import domain.invoice.Lot;
import domain.invoice.Reseller;
import exceptions.InvoiceException;
import services.InvoiceService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class InvoiceController {
    private final InvoiceService service;

    public InvoiceController() {
        this.service = new InvoiceService();
    }

    public Invoice create(Set<Lot> lots) {
        return service.createInvoice(lots);
    }

    public void add(Invoice invoice) {
        boolean sucess = service.addInvoice(invoice);
        if (!sucess) throw new InvoiceException("Error to adding invoice in database!");
    }

    public void remove(List<Invoice> invoices) {
        service.remove(invoices);
    }

    public Optional<Invoice> find(Reseller reseller) {
        return service.findInvoice(reseller);
    }

    public List<Invoice> findInvoices(Reseller reseller) {
        return service.findInvoices(reseller);
    }



}
