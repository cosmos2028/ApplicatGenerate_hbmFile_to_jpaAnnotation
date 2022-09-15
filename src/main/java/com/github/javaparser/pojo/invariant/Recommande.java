/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;

/**
 * Cette classe represente le pre-imprime fourni par la Poste qui est utilise pour certaines editions
 */
public class Recommande implements java.io.Serializable {

    /**
     * Identifiant version pour la s�rialisation.
     */
    private static final long serialVersionUID = -6541985777155601077L;

    private static final HashCodeEqualsHelper<Recommande> HE_HELPER = HashCodeEqualsHelper.of(Recommande.class, Recommande::getCodeTypeRecommande, Recommande::getNumeroRecommande);

    private String numeroRecommande;

    private String codeTypeRecommande;

    /**
     * @return Returns the codeTypeRecommande.
     */
    public String getCodeTypeRecommande() {
        return codeTypeRecommande;
    }

    /**
     * @param codeTypeRecommande
     *            The codeTypeRecommande to set.
     */
    public void setCodeTypeRecommande(String codeTypeRecommande) {
        this.codeTypeRecommande = codeTypeRecommande;
    }

    /**
     * @return Returns the numeroRecommande.
     */
    public String getNumeroRecommande() {
        return numeroRecommande;
    }

    /**
     * @param numeroRecommande
     *            The numeroRecommande to set.
     */
    public void setNumeroRecommande(String numeroRecommande) {
        this.numeroRecommande = numeroRecommande;
    }


    @Override
    public boolean equals(Object obj){
        return HE_HELPER.areEqual(this, obj);
    }

    @Override
    public int hashCode() {
        return HE_HELPER.getHashCode(this);
    }

}
