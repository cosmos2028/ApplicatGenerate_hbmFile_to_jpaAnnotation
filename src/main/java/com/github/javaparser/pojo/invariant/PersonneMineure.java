/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.personne.model;

import fr.gouv.justice.cassiopee.invariant.personne.service.enumeration.TypePersonneEnum;

/**
 * Personne physique mineure (si auteur dans une affaire pénale : mineure au moment des faits), même émancipée. Par
 * extension, jeune majeur (moins de 21 ans) lorsque suivi par le juge des enfants (JE) dans le cadre de la protection
 * du jeune majeur.
 */
public class PersonneMineure extends PersonnePhysique {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = 739803365412519557L;

    /**
     * L'identifiant de la personne mineure permet d'assurer l'unicité des mineurs dans la base de données.
     */
    private Long identifiantUnicite;

    /**
     * Indique si la personne mineure s’est enfuie de son domicile légal, ou de l’établissement de placement.
     */
    private boolean enFugue = false;

    /** Indicateur relatif aux mineurs étrangers isolés. */
    private boolean absenceRLsurTerritoire = false;

    /**
     * Le commentaire permet d'enregistrer par exemple l'appartenance du mineur à une bande.
     */
    private String commentaire;

    /** Année scolaire du mineur au moment de la saisie. */
    private String anneeScolaire;

    /** Classe dans laquelle le mineur est scolarisé au moment de la saisie. */
    private String classeScolarite;

    /** Etablissement dans lequel le mineur est scolarisé au moment de la saisie. */
    private String etablissementScolaire;

    /**
     * Quartier du domicile des parents du mineur. Le mineur y habite ou non. Rappel : pour un mineur, on appelle
     * "domicile" l'adresse postale de ses parents, et "résidence" l'adresse postale du mineur s'il est placé hors du
     * foyer parental.
     */
    private String quartierDomicile;

    /** Téléphone du domicile du mineur. */
    private String telephoneDomicile;

    /**
     * Quartier de la résidence du mineur (là où il habite ; peut être différent du domicile des parents). Rappel : pour
     * un mineur, on appelle "domicile" l'adresse postale de ses parents, et "résidence" l'adresse postale du mineur
     * s'il est placé hors du foyer parental.
     */
    private String quartierResidence;

    /** Téléphone de la résidence du mineur. */
    private String telephoneResidence;

    /** Permet de savoir qui exerce l'autorité parentale sur le mineur. */
    private ExerciceAutoriteParentale exerciceAutoriteParentale;

    /**
     * Détermine l'absence de représentant légal sur le territoire
     *
     * @return l'absence de représentant légal sur le territoire
     */
    public boolean isAbsenceRLsurTerritoire() {
        return absenceRLsurTerritoire;
    }

    /**
     * Précise l'absence de représentant légal sur le territoire
     *
     * @param absenceRLsurTerritoire
     *            l'absence de représentant légal sur le territoire
     */
    public void setAbsenceRLsurTerritoire(boolean absenceRLsurTerritoire) {
        this.absenceRLsurTerritoire = absenceRLsurTerritoire;
    }

    /**
     * Retourne l'année scolaire
     *
     * @return l'année scolaire.
     */
    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    /**
     * Positionne l'année scolaire
     *
     * @param anneeScolaire
     *            année scolaire
     */
    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    /**
     * Retourne la classe de scolarité
     *
     * @return la classe de scolarité
     */
    public String getClasseScolarite() {
        return classeScolarite;
    }

    /**
     * Positionne la classe de scolarité
     *
     * @param classeScolarite
     *            la classe de scolarité.
     */
    public void setClasseScolarite(String classeScolarite) {
        this.classeScolarite = classeScolarite;
    }

    /**
     * Retourne le commentaire
     *
     * @return le commentaire.
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * Positionne le commentaire
     *
     * @param commentaire
     *            Retourne le commentaire.
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * Précise si le mineur est en fugue
     *
     * @return vrai si le mineur est en fugue, faux sinon.
     */
    public boolean isEnFugue() {
        return enFugue;
    }

    /**
     * Positionne la présence de la fugue
     *
     * @param enFugue
     *            la présence d'une fugue.
     */
    public void setEnFugue(boolean enFugue) {
        this.enFugue = enFugue;
    }

    /**
     * Retourne l'établissement scolaire
     *
     * @return l'établissement scolaire.
     */
    public String getEtablissementScolaire() {
        return etablissementScolaire;
    }

    /**
     * Positionne l'établissement scolaire
     *
     * @param etablissementScolaire
     *            l'établissement scolaire.
     */
    public void setEtablissementScolaire(String etablissementScolaire) {
        this.etablissementScolaire = etablissementScolaire;
    }

    /**
     * Retourne l'exercice de l'autorité parentale
     *
     * @return Returns l'exercice de l'autorité parentale
     */
    public ExerciceAutoriteParentale getExerciceAutoriteParentale() {
        return exerciceAutoriteParentale;
    }

    /**
     * Positionne l'exercice de l'autorité parentale
     *
     * @param exerciceAutoriteParentale
     *            l'exercice de l'autorité parentale.
     */
    public void setExerciceAutoriteParentale(ExerciceAutoriteParentale exerciceAutoriteParentale) {
        this.exerciceAutoriteParentale = exerciceAutoriteParentale;
    }

    /**
     * Retourne l'identifiant d'unicité
     *
     * @return l'identifiant d'unicité.
     */
    public Long getIdentifiantUnicite() {
        return identifiantUnicite;
    }

    /**
     * Positionne l'identifiant d'unicité
     *
     * @param identifiantUnicite
     *            l'identifiant d'unicité.
     */
    public void setIdentifiantUnicite(Long identifiantUnicite) {
        this.identifiantUnicite = identifiantUnicite;
    }

    /**
     * Retourne le quartier domicile
     *
     * @return le quartier domicile.
     */
    public String getQuartierDomicile() {
        return quartierDomicile;
    }

    /**
     * Positionne le quartier domicile
     *
     * @param quartierDomicile
     *            le quartier domicile.
     */
    public void setQuartierDomicile(String quartierDomicile) {
        this.quartierDomicile = quartierDomicile;
    }

    /**
     * Retourne le quartier de résidence
     *
     * @return le quartier de résidence.
     */
    public String getQuartierResidence() {
        return quartierResidence;
    }

    /**
     * Positionne le quartier de résidence
     *
     * @param quartierResidence
     *            le quartier de résidence.
     */
    public void setQuartierResidence(String quartierResidence) {
        this.quartierResidence = quartierResidence;
    }

    /**
     * Retourne le téléphone du domicile
     *
     * @return le téléphone du domicile.
     */
    public String getTelephoneDomicile() {
        return telephoneDomicile;
    }

    /**
     * Positionne le téléphone du domicile
     *
     * @param telephoneDomicile
     *            le téléphone du domicile.
     */
    public void setTelephoneDomicile(String telephoneDomicile) {
        this.telephoneDomicile = telephoneDomicile;
    }

    /**
     * Retourne le téléphone de résidence
     *
     * @return le téléphone de résidence.
     */
    public String getTelephoneResidence() {
        return telephoneResidence;
    }

    /**
     * Positionne le téléphone de résidence
     *
     * @param telephoneResidence
     *            le téléphone de résidence.
     */
    public void setTelephoneResidence(String telephoneResidence) {
        this.telephoneResidence = telephoneResidence;
    }

    @Override
    public TypePersonneEnum getTypePersonne() {
        return TypePersonneEnum.MINEURE;
    }

}
