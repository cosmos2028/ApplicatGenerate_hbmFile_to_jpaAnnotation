/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.personne.model;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;
import fr.gouv.justice.cassiopee.invariant.affaire.model.Compte;

/**
 * Cl� composite
 */
public class PersonneAffCompteId implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3782028669097965105L;

    private static final HashCodeEqualsHelper<PersonneAffCompteId> HE_HELPER = HashCodeEqualsHelper.of(PersonneAffCompteId.class, PersonneAffCompteId::getIdCompte,
            PersonneAffCompteId::getIdPersonne);


    private Personne idPersonne;

    private Compte idCompte;

    /**
     * @return fkPerPerAffComPersonne
     */
    public Personne getIdPersonne() {
        return this.idPersonne;
    }

    /**
     * @param fkPerPerAffComPersonne
     */
    public void setIdPersonne(Personne fkPerPerAffComPersonne) {
        this.idPersonne = fkPerPerAffComPersonne;
    }

    @Override
    public boolean equals(final Object other) {
        return HE_HELPER.areEqual(this, other);
    }

    @Override
    public int hashCode() {
        return HE_HELPER.getHashCode(this);
    }

    public Compte getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Compte idCompte) {
        this.idCompte = idCompte;
    }

}
