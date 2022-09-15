/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.personne.model;

import java.io.Serializable;
import java.util.Date;

import fr.gouv.justice.cassiopee.referentiel.codification.model.TypePieceIdentite;
import fr.gouv.justice.cassiopee.referentiel.structure.model.ElementStructure;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Pays;

/**
 * Pièce administrative : passeport, carte nationale d'identité, etc..
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
     * Type de pièce administrative. Exemples : CI carte nationale d?identité, PA passeport, PC permis de conduire.
     */
    private TypePieceIdentite typePieceIdentite;

    private ElementStructure autoriteDelivranceFrancaise;

    /** Numéro de la pièce administrative. */
    private String numeroPiece;

    /** Date de délivrance de la pièce administrative. */
    private Date dateDelivrance;

    /**
     * Emetteur et lieu d'émission de la pièce administrative, s'il s'agit d'une structure à l'étranger.
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
     * Duplique une pièce administrative
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
