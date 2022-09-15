/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model.peine;

import fr.gouv.justice.cassiopee.common.util.DureeAnMoisJour;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.TypeValeurCaracteristiqueEnum;

/**
 * Valeur caract�ristique de type Dur�e
 */
public class ValeurCaracteristiquePeineDuree extends ValeurCaracteristiquePeineExterieur<DureeAnMoisJour> {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 1538539254156977956L;

    private DureeAnMoisJour duree;

    /**
     * r�serv� � hibernate
     */
    protected DureeAnMoisJour getDuree() {
        return duree;
    }

    /**
     * r�serv� � hibernate
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
