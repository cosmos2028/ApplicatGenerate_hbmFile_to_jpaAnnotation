package fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.Validate;

import fr.gouv.justice.cassiopee.administration.acteur.externe.model.PersonneQualifiee;
import fr.gouv.justice.cassiopee.administration.structure.model.Service;
import fr.gouv.justice.cassiopee.invariant.evenement.model.interfaces.IEvenementCaracs;
import fr.gouv.justice.cassiopee.invariant.exterieur.model.DispositifExterieur;
import fr.gouv.justice.cassiopee.invariant.personne.model.AvocatPersonneAffaire;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.CaracteristiqueType;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.EvenementType;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.AvocatCaracteristiqueTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.ElementStructureCaracteristiqueTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.PersonneQualifieeCaracteristiqueTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.ServiceCaracteristiqueTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.structure.model.ElementStructure;

/**
 * Classe abstraite permettant d'indiquer les donn�es n�cessaires aux �v�nements inclus dans le dispositif p�nal
 * ext�rieur de l'auteur. Il peut s'agir d'�v�nement de diff�rents types. Cette classe m�re se sp�cialise en classes
 * filles, comme :
 * <ul>
 * <li>�v�nement portant dispo</li>
 * <li>�v�nement de recherche ou d�tention</li>
 * <li>�v�nement de signification / notification</li>
 * </ul>
 */
