package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import java.io.Serializable;

/**
 * classe intermédiaire entre Objet valeur caracteristique metier et le DTO correspondant
 */
public class ValeurCaracteristiquePrononcable implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4948071921208271590L;

    /**
     * ValeurCaract.: cette classe est abstraite;
     */
    private ValeurCaracteristique valeurCaracteristique;

    /**
     * numero d'ordre des caract.
     */
    private Integer numeroOrdre;

    /**
     * @return numeroOrdre
     */
    public Integer getNumeroOrdre() {
        return numeroOrdre;
    }

    /**
     * @param numeroOrdre
     */
    public void setNumeroOrdre(Integer numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

    /**
     * @return valeur
     */
    public ValeurCaracteristique getValeurCaracteristique() {
        return valeurCaracteristique;
    }

    /**
     * @param valeur
     */
    public void setValeurCaracteristique(ValeurCaracteristique valeur) {
        this.valeurCaracteristique = valeur;
    }

}
