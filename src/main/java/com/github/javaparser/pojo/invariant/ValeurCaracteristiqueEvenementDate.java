/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement;

import java.util.Date;

import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.FormatCaracteristiqueEnum;

/**
 * objet modele pour les caracteristique de type date
 */
public class ValeurCaracteristiqueEvenementDate extends ValeurCaracteristiqueEvenementExterieur<Date> {

    /** Identifiant version pour la s�rialisation */
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
     * r�serv� � hibernate
     */
    protected Date getValeurDate() {
        return valeurDate;
    }

    /**
     * r�serv� � hibernate
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