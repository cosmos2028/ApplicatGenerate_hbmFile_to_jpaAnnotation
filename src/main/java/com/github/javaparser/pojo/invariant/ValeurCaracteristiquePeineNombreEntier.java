/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model.peine;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.TypeValeurCaracteristiqueEnum;

/**
 * Valeur caract�ristique de type Nombre Entier
 */
public class ValeurCaracteristiquePeineNombreEntier extends ValeurCaracteristiquePeineExterieur<Long> {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 42841313825459580L;

    private Long nombre;

    /**
     * r�serv� � hibernate
     */
    protected Long getNombre() {
        return nombre;
    }

    /**
     * r�serv� � hibernate
     */
    protected void setNombre(Long nombre) {
        this.nombre = nombre;
    }

    @Override
    public Long getValue() {
        return this.nombre;
    }

    @Override
    public void setValue(Long value) {
        this.nombre = value;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.gouv.justice.cassiopee.invariant.peineoumesure.model.ValeurCaracteristiquePeine#getTypeValeurCaracteristiqueEnum
     * ()
     */
    public TypeValeurCaracteristiqueEnum getTypeValeurCaracteristiqueEnum() {
        return TypeValeurCaracteristiqueEnum.NOMBRE_ENTIER;
    }
}
