/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model.peine;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.TypeValeurCaracteristiqueEnum;

/**
 * Valeur caract�ristique de type chaine
 */
public class ValeurCaracteristiquePeineChaine extends ValeurCaracteristiquePeineExterieur<String> {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 3057482680007167423L;

    private String chaine;

    /**
     * r�serv� � hibernate
     */
    protected String getChaine() {
        return chaine;
    }

    /**
     * r�serv� � hibernate
     */
    protected void setChaine(String chaine) {
        this.chaine = chaine;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.exterieur.model.peine.ValeurCaracteristiquePeineExterieur#getValue()
     */
    @Override
    public String getValue() {
        return this.chaine;
    }

    @Override
    public void setValue(String value) {
        this.chaine = value;

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.gouv.justice.cassiopee.invariant.peineoumesure.model.ValeurCaracteristiquePeine#getTypeValeurCaracteristiqueEnum
     * ()
     */
    public TypeValeurCaracteristiqueEnum getTypeValeurCaracteristiqueEnum() {
        return TypeValeurCaracteristiqueEnum.CHAINE;
    }
}
