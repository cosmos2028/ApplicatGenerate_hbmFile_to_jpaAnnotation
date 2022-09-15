/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.io.Serializable;
import java.util.Date;

import fr.gouv.justice.cassiopee.referentiel.codification.model.TypePieceIdentite;
import fr.gouv.justice.cassiopee.referentiel.structure.model.ElementStructure;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Pays;

/**
 * Pi�ce administrative : passeport, carte nationale d'identit�, etc..
 */
public class PieceAdministrative implements Serializable, Cloneable {

    /**
     *
     */
    private static final long serialVersionUID = -6638026416038798465L;

    private Long id;

    private Pays paysDelivrance;

    private Personne personne;

    private CategoriePermisConduire categoriePermisConduire;

    /**
     * Type de pi�ce administrative. Exemples : CI carte nationale d?identit�, PA passeport, PC permis de conduire.
     */
    private TypePieceIdentite typePieceIdentite;

    private ElementStructure autoriteDelivranceFrancaise;

    /** Num�ro de la pi�ce administrative. */
    private String numeroPiece;

    /** Date de d�livrance de la pi�ce administrative. */
    private Date dateDelivrance;

    /**
     * Emetteur et lieu d'�mission de la pi�ce administrative, s'il s'agit d'une structure � l'�tranger.
     */
    private String autoriteVilleDelivranceEtranger;

    /**
     *
     */
    public PieceAdministrative() {
    }

    /**
     * @return Id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @param id
     *            affecte id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return pays
     */
    public Pays getPaysDelivrance() {
        return this.paysDelivrance;
    }

    /**
     * @param pays
     *            affecte pays
     */
    public void setPaysDelivrance(Pays pays) {
        this.paysDelivrance = pays;
    }

    /**
     * @return personne
     */
    public Personne getPersonne() {
        return this.personne;
    }

    /**
     * @param personne
     *            affecte personne
     */
    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    /**
     * @return categoriePermisConduire
     */
    public CategoriePermisConduire getCategoriePermisConduire() {
        return this.categoriePermisConduire;
    }

    /**
     * @param categoriePermisConduire
     *            affecte categoriePermisConduire
     */
    public void setCategoriePermisConduire(CategoriePermisConduire categoriePermisConduire) {
        this.categoriePermisConduire = categoriePermisConduire;
    }

    /**
     * @return typePieceIdentite
     */
    public TypePieceIdentite getTypePieceIdentite() {
        return this.typePieceIdentite;
    }

    /**
     * @param typePieceIdentite
     *            affecte typePieceIdentite
     */
    public void setTypePieceIdentite(TypePieceIdentite typePieceIdentite) {
        this.typePieceIdentite = typePieceIdentite;
    }

    /**
     * @return autoriteDelivranceFrancaise
     */
    public ElementStructure getAutoriteDelivranceFrancaise() {
        return this.autoriteDelivranceFrancaise;
    }

    /**
     * @param autoriteDelivranceFrancaise
     *            affcete
     */
    public void setAutoriteDelivranceFrancaise(ElementStructure autoriteDelivranceFrancaise) {
        this.autoriteDelivranceFrancaise = autoriteDelivranceFrancaise;
    }

    /**
     * @return numeroPiece
     */
    public String getNumeroPiece() {
        return this.numeroPiece;
    }

    /**
     * @param numeroPiece
     *            affecte numeroPiece
     */
    public void setNumeroPiece(String numeroPiece) {
        this.numeroPiece = numeroPiece;
    }

    /**
     * @return dateDelivrance
     */
    public Date getDateDelivrance() {
        return this.dateDelivrance;
    }

    /**
     * @param dateDelivrance
     *            affecte dateDelivrance
     */
    public void setDateDelivrance(Date dateDelivrance) {
        this.dateDelivrance = dateDelivrance;
    }

    /**
     * @return autoriteVilleDelivranceEtranger
     */
    public String getAutoriteVilleDelivranceEtranger() {
        return this.autoriteVilleDelivranceEtranger;
    }

    /**
     * @param autoriteVilleDelivranceEtr
     *            affecte autoriteVilleDelivranceEtr
     */
    public void setAutoriteVilleDelivranceEtranger(String autoriteVilleDelivranceEtr) {
        this.autoriteVilleDelivranceEtranger = autoriteVilleDelivranceEtr;
    }

    /**
     * Duplique une pi�ce administrative
     */
    public Object clone() throws CloneNotSupportedException {
        PieceAdministrative newPiece = (PieceAdministrative) super.clone();
        newPiece.setId(null);
        newPiece.setPersonne(null);
        if (dateDelivrance != null) {
            newPiece.setDateDelivrance((Date) dateDelivrance.clone());
        }
        return newPiece;
    }
}
