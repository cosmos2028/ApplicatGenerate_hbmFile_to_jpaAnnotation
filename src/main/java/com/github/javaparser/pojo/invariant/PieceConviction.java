/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import fr.gouv.justice.cassiopee.invariant.evenement.model.Evenement;
import fr.gouv.justice.cassiopee.invariant.scelle.service.enumeration.EtatObjetEnum;
import fr.gouv.justice.cassiopee.invariant.scelle.service.enumeration.TypePieceConvictionEnum;

/**
 * Les objets saisis sont des pi�ces � conviction (d�nomm�es PAC) et sont d�pos�es au greffe du tribunal de grande
 * instance pour y �tre conserv�es tant que besoin. Ces pi�ces sont destin�es � emporter la conviction des juges ou des
 * jur�s sur la culpabilit� de la personne poursuivie. Classe "m�re" qui permet de d�crire chacune des pi�ces �
 * conviction contenues dans le scell�. Cette classe se sp�cialise dans les classes filles suivantes : - p�rissable
 * (stup�fiant / denr�e) - arme - munition - pi�ce objet - pi�ce mon�taire
 */
public abstract class PieceConviction implements java.io.Serializable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = -4299687613359470387L;

    /**
     * Identifiant de la PC
     */
    private Long id;

    /**
     * Le type de scell� contenant la piece � conviction
     */
    private TypeScelle typeScelle;

    /**
     * L'�tat de la piece � conviction(STOCKE, LIQUIDE...)
     */
    private EtatObjet etatObjet;

    /**
     * L'inventaire r�pertoriant cette PC.
     */
    private Inventaire inventaire;

    /**
     * Le scell� contenant cette PC.
     */
    private Scelle scelle;

    /**
     * Pr�sence de la PC dans le stock.
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
     * Sous type de la PC contenu par le scell�.
     */
    private SousTypeScelle codeSousTypeScelle;

    /**
     * Date du retour de la PC
     */
    private Date dateSuiteRetourPiece;

    /**
     * Commentaire suite � retour de la PC
     */
    private String commentaireSuiteRetourPiece;

    /**
     * Discriminant Hibernate
     */
    protected String discriminant;

    /**
     * Num�ro de PC
     */
    private String numero;

    /**
     * Cat�gorie de PC
     */
    private String categorie;

    /**
     * Constructeur
     */
    public PieceConviction() {
        //
    }

    /** L'ensemble des Evenements li�s � cette Piece � Conviction. */
    private Set<Evenement> evenements = new HashSet<Evenement>();

    /**
     * @return un TypePieceConvictionEnum correspondant � la sous-classe de Piece � conviction
     */
    public TypePieceConvictionEnum getTypePieceConvictionEnum() {
        return TypePieceConvictionEnum.findTypePieceConvictionEnumByCode(this.discriminant);
    }

    /**
     * Retourne l'identifiant Hibernate pour la persistance.
     *
     * @return un Long repr�sentant l'identifiant
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
     * Retourne le type de scell�
     */
    public TypeScelle getTypeScelle() {
        return this.typeScelle;
    }

    /**
     * Valorise le type de Scell�
     *
     * @param typeScelle
     */
    public void setTypeScelle(TypeScelle typeScelle) {
        this.typeScelle = typeScelle;
    }

    /**
     * Indentifie l'�tat de la PC
     */
    public EtatObjet getEtatObjet() {
        return this.etatObjet;
    }

    /**
     * Retourne l'�tat de la PC
     *
     * @param etatObjet
     */
    public void setEtatObjet(EtatObjet etatObjet) {
        this.etatObjet = etatObjet;
    }

    /**
     * Identifie l'inventaire r�pertoriant la PC
     */
    public Inventaire getInventaire() {
        return this.inventaire;
    }

    /**
     * Valorise l'inventaire r�pertoriant la PC
     *
     * @param fkScePieConInv
     */
    public void setInventaire(Inventaire fkScePieConInv) {
        this.inventaire = fkScePieConInv;
    }

    /**
     * Identifie le scell� contenant la PC
     */
    public Scelle getScelle() {
        return this.scelle;
    }

    /**
     * Valorise le scell� contenant la PC
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
     * Retourne le nombre de PC similaire dans le scell�
     */
    public Short getNombre() {
        return this.nombre;
    }

    /**
     * Valorise le nombre de PC similaire dans le scell�
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
     * Retourne l'ensemble des Ev�nements li�s � cette Pi�ce � Conviction.
     *
     * @return Returns the evenements.
     */
    public Set<Evenement> getEvenements() {
        return evenements;
    }

    /**
     * Valorise l'ensemble des Ev�nements li�s � cette Pi�ce � Conviction.
     *
     * @param evenements
     *            The evenements to set.
     */
    public void setEvenements(Set<Evenement> evenements) {
        this.evenements = evenements;
    }

    /**
     * Retourne le num�ro de la PC
     *
     * @return Returns the numero.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Valorise le num�ro de la PC
     *
     * @param numero
     *            The numero to set.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Retourne la cat�gorie de la PC
     *
     * @return Returns the categorie.
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * Valorise la cat�gorie de la PC
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
     * indique que la pi�ce est sortie ou non
     *
     * @return true ou false
     */
    public boolean estSortie() {
        return EtatObjetEnum.SORTI_DEFINITIVEMENT.is(this.getEtatObjet().getCode())
                || EtatObjetEnum.SORTI_PROVISOIREMENT.is(this.getEtatObjet().getCode());
    }

    /**
     * indique que la pi�ce est sortie d�finifive, supprim�e ou non
     *
     * @return true ou false
     */
    public boolean estSortieDefinitivementOuSupprime() {
        return EtatObjetEnum.SORTI_DEFINITIVEMENT.is(this.getEtatObjet().getCode())
                || EtatObjetEnum.SUPPRIME.is(this.getEtatObjet().getCode());
    }
}
