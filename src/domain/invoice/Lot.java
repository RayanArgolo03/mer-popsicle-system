package domain.invoice;

import domain.popsicle.PopsicleAbstract;

import java.util.Map;
import java.util.Objects;

public class Lot {
    private Map<PopsicleAbstract, Integer> popsiclesAndQuantities;
    private Reseller reseller;

    public Lot(Map<PopsicleAbstract, Integer> popsiclesAndQuantities, Reseller reseller) {
        this.popsiclesAndQuantities = popsiclesAndQuantities;
        this.reseller = reseller;
    }

    public Map<PopsicleAbstract, Integer> getPopsiclesAndQuantities() {
        return popsiclesAndQuantities;
    }

    public Reseller getReseller() {
        return reseller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lot lot = (Lot) o;
        return Objects.equals(popsiclesAndQuantities, lot.popsiclesAndQuantities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(popsiclesAndQuantities);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<PopsicleAbstract, Integer> entry : popsiclesAndQuantities.entrySet()) {
            sb.append(entry.getValue()).append(" ").append(entry.getKey()).append("'s");
        }

        return sb.toString();
    }
}
