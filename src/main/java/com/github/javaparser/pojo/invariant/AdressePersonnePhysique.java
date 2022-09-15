/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.util.Date;

import fr.gouv.justice.cassiopee.commons.metier.model.Adresse;

/**
 * Adresse de la personne physique. Contient les champs de l' #Adresse générique, plus la date de déclaration et un
 * boolean indiquant si l'adresse est déclarée.
 */
public class AdressePersonnePhysique extends Adresse implements Cloneable {

    /**
     * identifiant de sérialisation
     */
    private static final long serialVersionUID = 7843946257728851510L;

    private PersonnePhysique personne;

    private Date dateDeclaration;

    private boolean adresseDeclaree;

    private TypeAdresse typeAdresse;

    private String email;

    /**
     * Retourne la personne.
     *
     * @return la personne.
     */
    public PersonnePhysique getPersonne() {
        return personne;
    }

    /**
     * Positionne la personne.
     *
     * @param personne
     *            la personne.
     */
    public void setPersonne(PersonnePhysique personne) {
        this.personne = personne;
    }

    /**
     * Renvoie la Date de déclaration de l'adresse.
     *
     * @return une Date correspondant à la date de déclaration de l'adresse.
     */
    public Date getDateDeclaration() {
        return this.dateDeclaration;
    }

    /**
     * Valorise la Date de déclaration de l'adresse.
     *
     * @param dateDeclaration
     */
    public void setDateDeclaration(Date dateDeclaration) {
        this.dateDeclaration = dateDeclaration;
    }

    /**
     * Renvoie true s'il s'agit de l'adresse déclarée, false sinon. Utilisé seulement pour les auteurs.
     *
     * @return un Boolean indiquant si l'adresse est déclarée.
     */
    public boolean isAdresseDeclaree() {
        return adresseDeclaree;
    }

    /**
     * Positionne l'adresse déclarée Utilisé seulement pour les auteurs.
     *
     * @param adresseDeclaree
     *            adresse déclarée
     */
    public void setAdresseDeclaree(boolean adresseDeclaree) {
        this.adresseDeclaree = adresseDeclaree;
    }

    /**
     * Retourne le type d'adresse : domicile, professionelle...
     *
     * @return le type d'adresse
     */
    public TypeAdresse getTypeAdresse() {
        return typeAdresse;
    }

    /**
     * Retourne l'adresse email
     *
     * @return l'adresse email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Valorise l'adresse email
     *
     * @param email
     *            l'adresse email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Positionne le type d'adresse : domicile, professionelle...
     *
     * @param typeAdresse
     *            Tle type d'adresse
     */
    public void setTypeAdresse(TypeAdresse typeAdresse) {
        this.typeAdresse = typeAdresse;
    }

    /**
     * Duplique l'adresse
     *
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException {
        AdressePersonnePhysique newAdresse = (AdressePersonnePhysique) super.clone();
        newAdresse.setPersonne(null);
        if (dateDeclaration != null) {
            newAdresse.setDateDeclaration((Date) dateDeclaration.clone());
        }
        return newAdresse;
    }
}
