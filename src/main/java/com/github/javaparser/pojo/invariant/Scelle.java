/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.util.HashSet;
import java.util.Set;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.invariant.scelle.service.enumeration.EtatObjetEnum;

/**
 * Objet m�tier Scell�. Un scell� est un ensemble de pi�ce � conviction identifi� et inviolable. Un scell� d�signe tout
 * objet utile � la manifestation de la v�rit� dans le cadre d'une proc�dure judiciaire dans le but de la conserver � la
 * disposition de la justice en emp�chant qu'il soit alt�r�, modifi� ou �chang�. La meilleure fa�on de prot�ger un objet
 * est de l'envelopper dans un sachet plastique ferm� par un cachet de cire ; dans ce cas-l� le scell� est ouvert : on
 * peut voir son contenu. Un scell� peut contenir une ou plusieurs pi�ces � conviction. La classe "scell�" permet de
 * r�f�rencer l'ensemble des scell�s d'un d�p�t. Un scell� est typ� et peut m�me �tre sous typ� (liste de choix ou
 * saisie libre)
 */
public class Scelle extends Objet {

    /**
     * Identifiant de s�rialisation
     */
    private static final long serialVersionUID = 1123010120303632080L;

    /**
     * Le type du scell�(arme Blance, arme � feu...)
     */
    private TypeScelle typeScelle;

    /**
     * Les pi�ces � convictions contenu par le scell�
     */
    private java.util.Set<PieceConviction> pieceConvictions = new HashSet<PieceConviction>();

    /**
     * Le scell� est clos ou non
     */
    private Boolean indicateurFerme;

    /**
     * Le rang du scell�
     */
    private Integer rang;

    /**
     * Le num�ro du scell�
     */
    private String numero;

    @SuppressWarnings("unused")
    private String numeroScelle;

    /**
     * Etat de liquidation d'un scelle
     */
    private String etatLiquidation;

    /**
     * Retourne le type de scell�
     *
     * @return le type type de scell�
     */
    public TypeScelle getTypeScelle() {
        return this.typeScelle;
    }

    /**
     * Valorise le sous type de scell�
     *
     * @param typeScelle
     */
    public void setTypeScelle(TypeScelle typeScelle) {
        this.typeScelle = typeScelle;
    }

    /**
     * Retourne l'ensemble des pi�ces � conviction contenues dans ce scell�.
     *
     * @return un Set
     */
    public Set<PieceConviction> getPieceConvictions() {
        return this.pieceConvictions;
    }

    /**
     * Valorise l'ensemble des pi�ces � conviction contenues dans ce scell�.
     *
     * @param pieceConvictions
     */
    public void setPieceConvictions(java.util.Set<PieceConviction> pieceConvictions) {
        this.pieceConvictions = pieceConvictions;
    }

    /**
     * Indique si le scell� est ferm� ou non.
     */
    public Boolean getIndicateurFerme() {
        return this.indicateurFerme;
    }

    /**
     * Positionne le scell� � ferm� ou non.
     */
    public void setIndicateurFerme(Boolean scelleIndicateurFerme) {
        this.indicateurFerme = scelleIndicateurFerme;
    }

    /**
     * Indique le rang du scell�
     */
    public Integer getRang() {
        return this.rang;
    }

    /**
     * Positionne le rang du scell�
     *
     * @param scelleRang
     */
    public void setRang(Integer scelleRang) {
        this.rang = scelleRang;
    }

    /**
     * Indique le num�ro du scell�
     */
    public String getNumero() {
        return this.numero;
    }

    /**
     * Positionne le num�ro du scell�.
     *
     * @param scelleNumero
     *            Le num�ro du scell�
     */
    public void setNumero(String scelleNumero) {
        this.numero = scelleNumero;
    }

    public void setEtatLiquidation(String etatLiquidation) {
        this.etatLiquidation = etatLiquidation;
    }

    public String getEtatLiquidation() {
        return etatLiquidation;
    }

    /**
     * indique que le scell� ou une de ses pi�ces est sortie ou non
     *
     * @return true ou false
     */
    @RegleDeGestion(RG.EVTM204)
    public boolean estSortie() {
        if (EtatObjetEnum.SORTI_DEFINITIVEMENT.is(this.getEtatObjet().getCode())
                || EtatObjetEnum.SORTI_PROVISOIREMENT.is(this.getEtatObjet().getCode())) {
            return true;
        }

        for (PieceConviction pieceConviction : getPieceConvictions()) {
            if (pieceConviction.estSortie()) {
                return true;
            }
        }
        return false;
    }

    /**
     * liberation de l'emplacement si le scelle est reli� � des pi�ce � coniviction supprim� ou sortie d�finitivement
     */
    @RegleDeGestion(RG.EVTM206)
    public void liberableEmplacement() {
        for (PieceConviction pieceConviction : getPieceConvictions()) {
            if (!pieceConviction.estSortieDefinitivementOuSupprime()) {
                return;
            }
        }
        setEmplacement(null);
    }
}
