package domain.popsicle;

import enums.Packaging;

import java.math.BigDecimal;
import java.util.Set;

public class NormalPopsicle extends PopsicleAbstract {

    private Set<NutritionalAdditive> nutritionalAdditives;

    public NormalPopsicle(Set<Flavor> flavors, Set<Ingredient> ingredients, BigDecimal unitPrice, Packaging packaging) {
        super(flavors, ingredients, unitPrice, packaging);
    }

    public void setNutritionalAdditives(Set<NutritionalAdditive> nutritionalAdditives) {
        this.nutritionalAdditives = nutritionalAdditives;
    }

    @Override
    public String toString() {
        return super.toString() + "\n"
                + "Nutrional additives: " + nutritionalAdditives;
    }
}
