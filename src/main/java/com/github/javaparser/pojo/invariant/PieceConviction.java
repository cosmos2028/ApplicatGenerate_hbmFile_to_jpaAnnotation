/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.scelle.service.enumeration.EtatObjetEnum;
import fr.gouv.justice.cassiopee.invariant.scelle.service.enumeration.TypePieceConvictionEnum;

/**
 * Les objets saisis sont des pièces à conviction (dénommées PAC) et sont déposées au greffe du tribunal de grande
 * instance pour y être conservées tant que besoin. Ces pièces sont destinées à emporter la conviction des juges ou des
 * jurés sur la culpabilité de la personne poursuivie. Classe "mère" qui permet de décrire chacune des pièces à
 * conviction contenues dans le scellé. Cette classe se spécialise dans les classes filles suivantes : - périssable
 * (stupéfiant / denrée) - arme - munition - pièce objet - pièce monétaire
 */
public abstract class PieceConviction implements java.io.Serializable {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = -4299687613359470387L;

    /**
     * Identifiant de la PC
     */
    private Long id;

    /**
     * Le type de scellé contenant la piece à conviction
     */
    private TypeScelle typeScelle;

    /**
     * L'état de la piece à conviction(STOCKE, LIQUIDE...)
     */
    private EtatObjet etatObjet;

    /**
     * L'inventaire répertoriant cette PC.
     */
    private Inventaire inventaire;

    /**
     * Le scellé contenant cette PC.
     */
    private Scelle scelle;

    /**
     * Présence de la PC dans le stock.
     */
    private Boolean indicateurStock;

    /**
     * Indicateur si la PC est une valeur marchande.
     */
    private Boolean indicateurValeurMarchande;

    /**
     * Description de la PC
     */
    private String descriptif;

    /**
     * Nombre de PC
     */
    private Short nombre;

    /**
     * Nature de la PC
     */
    private String nature;

    /**
     * Libelle du sous type de la PC, pour la saisie libre.
     */
    private String libelleSousTypeScelle;

    /**
     * Sous type de la PC contenu par le scellé.
     */
    private SousTypeScelle codeSousTypeScelle;

    /**
     * Date du retour de la PC
     */
    private Date dateSuiteRetourPiece;

    /**
     * Commentaire suite à retour de la PC
     */
    private String commentaireSuiteRetourPiece;

    /**
     * Discriminant Hibernate
     */
    protected String discriminant;

    /**
     * Numéro de PC
     */
    private String numero;

    /**
     * Catégorie de PC
     */
    private String categorie;

    /**
     * Constructeur
     */
    public PieceConviction() {
        //
    }

    /** L'ensemble des Evenements liés à cette Piece à Conviction. */
    private Set<Evenement> evenements = new HashSet<Evenement>();

    /**
     * @return un TypePieceConvictionEnum correspondant à la sous-classe de Piece à conviction
     */
    public TypePieceConvictionEnum getTypePieceConvictionEnum() {
        return TypePieceConvictionEnum.findTypePieceConvictionEnumByCode(this.discriminant);
    }

    /**
     * Retourne l'identifiant Hibernate pour la persistance.
     *
     * @return un Long représentant l'identifiant
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Valorise l'identifiant Hibernate pour la persistance.
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne le type de scellé
     */
    public TypeScelle getTypeScelle() {
        return this.typeScelle;
    }

    /**
     * Valorise le type de Scellé
     *
     * @param typeScelle
     */
    public void setTypeScelle(TypeScelle typeScelle) {
        this.typeScelle = typeScelle;
    }

    /**
     * Indentifie l'état de la PC
     */
    public EtatObjet getEtatObjet() {
        return this.etatObjet;
    }

    /**
     * Retourne l'état de la PC
     *
     * @param etatObjet
     */
    public void setEtatObjet(EtatObjet etatObjet) {
        this.etatObjet = etatObjet;
    }

    /**
     * Identifie l'inventaire répertoriant la PC
     */
    public Inventaire getInventaire() {
        return this.inventaire;
    }

    /**
     * Valorise l'inventaire répertoriant la PC
     *
     * @param fkScePieConInv
     */
    public void setInventaire(Inventaire fkScePieConInv) {
        this.inventaire = fkScePieConInv;
    }

    /**
     * Identifie le scellé contenant la PC
     */
    public Scelle getScelle() {
        return this.scelle;
    }

    /**
     * Valorise le scellé contenant la PC
     *
     * @param scePieConObj
     */
    public void setScelle(Scelle scePieConObj) {
        this.scelle = scePieConObj;
    }

    /**
     * Indentifie si la PC est en stock
     */
    public Boolean getIndicateurStock() {
        return this.indicateurStock;
    }

