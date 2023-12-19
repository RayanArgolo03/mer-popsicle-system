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
    private final Set<Reseller> resellers;

    public ResellerRepository() {
        this.resellers = new HashSet<>();
    }

    public Set<Reseller> getResellers() {
        return Collections.unmodifiableSet(resellers);
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
