package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.io.Serializable;
import java.sql.Blob;

/**
 * InformationDebat
 *
 * @author cvb
 */
public class InformationDebat implements Serializable {

    private static final long serialVersionUID = 927652796958610837L;

    private Boolean avisQualificationModifiee;

    private String avisQualificationCommentaire;

    private Blob declarationAuteur;

    private Blob declarationPubliciteAuteur;

    private Blob declarationPubliciteAutres;

    private Blob declarationPubliciteAvocat;

    private Blob declarationPubliciteProcureur;

    private Blob declarationsAutres;

    private Blob declarationsAvocat;

    private Blob declarationRepresentantsLegauxOuSubstitues;

    private Blob requisitionsProcureur;

    private Short delaiAchevement;

    private Boolean enquetePrescrite;

    private Boolean presencePrefet;

    private Boolean presenceChefPolice;

    private DecisionSurAvocat decisionSurAvocat;

    private Boolean indicateurMentionPVDebatContradictoireMineur;

    /**
     * @return retourne avisQualificationCommentaire.
     */
    public String getAvisQualificationCommentaire() {
        return avisQualificationCommentaire;
    }

    /**
     * @param avisQualificationCommentaire
     *            affecte avisQualificationCommentaire
     */
    public void setAvisQualificationCommentaire(String avisQualificationCommentaire) {
        this.avisQualificationCommentaire = avisQualificationCommentaire;
    }

    /**
     * @return retourne avisQualificationModifiee.
     */
    public Boolean getAvisQualificationModifiee() {
        return avisQualificationModifiee;
    }

    /**
     * @param avisQualificationModifiee
     *            affecte avisQualificationModifiee
     */
    public void setAvisQualificationModifiee(Boolean avisQualificationModifiee) {
        this.avisQualificationModifiee = avisQualificationModifiee;
    }

    /**
     * @return retourne decisionSurAvocat.
     */
    public DecisionSurAvocat getDecisionSurAvocat() {
        return decisionSurAvocat;
    }

    /**
     * @param decisionSurAvocat
     *            affecte decisionSurAvocat
     */
    public void setDecisionSurAvocat(DecisionSurAvocat decisionSurAvocat) {
        this.decisionSurAvocat = decisionSurAvocat;
    }

    /**
     * @return retourne declarationAuteur.
     */
    public Blob getDeclarationAuteur() {
        return declarationAuteur;
    }

    /**
     * @param declarationAuteur
     *            affecte declarationAuteur
     */
    public void setDeclarationAuteur(Blob declarationAuteur) {
        this.declarationAuteur = declarationAuteur;
    }

    /**
     * @return retourne declarationPubliciteAuteur.
     */
    public Blob getDeclarationPubliciteAuteur() {
        return declarationPubliciteAuteur;
    }

    /**
     * @param declarationPubliciteAuteur
     *            affecte declarationPubliciteAuteur
     */
    public void setDeclarationPubliciteAuteur(Blob declarationPubliciteAuteur) {
        this.declarationPubliciteAuteur = declarationPubliciteAuteur;
    }

    /**
     * @return retourne declarationPubliciteAutres.
     */
    public Blob getDeclarationPubliciteAutres() {
        return declarationPubliciteAutres;
    }

    /**
     * @param declarationPubliciteAutres
     *            affecte declarationPubliciteAutres
     */
    public void setDeclarationPubliciteAutres(Blob declarationPubliciteAutres) {
        this.declarationPubliciteAutres = declarationPubliciteAutres;
    }

    /**
     * @return retourne declarationPubliciteAvocat.
     */
    public Blob getDeclarationPubliciteAvocat() {
        return declarationPubliciteAvocat;
    }

    /**
     * @param declarationPubliciteAvocat
     *            affecte declarationPubliciteAvocat
     */
    public void setDeclarationPubliciteAvocat(Blob declarationPubliciteAvocat) {
        this.declarationPubliciteAvocat = declarationPubliciteAvocat;
    }

