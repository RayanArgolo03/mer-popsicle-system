package repository;

import domain.invoice.Reseller;
import domain.login.Login;
import interfaces.IResellerRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class ResellerRepository implements IResellerRepository {
    private final TreeSet<Reseller> resellers;


    public ResellerRepository() {
        this.resellers = new HashSet<>();
    }

    public Reseller getFirstReseller() {
        return resellers.first();
    }

    public Reseller getLastReseller() {
        return resellers.last();
    }

    @Override
    public boolean add(Reseller reseller) {
        return resellers.add(reseller);
    }

    @Override
    public Optional<Reseller> findBy(Predicate<Reseller> predicate) {
        return resellers.stream()
                .filter(predicate)
                .findFirst();
    }

    @Override
    public boolean add(Reseller reseller) {
        return resellers.add(reseller);
    }

    @Override
    public void remove(Reseller reseller) {
        resellers.remove(reseller);
    }

    public Optional<Reseller> findByLogin(Login login) {
        return findBy(reseller -> reseller.getLogin().equals(login));
    }

}
