package fr.gouv.justice.cassiopee.invariant.infraction.model;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.DetailDommageInteret;
import fr.gouv.justice.cassiopee.processus.ech.model.DoublonLienPersonneInfraction;
import fr.gouv.justice.cassiopee.processus.ech.model.ReferentielPV;

/**
 * Le lien personne infraction associe une personne et une infraction. La personne peut avoir le r�le auteur ou le r�le
 * victime. Le type de lien permet de distinguer les associations infraction victime et infraction auteur. <br>
 * Le lien victime infraction ne poss�de pas d'attributs. <br>
 * Dans le cas d'un auteur des attributs sp�cifiques indiquent le mode de participation, et les �tats du lien auteur
 * infraction.
 *
 * @see fr.gouv.justice.cassiopee.invariant.infraction.model.TypeLienPersonneInfractionEnum
 */
public abstract class LienPersonneInfraction implements java.io.Serializable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 4792074656350889336L;

    /** Identifiant de l'objet LienPersonneInfraction */
    private Long id;

    /** Personne associ�e */
    private Personne personne;

    /** Infraction associ�e */
    private Infraction infraction;

    /** Infraction d'origine avant requalification */
    private Infraction infractionOrigine;

    private Boolean requisDisqualifie;

    private EtatInfraction etatInfraction;

    private Set<DetailDommageInteret> lienDiPersonneInfraction = new HashSet<>();

    /**
     * Rassemble les informations dans le cas d'une personne import�e par les EIA
     */
    private ReferentielPV referentielPV;
    
    private Set<DoublonLienPersonneInfraction> doublons = new HashSet<>();

    /**
     * indique le type de lien :LienAuteurInfraction, LienVictimeInfraction
     *
     * @return le type de lien
     */
    protected abstract TypeLienPersonneInfractionEnum getTypeLienPersonneInfraction();

    /**
     * Identifiant de l'objet LienPersonneInfraction
     *
     * @return l'identifiant de l'objet
     */
    public Long getId() {
        return id;
    }

    /**
     * Renseigne l'identifiant de l'objet LienPersonneInfraction
     *
     * @param id
     *            l'identifiant de l'objet
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne l'infraction
     *
     * @return l'infraction
     */
    public Infraction getInfraction() {
        return infraction;
    }

    /**
     * Renseigne l'infraction
     *
     * @param infraction
     *            l'infraction concern�e
     */
    public void setInfraction(Infraction infraction) {
        this.infraction = infraction;
    }

    /**
     * Retourne l'infraction d'origine
     *
     * @return l'infraction d'origine
     */
    public Infraction getInfractionOrigine() {
        return infractionOrigine;
    }

    /**
     * Modification de l'infraction d'origine
     *
     * @param infractionOrigine
     *            : nouvelle infraction d'origine
     */
    public void setInfractionOrigine(final Infraction infractionOrigine) {
        this.infractionOrigine = infractionOrigine;
    }

    /**
     * @return the requisDisqualifie
     */
    public Boolean getRequisDisqualifie() {
        return requisDisqualifie;
    }

    /**
     * @param requisDisqualifie
     *            the requisDisqualifie to set
     */
    public void setRequisDisqualifie(Boolean requisDisqualifie) {
        this.requisDisqualifie = requisDisqualifie;
    }

    /**
     * Indique l'�tat de l'infraction pour l'auteur concern�.
     *
     * @return EtatInfraction
     */
    public EtatInfraction getEtatInfraction() {
        return this.etatInfraction;
    }

    /**
     * Renseigne l'�tat de l'infraction pour l'auteur concern�.
     *
     * @param etatInfraction
     *            l'�tat de l'infraction
     */
    public void setEtatInfraction(EtatInfraction etatInfraction) {
        this.etatInfraction = etatInfraction;
    }

    /**
     * Retourne la personne concern�e
     *
     * @return la personne
     */
    public Personne getPersonne() {
        return personne;
    }

    /**
     * Renseigne la personne
     *
     * @param personne
     *            la personne
     */
    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Set<DetailDommageInteret> getLienDiPersonneInfraction() {
        return lienDiPersonneInfraction;
    }

    public void setLienDiPersonneInfraction(Set<DetailDommageInteret> lienDiPersonneInfraction) {
        this.lienDiPersonneInfraction = lienDiPersonneInfraction;
    }

    public ReferentielPV getReferentielPV() {
        return referentielPV;
    }

    public void setReferentielPV(ReferentielPV referentielPV) {
        this.referentielPV = referentielPV;
    }

    public Set<DoublonLienPersonneInfraction> getDoublons() {
        if (doublons == null) {
            doublons = new HashSet<>();
        }
        return doublons;
    }

    public void setDoublons(Set<DoublonLienPersonneInfraction> doublons) {
        this.doublons = doublons;
    }
    
    public boolean hasDoublonEligibleTaj() {
        if (CollectionUtils.isNotEmpty(doublons)) {
            return doublons.stream().anyMatch(doublon -> doublon.getReferentielPV().isEligibleTAJ());
        }
        return false;
    }
    
    public boolean hasReferentielEligibleTaj() {
        if (referentielPV != null) {
            return referentielPV.isEligibleTAJ();
        }
        return false;
    }
}