/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.util.Set;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.invariant.evenement.service.enumeration.TypeEvenementEnum;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.PeineOuMesure;

/**
 * Il s'agit d'événement concernant une seule personne et portant un ensemble de mesures (MESURE DECIDEE) pouvant
 * modifier ou remplacer un autre ensemble de mesures précédemment prononcées (MESURE CONCERNEE). La modification de
 * mesure se traduit par la création d'une nouvelle version de la mesure concernée.<br>
 * RG.EVTM002 2>
 */
@RegleDeGestion(RG.EVTM002)
public class EvenementModifiantOuSubstituantMesure extends Evenement {

    private static final long serialVersionUID = 7430088215451570532L;

    /**
     * Mesures concernées. Il s'agit des mesures modifiées ou substituées par cet événement.
     */
    // TODO utiliser mesuresSuivies de EvenementDeSuiviMesure !
    private Set<PeineOuMesure> mesureConcernees;

    /**
     * Mesures décidées. Il s'agit des mesures portées (crées) par cet événement pour remplacer les mesures concernées.
     */
    private Set<PeineOuMesure> mesureDecidees;

    // TODO : à faire get set et logique autour de ce type d'evenement

    @Override
    public TypeEvenementEnum getTypeEvenementEnum() {
        return TypeEvenementEnum.MODIFIANT_OU_SUBSTITUANT_MESURE;
    }

    public Set<PeineOuMesure> getMesureConcernees() {
        return mesureConcernees;
    }

    public void setMesureConcernees(Set<PeineOuMesure> mesureConcernees) {
        this.mesureConcernees = mesureConcernees;
    }

    public Set<PeineOuMesure> getMesureDecidees() {
        return mesureDecidees;
    }

    public void setMesureDecidees(Set<PeineOuMesure> mesureDecidees) {
        this.mesureDecidees = mesureDecidees;
    }
}
