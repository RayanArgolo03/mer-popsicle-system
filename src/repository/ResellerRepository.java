package repository;

import domain.invoice.Reseller;
import domain.login.Login;
import interfaces.IResellerRepository;

import java.util.*;
import java.util.function.Predicate;

public class ResellerRepository implements IResellerRepository {
    private final TreeSet<Reseller> resellers;


    public ResellerRepository() {
        this.resellers = new TreeSet<>(Comparator.comparing(Reseller::getId));
    }

    public TreeSet<Reseller> getResellers() {
        return resellers;
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
    public void remove(Reseller reseller) {
        resellers.remove(reseller);
    }

    public Optional<Reseller> findByLogin(Login login) {
        return findBy(reseller -> reseller.getLogin().equals(login));
    }

}
