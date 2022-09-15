/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

import java.util.HashSet;
import java.util.Set;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.invariant.scelle.service.enumeration.EtatObjetEnum;

/**
 * Objet métier Scellé. Un scellé est un ensemble de pièce à conviction identifié et inviolable. Un scellé désigne tout
 * objet utile à la manifestation de la vérité dans le cadre d'une procédure judiciaire dans le but de la conserver à la
 * disposition de la justice en empêchant qu'il soit altéré, modifié ou échangé. La meilleure façon de protéger un objet
 * est de l'envelopper dans un sachet plastique fermé par un cachet de cire ; dans ce cas-là le scellé est ouvert : on
 * peut voir son contenu. Un scellé peut contenir une ou plusieurs pièces à conviction. La classe "scellé" permet de
 * référencer l'ensemble des scellés d'un dépôt. Un scellé est typé et peut même être sous typé (liste de choix ou
 * saisie libre)
 */
public class Scelle extends Objet {

    /**
     * Identifiant de sérialisation
     */
    private static final long serialVersionUID = 1123010120303632080L;

    /**
     * Le type du scellé(arme Blance, arme à feu...)
     */
    private TypeScelle typeScelle;

    /**
     * Les pièces à convictions contenu par le scellé
     */
    private java.util.Set<PieceConviction> pieceConvictions = new HashSet<PieceConviction>();

    /**
     * Le scellé est clos ou non
     */
    private Boolean indicateurFerme;

    /**
     * Le rang du scellé
     */
    private Integer rang;

    /**
     * Le numéro du scellé
     */
    private String numero;

    @SuppressWarnings("unused")
    private String numeroScelle;

    /**
     * Etat de liquidation d'un scelle
     */
    private String etatLiquidation;

    /**
     * Retourne le type de scellé
     *
     * @return le type type de scellé
     */
    public TypeScelle getTypeScelle() {
        return this.typeScelle;
    }

    /**
     * Valorise le sous type de scellé
     *
     * @param typeScelle
     */
    public void setTypeScelle(TypeScelle typeScelle) {
        this.typeScelle = typeScelle;
    }

    /**
     * Retourne l'ensemble des pièces à conviction contenues dans ce scellé.
     *
     * @return un Set
     */
    public Set<PieceConviction> getPieceConvictions() {
        return this.pieceConvictions;
    }

    /**
     * Valorise l'ensemble des pièces à conviction contenues dans ce scellé.
     *
     * @param pieceConvictions
     */
    public void setPieceConvictions(java.util.Set<PieceConviction> pieceConvictions) {
        this.pieceConvictions = pieceConvictions;
    }

    /**
     * Indique si le scellé est fermé ou non.
     */
    public Boolean getIndicateurFerme() {
        return this.indicateurFerme;
    }

    /**
     * Positionne le scellé à fermé ou non.
     */
    public void setIndicateurFerme(Boolean scelleIndicateurFerme) {
        this.indicateurFerme = scelleIndicateurFerme;
    }

    /**
     * Indique le rang du scellé
     */
    public Integer getRang() {
        return this.rang;
    }

    /**
     * Positionne le rang du scellé
     *
     * @param scelleRang
     */
    public void setRang(Integer scelleRang) {
        this.rang = scelleRang;
    }

    /**
     * Indique le numéro du scellé
     */
    public String getNumero() {
        return this.numero;
    }

    /**
     * Positionne le numéro du scellé.
     *
     * @param scelleNumero
     *            Le numéro du scellé
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
     * indique que le scellé ou une de ses pièces est sortie ou non
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
     * liberation de l'emplacement si le scelle est relié à des pièce à coniviction supprimé ou sortie définitivement
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
