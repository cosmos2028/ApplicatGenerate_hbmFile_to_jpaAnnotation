/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.personne.model;

import fr.gouv.justice.cassiopee.common.util.DateIncomplete;
import fr.gouv.justice.cassiopee.processus.execution.model.EtatRequete;
import fr.gouv.justice.cassiopee.referentiel.codification.model.Civilite;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Commune;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Nationalite;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Pays;

/**
 * Cette classe permet de concerver les valeurs suite � une requ�te en rectification sur erreur mat�rielle pour une
 * identit�.<br>
 * Les valeurs corrig�es d'une identit� sont conserv�es suite � la requ�te en rectification en erreur mat�rielle, en
 * attendant sa validation.<br>
 * Les valeurs obsol�tes sont �galement conserv�es une fois la rectification accord�e.<br>
 * L'attribut etatRectification permet de d�terminer si le jugement a �t� effectu�.
 */
public class ErreurMaterielle implements java.io.Serializable {

    /**
     * Identifiant de s�rialisation
     */
    private static final long serialVersionUID = -6363278957095259510L;

    private Long id;

    private Commune communeNaissanceFrancaise;

    private String communeNaissanceEtrangere;

    private Nationalite nationalite1;

    private PersonnePhysique personne;

    private Civilite civilite;

    private Pays paysNaissance;

    private Nationalite nationalite2;

    private String nomNaissance;

    private String nomUsage;

    private String prenom;

    private String prenom2;

    private String prenom3;

    private DateIncomplete dateNaissance;

    private String nomPere;

    private String prenomsPere;

    private String nomMere;

    private String prenomsMere;

    private EtatRequete etatRectification;

    /**
     * Retourne le libell� de la commune de naissance, si elle est � l'�tranger, null sinon.
     *
     * @return le libell� de la commune de naissance.
     */
    public String getCommuneNaissanceEtrangere() {
        return communeNaissanceEtrangere;
    }

    /**
     * Positionne le libell� de la commune de naissance, si elle est � l'�tranger, null sinon.
     *
     * @param communeNaissanceEtrangere
     *            le libell� de la commune de naissance.
     */
    public void setCommuneNaissanceEtrangere(String communeNaissanceEtrangere) {
        this.communeNaissanceEtrangere = communeNaissanceEtrangere;
    }

    /**
     * Retourne la civilit� de la personne.
     *
     * @return la civilit� de la personne.
     */
    public Civilite getCivilite() {
        return civilite;
    }

    /**
     * Positionne la civilit� de la personne.
     *
     * @param civilite
     *            la civilit� de la personne.
     */
    public void setCivilite(Civilite civilite) {
        this.civilite = civilite;
    }

    /**
     * @return retourne la commune de naissance, si elle est fran�aise.
     */
    public Commune getCommuneNaissanceFrancaise() {
        return communeNaissanceFrancaise;
    }

    /**
     * @param communeNaissance
     *            affecte la commune de naissance, si elle est fran�aise.
     */
    public void setCommuneNaissanceFrancaise(Commune communeNaissance) {
        this.communeNaissanceFrancaise = communeNaissance;
    }

    /**
     * @return retourne la date de naissance.
     */
    public DateIncomplete getDateNaissance() {
        return dateNaissance;
    }

