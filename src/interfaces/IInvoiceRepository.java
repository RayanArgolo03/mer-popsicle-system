package interfaces;

import domain.invoice.Invoice;

import java.util.Optional;
import java.util.function.Predicate;

public interface IInvoiceRepository extends IBusinessEntityRepository<Invoice> {
    boolean add(Invoice Invoice);
    Optional<Invoice> findBy(Predicate<Invoice> predicate);
}
