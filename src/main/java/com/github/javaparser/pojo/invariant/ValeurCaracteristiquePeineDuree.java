/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model.peine;

import fr.gouv.justice.cassiopee.common.util.DureeAnMoisJour;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.TypeValeurCaracteristiqueEnum;

/**
 * Valeur caractéristique de type Durée
 */
public class ValeurCaracteristiquePeineDuree extends ValeurCaracteristiquePeineExterieur<DureeAnMoisJour> {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = 1538539254156977956L;

    private DureeAnMoisJour duree;

    /**
     * réservé à hibernate
     */
    protected DureeAnMoisJour getDuree() {
        return duree;
    }

    /**
     * réservé à hibernate
     */
    protected void setDuree(DureeAnMoisJour duree) {
        this.duree = duree;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.peineoumesure.model.ValeurCaracteristiquePeine#getValue()
     */
    @Override
    public DureeAnMoisJour getValue() {
        return this.duree;
    }

    @Override
    public void setValue(DureeAnMoisJour value) {
        this.duree = value;

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.gouv.justice.cassiopee.invariant.peineoumesure.model.ValeurCaracteristiquePeine#getTypeValeurCaracteristiqueEnum
     * ()
     */
    public TypeValeurCaracteristiqueEnum getTypeValeurCaracteristiqueEnum() {
        return TypeValeurCaracteristiqueEnum.DUREE;
    }
}
