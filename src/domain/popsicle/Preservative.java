package domain.popsicle;

import java.util.Objects;

public class Preservative {
    private String name;
    private String description;

    public Preservative(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Preservative that = (Preservative) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return name + " - " + description;
    }
}
