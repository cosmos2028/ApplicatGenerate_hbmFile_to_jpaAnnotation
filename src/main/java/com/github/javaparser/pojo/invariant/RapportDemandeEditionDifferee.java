/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

/**
 * le rapport de composition pour une demande d'édition différée
 */
public class RapportDemandeEditionDifferee {

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

}
