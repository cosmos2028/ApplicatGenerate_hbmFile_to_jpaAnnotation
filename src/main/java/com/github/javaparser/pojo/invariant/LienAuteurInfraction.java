package fr.gouv.justice.cassiopee.invariant.infraction.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.exterieur.model.DispositifExterieurAuteur;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.PersonneEvenement;
import fr.gouv.justice.cassiopee.referentiel.infraction.codification.model.ModaliteParticipation;

/**
 * Détail du lien entre un auteur et une infraction. NOTE: appelé indifféremment "auteur-infraction" ou
 * "lien auteur-infraction".
 */
public class LienAuteurInfraction extends LienPersonneInfraction implements Cloneable  {

    private static final Logger LOGGER = LoggerFactory.getLogger(LienAuteurInfraction.class);

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = 4792074656350889336L;

    /** Lien auteur-infraction d'origine avant requalification. */
    private LienAuteurInfraction lienAuteurInfractionOrigine;

    private Set<LienAuteurInfraction> lienAuteurInfractionRemplacants = new LinkedHashSet<>();

    /** Etat lien auteur infraction */
    private EtatLienAuteurInfraction etatLienAuteurInfraction;

    /** Etat lien auteur infraction avant ordonnance */
    private EtatLienAuteurInfraction etatLienAuteurInfractionAvantOrdonnance;

    /** Etat par anticipation lien auteur infraction */
    @RegleDeGestion(RG.EVTM106)
    private EtatLienAuteurInfraction etatLienAuteurInfractionAnticipe;

    /** Modalités précédentes à l'infraction. Seule une modalité est en cours */
    private List<ModalitePrecedente> modalitesPrecedentes = new ArrayList<>();

    private DispositifExterieurAuteur dispositifExterieurAuteur;

    /**
     * Qualification développée définie pour cet auteur, cette infraction et les modalités de participation
     */
    private QualificationDeveloppee qualificationDeveloppee;

    /**
     * Qualification développée Requise définie pour cet auteur, cette infraction et les modalités de participation
     */
    private QualificationDeveloppee qualificationDeveloppeeRequise;

    private ModaliteParticipation modaliteParticipation;

    private ModaliteParticipation modaliteParticipationRd;

    /** événements sur le lien auteur infraction */
    private Set<PersonneEvenement> personneEvenementsSurLienAuteurInfraction = new LinkedHashSet<>();

    private Integer evenementModifiantInfraction;

    @Override
    public Object clone() {
        LienAuteurInfraction lienAuteurInfraction = null;
        try {
            lienAuteurInfraction = (LienAuteurInfraction) super.clone();
        } catch (CloneNotSupportedException cnse) {
            LOGGER.error("methode Clone non implementee", cnse);
        }
        return lienAuteurInfraction;
    }

    /**
     * Etat par anticipation lien auteur infraction
     *
     * @param etatLienAuteurInfractionParAnticipation
     */
    public void setEtatLienAuteurInfractionAnticipe(EtatLienAuteurInfraction etatLienAuteurInfractionAnticipe) {
        this.etatLienAuteurInfractionAnticipe = etatLienAuteurInfractionAnticipe;
    }

    /**
     * Methode permettant de récuppérer l'état lien auteur infraction avant la creation de l'événement ordonnance
     *
     * @return l'état du lien auteur infraction avant la creation de l'événement ordonnance
     */
    public EtatLienAuteurInfraction getEtatLienAuteurInfractionAvantOrdonnance() {
        return etatLienAuteurInfractionAvantOrdonnance;
    }

    /**
     * Méthode permettant d'aliementer l'tat lien auteur infraction avant la creation de l'événement ordonnance
     *
     * @param etatLienAuteurInfractionAvantOrdonnance
     *            : Etat lien auteur infraction avant la creation de l'événement ordonnance
     */
    public void setEtatLienAuteurInfractionAvantOrdonnance(
            final EtatLienAuteurInfraction etatLienAuteurInfractionAvantOrdonnance) {
        this.etatLienAuteurInfractionAvantOrdonnance = etatLienAuteurInfractionAvantOrdonnance;
    }

    /**
     * Etat par anticipation lien auteur infraction
     *
     * @return
     */
    public EtatLienAuteurInfraction getEtatLienAuteurInfractionAnticipe() {
        return etatLienAuteurInfractionAnticipe;
    }

    public ModaliteParticipation getModaliteParticipation() {
        return modaliteParticipation;
    }

    /**
     * @return la modalité de participation du contexte RD
     */
    public ModaliteParticipation getModaliteParticipationRd() {
        return modaliteParticipationRd;
    }

    /**
     * @param modaliteParticipationRd
     */
    public void setModaliteParticipationRd(ModaliteParticipation modaliteParticipationRd) {
        this.modaliteParticipationRd = modaliteParticipationRd;
    }

    /**
     * Indique l'état de l'auteur pour l'infraction
     *
     * @return EtatLienAuteurInfraction
     */
    public EtatLienAuteurInfraction getEtatLienAuteurInfraction() {
        return etatLienAuteurInfraction;
    }

