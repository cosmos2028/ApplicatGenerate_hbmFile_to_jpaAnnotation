package fr.gouv.justice.cassiopee.invariant.infraction.model;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

import fr.gouv.justice.cassiopee.administration.parametrage.model.TypeInfractionMetier;
import fr.gouv.justice.cassiopee.commons.metier.model.DateIncompleteAvecPrefixe;
import fr.gouv.justice.cassiopee.invariant.affaire.model.AffairePenale;
import fr.gouv.justice.cassiopee.processus.ech.model.InfractionTajEligible;
import fr.gouv.justice.cassiopee.processus.ech.model.ReferentielPVPNGNCNTCA;
import fr.gouv.justice.cassiopee.referentiel.infraction.codification.model.NatureAffaire;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Commune;

public class InfractionMetier  implements InfractionTajEligible{

    private Long id;
    private AffairePenale affairePenale;
    private TypeInfractionMetier typeInfractionMetier;
    private ReferentielPVPNGNCNTCA referentielPV;
    /** Commune du lieu de l'infraction */
    private Commune commune;
    /** Description du lieu de l'infraction (texte libre) */
    private String libelleLieuInfraction;

    /** Date de début de l'infraction */
    private DateIncompleteAvecPrefixe dateDebut;

    /** Date de fin de l'infraction */
    private DateIncompleteAvecPrefixe dateFin;

    /** Rang de l'infraction dans le dossier */
    private Integer rang;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public AffairePenale getAffairePenale() {
        return affairePenale;
    }
    public void setAffairePenale(AffairePenale affairePenale) {
        this.affairePenale = affairePenale;
    }
    public TypeInfractionMetier getTypeInfractionMetier() {
        return typeInfractionMetier;
    }
    public void setTypeInfractionMetier(TypeInfractionMetier typeInfractionMetier) {
        this.typeInfractionMetier = typeInfractionMetier;
    }
    public ReferentielPVPNGNCNTCA getReferentielPV() {
        return referentielPV;
    }
    public void setReferentielPV(ReferentielPVPNGNCNTCA referentielPV) {
        this.referentielPV = referentielPV;
    }
    public Commune getCommune() {
        return commune;
    }
    public void setCommune(Commune commune) {
        this.commune = commune;
    }
    public String getLibelleLieuInfraction() {
        return libelleLieuInfraction;
    }
    public void setLibelleLieuInfraction(String libelleLieuInfraction) {
        this.libelleLieuInfraction = libelleLieuInfraction;
    }
    public DateIncompleteAvecPrefixe getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(DateIncompleteAvecPrefixe dateDebut) {
        this.dateDebut = dateDebut;
    }
    public DateIncompleteAvecPrefixe getDateFin() {
        return dateFin;
    }
    public void setDateFin(DateIncompleteAvecPrefixe dateFin) {
        this.dateFin = dateFin;
    }
    public Integer getRang() {
        return rang;
    }
    public void setRang(Integer rang) {
        this.rang = rang;
    }
    @Override
    public Long getNumeroNatinf() {
        return typeInfractionMetier == null ? null : typeInfractionMetier.getCodeNatinfs();
    }
    @Override
    public String getQualificationSimplifiee() {
        return typeInfractionMetier == null ? null : typeInfractionMetier.getLibEnquete();
    }
    @Override
    public Set<LienPersonneInfraction> getLiensPersonneInfraction() {
        return Collections.emptySet();
    }
    @Override
    public String getCodeType() {
        return String.valueOf(typeInfractionMetier.getType());
    }

    public Date getDateInfraction() {
        return Infraction.computeDateRechercheVersionNatinf(getDateDebut(), getDateFin());
    }
    @Override
    public boolean isEnCours() {
        return true;
    }
    @Override
    public NatureAffaire getNatureAffaire() {
        return typeInfractionMetier.getNatureAffaire();
    }
    @Override
    public boolean hasDoublonEligibleTaj() {
        return false;
    }
}