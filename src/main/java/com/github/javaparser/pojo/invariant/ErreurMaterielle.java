/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.personne.model;

import fr.gouv.justice.cassiopee.common.util.DateIncomplete;
import fr.gouv.justice.cassiopee.processus.execution.model.EtatRequete;
import fr.gouv.justice.cassiopee.referentiel.codification.model.Civilite;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Commune;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Nationalite;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Pays;

/**
 * Cette classe permet de concerver les valeurs suite à une requête en rectification sur erreur matérielle pour une
 * identité.<br>
 * Les valeurs corrigées d'une identité sont conservées suite à la requête en rectification en erreur matérielle, en
 * attendant sa validation.<br>
 * Les valeurs obsolètes sont également conservées une fois la rectification accordée.<br>
 * L'attribut etatRectification permet de déterminer si le jugement a été effectué.
 */
public class ErreurMaterielle implements java.io.Serializable {

    /**
     * Identifiant de sérialisation
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
     * Retourne le libellé de la commune de naissance, si elle est à l'étranger, null sinon.
     *
     * @return le libellé de la commune de naissance.
     */
    public String getCommuneNaissanceEtrangere() {
        return communeNaissanceEtrangere;
    }

    /**
     * Positionne le libellé de la commune de naissance, si elle est à l'étranger, null sinon.
     *
     * @param communeNaissanceEtrangere
     *            le libellé de la commune de naissance.
     */
    public void setCommuneNaissanceEtrangere(String communeNaissanceEtrangere) {
        this.communeNaissanceEtrangere = communeNaissanceEtrangere;
    }

    /**
     * Retourne la civilité de la personne.
     *
     * @return la civilité de la personne.
     */
    public Civilite getCivilite() {
        return civilite;
    }

    /**
     * Positionne la civilité de la personne.
     *
     * @param civilite
     *            la civilité de la personne.
     */
    public void setCivilite(Civilite civilite) {
        this.civilite = civilite;
    }

    /**
     * @return retourne la commune de naissance, si elle est française.
     */
    public Commune getCommuneNaissanceFrancaise() {
        return communeNaissanceFrancaise;
    }

    /**
     * @param communeNaissance
     *            affecte la commune de naissance, si elle est française.
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
     * @return retourne l'identifiant de l'Erreur Matérielle.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            affecte l'identifiant de l'Erreur Matérielle
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return retourne la 1ère nationalité.
     */
    public Nationalite getNationalite1() {
        return nationalite1;
    }

    /**
     * @param nationalite1
     *            affecte la 1ère nationalité
     */
    public void setNationalite1(Nationalite nationalite1) {
        this.nationalite1 = nationalite1;
    }

    /**
     * @return retourne la 2nde nationalité.
     */
    public Nationalite getNationalite2() {
        return nationalite2;
    }

    /**
     * @param nationalite2
     *            affecte la 2nde nationalité.
     */
    public void setNationalite2(Nationalite nationalite2) {
        this.nationalite2 = nationalite2;
    }

    /**
     * @return retourne le nom de la mère.
     */
    public String getNomMere() {
        return nomMere;
    }

    /**
     * @param nomMere
     *            affecte le nom de la mère.
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
     * @return retourne le nom du père.
     */
    public String getNomPere() {
        return nomPere;
    }

    /**
     * @param nomPere
     *            affecte le nom du père.
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
     * @return retourne la personne physique pour laquelle les informations sont erronées.
     */
    public PersonnePhysique getPersonne() {
        return personne;
    }

    /**
     * @param personne
     *            affecte la personne physique pour laquelle les informations sont erronées.
     */
    public void setPersonne(PersonnePhysique personne) {
        this.personne = personne;
    }

    /**
     * @return retourne le 1er prénom de la personne.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom
     *            affecte le 1er prénom de la personne.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return retourne le 2nd prénom de la personne.
     */
    public String getPrenom2() {
        return prenom2;
    }

    /**
     * @param prenom2
     *            affecte le 2nd prénom de la personne.
     */
    public void setPrenom2(String prenom2) {
        this.prenom2 = prenom2;
    }

    /**
     * @return retourne le 3ème prénom de la personne.
     */
    public String getPrenom3() {
        return prenom3;
    }

    /**
     * @param prenom3
     *            affecte le 3ème prénom de la personne.
     */
    public void setPrenom3(String prenom3) {
        this.prenom3 = prenom3;
    }

    /**
     * @return retourne les prénoms de la mère.
     */
    public String getPrenomsMere() {
        return prenomsMere;
    }

    /**
     * @param prenomsMere
     *            affecte les prénoms de la mère.
     */
    public void setPrenomsMere(String prenomsMere) {
        this.prenomsMere = prenomsMere;
    }

    /**
     * @return retourne les prénoms du père.
     */
    public String getPrenomsPere() {
        return prenomsPere;
    }

    /**
     * @param prenomsPere
     *            affecte les prénoms du père.
     */
    public void setPrenomsPere(String prenomsPere) {
        this.prenomsPere = prenomsPere;
    }

    /**
     * Retourne l'état de la requête : demandée ou accordée<br>
     * Si elle est demandée, celà signifie que les données de cette classe représente les nouvelles valeurs corrigées.<br>
     * Si elle est accordée, elles représentent les anciennes données erronées.
     *
     * @return l'état de la requête
     */
    public EtatRequete getEtatRectification() {
        return etatRectification;
    }

    /**
     * Positionne l'état de la requête : demandée ou accordée<br>
     * Si elle est demandée, celà signifie que les données de cette classe représente les nouvelles valeurs corrigées.<br>
     * Si elle est accordée, elles représentent les anciennes données erronées.
     *
     * @param etatRectification
     *            l'état de la requête
     */
    public void setEtatRectification(EtatRequete etatRectification) {
        this.etatRectification = etatRectification;
    }

}
