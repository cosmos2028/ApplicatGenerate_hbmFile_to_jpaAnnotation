package fr.gouv.justice.cassiopee.invariant.personneevenement.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.infraction.model.Infraction;
import fr.gouv.justice.cassiopee.invariant.infraction.model.LienAuteurInfraction;
import fr.gouv.justice.cassiopee.invariant.infraction.model.LienPersonneInfraction;
import fr.gouv.justice.cassiopee.invariant.infraction.model.LienVictimeInfraction;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.DetailDommageInteretFrappeAppel;
import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;
import fr.gouv.justice.cassiopee.invariant.personne.model.TypeDetailReparation;
import fr.gouv.justice.cassiopee.invariant.personne.service.enumeration.TypeReparationEnum;
import fr.gouv.justice.cassiopee.referentiel.codification.model.Devise;

public abstract class DetailDommageInteret implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Personne personne;

    private Evenement evenement;

    private BigDecimal montantDemande;

    private BigDecimal montantAccorde;

    private boolean indicateurProvision;

    private boolean indicateurRejet;

    private TypeDetailReparation typeDetailReparation;

    private Date dateDI;

    private Devise devise;

    protected Set<Personne> lienDiPersonne = new HashSet<>();

    private Set<DetailDommageInteretFrappeAppel> dommagesInteretsFrappesAppel = new HashSet<>();

    public DetailDommageInteret() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public BigDecimal getMontantDemande() {
        return montantDemande;
    }

    public void setMontantDemande(BigDecimal montantDemande) {
        this.montantDemande = montantDemande;
    }

    public BigDecimal getMontantAccorde() {
        return montantAccorde;
    }

    public void setMontantAccorde(BigDecimal montantAccorde) {
        this.montantAccorde = montantAccorde;
    }

    public boolean isIndicateurProvision() {
        return indicateurProvision;
    }

    public void setIndicateurProvision(boolean indicateurProvision) {
        this.indicateurProvision = indicateurProvision;
    }

    public boolean isIndicateurRejet() {
        return indicateurRejet;
    }

    public void setIndicateurRejet(boolean indicateurRejet) {
        this.indicateurRejet = indicateurRejet;
    }

    public TypeDetailReparation getTypeDetailReparation() {
        return typeDetailReparation;
    }

    public void setTypeDetailReparation(TypeDetailReparation typeDetailReparation) {
        this.typeDetailReparation = typeDetailReparation;
    }

    public Set<Personne> getLienDiPersonne() {
        return lienDiPersonne;
    }

    public void setLienDiPersonne(Set<Personne> lienDiPersonne) {
        this.lienDiPersonne = lienDiPersonne;
    }

    public Set<DetailDommageInteretFrappeAppel> getDommagesInteretsFrappesAppel() {
        return dommagesInteretsFrappesAppel;
    }

    public void setDommagesInteretsFrappesAppel(Set<DetailDommageInteretFrappeAppel> dommagesInteretsFrappesAppel) {
        this.dommagesInteretsFrappesAppel = dommagesInteretsFrappesAppel;
    }

    public DetailDommageInteret dupliquerDetailDi() {

        if (this instanceof DetailDommageInteretArticle4751) {
            DetailDommageInteretArticle4751 detailDiPrononceDuplicate = new DetailDommageInteretArticle4751();
            copyDetailDommageInteret(this, detailDiPrononceDuplicate);
            return detailDiPrononceDuplicate;
        } else {
            DetailDommageInteretDi detailDiPrononceDuplicate = new DetailDommageInteretDi();

            Set<LienPersonneInfraction> listLienDiPersonneDuplicate = new HashSet<>();
            DetailDommageInteretDi detailDommageInteretDi = (DetailDommageInteretDi) this;
            if (detailDommageInteretDi.getLienDiPersonneInfraction() != null
                    && !detailDommageInteretDi.getLienDiPersonneInfraction().isEmpty()) {
                // On ajoute les lien dommage intérêt personne infraction
                for (LienPersonneInfraction lienPersonneInfractionOrigine : detailDommageInteretDi.getLienDiPersonneInfraction()) {
                    // Pour cela il faut dupliquer chaque lien
                    LienPersonneInfraction lienPersonneInfractionDuplique;
                    if (lienPersonneInfractionOrigine instanceof LienAuteurInfraction) {
                        lienPersonneInfractionDuplique = new LienAuteurInfraction();
                    } else {
                        lienPersonneInfractionDuplique = new LienVictimeInfraction();
                    }
                    // En valorisant l'id du nouveau lien
                    lienPersonneInfractionDuplique.setId(lienPersonneInfractionOrigine.getId());
                    // Et l'ajouter à la liste des lien
                    listLienDiPersonneDuplicate.add(lienPersonneInfractionDuplique);
                }
                // Pour le valoriser dans le dommage et intérêt dupliquer
                detailDiPrononceDuplicate.setLienDiPersonneInfraction(listLienDiPersonneDuplicate);
            }

            copyDetailDommageInteret(this, detailDiPrononceDuplicate);
            return detailDiPrononceDuplicate;
        }
    }

    /**
     * Copie des informations des dommages intérêts commun entre l'article 475-1 CPP et le Dommage intérêt (classique)
     *
     * @param from
     * @param to
     */
    private void copyDetailDommageInteret(DetailDommageInteret from, DetailDommageInteret to) {

        to.setId(null);
        to.setIndicateurRejet(from.isIndicateurRejet());
        to.setIndicateurProvision(from.isIndicateurProvision());
        to.setMontantAccorde(from.getMontantAccorde());
        to.setMontantDemande(from.getMontantDemande());
        to.setTypeDetailReparation(from.getTypeDetailReparation());
        to.setDevise(from.getDevise());

        if (from.getLienDiPersonne() != null && !from.getLienDiPersonne().isEmpty()) {
            // On duplique la relation
            to.setLienDiPersonne(new HashSet<Personne>(from.getLienDiPersonne()));
        }
    }


    public abstract TypeReparationEnum getTypeReparation();

    public abstract List<Personne> getAuteurs();

    public abstract List<Infraction> getInfractions();

    public Date getDateDI() {
        return dateDI;
    }

    public void setDateDI(Date dateDI) {
        this.dateDI = dateDI;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dateDI == null) ? 0 : dateDI.hashCode());
        result = prime * result
                + ((evenement == null) ? 0 : evenement.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + (indicateurProvision ? 1231 : 1237);
        result = prime * result + (indicateurRejet ? 1231 : 1237);
        result = prime * result
                + ((lienDiPersonne == null) ? 0 : lienDiPersonne.hashCode());
        result = prime * result
                + ((montantAccorde == null) ? 0 : montantAccorde.hashCode());
        result = prime * result
                + ((montantDemande == null) ? 0 : montantDemande.hashCode());
        result = prime * result
                + ((personne == null) ? 0 : personne.hashCode());
        result = prime
                * result
                + ((typeDetailReparation == null) ? 0 : typeDetailReparation
                        .hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DetailDommageInteret other = (DetailDommageInteret) obj;
        if (dateDI == null) {
            if (other.dateDI != null)
                return false;
        } else if (!dateDI.equals(other.dateDI))
            return false;
        if (evenement == null) {
            if (other.evenement != null)
                return false;
        } else if (!evenement.equals(other.evenement))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (indicateurProvision != other.indicateurProvision)
            return false;
        if (indicateurRejet != other.indicateurRejet)
            return false;
        if (lienDiPersonne == null) {
            if (other.lienDiPersonne != null)
                return false;
        } else if (!lienDiPersonne.equals(other.lienDiPersonne))
            return false;
        if (montantAccorde == null) {
            if (other.montantAccorde != null)
                return false;
        } else if (!montantAccorde.equals(other.montantAccorde))
            return false;
        if (montantDemande == null) {
            if (other.montantDemande != null)
                return false;
        } else if (!montantDemande.equals(other.montantDemande))
            return false;
        if (personne == null) {
            if (other.personne != null)
                return false;
        } else if (!personne.equals(other.personne))
            return false;
        if (typeDetailReparation == null) {
            if (other.typeDetailReparation != null)
                return false;
        } else if (!typeDetailReparation.equals(other.typeDetailReparation))
            return false;
        return true;
    }

    /**
     * @return the devise
     */
    public Devise getDevise() {
        return devise;
    }

    /**
     * @param devise the devise to set
     */
    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    public DetailDommageInteretFrappeAppel getDommagesInteretsFrappesAppel(Long idEvenementAppel) {
        return dommagesInteretsFrappesAppel.stream()
                .filter(x -> x.getEvenementAppel().getId().equals(idEvenementAppel))
                .findFirst().orElse(null);
    }

}
