/**
 * Copyright (c) 2020 IT Train Wlodzimierz Krakowski (www.refactoring.pl)
 * Sources are available only for personal usage by Udemy students of this course.
 */
package pl.refactoring.interpreter.emerging;

import pl.refactoring.interpreter.emerging.specs.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class RealEstateFinder {
    private List<RealEstate> repository;

    public RealEstateFinder(List<RealEstate> repository) {
        this.repository = repository;
    }

    public List<RealEstate> bySpec(Spec spec) {
        return repository.stream()
                .filter(spec::isSatisfiedBy)
                .collect(Collectors.toList());
    }

    public List<RealEstate> byBelowArea(float maxBuildingArea){
        return bySpec(new BelowAreaSpec(maxBuildingArea));
    }

    public List<RealEstate> byMaterial(EstateMaterial material){
        return bySpec(new MaterialSpec(material));
    }

    public List<RealEstate> byMaterialBelowArea(EstateMaterial material, float maxBuildingArea){
        return bySpec(new AndSpec(new MaterialSpec(material), new BelowAreaSpec(maxBuildingArea)));
    }

    public List<RealEstate> byPlacement(EstatePlacement placement){
        return bySpec(new PlacementSpec(placement));
    }

    public List<RealEstate> byAvoidingPlacement(EstatePlacement placement){
        Spec spec = new PlacementSpec(placement);
        Spec notSpec = new NotSpec(spec);
        return bySpec(notSpec);
    }

    public List<RealEstate> byAreaRange(float minArea, float maxArea){
        return bySpec(new AreaRangeSpec(minArea, maxArea));
    }

    public List<RealEstate> byType(EstateType type){
        return bySpec(new TypeSpec(type));
    }

    public List<RealEstate> byVerySpecificCriteria(EstateType type, EstatePlacement placement, EstateMaterial material){
        List<RealEstate> foundRealEstates = new ArrayList<>();

        Iterator<RealEstate> estates = repository.iterator();
        while (estates.hasNext()) {
            RealEstate estate = estates.next();
            if (new TypeSpec(type).isSatisfiedBy(estate) &&
                    new PlacementSpec(placement).isSatisfiedBy(estate) &&
                    new MaterialSpec(material).isSatisfiedBy(estate))
                foundRealEstates.add(estate);
        }
        return foundRealEstates;
    }

}
