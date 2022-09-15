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
 * D�tail du lien entre un auteur et une infraction. NOTE: appel� indiff�remment "auteur-infraction" ou
 * "lien auteur-infraction".
 */
public class LienAuteurInfraction extends LienPersonneInfraction implements Cloneable  {

    private static final Logger LOGGER = LoggerFactory.getLogger(LienAuteurInfraction.class);

    /** Identifiant version pour la s�rialisation */
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

    /** Modalit�s pr�c�dentes � l'infraction. Seule une modalit� est en cours */
    private List<ModalitePrecedente> modalitesPrecedentes = new ArrayList<>();

    private DispositifExterieurAuteur dispositifExterieurAuteur;

    /**
     * Qualification d�velopp�e d�finie pour cet auteur, cette infraction et les modalit�s de participation
     */
    private QualificationDeveloppee qualificationDeveloppee;

    /**
     * Qualification d�velopp�e Requise d�finie pour cet auteur, cette infraction et les modalit�s de participation
     */
    private QualificationDeveloppee qualificationDeveloppeeRequise;

    private ModaliteParticipation modaliteParticipation;

    private ModaliteParticipation modaliteParticipationRd;

    /** �v�nements sur le lien auteur infraction */
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
     * Methode permettant de r�cupp�rer l'�tat lien auteur infraction avant la creation de l'�v�nement ordonnance
     *
     * @return l'�tat du lien auteur infraction avant la creation de l'�v�nement ordonnance
     */
    public EtatLienAuteurInfraction getEtatLienAuteurInfractionAvantOrdonnance() {
        return etatLienAuteurInfractionAvantOrdonnance;
    }

    /**
     * M�thode permettant d'aliementer l'tat lien auteur infraction avant la creation de l'�v�nement ordonnance
     *
     * @param etatLienAuteurInfractionAvantOrdonnance
     *            : Etat lien auteur infraction avant la creation de l'�v�nement ordonnance
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
     * @return la modalit� de participation du contexte RD
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
     * Indique l'�tat de l'auteur pour l'infraction
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
     * Indique la Qualification d�velopp�e d�finie pour cet auteur, cette infraction et les modalit�s de participation
     *
     * @return la qualification d�velopp�e
     */
    public QualificationDeveloppee getQualificationDeveloppee() {
        return qualificationDeveloppee;
    }

    /**
     * Renseigne la Qualification d�velopp�e d�finie pour cet auteur, cette infraction et les modalit�s de participation
     *
     * @param qualificationDeveloppee
     *            la qualification d�velopp�e
     */
    public void setQualificationDeveloppee(QualificationDeveloppee qualificationDeveloppee) {
        this.qualificationDeveloppee = qualificationDeveloppee;
    }

    /**
     * Indique la Qualification d�velopp�e Requise d�finie pour cet auteur, cette infraction et les modalit�s de
     * participation
     *
     * @return la qualification d�velopp�e Requise
     */
    public QualificationDeveloppee getQualificationDeveloppeeRequise() {
        return qualificationDeveloppeeRequise;
    }

    /**
     * Renseigne la Qualification d�velopp�e Requise d�finie pour cet auteur, cette infraction et les modalit�s de
     * participation
     *
     * @param qualificationDeveloppee
     *            la qualification d�velopp�e Requise
     */
    public void setQualificationDeveloppeeRequise(QualificationDeveloppee qualificationDeveloppeeRequise) {
        this.qualificationDeveloppeeRequise = qualificationDeveloppeeRequise;
    }

    /**
     * Renseigne l'�tat de l'auteur pour l'infraction
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
     * @return la derni�re modalit� pr�c�dente.
     */
    public ModalitePrecedente getDerniereModalitePrecedente() {
        if (this.getModalitesPrecedentes() == null || this.getModalitesPrecedentes().isEmpty()) {
            return null;
        }
        return this.getModalitesPrecedentes().get(this.getModalitesPrecedentes().size() - 1);
    }

    /**
     * indique si une qualification d�velopp�e est pr�sente
     *
     * @return vrai si une qualification d�velopp�e est pr�sente
     */
    public boolean isQualificationDeveloppePresente() {
        return isQualificationDeveloppeSpecifiquePresente();
    }

    /**
     * indique si une qualification d�velopp�e requise est pr�sente
     *
     * @return vrai si une qualification d�velopp�e requise est pr�sente
     */
    public boolean isQualificationDeveloppeRequisePresente() {
        return isQualificationDeveloppeRequiseSpecifiquePresente();
    }

