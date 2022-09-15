/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.personne.model;

import fr.gouv.justice.cassiopee.invariant.personne.service.enumeration.TypePersonneEnum;

/**
 * Personne physique mineure (si auteur dans une affaire p�nale : mineure au moment des faits), m�me �mancip�e. Par
 * extension, jeune majeur (moins de 21 ans) lorsque suivi par le juge des enfants (JE) dans le cadre de la protection
 * du jeune majeur.
 */
public class PersonneMineure extends PersonnePhysique {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 739803365412519557L;

    /**
     * L'identifiant de la personne mineure permet d'assurer l'unicit� des mineurs dans la base de donn�es.
     */
    private Long identifiantUnicite;

    /**
     * Indique si la personne mineure s�est enfuie de son domicile l�gal, ou de l��tablissement de placement.
     */
    private boolean enFugue = false;

    /** Indicateur relatif aux mineurs �trangers isol�s. */
    private boolean absenceRLsurTerritoire = false;

    /**
     * Le commentaire permet d'enregistrer par exemple l'appartenance du mineur � une bande.
     */
    private String commentaire;

    /** Ann�e scolaire du mineur au moment de la saisie. */
    private String anneeScolaire;

    /** Classe dans laquelle le mineur est scolaris� au moment de la saisie. */
    private String classeScolarite;

    /** Etablissement dans lequel le mineur est scolaris� au moment de la saisie. */
    private String etablissementScolaire;

    /**
     * Quartier du domicile des parents du mineur. Le mineur y habite ou non. Rappel : pour un mineur, on appelle
     * "domicile" l'adresse postale de ses parents, et "r�sidence" l'adresse postale du mineur s'il est plac� hors du
     * foyer parental.
     */
    private String quartierDomicile;

    /** T�l�phone du domicile du mineur. */
    private String telephoneDomicile;

    /**
     * Quartier de la r�sidence du mineur (l� o� il habite ; peut �tre diff�rent du domicile des parents). Rappel : pour
     * un mineur, on appelle "domicile" l'adresse postale de ses parents, et "r�sidence" l'adresse postale du mineur
     * s'il est plac� hors du foyer parental.
     */
    private String quartierResidence;

    /** T�l�phone de la r�sidence du mineur. */
    private String telephoneResidence;

    /** Permet de savoir qui exerce l'autorit� parentale sur le mineur. */
    private ExerciceAutoriteParentale exerciceAutoriteParentale;

    /**
     * D�termine l'absence de repr�sentant l�gal sur le territoire
     *
     * @return l'absence de repr�sentant l�gal sur le territoire
     */
    public boolean isAbsenceRLsurTerritoire() {
        return absenceRLsurTerritoire;
    }

    /**
     * Pr�cise l'absence de repr�sentant l�gal sur le territoire
     *
     * @param absenceRLsurTerritoire
     *            l'absence de repr�sentant l�gal sur le territoire
     */
    public void setAbsenceRLsurTerritoire(boolean absenceRLsurTerritoire) {
        this.absenceRLsurTerritoire = absenceRLsurTerritoire;
    }

    /**
     * Retourne l'ann�e scolaire
     *
     * @return l'ann�e scolaire.
     */
    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    /**
     * Positionne l'ann�e scolaire
     *
     * @param anneeScolaire
     *            ann�e scolaire
     */
    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    /**
     * Retourne la classe de scolarit�
     *
     * @return la classe de scolarit�
     */
    public String getClasseScolarite() {
        return classeScolarite;
    }

    /**
     * Positionne la classe de scolarit�
     *
     * @param classeScolarite
     *            la classe de scolarit�.
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
     * Pr�cise si le mineur est en fugue
     *
     * @return vrai si le mineur est en fugue, faux sinon.
     */
    public boolean isEnFugue() {
        return enFugue;
    }

    /**
     * Positionne la pr�sence de la fugue
     *
     * @param enFugue
     *            la pr�sence d'une fugue.
     */
    public void setEnFugue(boolean enFugue) {
        this.enFugue = enFugue;
    }

    /**
     * Retourne l'�tablissement scolaire
     *
     * @return l'�tablissement scolaire.
     */
    public String getEtablissementScolaire() {
        return etablissementScolaire;
    }

    /**
     * Positionne l'�tablissement scolaire
     *
     * @param etablissementScolaire
     *            l'�tablissement scolaire.
     */
    public void setEtablissementScolaire(String etablissementScolaire) {
        this.etablissementScolaire = etablissementScolaire;
    }

    /**
     * Retourne l'exercice de l'autorit� parentale
     *
     * @return Returns l'exercice de l'autorit� parentale
     */
    public ExerciceAutoriteParentale getExerciceAutoriteParentale() {
        return exerciceAutoriteParentale;
    }

    /**
     * Positionne l'exercice de l'autorit� parentale
     *
     * @param exerciceAutoriteParentale
     *            l'exercice de l'autorit� parentale.
     */
    public void setExerciceAutoriteParentale(ExerciceAutoriteParentale exerciceAutoriteParentale) {
        this.exerciceAutoriteParentale = exerciceAutoriteParentale;
    }

    /**
     * Retourne l'identifiant d'unicit�
     *
     * @return l'identifiant d'unicit�.
     */
    public Long getIdentifiantUnicite() {
        return identifiantUnicite;
    }

    /**
     * Positionne l'identifiant d'unicit�
     *
     * @param identifiantUnicite
     *            l'identifiant d'unicit�.
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
     * Retourne le quartier de r�sidence
     *
     * @return le quartier de r�sidence.
     */
    public String getQuartierResidence() {
        return quartierResidence;
    }

    /**
     * Positionne le quartier de r�sidence
     *
     * @param quartierResidence
     *            le quartier de r�sidence.
     */
    public void setQuartierResidence(String quartierResidence) {
        this.quartierResidence = quartierResidence;
    }

    /**
     * Retourne le t�l�phone du domicile
     *
     * @return le t�l�phone du domicile.
     */
    public String getTelephoneDomicile() {
        return telephoneDomicile;
    }

    /**
     * Positionne le t�l�phone du domicile
     *
     * @param telephoneDomicile
     *            le t�l�phone du domicile.
     */
    public void setTelephoneDomicile(String telephoneDomicile) {
        this.telephoneDomicile = telephoneDomicile;
    }

    /**
     * Retourne le t�l�phone de r�sidence
     *
     * @return le t�l�phone de r�sidence.
     */
    public String getTelephoneResidence() {
        return telephoneResidence;
    }

    /**
     * Positionne le t�l�phone de r�sidence
     *
     * @param telephoneResidence
     *            le t�l�phone de r�sidence.
     */
    public void setTelephoneResidence(String telephoneResidence) {
        this.telephoneResidence = telephoneResidence;
    }

    @Override
    public TypePersonneEnum getTypePersonne() {
        return TypePersonneEnum.MINEURE;
    }

}
