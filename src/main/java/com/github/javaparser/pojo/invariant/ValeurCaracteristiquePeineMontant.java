/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model.peine;

import java.math.BigDecimal;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.TypeValeurCaracteristiqueEnum;

/**
 * Valeur caract�ristique de type Montant
 */
public class ValeurCaracteristiquePeineMontant extends ValeurCaracteristiquePeineExterieur<BigDecimal> {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = -6624723603556809792L;

    private BigDecimal montant;

    /**
     * r�serv� � hibernate
     */
    protected BigDecimal getMontant() {
        return montant;
    }

    /**
     * r�serv� � hibernate
     */
    protected void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    @Override
    public BigDecimal getValue() {
        return this.montant;
    }

    @Override
    public void setValue(BigDecimal value) {
        this.montant = value;

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.gouv.justice.cassiopee.invariant.peineoumesure.model.ValeurCaracteristiquePeine#getTypeValeurCaracteristiqueEnum
     * ()
     */
    public TypeValeurCaracteristiqueEnum getTypeValeurCaracteristiqueEnum() {
        return TypeValeurCaracteristiqueEnum.MONTANT;
    }
}
