/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */

package fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement;

import fr.gouv.justice.cassiopee.common.util.Montant;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.FormatCaracteristiqueEnum;

/**
 * objet modele pour les caracteristique de type date
 *
 * @see fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.FormatCaracteristiqueEnum#DUREE
 */
public class ValeurCaracteristiqueEvenementMontant extends ValeurCaracteristiqueEvenementExterieur<Montant> {

    private static final long serialVersionUID = -4770676327422623963L;

    private Montant montant;

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#getFormat()
     */
    public FormatCaracteristiqueEnum getFormat() {
        return FormatCaracteristiqueEnum.MONTANT;
    }

    /**
     * réservé à hibernate
     */
    protected Montant getMontant() {
        return montant;
    }

    /**
     * réservé à hibernate
     */
    protected void setMontant(Montant value) {
        this.montant = value;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#getValue()
     */
    @Override
    public Montant getValue() {
        return this.montant;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#setValue()
     */
    @Override
    public void setValue(Montant value) {
        this.montant = value;
    }
}