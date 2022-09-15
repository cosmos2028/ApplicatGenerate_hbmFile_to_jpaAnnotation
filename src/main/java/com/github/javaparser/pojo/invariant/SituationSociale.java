/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import fr.gouv.justice.cassiopee.referentiel.codification.model.Ape;
import fr.gouv.justice.cassiopee.referentiel.codification.model.Csp;
import fr.gouv.justice.cassiopee.referentiel.codification.model.Decoration;
import fr.gouv.justice.cassiopee.referentiel.codification.model.NiveauEtude;
import fr.gouv.justice.cassiopee.referentiel.codification.model.SituationEmploi;
import fr.gouv.justice.cassiopee.referentiel.codification.model.SituationMilitaire;
import fr.gouv.justice.cassiopee.referentiel.codification.model.StatutParticulier;

/**
 * Donn�es relatives � la situation sociale de la personne auteur : niveau d'�tudes, situation familiale, situation �
 * l'�gard de l'emploi...
 */

public class SituationSociale implements Serializable, Cloneable {

    private static final long serialVersionUID = -6498359729669701457L;

    /**
     * Cet attribut est conserv� uniquement pour la reprise de l?existant. En effet, il n?y a plus d?appel�s. Pour les
     * militaires de carri�re, l'information est enregistr�e dans la zone profession.
     */
    private SituationMilitaire situationMilitaire;

    /**
     * Nombre d?enfants de la personne physique. Une telle zone peut �tre utilis�e pour qualifier l?importance d?une
     * fratrie, le nombre d?enfants l�gitimes, � charge, ? C?est le contexte qui en d�termine la signification. Donn�e
     * utile � l?instruction et au jugement.
     */
    private Integer nombreEnfants;

    /**
     * Capacit� d?une personne � s?exprimer tant par la lecture que par l?�crit, dans la langue fran�aise. Vaut Vrai si
     * la personne parle et lit le fran�ais. Vaut Faux sinon. Dans ce cas, la pr�sence d'un interpr�te sera requise lors
     * de toute comparution. Donn�e utile dans les proc�dures de traitement direct, et pour les JI et JE.
     */
    private Boolean francaisLuEcrit;

    /** D�coration obtenue. */
    private Set<Decoration> decorations = new HashSet<Decoration>();

    /**
     * Ce statut entra�ne l?immunit�, qui peut faire l?objet d?une demande de lev�e d?immunit� aupr�s de l?autorit�
     * comp�tente. Exemples : d�put�, s�nateur, consul, ambassadeur.
     */
    private StatutParticulier statutParticulier;

    /** Situation par rapport � l?emploi. */
    private SituationEmploi situationEmploi;

    /**
     * Code NAF (Nature d?activit� fran�aise) de la personne auteur ou mineur signal�. La nomenclature d?activit�s
     * fran�aise (NAF) est la nouvelle nomenclature nationale statistique d?activit�s qui se substitue depuis le 1er
     * janvier 1993 � la NAP de 1973 (nomenclature d?activit�s et de produits) pour sa partie �activit�s �. Elle fait
     * l?objet d?un d�cret commun avec la CPF (classification des produits fran�ais) qui oblige l?ensemble des
     * organismes publics � utiliser la nomenclature (ou ses d�riv�es) dans les textes officiels, d�cisions, documents,
     * travaux et �tudes. Le code caract�risant l?activit� principale exerc�e par les unit�s �conomiques (code APE),
     * attribu� par l?INSEE, est d�termin� � partir du niveau le plus d�taill� de la NAF.
     */
    private Ape ape;

    /**
     * Code de la cat�gorie socioprofessionnelle (CSP, nomm� aussi CS-ESE : cat�gorie socio professionnelle des emplois
     * des salari�s d?entreprises). Les niveaux des cat�gories socioprofessionnelles (niveaux 5, 23) sont un
     * regroupement direct du niveau le plus fin des professions (niveau 350). Le niveau agr�g� contient 5 postes cod�s
     * chacun sur 1 seul caract�re.
     */
    private Csp csp;