    /**
     * @return retourne declarationPubliciteProcureur.
     */
    public Blob getDeclarationPubliciteProcureur() {
        return declarationPubliciteProcureur;
    }

    /**
     * @param declarationPubliciteProcureur
     *            affecte declarationPubliciteProcureur
     */
    public void setDeclarationPubliciteProcureur(Blob declarationPubliciteProcureur) {
        this.declarationPubliciteProcureur = declarationPubliciteProcureur;
    }

    /**
     * @return retourne declarationRepresentantsLegauxOuSubstitues.
     */
    public Blob getDeclarationRepresentantsLegauxOuSubstitues() {
        return declarationRepresentantsLegauxOuSubstitues;
    }

    /**
     * @param declarationRepresentantsLegauxOuSubstitues
     *            affecte declarationRepresentantsLegauxOuSubstitues
     */
    public void setDeclarationRepresentantsLegauxOuSubstitues(Blob declarationRepresentantsLegauxOuSubstitues) {
        this.declarationRepresentantsLegauxOuSubstitues = declarationRepresentantsLegauxOuSubstitues;
    }

    /**
     * @return retourne declarationsAutres.
     */
    public Blob getDeclarationsAutres() {
        return declarationsAutres;
    }

    /**
     * @param declarationsAutres
     *            affecte declarationsAutres
     */
    public void setDeclarationsAutres(Blob declarationsAutres) {
        this.declarationsAutres = declarationsAutres;
    }

    /**
     * @return retourne declarationsAvocat.
     */
    public Blob getDeclarationsAvocat() {
        return declarationsAvocat;
    }

    /**
     * @param declarationsAvocat
     *            affecte declarationsAvocat
     */
    public void setDeclarationsAvocat(Blob declarationsAvocat) {
        this.declarationsAvocat = declarationsAvocat;
    }

    /**
     * @return retourne delaiAchevement.
     */
    public Short getDelaiAchevement() {
        return delaiAchevement;
    }

    /**
     * @param delaiAchevement
     *            affecte delaiAchevement
     */
    public void setDelaiAchevement(Short delaiAchevement) {
        this.delaiAchevement = delaiAchevement;
    }

    /**
     * @return retourne enquetePrescrite.
     */
    public Boolean getEnquetePrescrite() {
        return enquetePrescrite;
    }

    /**
     * @param enquetePrescrite
     *            affecte enquetePrescrite
     */
    public void setEnquetePrescrite(Boolean enquetePrescrite) {
        this.enquetePrescrite = enquetePrescrite;
    }

    /**
     * @return Boolean
     */
    public Boolean getPresenceChefPolice() {
        return presenceChefPolice;
    }

    /**
     * @param presenceChefPolice
     */
    public void setPresenceChefPolice(Boolean presenceChefPolice) {
        this.presenceChefPolice = presenceChefPolice;
    }

    /**
     * @return Boolean
     */
    public Boolean getPresencePrefet() {
        return presencePrefet;
    }

    /**
     * @param presencePrefet
     */
    public void setPresencePrefet(Boolean presencePrefet) {
        this.presencePrefet = presencePrefet;
    }

    /**
     * @return Boolean
     */
    public Boolean getIndicateurMentionPVDebatContradictoireMineur() {
        return indicateurMentionPVDebatContradictoireMineur;
    }

    /**
     * @param indicateurMentionPVDebatContradictoireMineur
     */
    public void setIndicateurMentionPVDebatContradictoireMineur(Boolean indicateurMentionPVDebatContradictoireMineur) {
        this.indicateurMentionPVDebatContradictoireMineur = indicateurMentionPVDebatContradictoireMineur;
    }

    /**
     * @return Returns the requisitionsProcureur.
     */
    public Blob getRequisitionsProcureur() {
        return requisitionsProcureur;
    }

    /**
     * @param requisitionsProcureur
     *            The requisitionsProcureur to set.
     */
    public void setRequisitionsProcureur(Blob requisitionsProcureur) {
        this.requisitionsProcureur = requisitionsProcureur;
    }

}
