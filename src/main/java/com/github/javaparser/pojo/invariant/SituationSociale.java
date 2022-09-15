/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
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
 * Données relatives à la situation sociale de la personne auteur : niveau d'études, situation familiale, situation à
 * l'égard de l'emploi...
 */

public class SituationSociale implements Serializable, Cloneable {

    private static final long serialVersionUID = -6498359729669701457L;

    /**
     * Cet attribut est conservé uniquement pour la reprise de l?existant. En effet, il n?y a plus d?appelés. Pour les
     * militaires de carrière, l'information est enregistrée dans la zone profession.
     */
    private SituationMilitaire situationMilitaire;

    /**
     * Nombre d?enfants de la personne physique. Une telle zone peut être utilisée pour qualifier l?importance d?une
     * fratrie, le nombre d?enfants légitimes, à charge, ? C?est le contexte qui en détermine la signification. Donnée
     * utile à l?instruction et au jugement.
     */
    private Integer nombreEnfants;

    /**
     * Capacité d?une personne à s?exprimer tant par la lecture que par l?écrit, dans la langue française. Vaut Vrai si
     * la personne parle et lit le français. Vaut Faux sinon. Dans ce cas, la présence d'un interprète sera requise lors
     * de toute comparution. Donnée utile dans les procédures de traitement direct, et pour les JI et JE.
     */
    private Boolean francaisLuEcrit;

    /** Décoration obtenue. */
    private Set<Decoration> decorations = new HashSet<Decoration>();

    /**
     * Ce statut entraîne l?immunité, qui peut faire l?objet d?une demande de levée d?immunité auprès de l?autorité
     * compétente. Exemples : député, sénateur, consul, ambassadeur.
     */
    private StatutParticulier statutParticulier;

    /** Situation par rapport à l?emploi. */
    private SituationEmploi situationEmploi;

    /**
     * Code NAF (Nature d?activité française) de la personne auteur ou mineur signalé. La nomenclature d?activités
     * française (NAF) est la nouvelle nomenclature nationale statistique d?activités qui se substitue depuis le 1er
     * janvier 1993 à la NAP de 1973 (nomenclature d?activités et de produits) pour sa partie «activités ». Elle fait
     * l?objet d?un décret commun avec la CPF (classification des produits français) qui oblige l?ensemble des
     * organismes publics à utiliser la nomenclature (ou ses dérivées) dans les textes officiels, décisions, documents,
     * travaux et études. Le code caractérisant l?activité principale exercée par les unités économiques (code APE),
     * attribué par l?INSEE, est déterminé à partir du niveau le plus détaillé de la NAF.
     */
    private Ape ape;

    /**
     * Code de la catégorie socioprofessionnelle (CSP, nommé aussi CS-ESE : catégorie socio professionnelle des emplois
     * des salariés d?entreprises). Les niveaux des catégories socioprofessionnelles (niveaux 5, 23) sont un
     * regroupement direct du niveau le plus fin des professions (niveau 350). Le niveau agrégé contient 5 postes codés
     * chacun sur 1 seul caractère.
     */
    private Csp csp;

    /**
     * Code du niveau d?études, d?instruction d?une personne physique. Codification issue de l?éducation nationale.
     * Concerne les personnes majeures ET les personnes mineures. Exemples : CAP, BEP, BAC ? Donnée notamment utile au
     * JE en assistance éducative.
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
