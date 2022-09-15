/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.infraction.model;

import java.util.Date;

import fr.gouv.justice.cassiopee.administration.locale.model.TypeArrete;
import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;

/**
 * Un texte local est une r�f�rence de l'arr�t� municipal ou pr�fectoral ou du d�cret d�finissant l'infraction.
 */
public class TexteLocal implements java.io.Serializable, Cloneable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = -2086917153161549913L;

    private static final HashCodeEqualsHelper<TexteLocal> HE_HELPER = HashCodeEqualsHelper.of(TexteLocal.class, TexteLocal::getId, TexteLocal::getReferenceArrete, TexteLocal::getTitre);

    /** Identifiant de l'objet TexteLocal */
    private Long id;

    /** Indique le type d'arr�t� municipal ou pr�fectoral */
    private TypeArrete typeArrete;

    /** R�f�rence de l'arr�t� */
    private String referenceArrete;

    /** Date de l'arr�t� */
    private Date dateArrete;

    /** Ville concern�e par l'arr�t� */
    private String villeArrete;

    /** Titre de l'arr�t� */
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
     * Retourne le type d'arr�t� municipal ou pr�fectoral
     */
    public TypeArrete getTypeArrete() {
        return this.typeArrete;
    }

    /**
     * Renseigne le type d'arr�t� municipal ou pr�fectoral
     *
     * @param typeArrete
     *            le type de l'arr�t�
     */
    public void setTypeArrete(TypeArrete typeArrete) {
        this.typeArrete = typeArrete;
    }

    /**
     * Indique la r�f�rence de l'arr�t�
     */
    public String getReferenceArrete() {
        return this.referenceArrete;
    }

    /**
     * Renseigne la r�f�rence de l'arr�t�
     *
     * @param referenceArrete
     *            la r�f�rence de l'arr�t�
     */
    public void setReferenceArrete(String referenceArrete) {
        this.referenceArrete = referenceArrete;
    }

    /**
     * Retourne la date de l'arr�t�
     */
    public Date getDateArrete() {
        return this.dateArrete;
    }

    /**
     * Indique la date de l'arr�t�
     *
     * @param dateArrete
     *            la date de l'arr�t�
     */
    public void setDateArrete(Date dateArrete) {
        this.dateArrete = dateArrete;
    }

    /**
     * Indique la ville concern�e par l'arr�t�.
     *
     * @return un String indiquant la ville concern�e par l'arr�t�.
     */
    public String getVilleArrete() {
        return this.villeArrete;
    }

    /**
     * Renseigne la ville de l'arr�t�
     *
     * @param villeArrete
     *            la ville de l'arr�t�
     */
    public void setVilleArrete(String villeArrete) {
        this.villeArrete = villeArrete;
    }

    /**
     * Retourne le titre de l'arr�t�.
     *
     * @return un String repr�sentant le titre de l'arr�t�.
     */
    public String getTitre() {
        return this.titre;
    }

    /**
     * Renseigne le titre de l'arr�t�
     *
     * @param titre
     *            le titre de l'arr�t�
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Renvoie un clone de ce texte local, non encore sauv� en base et non li� � d'autres objets.
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
