/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.util.Set;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.invariant.evenement.service.enumeration.TypeEvenementEnum;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.PeineOuMesure;

/**
 * Il s'agit d'�v�nement concernant une seule personne et portant un ensemble de mesures (MESURE DECIDEE) pouvant
 * modifier ou remplacer un autre ensemble de mesures pr�c�demment prononc�es (MESURE CONCERNEE). La modification de
 * mesure se traduit par la cr�ation d'une nouvelle version de la mesure concern�e.<br>
 * RG.EVTM002 2>
 */
@RegleDeGestion(RG.EVTM002)
public class EvenementModifiantOuSubstituantMesure extends Evenement {

    private static final long serialVersionUID = 7430088215451570532L;

    /**
     * Mesures concern�es. Il s'agit des mesures modifi�es ou substitu�es par cet �v�nement.
     */
    // TODO utiliser mesuresSuivies de EvenementDeSuiviMesure !
    private Set<PeineOuMesure> mesureConcernees;

    /**
     * Mesures d�cid�es. Il s'agit des mesures port�es (cr�es) par cet �v�nement pour remplacer les mesures concern�es.
     */
    private Set<PeineOuMesure> mesureDecidees;

    // TODO : � faire get set et logique autour de ce type d'evenement

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