    /**
     * Valorise la PC dans le stock
     *
     * @param indicateurStock
     */
    public void setIndicateurStock(Boolean indicateurStock) {
        this.indicateurStock = indicateurStock;
    }

    /**
     * Identifie si la PC est une valeur marchande
     */
    public Boolean getIndicateurValeurMarchande() {
        return this.indicateurValeurMarchande;
    }

    /**
     * Valorise si la PC est une valeur marchande
     *
     * @param indicateurValeurMarchande
     */
    public void setIndicateurValeurMarchande(Boolean indicateurValeurMarchande) {
        this.indicateurValeurMarchande = indicateurValeurMarchande;
    }

    /**
     * Identifie la description de la PC
     */
    public String getDescriptif() {
        return this.descriptif;
    }

    /**
     * Retourne la description de la PC
     *
     * @param descriptif
     */
    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    /**
     * Retourne le nombre de PC similaire dans le scellé
     */
    public Short getNombre() {
        return this.nombre;
    }

    /**
     * Valorise le nombre de PC similaire dans le scellé
     *
     * @param nombre
     */
    public void setNombre(Short nombre) {
        this.nombre = nombre;
    }

    /**
     * Retourne la nature de la PC
     */
    public String getNature() {
        return this.nature;
    }

    /**
     * Valorise la nature de la PC
     *
     * @param nature
     */
    public void setNature(String nature) {
        this.nature = nature;
    }

    /**
     * Retourne le libelle saisi de la PC
     */
    public String getLibelleSousTypeScelle() {
        return this.libelleSousTypeScelle;
    }

    /**
     * Valorise le libelle saisi de la PC
     *
     * @param libelleSousTypeScelle
     */
    public void setLibelleSousTypeScelle(String libelleSousTypeScelle) {
        this.libelleSousTypeScelle = libelleSousTypeScelle;
    }

    /**
     * Retourne la date de retour
     */
    public Date getDateSuiteRetourPiece() {
        return this.dateSuiteRetourPiece;
    }

    /**
     * Valorise la date de retour
     *
     * @param dateSuiteRetourPiece
     */
    public void setDateSuiteRetourPiece(Date dateSuiteRetourPiece) {
        this.dateSuiteRetourPiece = dateSuiteRetourPiece;
    }

    /**
     * Retourn les commentaires du retour
     */
    public String getCommentaireSuiteRetourPiece() {
        return this.commentaireSuiteRetourPiece;
    }

    /**
     * Valorise les commentaires du retour
     *
     * @param commentaireSuiteRetourPiece
     */
    public void setCommentaireSuiteRetourPiece(String commentaireSuiteRetourPiece) {
        this.commentaireSuiteRetourPiece = commentaireSuiteRetourPiece;
    }

    /**
     * Retourne l'ensemble des Evénements liés à cette Pièce à Conviction.
     *
     * @return Returns the evenements.
     */
    public Set<Evenement> getEvenements() {
        return evenements;
    }

    /**
     * Valorise l'ensemble des Evénements liés à cette Pièce à Conviction.
     *
     * @param evenements
     *            The evenements to set.
     */
    public void setEvenements(Set<Evenement> evenements) {
        this.evenements = evenements;
    }

    /**
     * Retourne le numéro de la PC
     *
     * @return Returns the numero.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Valorise le numéro de la PC
     *
     * @param numero
     *            The numero to set.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Retourne la catégorie de la PC
     *
     * @return Returns the categorie.
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * Valorise la catégorie de la PC
     *
     * @param categorie
     *            The categorie to set.
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    /**
     * Retourne le souTypeScelle de la PC
     *
     * @return Returns the codeSousTypeScelle.
     */
    public SousTypeScelle getCodeSousTypeScelle() {
        return codeSousTypeScelle;
    }

    /**
     * Valorise le souTypeScelle de la PC
     *
     * @param codeSousTypeScelle
     *            The codeSousTypeScelle to set.
     */
    public void setCodeSousTypeScelle(SousTypeScelle codeSousTypeScelle) {
        this.codeSousTypeScelle = codeSousTypeScelle;
    }

    /**
     * indique que la pièce est sortie ou non
     *
     * @return true ou false
     */
    public boolean estSortie() {
        return EtatObjetEnum.SORTI_DEFINITIVEMENT.is(this.getEtatObjet().getCode())
                || EtatObjetEnum.SORTI_PROVISOIREMENT.is(this.getEtatObjet().getCode());
    }

    /**
     * indique que la pièce est sortie définifive, supprimée ou non
     *
     * @return true ou false
     */
    public boolean estSortieDefinitivementOuSupprime() {
        return EtatObjetEnum.SORTI_DEFINITIVEMENT.is(this.getEtatObjet().getCode())
                || EtatObjetEnum.SUPPRIME.is(this.getEtatObjet().getCode());
    }
}