    /**
     * Code du niveau d?�tudes, d?instruction d?une personne physique. Codification issue de l?�ducation nationale.
     * Concerne les personnes majeures ET les personnes mineures. Exemples : CAP, BEP, BAC ? Donn�e notamment utile au
     * JE en assistance �ducative.
     */
    private NiveauEtude niveauEtude;

    /**
     * @return ape
     */
    public Ape getApe() {
        return ape;
    }

    /**
     * @param ape
     */
    public void setApe(Ape ape) {
        this.ape = ape;
    }

    /**
     * @return csp
     */
    public fr.gouv.justice.cassiopee.referentiel.codification.model.Csp getCsp() {
        return csp;
    }

    /**
     * @param csp
     *            affecte csp
     */
    public void setCsp(Csp csp) {
        this.csp = csp;
    }

    /**
     * @return decorations
     */
    public Set<Decoration> getDecorations() {
        return decorations;
    }

    /**
     * @param decorations
     *            affecte decorations
     */
    public void setDecorations(Set<Decoration> decorations) {
        this.decorations = decorations;
    }

    /**
     * @return niveauEtude
     */
    public NiveauEtude getNiveauEtude() {
        return niveauEtude;
    }

    /**
     * @param niveauEtude
     *            affecte niveauEtude
     */
    public void setNiveauEtude(NiveauEtude niveauEtude) {
        this.niveauEtude = niveauEtude;
    }

    /**
     * @return francaisLuEcrit
     */
    public Boolean getFrancaisLuEcrit() {
        return francaisLuEcrit;
    }

    /**
     * @param sitSocialeFrancaisLuEcrit
     *            affecte francaisLuEcrit
     */
    public void setFrancaisLuEcrit(Boolean sitSocialeFrancaisLuEcrit) {
        this.francaisLuEcrit = sitSocialeFrancaisLuEcrit;
    }

    /**
     * @return nombreEnfants
     */
    public Integer getNombreEnfants() {
        return nombreEnfants;
    }

    /**
     * @param nombreEnfants
     *            affecte nombreEnfants
     */
    public void setNombreEnfants(Integer nombreEnfants) {
        this.nombreEnfants = nombreEnfants;
    }

    /**
     * @return situationEmploi
     */
    public SituationEmploi getSituationEmploi() {
        return situationEmploi;
    }

    /**
     * @param situationEmploi
     *            affecte situationEmploi
     */
    public void setSituationEmploi(SituationEmploi situationEmploi) {
        this.situationEmploi = situationEmploi;
    }

    /**
     * @return situationMilitaire
     */
    public SituationMilitaire getSituationMilitaire() {
        return situationMilitaire;
    }

    /**
     * @param situationMilitaire
     *            affecte situationMilitaire
     */
    public void setSituationMilitaire(SituationMilitaire situationMilitaire) {
        this.situationMilitaire = situationMilitaire;
    }

    /**
     * @return statutParticulier
     */
    public StatutParticulier getStatutParticulier() {
        return statutParticulier;
    }

    /**
     * @param statutParticulier
     *            affecte statutParticulier
     */
    public void setStatutParticulier(StatutParticulier statutParticulier) {
        this.statutParticulier = statutParticulier;
    }

    /**
     * duplique une situation sociale
     */
    public Object clone() throws CloneNotSupportedException {
        SituationSociale newSituation = (SituationSociale) super.clone();
        if (nombreEnfants != null) {
            newSituation.setNombreEnfants(Integer.valueOf(nombreEnfants));
        }
        if (francaisLuEcrit != null) {
            newSituation.setFrancaisLuEcrit(Boolean.valueOf(francaisLuEcrit));
        }
        newSituation.setDecorations(new HashSet<Decoration>());
        for (Decoration decoration : decorations) {
            newSituation.getDecorations().add(decoration);
        }
        return newSituation;
    }

}
