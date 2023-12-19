package repository;

import domain.invoice.Lot;
import domain.invoice.Reseller;
import interfaces.ILotRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LotRepository implements ILotRepository {
    private List<Lot> lots;

    public LotRepository() {
        this.lots = new ArrayList<>();
    }

    @Override
    public List<Lot> findAll(Predicate<Lot> predicate) {
        return lots.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public boolean add(Collection<Lot> lots) {
        return this.lots.addAll(lots);
    }

    @Override
    public void removeAll(List<Lot> elements) {
        lots.removeAll(elements);
    }


    public List<Lot> findAllLots(Reseller reseller) {
        return findAll(lot -> lot.getReseller().equals(reseller));
    }
}
