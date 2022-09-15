/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.util.Date;

import fr.gouv.justice.cassiopee.commons.metier.model.Adresse;

/**
 * Adresse de la personne physique. Contient les champs de l' #Adresse g�n�rique, plus la date de d�claration et un
 * boolean indiquant si l'adresse est d�clar�e.
 */
public class AdressePersonnePhysique extends Adresse implements Cloneable {

    /**
     * identifiant de s�rialisation
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
     * Renvoie la Date de d�claration de l'adresse.
     *
     * @return une Date correspondant � la date de d�claration de l'adresse.
     */
    public Date getDateDeclaration() {
        return this.dateDeclaration;
    }

    /**
     * Valorise la Date de d�claration de l'adresse.
     *
     * @param dateDeclaration
     */
    public void setDateDeclaration(Date dateDeclaration) {
        this.dateDeclaration = dateDeclaration;
    }

    /**
     * Renvoie true s'il s'agit de l'adresse d�clar�e, false sinon. Utilis� seulement pour les auteurs.
     *
     * @return un Boolean indiquant si l'adresse est d�clar�e.
     */
    public boolean isAdresseDeclaree() {
        return adresseDeclaree;
    }

    /**
     * Positionne l'adresse d�clar�e Utilis� seulement pour les auteurs.
     *
     * @param adresseDeclaree
     *            adresse d�clar�e
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
