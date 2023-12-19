package services;

import domain.popsicle.*;
import enums.AdditiveType;

import java.util.Set;

public final class MokService {

    public static Set<NutritionalAdditive> getNutritionalAdditives() {
        return Set.of(
                new NutritionalAdditive(AdditiveType.VITAMINS, "Vitamin c", "H24C66"),
                new NutritionalAdditive(AdditiveType.MINERAL_SALTS, "Potassium", "9OOOPI61")
        );
    }

    public static Set<Preservative> getPreservatives() {
        return Set.of(
                new Preservative("Drying", "Is a Drying"),
                new Preservative("Fermentation", "Is a Fermentation")
        );
    }


}
