/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice. Il ne peut être utilisé, reproduit ou
 * divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.io.Serializable;

public class PersonneLienJuridiqueBean implements Serializable {

    /**
     * Identifiant pour la serialization
     */
    private static final long serialVersionUID = -7823933593102255358L;

    private Personne personne;

    private LienPersonnes lienPersonnes;

    public PersonneLienJuridiqueBean(Personne personne, LienPersonnes lienPersonnes) {
        this.personne = personne;
        this.lienPersonnes = lienPersonnes;
    }

    /**
     * @return Returns the lienJuridique.
     */
    public LienPersonnes getLienPersonnes() {
        return this.lienPersonnes;
    }

    /**
     * @param lienPersonnes
     *            The lienPersonnes to set.
     */
    public void setLienPersonnes(LienPersonnes lienPersonnes) {
        this.lienPersonnes = lienPersonnes;
    }

    /**
     * @return Returns the personne.
     */
    public Personne getPersonne() {
        return personne;
    }

    /**
     * @param personne
     *            The personne to set.
     */
    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

}
