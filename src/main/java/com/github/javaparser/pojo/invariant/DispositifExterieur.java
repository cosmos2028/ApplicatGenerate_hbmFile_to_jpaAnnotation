package fr.gouv.justice.cassiopee.invariant.exterieur.model;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement.EvenementExterieur;
import fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement.EvenementExterieurPortantDispo;
import fr.gouv.justice.cassiopee.invariant.exterieur.service.enumeration.TypeDispositifExterieurEnum;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.LienPeineDispoExterieur;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.PeineOuMesure;
import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;

/**
 * Classe abstraite créée pour regrouper les données communes aux dispositifs extérieurs d'un auteur et d'une victime.
 */
public abstract class DispositifExterieur implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4179793746862576507L;

    private Long id;

    private Personne personne;

    private String typeDispositif;

    /**
     * Retourne l'énumération correspondant à la sous-classe de DispositifExterieur (auteur ou victime)
     *
     * @return l'énumération
     */
    public abstract TypeDispositifExterieurEnum getTypeDispositifExterieurEnum();

    private Integer rangPersonneDansAffaireOrigine;

    private Set<EvenementExterieur> evenements = new LinkedHashSet<EvenementExterieur>();

    private Set<PeineOuMesure> peineOuMesuresAffaireLiees = new HashSet<PeineOuMesure>();

    private Set<LienPeineDispoExterieur> lienPeinesAffaire = new HashSet<LienPeineDispoExterieur>();

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

    public Set<EvenementExterieur> getEvenements() {
        return evenements;
    }

    public void setEvenements(Set<EvenementExterieur> evenements) {
        this.evenements = evenements;
    }

    /**
     * Renvoie l'événement portant dispositif (événement de décision) de ce dispositif extérieur. Renvoie le premier
     * trouvé ou null, sans vérifier l'existence ou l'unicité. En principe un dispositif extérieur doit toujours avoir
     * un seul événement de décision.
     *
     * @return l'événement po
     */
    public EvenementExterieurPortantDispo getEvenementDecision() {
        for (EvenementExterieur evt : this.getEvenements()) {
            if (evt instanceof EvenementExterieurPortantDispo) {
                return (EvenementExterieurPortantDispo) evt;
            }
        }
        return null;
    }

    /**
     * Renvoie l'événement du dispositif extérieur portant l'ID désirée. Renvoie le premier trouvé ou null, sans
     * vérifier l'existence.
     *
     * @param id
     *            l' id désirée.
     * @return l' EvenementExterieur trouvé, ou null.
     */
    public EvenementExterieur getEvenementById(Long id) {
        if (id == null) {
            return null;
        }
        for (EvenementExterieur evt : this.getEvenements()) {
            if (id.equals(evt.getId())) {
                return evt;
            }
        }
        return null;
    }

    /**
     * @return Returns the rangPersonneDansAffaireOrigine.
     */
    public Integer getRangPersonneDansAffaireOrigine() {
        return rangPersonneDansAffaireOrigine;
    }

    /**
     * @param rangPersonneDansAffaireOrigine
     *            The rangPersonneDansAffaireOrigine to set.
     */
    public void setRangPersonneDansAffaireOrigine(Integer rangPersonneDansAffaireOrigine) {
        this.rangPersonneDansAffaireOrigine = rangPersonneDansAffaireOrigine;
    }

    /**
     * Renvoie les peines ou mesures de l'affaire, liées à ce dispositif extérieur.
     *
     * @return Returns the peineOuMesures.
     */
    public Set<PeineOuMesure> getPeineOuMesuresAffaireLiees() {
        return peineOuMesuresAffaireLiees;
    }

    /**
     * Valorise les peines ou mesures de l'affaire, liées à ce dispositif extérieur.
     *
     * @param peineOuMesures
     *            The peineOuMesures to set.
     */
    public void setPeineOuMesuresAffaireLiees(Set<PeineOuMesure> peineOuMesures) {
        this.peineOuMesuresAffaireLiees = peineOuMesures;
    }

    /**
     * Renvoie les liens entre ce dispositif extérieur et les peines de l'affaire.
     *
     * @return Returns the lienPeinesAffaire.
     */
    public Set<LienPeineDispoExterieur> getLienPeinesAffaire() {
        return lienPeinesAffaire;
    }

    /**
     * Valorise les liens entre ce dispositif extérieur et les peines de l'affaire.
     *
     * @param lienPeinesAffaire
     *            The lienPeinesAffaire to set.
     */
    public void setLienPeinesAffaire(Set<LienPeineDispoExterieur> lienPeinesAffaire) {
        this.lienPeinesAffaire = lienPeinesAffaire;
    }

    /**
     * @return Returns the typeDispositif.
     */
    public String getTypeDispositif() {
        return typeDispositif;
    }

    /**
     * @param typeDispositif
     *            The typeDispositif to set.
     */
    private void setTypeDispositif(String typeDispositif) {
        this.typeDispositif = typeDispositif;
    }

}
