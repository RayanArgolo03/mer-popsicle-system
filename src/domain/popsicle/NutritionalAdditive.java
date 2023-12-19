package domain.popsicle;

import enums.AdditiveType;

import java.util.Objects;

public class NutritionalAdditive {
    private AdditiveType additiveType;
    private String name;
    private String chemicalFormula;

    public NutritionalAdditive(AdditiveType additiveType, String name, String chemicalFormula) {
        this.additiveType = additiveType;
        this.name = name;
        this.chemicalFormula = chemicalFormula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NutritionalAdditive that = (NutritionalAdditive) o;
        return additiveType == that.additiveType && Objects.equals(name, that.name) && Objects.equals(chemicalFormula, that.chemicalFormula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(additiveType, name, chemicalFormula);
    }

    @Override
    public String toString() {
        return additiveType + ", " + name + ", " + chemicalFormula;
    }


}
