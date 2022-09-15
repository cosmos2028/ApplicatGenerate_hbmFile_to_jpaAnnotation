/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;

/**
 * le rapport de composition pour une demande d'édition différée
 */
public class RapportErreurEdition implements java.io.Serializable {

    /**
     * Identifiant version pour la sérialisation.
     */
    private static final long serialVersionUID = -6541985777155601077L;

    private static final HashCodeEqualsHelper<RapportErreurEdition> HE_HELPER = HashCodeEqualsHelper.of(RapportErreurEdition.class, RapportErreurEdition::getMessageErreur);
    private String messageErreur;

    /**
     * retourne le message en cas d'erreur de composition
     *
     * @return le message en cas d'erreur de composition
     */
    public String getMessageErreur() {
        return messageErreur;
    }

    /**
     * renseigne le message en cas d'erreur de composition
     *
     * @param messageErreur
     *            le message en cas d'erreur de composition
     */
    public void setMessageErreur(String messageErreur) {
        this.messageErreur = messageErreur;
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
