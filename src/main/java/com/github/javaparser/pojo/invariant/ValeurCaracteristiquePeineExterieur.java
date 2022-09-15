/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model.peine;

import org.apache.commons.lang.builder.CompareToBuilder;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.model.ValeurCaracteristiquePeine;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.TypeCaractPeineMesure;

/**
 * Mod�le de la valeur de la valeur de caract�ristique de peine ext�rieur
 *
 * @param <E>
 *            le type de valeur
 */
public abstract class ValeurCaracteristiquePeineExterieur<E> implements ValeurCaracteristiquePeine<E>, Comparable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 1L;

    private Long id;

    private PeineExterieur peineExterieur;

    private TypeCaractPeineMesure typeCaractPeineMesure;

    /**
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return la PeineExterieur � laquelle est ratach�e cette caract�ristique.
     */
    public PeineExterieur peine() {
        return peineExterieur;
    }

    /**
     * @return Returns the peineExterieur.
     */
    public PeineExterieur getPeineExterieur() {
        return peineExterieur;
    }

    /**
     * @param peineExterieur
     *            la PeineExterieur � laquelle est rattach�e cette caract�ristique.
     */
    public void setPeineExterieur(PeineExterieur peineExterieur) {
        this.peineExterieur = peineExterieur;
    }

    public TypeCaractPeineMesure getTypeCaractPeineMesure() {
        return typeCaractPeineMesure;
    }

    public void setTypeCaractPeineMesure(TypeCaractPeineMesure typeCaractPeineMesure) {
        this.typeCaractPeineMesure = typeCaractPeineMesure;
    }

    /**
     * Ordre naturel
     */
    public int compareTo(Object object) {

        ValeurCaracteristiquePeineExterieur myClass = (ValeurCaracteristiquePeineExterieur) object;

        return new CompareToBuilder().append(this.getTypeCaractPeineMesure().getCode(),
                myClass.getTypeCaractPeineMesure().getCode()).append(this.getPeineExterieur().getId(),
                myClass.getPeineExterieur().getId()).toComparison();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.peineoumesure.model.ValeurCaracteristiquePeine#getValue()
     */
    abstract public E getValue();

    /*
     * (non-Javadoc)
     *
     * @see fr.gouv.justice.cassiopee.invariant.peineoumesure.model.ValeurCaracteristiquePeine#setValue(null)
     */
    abstract public void setValue(E value);

}
