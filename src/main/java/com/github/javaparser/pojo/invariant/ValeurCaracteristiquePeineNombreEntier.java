/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model.peine;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.TypeValeurCaracteristiqueEnum;

/**
 * Valeur caractéristique de type Nombre Entier
 */
public class ValeurCaracteristiquePeineNombreEntier extends ValeurCaracteristiquePeineExterieur<Long> {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = 42841313825459580L;

    private Long nombre;

    /**
     * réservé à hibernate
     */
    protected Long getNombre() {
        return nombre;
    }

    /**
     * réservé à hibernate
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
