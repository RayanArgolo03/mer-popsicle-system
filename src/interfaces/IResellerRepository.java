package interfaces;

import domain.invoice.Reseller;

import java.util.Optional;
import java.util.function.Predicate;

public interface IResellerRepository {
    Optional<Reseller> findBy(Predicate<Reseller> predicate);

    boolean add(Reseller reseller);

    void remove(Reseller reseller);
}
