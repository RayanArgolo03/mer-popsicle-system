package factory;

import domain.popsicle.*;
import enums.Packaging;
import enums.PopsicleType;

import java.math.BigDecimal;
import java.util.Set;

public class PopsicleFactory {
    public static PopsicleAbstract newPopsicle(PopsicleType popsicleType, Set<Flavor> flavors, Set<Ingredient> ingredients, BigDecimal unitPrice, Packaging packaging) {
        return switch (popsicleType) {
            case DIET -> new NormalPopsicle(flavors, ingredients, unitPrice, packaging);
            case CANDY -> new MilkPopsicle(flavors, ingredients, unitPrice, packaging);
        };
    }

}
