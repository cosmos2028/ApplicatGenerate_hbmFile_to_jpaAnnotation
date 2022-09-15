/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.personne.model;

import fr.gouv.justice.cassiopee.commons.metier.model.Adresse;

/**
 * Adresse de la personne morale
 */
public class AdressePersonneMorale extends Adresse {

    private static final long serialVersionUID = 7084589264882798532L;

    /**
     * T�l�copie d'une personne morale
     */
    private String numeroTelecopie;

    /**
     * e-mail de la personne morale.
     */
    private String email;

    private String Telephone;

    /**
     * Retourne l'e-mail de la personne morale
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Positionne l'email de la personne morale
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retourne le num�ro de t�l�copie de la personne morale
     *
     * @return numeroTelecopie
     */
    public String getNumeroTelecopie() {
        return numeroTelecopie;
    }

    /**
     * Positionne le num�ro de t�l�copie de la personne morale
     *
     * @param numeroTelecopie
     */
    public void setNumeroTelecopie(String numeroTelecopie) {
        this.numeroTelecopie = numeroTelecopie;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return Telephone;
    }

    /**
     * @param telephone
     *            the telephone to set
     */
    public void setTelephone(String telephone) {
        Telephone = telephone;
    }
}
