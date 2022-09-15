package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import java.util.HashSet;
import java.util.Set;

import fr.gouv.justice.cassiopee.invariant.infraction.model.Infraction;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.PersonneEvenement;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.FamillePeineMesure;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.PeineOuMesureType;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.TitrePeineOuMesure;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.service.enumeration.TitrePeineMesureEnum;

/**
 *
 *
 */
public class Groupe implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3509852759613662448L;

    private Long id;

    private PersonneEvenement personneEvenement;

    private TypeGroupe typeGroupe;

    private Set<Infraction> infractions = new HashSet<>();

    private Set<PeineOuMesure> peineOuMesures = new HashSet<>();

    /*
     * Initialisée à vide à la création du dispositif pénal et mise à jour à la validation de l'IHM des peines
     * complémentaires des délits (F04) Dans le cadre des crimes et délit, cette donnée contient la mesure de référence
     * pour la recherche des peines alternatives
     */
    private PeineOuMesureType peineOuMesureTypeRef;

    /*
     * Initialisée à vide à la création du dispositif pénal et mise à jour à la validation de l'IHM des peines
     * complémentaires des contravention (F08) Dans le cadre des contraventions, cette donnée contient le nom de la
     * famille contenant les peines alternatives
     */
    private FamillePeineMesure famillePeinesAlternative;

    /**
     *
     */
    public Groupe() {
    }

    /**
     * @return Long
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return PersonneEvenement
     */
    public PersonneEvenement getPersonneEvenement() {
        return this.personneEvenement;
    }

    /**
     * @param personneEvenement
     */
    public void setPersonneEvenement(PersonneEvenement personneEvenement) {
        this.personneEvenement = personneEvenement;
    }

    /**
     * @return TypeGroupe
     */
    public TypeGroupe getTypeGroupe() {
        return this.typeGroupe;
    }

    /**
     * @param typeGroupe
     */
    public void setTypeGroupe(TypeGroupe typeGroupe) {
        this.typeGroupe = typeGroupe;
    }

    /**
     * @return Set<PeineOuMesure>
     */
    public Set<PeineOuMesure> getPeineOuMesures() {
        return this.peineOuMesures;
    }

    /**
     * @param peineOuMesures
     */
    public void setPeineOuMesures(Set<PeineOuMesure> peineOuMesures) {
        this.peineOuMesures = peineOuMesures;
    }

    /**
     * @return Set<Infraction>
     */
    public Set<Infraction> getInfractions() {
        return infractions;
    }

    /**
     * @param infractions
     */
    public void setInfractions(Set<Infraction> infractions) {
        this.infractions = infractions;
    }

    /**
     * @return famillePeineMesure
     */
    public FamillePeineMesure getFamillePeinesAlternative() {
        return famillePeinesAlternative;
    }

    /**
     * @param famillePeineMesure
     */
    public void setFamillePeinesAlternative(FamillePeineMesure famillePeineMesure) {
        this.famillePeinesAlternative = famillePeineMesure;
    }

    /**
     * @return PeineOuMesureType
     */
    public PeineOuMesureType getPeineOuMesureTypeRef() {
        return peineOuMesureTypeRef;
    }

    /**
     * @param peineOuMesureTypeRef
     */
    public void setPeineOuMesureTypeRef(PeineOuMesureType peineOuMesureTypeRef) {
        this.peineOuMesureTypeRef = peineOuMesureTypeRef;
    }

    /**
     * permet de savoir s'il existe au moins une peine prononcee pour le groupe à titre titrePeineMesureEnum
     *
     * @param titrePeineMesureEnum
     * @return boolean
     */
    public boolean existeAuMoinsUnePeinePrononceeAtitre(TitrePeineMesureEnum titrePeineMesureEnum) {
        for (PeineOuMesure peineOuMesure : this.getPeineOuMesures()) {
            TitrePeineOuMesure titrePeineOuMesure = peineOuMesure.getTitrePeineOuMesure();
            if (titrePeineOuMesure != null && titrePeineOuMesure.getCode().equals(titrePeineMesureEnum.getCode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @author crt permet de savoir s'il existe au moins une peine prononcee pour le groupe à titre titrePeineMesureEnum
     *         en passant par les infractions de la personne-evt
     * @param titrePeineMesureEnum
     * @return boolean
     */
    public boolean existeAuMoinsUnePeinePrononceeAtitreViaInfractions(TitrePeineMesureEnum titrePeineMesureEnum) {
        // Parcours des infractions
        for (Infraction infraction : getInfractions()) {
            // Recherche de la personne-événement de l'infraction
            PersonneEvenementInfraction personneEvenementInfraction = personneEvenement
                    .getPersonneEvenementInfractionByInfractionId(infraction.getId());

            // Recherche s'il existe au moins une peine de titre recherché pour
            // l'infraction
            if (personneEvenementInfraction != null
                    && personneEvenementInfraction.existeAuMoinsUnePeinePrononceeAtitre(titrePeineMesureEnum)) {
                return true;
            }
        }
        return false;
    }

    /**
     * rechercher les peines du groupe qui sont de type TypePeineOuMesureEnum et à titre titrePeineMesureEnum
     *
     * @param typePeineOuMesureEnum
     * @param titrePeineMesureEnum
     * @return Set<PeineOuMesure>
     */
    public Set<PeineOuMesure> findPeinesOuMesureByTypeAndTitre(TypePeineOuMesureEnum typePeineOuMesureEnum,
            TitrePeineMesureEnum titrePeineMesureEnum) {
        Set<PeineOuMesure> peineOuMesureSet = new HashSet<>();
        for (PeineOuMesure peineOuMesure : this.getPeineOuMesures()) {
            if (peineOuMesure.getTypePeineOuMesureEnum().getType().equals(typePeineOuMesureEnum.getType())
                    && peineOuMesure.isTitrePeineEquals(titrePeineMesureEnum.getCode())) {

                peineOuMesureSet.add(peineOuMesure);
            }
        }
        return peineOuMesureSet;
    }

    /**
     * clone d'un objet type Groupe
     *
     * @throws CloneNotSupportedException
     */
    public Groupe cloneForCRPC(PersonneEvenement pevEvt) throws CloneNotSupportedException {

        Groupe g = new Groupe();

        g.setFamillePeinesAlternative(this.getFamillePeinesAlternative());

        for (Infraction infr : this.getInfractions()) {
            g.getInfractions().add(infr);

        }

        for (PeineOuMesure peinOuMes : this.getPeineOuMesures()) {
            PeineOuMesure pMTransiente = peinOuMes.dupliquerPeineOuMesure();
            pMTransiente.setPersonneEvenement(pevEvt);
            pMTransiente.addGroupe(g);
            g.getPeineOuMesures().add(pMTransiente);

        }

        g.setPeineOuMesureTypeRef(this.getPeineOuMesureTypeRef());
        g.setTypeGroupe(this.getTypeGroupe());
        g.setPersonneEvenement(pevEvt);
        pevEvt.getGroupes().add(g);

        return g;

    }

}
