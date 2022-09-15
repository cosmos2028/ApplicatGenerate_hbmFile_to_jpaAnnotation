package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import java.math.BigDecimal;

/**
 * Valeur caractéristique de type Montant
 */
public class ValeurCaracteristiqueMontant extends ValeurCaracteristique<BigDecimal> {

    /**
     *
     */
    private static final long serialVersionUID = -6624723603556809792L;

    private BigDecimal montant;

    /**
     * réservé à hibernate
     */
    protected BigDecimal getMontant() {
        return montant;
    }

    /**
     * réservé à hibernate
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
