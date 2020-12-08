/**
 * Copyright (c) 2020 IT Train Wlodzimierz Krakowski (www.refactoring.pl)
 * Sources are available only for personal usage by Udemy students of this course.
 */
package pl.refactoring.interpreter.completed;

import pl.refactoring.interpreter.completed.spec.*;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static pl.refactoring.interpreter.completed.spec.Specs.ofType;

public class RealEstateFinder {
    private List<RealEstate> repository;

    public RealEstateFinder(List<RealEstate> repository) {
        this.repository = repository;
    }

    public List<RealEstate> bySpec(Predicate<RealEstate> spec) {
        return repository.stream()
                .filter(spec::test)
                .collect(Collectors.toList());
    }

    @Deprecated
    public List<RealEstate> byBelowArea(float maxBuildingArea){
        return bySpec(Specs.belowArea(maxBuildingArea));
    }

    @Deprecated
    public List<RealEstate> byMaterial(EstateMaterial material){
        return bySpec(Specs.ofMaterial(material));
    }

    @Deprecated
    public List<RealEstate> byMaterialBelowArea(EstateMaterial material, float maxBuildingArea){
        Spec materialSpec = Specs.ofMaterial(material);
        Spec belowAreaSpec = Specs.belowArea(maxBuildingArea);

        AndSpecBuilder andSpecBuilder = new AndSpecBuilder();
        andSpecBuilder.withSpec(materialSpec)
                .withSpec(belowAreaSpec);
        return bySpec(andSpecBuilder.createAndSpec());
    }

    @Deprecated
    public List<RealEstate> byPlacement(EstatePlacement placement){
        return bySpec(Specs.placedIn(placement));
    }

    @Deprecated
    public List<RealEstate> byAvoidingPlacement(EstatePlacement placement){
        return bySpec(Specs.not(Specs.placedIn(placement)));
    }

    @Deprecated
    public List<RealEstate> byAreaRange(float minArea, float maxArea){
        return bySpec(Specs.ofAreaRange(minArea, maxArea));
    }

    @Deprecated
    public List<RealEstate> byType(EstateType type){
        return bySpec(ofType(type));
    }

    @Deprecated
    public List<RealEstate> byVerySpecificCriteria(EstateType type, EstatePlacement placement, EstateMaterial material){
        return bySpec(new AndSpecBuilder()
                .withSpec(ofType(type))
                .withSpec(Specs.placedIn(placement))
                .withSpec(Specs.ofMaterial(material))
                .createAndSpec());
    }

}
