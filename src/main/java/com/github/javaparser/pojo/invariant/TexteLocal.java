/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.infraction.model;

import java.util.Date;

import fr.gouv.justice.cassiopee.administration.locale.model.TypeArrete;
import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;

/**
 * Un texte local est une référence de l'arrêté municipal ou préfectoral ou du décret définissant l'infraction.
 */
public class TexteLocal implements java.io.Serializable, Cloneable {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = -2086917153161549913L;

    private static final HashCodeEqualsHelper<TexteLocal> HE_HELPER = HashCodeEqualsHelper.of(TexteLocal.class, TexteLocal::getId, TexteLocal::getReferenceArrete, TexteLocal::getTitre);

    /** Identifiant de l'objet TexteLocal */
    private Long id;

    /** Indique le type d'arrêté municipal ou préfectoral */
    private TypeArrete typeArrete;

    /** Référence de l'arrêté */
    private String referenceArrete;

    /** Date de l'arrêté */
    private Date dateArrete;

    /** Ville concernée par l'arrêté */
    private String villeArrete;

    /** Titre de l'arrêté */
    private String titre;

    /**
     * Retourne l'identifiant de l'objet TexteLocal
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Renseigne l'identifiant de l'objet TexteLocal
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne le type d'arrêté municipal ou préfectoral
     */
    public TypeArrete getTypeArrete() {
        return this.typeArrete;
    }

    /**
     * Renseigne le type d'arrêté municipal ou préfectoral
     *
     * @param typeArrete
     *            le type de l'arrêté
     */
    public void setTypeArrete(TypeArrete typeArrete) {
        this.typeArrete = typeArrete;
    }

    /**
     * Indique la référence de l'arrêté
     */
    public String getReferenceArrete() {
        return this.referenceArrete;
    }

    /**
     * Renseigne la référence de l'arrêté
     *
     * @param referenceArrete
     *            la référence de l'arrêté
     */
    public void setReferenceArrete(String referenceArrete) {
        this.referenceArrete = referenceArrete;
    }

    /**
     * Retourne la date de l'arrêté
     */
    public Date getDateArrete() {
        return this.dateArrete;
    }

    /**
     * Indique la date de l'arrêté
     *
     * @param dateArrete
     *            la date de l'arrêté
     */
    public void setDateArrete(Date dateArrete) {
        this.dateArrete = dateArrete;
    }

    /**
     * Indique la ville concernée par l'arrêté.
     *
     * @return un String indiquant la ville concernée par l'arrêté.
     */
    public String getVilleArrete() {
        return this.villeArrete;
    }

    /**
     * Renseigne la ville de l'arrêté
     *
     * @param villeArrete
     *            la ville de l'arrêté
     */
    public void setVilleArrete(String villeArrete) {
        this.villeArrete = villeArrete;
    }

    /**
     * Retourne le titre de l'arrêté.
     *
     * @return un String représentant le titre de l'arrêté.
     */
    public String getTitre() {
        return this.titre;
    }

    /**
     * Renseigne le titre de l'arrêté
     *
     * @param titre
     *            le titre de l'arrêté
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Renvoie un clone de ce texte local, non encore sauvé en base et non lié à d'autres objets.
     *
     * @throws CloneNotSupportedException
     */
    @Override
    public TexteLocal clone() throws CloneNotSupportedException {
        TexteLocal clone = (TexteLocal) super.clone();
        if (this.dateArrete != null) {
            clone.setDateArrete((Date) this.dateArrete.clone());
        }
        return clone;
    }

    @Override
    public boolean equals(Object obj){
        return HE_HELPER.areEqual(this, obj);
    }

    @Override
    public int hashCode() {
        return HE_HELPER.getHashCode(this);
    }

}