    public void setModaliteParticipation(ModaliteParticipation modaliteParticipation) {
        this.modaliteParticipation = modaliteParticipation;
    }

    /**
     * Indique la Qualification développée définie pour cet auteur, cette infraction et les modalités de participation
     *
     * @return la qualification développée
     */
    public QualificationDeveloppee getQualificationDeveloppee() {
        return qualificationDeveloppee;
    }

    /**
     * Renseigne la Qualification développée définie pour cet auteur, cette infraction et les modalités de participation
     *
     * @param qualificationDeveloppee
     *            la qualification développée
     */
    public void setQualificationDeveloppee(QualificationDeveloppee qualificationDeveloppee) {
        this.qualificationDeveloppee = qualificationDeveloppee;
    }

    /**
     * Indique la Qualification développée Requise définie pour cet auteur, cette infraction et les modalités de
     * participation
     *
     * @return la qualification développée Requise
     */
    public QualificationDeveloppee getQualificationDeveloppeeRequise() {
        return qualificationDeveloppeeRequise;
    }

    /**
     * Renseigne la Qualification développée Requise définie pour cet auteur, cette infraction et les modalités de
     * participation
     *
     * @param qualificationDeveloppee
     *            la qualification développée Requise
     */
    public void setQualificationDeveloppeeRequise(QualificationDeveloppee qualificationDeveloppeeRequise) {
        this.qualificationDeveloppeeRequise = qualificationDeveloppeeRequise;
    }

    /**
     * Renseigne l'état de l'auteur pour l'infraction
     *
     * @param etatLienAuteurInfraction
     */
    public void setEtatLienAuteurInfraction(EtatLienAuteurInfraction etatLienAuteurInfraction) {
        this.etatLienAuteurInfraction = etatLienAuteurInfraction;
    }

    @Override
    protected TypeLienPersonneInfractionEnum getTypeLienPersonneInfraction() {
        return TypeLienPersonneInfractionEnum.AUTEUR_INFRACTION;
    }

    /**
     * indique s'il s'agit d'un lien auteur infraction
     *
     * @param lienPersonneInfraction
     *            lien personne infraction
     * @return true s'il s'agit d'un lien auteur infraction
     */
    public static boolean isLienAuteurInfraction(LienPersonneInfraction lienPersonneInfraction) {
        return TypeLienPersonneInfractionEnum.AUTEUR_INFRACTION.equals(lienPersonneInfraction
                .getTypeLienPersonneInfraction());
    }

    public List<ModalitePrecedente> getModalitesPrecedentes() {
        return modalitesPrecedentes;
    }

    public void setModalitesPrecedentes(List<ModalitePrecedente> modalitesPrecedentes) {
        this.modalitesPrecedentes = modalitesPrecedentes;
    }

    /**
     * @return la dernière modalité précédente.
     */
    public ModalitePrecedente getDerniereModalitePrecedente() {
        if (this.getModalitesPrecedentes() == null || this.getModalitesPrecedentes().isEmpty()) {
            return null;
        }
        return this.getModalitesPrecedentes().get(this.getModalitesPrecedentes().size() - 1);
    }

    /**
     * indique si une qualification développée est présente
     *
     * @return vrai si une qualification développée est présente
     */
    public boolean isQualificationDeveloppePresente() {
        return isQualificationDeveloppeSpecifiquePresente();
    }

    /**
     * indique si une qualification développée requise est présente
     *
     * @return vrai si une qualification développée requise est présente
     */
    public boolean isQualificationDeveloppeRequisePresente() {
        return isQualificationDeveloppeRequiseSpecifiquePresente();
    }

    /**
     * indique si une qualification développée spécifique pour ce lien est présente
     *
     * @return vrai si une qualification développée est présente
     */
    public boolean isQualificationDeveloppeSpecifiquePresente() {
        return (this.qualificationDeveloppee != null) && (this.qualificationDeveloppee.getQdTexte() != null);
    }

    /**
     * indique si une qualification développée Requise spécifique pour ce lien est présente
     *
     * @return vrai si une qualification développée Requise est présente
     */
    public boolean isQualificationDeveloppeRequiseSpecifiquePresente() {
        return (this.qualificationDeveloppeeRequise != null)
                && (this.qualificationDeveloppeeRequise.getQdTexte() != null);
    }

    /**
     * Indique si le lien est à l'état "En cours".
     *
     * @return true si le lien est à l'état "En cours", faux sinon.
     */
    public boolean isEnCours() {
        return EtatInfractionEnum.ENCOURS.getCode().equals(getEtatInfraction().getCode());

    }

    /**
     * Indique si le lien est à l'état "REQUIS".
     *
     * @return true si le lien est à l'état "REQUIS", faux sinon.
     */
    public boolean isRequis() {
        return EtatInfractionEnum.REQUIS.getCode().equals(getEtatInfraction().getCode());
    }