public abstract class EvenementExterieur implements IEvenementCaracs<ValeurCaracteristiqueEvenementExterieur>,
        java.io.Serializable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 8303032768447473047L;

    /** Identifiant de l'objet EvenementExterieur */
    private Long id;

    /** Evenement Type */
    private EvenementType evenementType;

    /**
     * Date de l'�v�nement
     */
    private Date dateEvenement;

    /** Dispositif ext�rieur qui contient l'�v�nement */
    private DispositifExterieur dispositifExterieur;

    /** Element de structure �metteur de l'�v�nement */
    private ElementStructure emetteur;

    /** les caract�ristiques persist�es de l'�v�nement */
    private Map<String, ValeurCaracteristiqueEvenementExterieur> valeurCaracteristiques = new LinkedHashMap<String, ValeurCaracteristiqueEvenementExterieur>();

    private ElementStructure juridictionParquetExterieur;

    /**
     * Toutes les caract�ristiques de l'�v�nement
     *
     * @return un ValeurCaracteristiquesExterieurUpdater impl�mentant un Map de toutes les caract�ristiques, celles
     *         enregistr�es pour cet �v�nement aussi bien que celles de l'�v�nement-type.
     */
    public ValeurCaracteristiquesExterieurUpdater getCaracteristiques() {
        return new ValeurCaracteristiquesExterieurUpdater(this);
    }

    /**
     * Toutes les caract�ristiques de l'�v�nement sans mettre � jour la BD (a utiliser cote edition)
     *
     * @return un ValeurCaracteristiquesExterieurUpdater impl�mentant un Map de toutes les caract�ristiques, celles
     *         enregistr�es pour cet �v�nement aussi bien que celles de l'�v�nement-type.
     */
    public ValeurCaracteristiquesExterieurUpdater getCaracteristiquesForEdition() {
        return new ValeurCaracteristiquesExterieurUpdater(this, false);
    }

    /**
     * Valorise les caract�ristiques de l'�v�nement.
     *
     * @param caracteristiques
     *            un Set contenant les caract�ristiques de l'�v�nement.
     */
    public void setCaracteristiques(Map<String, ValeurCaracteristiqueEvenementExterieur> caracteristiques) {
        this.setCaracteristiquesPersistante(caracteristiques);
    }

    /**
     * Retourne le dispositif ext�rieur
     *
     * @return le dispositif ext�rieur
     */
    public DispositifExterieur getDispositifExterieur() {
        return dispositifExterieur;
    }

    /**
     * Renseigne le dispositif ext�rieur
     *
     * @param dispositifExterieur
     */
    public void setDispositifExterieur(DispositifExterieur dispositifExterieur) {
        this.dispositifExterieur = dispositifExterieur;
    }

    /**
     * Retourne l'�lement de structure �metteur de l'�v�nement
     *
     * @return l'�lement de structure �metteur de l'�v�nement
     */
    public ElementStructure getEmetteur() {
        return emetteur;
    }

    /**
     * Renseigne l'�lement de structure �metteur de l'�v�nement
     *
     * @param emetteur
     *            l'�lement de structure �metteur de l'�v�nement
     */
    public void setEmetteur(ElementStructure emetteur) {
        this.emetteur = emetteur;
    }

    /**
     * Retourne le type de l'�venement
     *
     * @return le type de l'�venement
     */
    public EvenementType getEvenementType() {
        return evenementType;
    }

    /**
     * Renseigne le type de l'�venement
     *
     * @param evenementType
     *            le type de l'�venement
     */
    public void setEvenementType(EvenementType evenementType) {
        this.evenementType = evenementType;
    }

    /**
     * retourne l'identifiant de l'objet EvenementExterieur
     *
     * @return l'identifiant de l'objet EvenementExterieur
     */
    public Long getId() {
        return id;
    }

    /**
     * renseigne l'identifiant de l'objet EvenementExterieur
     *
     * @param id
     *            l'identifiant de l'objet EvenementExterieur
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * retourne la table des caract�ristiques
     *
     * @return la table des caract�ristiques
     */
    public Map<String, ValeurCaracteristiqueEvenementExterieur> getValeurCaracteristiques() {
        return valeurCaracteristiques;
    }

    /**
     * renseigne la table des caract�ristiques
     *
     * @param valeurCaracteristiques
     *            la table des caract�ristiques
     */
    public void setValeurCaracteristiques(Map<String, ValeurCaracteristiqueEvenementExterieur> valeurCaracteristiques) {
        this.valeurCaracteristiques = valeurCaracteristiques;
    }

    /**
     * retourne la valeur d'une caract�ristique identifi�e par le type de caract�ristique
     *
     * @param <E>
     *            type de valeur
     * @param caracteristiqueType
     *            caracteristiqueType type de caract�ristique
     * @return la valeur d'une caract�ristique identifi�e par le type de caract�ristique
     */
    @SuppressWarnings("unchecked")
    public <E> ValeurCaracteristiqueEvenementExterieur<E> getValeurCaracteristique(
            CaracteristiqueType caracteristiqueType) {

        Validate.notNull(caracteristiqueType, "caracteristiqueTypeCode obligatoire");

        return this.valeurCaracteristiques.get(caracteristiqueType.getCode());
    }

    /**
     * @return Returns the dateEvenement.
     */
    @Override
    public Date getDateEvenement() {
        return dateEvenement;
    }

    /**
     * @param dateEvenement
     *            The dateEvenement to set.
     */
    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof EvenementExterieur)) {
            return false;
        }
        final EvenementExterieur evt = (EvenementExterieur) other;

        if (this.getId() != null && evt.getId() != null) {
            return this.getId().equals(evt.getId());
        }

        return false;

    }

    @Override
    public Map<String, ValeurCaracteristiqueEvenementExterieur> getCaracteristiquesPersistante() {
        return valeurCaracteristiques;
    }

    @Override
    public void setCaracteristiquesPersistante(
            Map<String, ValeurCaracteristiqueEvenementExterieur> caracteristiquesPersistante) {
        setValeurCaracteristiques(caracteristiquesPersistante);

    }

    public ElementStructure getJuridictionParquetExterieur() {
        return juridictionParquetExterieur;
    }

    public void setJuridictionParquetExterieur(ElementStructure juridictionParquetExterieur) {
        this.juridictionParquetExterieur = juridictionParquetExterieur;
    }

    @Override
    public EvenementType getEvenementTypePourCaracs() {
        return evenementType;
    }

    public ValeurCaracteristiqueEvenementExterieur findCaracteristiquePersistanteByCode(String code) {
        if (code == null || code.isEmpty()) {
            return null;
        }
        return getCaracteristiquesPersistante().get(code);
    }

    public ElementStructure findElementStructureCaracteristique(ElementStructureCaracteristiqueTypeEnum typeEnum) {
        if (typeEnum == null || typeEnum.getCode().isEmpty()) {
            return null;
        }
        ValeurCaracteristiqueEvenementExterieur valcar = findCaracteristiquePersistanteByCode(typeEnum.getCode());
        return (ElementStructure) valcar.getValue();
    }

    public Service findServiceCaracteristique(ServiceCaracteristiqueTypeEnum typeEnum) {
        if (typeEnum == null || typeEnum.getCode().isEmpty()) {
            return null;
        }
        ValeurCaracteristiqueEvenementExterieur valcar = findCaracteristiquePersistanteByCode(typeEnum.getCode());
        return (Service) valcar.getValue();
    }

    public PersonneQualifiee findPersonneQualifieeCaracteristique(PersonneQualifieeCaracteristiqueTypeEnum typeEnum) {
        if (typeEnum == null || typeEnum.getCode().isEmpty()) {
            return null;
        }
        ValeurCaracteristiqueEvenementExterieur valcar = findCaracteristiquePersistanteByCode(typeEnum.getCode());
        return (PersonneQualifiee) valcar.getValue();
    }

    public AvocatPersonneAffaire findAvocatCaracteristique(AvocatCaracteristiqueTypeEnum typeEnum) {
        if (typeEnum == null || typeEnum.getCode().isEmpty()) {
            return null;
        }
        ValeurCaracteristiqueEvenementExterieur valcar = findCaracteristiquePersistanteByCode(typeEnum.getCode());
        return (AvocatPersonneAffaire) valcar.getValue();
    }
}
