package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.io.Serializable;

import fr.gouv.justice.cassiopee.invariant.evenement.service.dto.caracteristique.CaracteristiqueDTO;

public class MajCaracteristique {

    private String code;

    private Serializable valueBefore;

    private Serializable valueAfter;

    public MajCaracteristique(CaracteristiqueDTO before, CaracteristiqueDTO after){
        valueBefore = before.getValue();
        valueAfter = after.getValue();
        code = after.getCode();
    }

    public String getCode() {
        return code;
    }

    public Serializable getValueBefore() {
        return valueBefore;
    }

    public Serializable getValueAfter() {
        return valueAfter;
    }



}
