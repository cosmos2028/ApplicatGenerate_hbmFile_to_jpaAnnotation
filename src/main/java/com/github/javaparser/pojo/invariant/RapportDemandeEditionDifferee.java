/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

/**
 * le rapport de composition pour une demande d'�dition diff�r�e
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
