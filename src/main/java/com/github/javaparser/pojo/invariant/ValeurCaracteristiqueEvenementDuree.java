/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */

package fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement;

import fr.gouv.justice.cassiopee.common.util.DureeAnMoisJour;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.FormatCaracteristiqueEnum;

/**
 * objet modele pour les caracteristique de type date
 *
 * @see fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.FormatCaracteristiqueEnum#DUREE
 */
public class ValeurCaracteristiqueEvenementDuree extends ValeurCaracteristiqueEvenementExterieur<DureeAnMoisJour> {

    private static final long serialVersionUID = -4770676327422623963L;

    private DureeAnMoisJour valeurDuree;

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#getFormat()
     */
    public FormatCaracteristiqueEnum getFormat() {
        return FormatCaracteristiqueEnum.DUREE;
    }

    /**
     * r�serv� � hibernate
     */
    protected DureeAnMoisJour getValeurDuree() {
        return valeurDuree;
    }

    /**
     * r�serv� � hibernate
     */
    protected void setValeurDuree(DureeAnMoisJour value) {
        this.valeurDuree = value;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#getValue()
     */
    @Override
    public DureeAnMoisJour getValue() {
        return this.valeurDuree;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#setValue()
     */
    @Override
    public void setValue(DureeAnMoisJour value) {
        this.valeurDuree = value;
    }
}