    /**
     * indique si une qualification d�velopp�e sp�cifique pour ce lien est pr�sente
     *
     * @return vrai si une qualification d�velopp�e est pr�sente
     */
    public boolean isQualificationDeveloppeSpecifiquePresente() {
        return (this.qualificationDeveloppee != null) && (this.qualificationDeveloppee.getQdTexte() != null);
    }

    /**
     * indique si une qualification d�velopp�e Requise sp�cifique pour ce lien est pr�sente
     *
     * @return vrai si une qualification d�velopp�e Requise est pr�sente
     */
    public boolean isQualificationDeveloppeRequiseSpecifiquePresente() {
        return (this.qualificationDeveloppeeRequise != null)
                && (this.qualificationDeveloppeeRequise.getQdTexte() != null);
    }

    /**
     * Indique si le lien est � l'�tat "En cours".
     *
     * @return true si le lien est � l'�tat "En cours", faux sinon.
     */
    public boolean isEnCours() {
        return EtatInfractionEnum.ENCOURS.getCode().equals(getEtatInfraction().getCode());

    }

    /**
     * Indique si le lien est � l'�tat "REQUIS".
     *
     * @return true si le lien est � l'�tat "REQUIS", faux sinon.
     */
    public boolean isRequis() {
        return EtatInfractionEnum.REQUIS.getCode().equals(getEtatInfraction().getCode());
    }

    /**
     * Indique si le lien est � l'�tat "ENCOURS" et que le booleen requis_disqualifie vaut true.
     *
     * @return true si le lien est � l'�tat "ENCOURS" et que le booleen requis_disqualifie vaut true, faux sinon.
     */
    public boolean isEnCoursDisqualifie() {
        return EtatInfractionEnum.DISQUALIFIEE.getCode().equals(getEtatInfraction().getCode())
                && EtatInfractionEnum.ENCOURS.getCode().equals(getEtatInfraction().getCode());
    }

    /**
     * Indique si le lien est � l'�tat "ENCOURS" et que le booleen requis_disqualifie vaut false.
     *
     * @return true si le lien est � l'�tat "ENCOURS" et que le booleen requis_disqualifie vaut false, faux sinon.
     */
    public boolean isEnCoursNonDisqualifie() {
        return !isEnCoursDisqualifie();
    }

    /**
     * Indique si l'infraction a �t� cr��e par disqualification/requalification, c'est-�-dire si elle est li�e � un
     * pr�c�dent lien-auteur infraciton ou modalit�.
     *
     * @return true si le lien a un lien auteur-infraction origine ou des modalit�s pr�c�dentes, false sinon.
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
     * retourne les �v�nements sur le lien auteur infraction
     *
     * @return les �v�nements sur le lien auteur infraction
     */
    public Set<Evenement> findAllEvenementsSurLienAuteurInfraction() {
        Set<Evenement> evenementsSurLien = new HashSet<>();
        for (PersonneEvenement personneEvenement : this.getPersonneEvenementsSurLienAuteurInfraction()) {
            evenementsSurLien.add(personneEvenement.getEvenement());
        }
        return evenementsSurLien;
    }

    /**
     * Lien auteur-infraction remplac� par celui-ci au cours d'une disqualification/requalification.
     *
     * @return Returns the lienAuteurInfractionOrigine.
     */
    public LienAuteurInfraction getLienAuteurInfractionOrigine() {
        return lienAuteurInfractionOrigine;
    }

    /**
     * Valorise le lien auteur-infraction remplac� par celui-ci au cours d'une disqualification/requalification.
     *
     * @param lienAuteurInfractionOrigine
     *            The lienAuteurInfractionOrigine to set.
     */
    public void setLienAuteurInfractionOrigine(LienAuteurInfraction lienAuteurInfractionOrigine) {
        this.lienAuteurInfractionOrigine = lienAuteurInfractionOrigine;
    }

    /**
     * Liens auteur-infraction ayant remplac� celui-ci au cours d'une disqualification/requalification. Note: si cette
     * collection contient des �l�ments, alors l'objet LienAuteurInfraction courant doit �tre � l'�tat disqualifi�.
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
     * Renvoie le dispositif ext�rieur auteur rattach� � ce lien auteur-infraction.
     *
     * @return Returns the dispositifExterieurAuteur.
     */
    public DispositifExterieurAuteur getDispositifExterieurAuteur() {
        return dispositifExterieurAuteur;
    }

    /**
     * Valorise le dispositif ext�rieur auteur rattach� � ce lien auteur-infraction.
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