    /**
     * Indique si le lien est à l'état "ENCOURS" et que le booleen requis_disqualifie vaut true.
     *
     * @return true si le lien est à l'état "ENCOURS" et que le booleen requis_disqualifie vaut true, faux sinon.
     */
    public boolean isEnCoursDisqualifie() {
        return EtatInfractionEnum.DISQUALIFIEE.getCode().equals(getEtatInfraction().getCode())
                && EtatInfractionEnum.ENCOURS.getCode().equals(getEtatInfraction().getCode());
    }

    /**
     * Indique si le lien est à l'état "ENCOURS" et que le booleen requis_disqualifie vaut false.
     *
     * @return true si le lien est à l'état "ENCOURS" et que le booleen requis_disqualifie vaut false, faux sinon.
     */
    public boolean isEnCoursNonDisqualifie() {
        return !isEnCoursDisqualifie();
    }

    /**
     * Indique si l'infraction a été créée par disqualification/requalification, c'est-à-dire si elle est liée à un
     * précédent lien-auteur infraciton ou modalité.
     *
     * @return true si le lien a un lien auteur-infraction origine ou des modalités précédentes, false sinon.
     */
    public boolean hasPrecedentAuteurInfractionOuModalite() {
        return (this.getLienAuteurInfractionOrigine() != null)
                || (this.getModalitesPrecedentes() != null && !this.getModalitesPrecedentes().isEmpty());
    }

    /**
     * @return Returns the personneEvenementsSurLienAuteurInfraction.
     */
    public Set<PersonneEvenement> getPersonneEvenementsSurLienAuteurInfraction() {
        return personneEvenementsSurLienAuteurInfraction;
    }

    /**
     * @param personneEvenementsSurLienAuteurInfraction
     *            The personneEvenementsSurLienAuteurInfraction to set.
     */
    public void setPersonneEvenementsSurLienAuteurInfraction(
            Set<PersonneEvenement> personneEvenementsSurLienAuteurInfraction) {
        this.personneEvenementsSurLienAuteurInfraction = personneEvenementsSurLienAuteurInfraction;
    }

    /**
     * retourne les événements sur le lien auteur infraction
     *
     * @return les événements sur le lien auteur infraction
     */
    public Set<Evenement> findAllEvenementsSurLienAuteurInfraction() {
        Set<Evenement> evenementsSurLien = new HashSet<>();
        for (PersonneEvenement personneEvenement : this.getPersonneEvenementsSurLienAuteurInfraction()) {
            evenementsSurLien.add(personneEvenement.getEvenement());
        }
        return evenementsSurLien;
    }

    /**
     * Lien auteur-infraction remplacé par celui-ci au cours d'une disqualification/requalification.
     *
     * @return Returns the lienAuteurInfractionOrigine.
     */
    public LienAuteurInfraction getLienAuteurInfractionOrigine() {
        return lienAuteurInfractionOrigine;
    }

    /**
     * Valorise le lien auteur-infraction remplacé par celui-ci au cours d'une disqualification/requalification.
     *
     * @param lienAuteurInfractionOrigine
     *            The lienAuteurInfractionOrigine to set.
     */
    public void setLienAuteurInfractionOrigine(LienAuteurInfraction lienAuteurInfractionOrigine) {
        this.lienAuteurInfractionOrigine = lienAuteurInfractionOrigine;
    }

    /**
     * Liens auteur-infraction ayant remplacé celui-ci au cours d'une disqualification/requalification. Note: si cette
     * collection contient des éléments, alors l'objet LienAuteurInfraction courant doit être à l'état disqualifié.
     *
     * @return Returns the lienAuteurInfractionRemplacants.
     */
    public Set<LienAuteurInfraction> getLienAuteurInfractionRemplacants() {
        return lienAuteurInfractionRemplacants;
    }

    /**
     * @param lienAuteurInfractionRemplacants
     *            The lienAuteurInfractionRemplacants to set.
     */
    public void setLienAuteurInfractionRemplacants(Set<LienAuteurInfraction> lienAuteurInfractionRemplacants) {
        this.lienAuteurInfractionRemplacants = lienAuteurInfractionRemplacants;
    }

    /**
     * Renvoie le dispositif extérieur auteur rattaché à ce lien auteur-infraction.
     *
     * @return Returns the dispositifExterieurAuteur.
     */
    public DispositifExterieurAuteur getDispositifExterieurAuteur() {
        return dispositifExterieurAuteur;
    }

    /**
     * Valorise le dispositif extérieur auteur rattaché à ce lien auteur-infraction.
     *
     * @param dispositifExterieurAuteur
     *            The dispositifExterieurAuteur to set.
     */
    public void setDispositifExterieurAuteur(DispositifExterieurAuteur dispositifExterieurAuteur) {
        this.dispositifExterieurAuteur = dispositifExterieurAuteur;
    }

    public Integer getEvenementModifiantInfraction() {
        return evenementModifiantInfraction;
    }

    public void setEvenementModifiantInfraction(Integer evenementModifiantInfraction) {
        this.evenementModifiantInfraction = evenementModifiantInfraction;
    }

}
