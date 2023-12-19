package domain.popsicle;

import enums.Packaging;

import java.math.BigDecimal;
import java.util.*;

public abstract class PopsicleAbstract {
    private Set<Flavor> flavors;
    private Set<Ingredient> ingredients;
    private BigDecimal unitPrice;
    private Packaging packaging;

    public PopsicleAbstract(Set<Flavor> flavors, Set<Ingredient> ingredients, BigDecimal unitPrice, Packaging packaging) {
        this.flavors = flavors;
        this.ingredients = ingredients;
        this.unitPrice = unitPrice;
        this.packaging = packaging;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PopsicleAbstract that = (PopsicleAbstract) o;
        return Objects.equals(flavors, that.flavors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flavors);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Popsicle of ");

        int i = 0;
        for (Flavor flavor : flavors) {
            sb.append(flavor);
            if (flavors.size() > 1 && i < flavors.size()) {
                sb.append(" with ");
            }
            i++;
        }

        sb.append("\n").append("Made with ");
        i = 0;
        for (Ingredient ingredient : ingredients) {
            sb.append(ingredient);
            if (ingredients.size() > 1 && i < ingredients.size() - 1) {
                sb.append(", ");
            }
            i++;
        }

        sb.append("\n").append("Costing ").append(unitPrice);
        sb.append("\n").append("Packed in ").append(packaging);


        return sb.toString();
    }

}
