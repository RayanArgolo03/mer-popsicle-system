package interfaces;

import java.util.List;
import java.util.function.Predicate;


//Using business repositories (lot and invoice)
public interface IBusinessEntityRepository<T> {

    List<T> findAll(Predicate<T> predicate);

    void removeAll(List<T> objects);

}
