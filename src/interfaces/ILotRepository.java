package interfaces;

import domain.invoice.Lot;

import java.util.Collection;

public interface ILotRepository extends IBusinessEntityRepository<Lot> {
    boolean add(Collection<Lot> elements);
}
