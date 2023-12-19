package domain.popsicle;

import enums.Packaging;

import java.math.BigDecimal;
import java.util.Set;

public class MilkPopsicle extends PopsicleAbstract {
    private Set<Preservative> preservatives;

    public MilkPopsicle(Set<Flavor> flavors, Set<Ingredient> ingredients, BigDecimal unitPrice, Packaging packaging) {
        super(flavors, ingredients, unitPrice, packaging);

    }

    public void setPreservatives(Set<Preservative> preservatives) {
        this.preservatives = preservatives;
    }

    @Override
    public String toString() {
        return super.toString() + "\n"
                + "Preservatives: " + preservatives;
    }
}
