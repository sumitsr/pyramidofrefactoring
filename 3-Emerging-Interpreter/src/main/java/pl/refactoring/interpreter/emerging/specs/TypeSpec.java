package pl.refactoring.interpreter.emerging.specs;

import pl.refactoring.interpreter.emerging.EstateType;
import pl.refactoring.interpreter.emerging.RealEstate;
import pl.refactoring.interpreter.emerging.Spec;

public class TypeSpec implements Spec {
    private EstateType type;

    public TypeSpec(EstateType type) {
        this.type = type;
    }

    public boolean isSatisfiedBy(RealEstate estate) {
        return estate.getType().equals(type);
    }
}