package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import org.apache.commons.lang.builder.CompareToBuilder;

import fr.gouv.justice.cassiopee.common.exception.TechnicalException;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.TypeCaractPeineMesure;

/**
 * mod�le g�n�rique d'une valeur de caract�ristique de peine et mesure
 *
 * @param <E>
 *            type de valeur
 */
public abstract class ValeurCaracteristique<E> implements ValeurCaracteristiquePeine<E>, Comparable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 5897104802410747441L;

    /** identifiant compos� de la valeur de la caract�ristique de peine et mesure */
    private ValeurCaracteristiqueId id;

    private String discriminant;

    /**
     * @return ValeurCaracteristiqueId
     */
    public ValeurCaracteristiqueId getId() {
        return this.id;
    }

    /**
     * @param id
     */
    public void setId(ValeurCaracteristiqueId id) {
        this.id = id;
    }

    /**
     * @return String
     */
    public String getDiscriminant() {
        return discriminant;
    }

    /**
     * @param discriminant
     */
    public void setDiscriminant(String discriminant) {
        this.discriminant = discriminant;
    }

    /**
     * m�thode pour recup�rer de fa�on g�n�rique la valeur de la caracteristique
     *
     * @return E
     */
    public abstract E getValue();

    /**
     * m�thode pour affecter de fa�on g�n�rique la valeur de la caracteristique
     *
     * @param value
     */
    public abstract void setValue(E value);

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.gouv.justice.cassiopee.invariant.peineoumesure.model.ValeurCaracteristiquePeine#getTypeCaractPeineMesure()
     */
    public TypeCaractPeineMesure getTypeCaractPeineMesure() {
        return id.getTypeCaractMesure();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.gouv.justice.cassiopee.invariant.peineoumesure.model.ValeurCaracteristiquePeine#setTypeCaractPeineMesure(fr
     * .gouv.justice.cassiopee.referentiel.peineoumesure.model.TypeCaractPeineMesure)
     */
    public void setTypeCaractPeineMesure(TypeCaractPeineMesure typeCaractPeineMesure) {
        id.setTypeCaractMesure(typeCaractPeineMesure);
    }

    /**
     * Ordre naturel
     */
    @Override
    public int compareTo(Object object) {

        ValeurCaracteristique myClass = (ValeurCaracteristique) object;

        return new CompareToBuilder().append(this.getId().getTypeCaractMesure().getCode(),
                myClass.getId().getTypeCaractMesure().getCode()).append(this.id.getPeineOuMesure().getId(),
                myClass.id.getPeineOuMesure().getId()).toComparison();
    }

    /**
     * dupliquer une caract. � partir de la caract. actuelle (meme valeur, disriminant, type caract.) et pour une peine
     * donn�e (cette peine est utilis�e pour construire valeurCaracteristiqueId: 2 caract. ne peuvent avoir meme type
     * caract. et meme peine)
     *
     * @param peineOuMesure
     * @return Valeur caract. dupliqu�e
     */
    public ValeurCaracteristique dupliquerCaract(PeineOuMesure peineOuMesure) {
        ValeurCaracteristique valeurCaracteristiqueDuplicate;
        try {
            valeurCaracteristiqueDuplicate = this.getClass().newInstance();
        } catch (InstantiationException e) {
            throw new TechnicalException("Instantiation Exception", e);
        } catch (IllegalAccessException e) {
            throw new TechnicalException("IllegalAccessException", e);
        }
        // meme valeur
        valeurCaracteristiqueDuplicate.setValue(this.getValue());
        ValeurCaracteristiqueId valeurCaracteristiqueId = new ValeurCaracteristiqueId();
        // meme type caract.
        valeurCaracteristiqueId.setTypeCaractMesure(this.getTypeCaractPeineMesure());
        // peine en param
        valeurCaracteristiqueId.setPeineOuMesure(peineOuMesure);
        valeurCaracteristiqueDuplicate.setId(valeurCaracteristiqueId);

        return valeurCaracteristiqueDuplicate;
    }
}