    /**
     * @param dateNaissance
     *            affecte la date de Naissance
     */
    public void setDateNaissance(DateIncomplete dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * @return retourne l'identifiant de l'Erreur Mat�rielle.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            affecte l'identifiant de l'Erreur Mat�rielle
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return retourne la 1�re nationalit�.
     */
    public Nationalite getNationalite1() {
        return nationalite1;
    }

    /**
     * @param nationalite1
     *            affecte la 1�re nationalit�
     */
    public void setNationalite1(Nationalite nationalite1) {
        this.nationalite1 = nationalite1;
    }

    /**
     * @return retourne la 2nde nationalit�.
     */
    public Nationalite getNationalite2() {
        return nationalite2;
    }

    /**
     * @param nationalite2
     *            affecte la 2nde nationalit�.
     */
    public void setNationalite2(Nationalite nationalite2) {
        this.nationalite2 = nationalite2;
    }

    /**
     * @return retourne le nom de la m�re.
     */
    public String getNomMere() {
        return nomMere;
    }

    /**
     * @param nomMere
     *            affecte le nom de la m�re.
     */
    public void setNomMere(String nomMere) {
        this.nomMere = nomMere;
    }

    /**
     * @return retourne le nom de naissance.
     */
    public String getNomNaissance() {
        return nomNaissance;
    }

    /**
     * @param nomNaissance
     *            affecte le nom de naissance.
     */
    public void setNomNaissance(String nomNaissance) {
        this.nomNaissance = nomNaissance;
    }

    /**
     * @return retourne le nom du p�re.
     */
    public String getNomPere() {
        return nomPere;
    }

    /**
     * @param nomPere
     *            affecte le nom du p�re.
     */
    public void setNomPere(String nomPere) {
        this.nomPere = nomPere;
    }

    /**
     * @return retourne le nom d'usage.
     */
    public String getNomUsage() {
        return nomUsage;
    }

    /**
     * @param nomUsage
     *            affecte le nom d'usage.
     */
    public void setNomUsage(String nomUsage) {
        this.nomUsage = nomUsage;
    }

    /**
     * @return retourne le pays de naissance
     */
    public Pays getPaysNaissance() {
        return paysNaissance;
    }

    /**
     * @param paysNaissance
     *            affecte le pays de naissance
     */
    public void setPaysNaissance(Pays paysNaissance) {
        this.paysNaissance = paysNaissance;
    }

    /**
     * @return retourne la personne physique pour laquelle les informations sont erron�es.
     */
    public PersonnePhysique getPersonne() {
        return personne;
    }

    /**
     * @param personne
     *            affecte la personne physique pour laquelle les informations sont erron�es.
     */
    public void setPersonne(PersonnePhysique personne) {
        this.personne = personne;
    }

    /**
     * @return retourne le 1er pr�nom de la personne.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom
     *            affecte le 1er pr�nom de la personne.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return retourne le 2nd pr�nom de la personne.
     */
    public String getPrenom2() {
        return prenom2;
    }

    /**
     * @param prenom2
     *            affecte le 2nd pr�nom de la personne.
     */
    public void setPrenom2(String prenom2) {
        this.prenom2 = prenom2;
    }

    /**
     * @return retourne le 3�me pr�nom de la personne.
     */
    public String getPrenom3() {
        return prenom3;
    }

    /**
     * @param prenom3
     *            affecte le 3�me pr�nom de la personne.
     */
    public void setPrenom3(String prenom3) {
        this.prenom3 = prenom3;
    }

    /**
     * @return retourne les pr�noms de la m�re.
     */
    public String getPrenomsMere() {
        return prenomsMere;
    }

    /**
     * @param prenomsMere
     *            affecte les pr�noms de la m�re.
     */
    public void setPrenomsMere(String prenomsMere) {
        this.prenomsMere = prenomsMere;
    }

    /**
     * @return retourne les pr�noms du p�re.
     */
    public String getPrenomsPere() {
        return prenomsPere;
    }

    /**
     * @param prenomsPere
     *            affecte les pr�noms du p�re.
     */
    public void setPrenomsPere(String prenomsPere) {
        this.prenomsPere = prenomsPere;
    }

    /**
     * Retourne l'�tat de la requ�te : demand�e ou accord�e<br>
     * Si elle est demand�e, cel� signifie que les donn�es de cette classe repr�sente les nouvelles valeurs corrig�es.<br>
     * Si elle est accord�e, elles repr�sentent les anciennes donn�es erron�es.
     *
     * @return l'�tat de la requ�te
     */
    public EtatRequete getEtatRectification() {
        return etatRectification;
    }

    /**
     * Positionne l'�tat de la requ�te : demand�e ou accord�e<br>
     * Si elle est demand�e, cel� signifie que les donn�es de cette classe repr�sente les nouvelles valeurs corrig�es.<br>
     * Si elle est accord�e, elles repr�sentent les anciennes donn�es erron�es.
     *
     * @param etatRectification
     *            l'�tat de la requ�te
     */
    public void setEtatRectification(EtatRequete etatRectification) {
        this.etatRectification = etatRectification;
    }

}
