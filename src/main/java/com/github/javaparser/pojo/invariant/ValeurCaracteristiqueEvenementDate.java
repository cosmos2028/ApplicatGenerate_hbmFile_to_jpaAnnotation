/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement;

import java.util.Date;

import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.FormatCaracteristiqueEnum;

/**
 * objet modele pour les caracteristique de type date
 */
public class ValeurCaracteristiqueEvenementDate extends ValeurCaracteristiqueEvenementExterieur<Date> {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = -8684048943793856995L;

    private Date valeurDate;

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#getFormat()
     */
    public FormatCaracteristiqueEnum getFormat() {
        return FormatCaracteristiqueEnum.DATE;
    }

    /**
     * réservé à hibernate
     */
    protected Date getValeurDate() {
        return valeurDate;
    }

    /**
     * réservé à hibernate
     */
    protected void setValeurDate(Date valeurDate) {
        this.valeurDate = valeurDate;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#getValue()
     */
    @Override
    public Date getValue() {
        return this.valeurDate;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement#setValue()
     */
    @Override
    public void setValue(Date value) {
        this.valeurDate = value;
    }
}