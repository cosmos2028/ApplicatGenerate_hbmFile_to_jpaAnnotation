/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.scelle.model;

/**
 * Transfert scellé. Sert à l'historisation des transferts. Se subdivise en trois classes filles : transfert affaire -
 * transfert dépôt - transfert gardien à gardien.
 */
public class Transfert implements java.io.Serializable {

    private static final long serialVersionUID = -4299687613359470387L;

    private Long id;

    private Depot depot;

    /**
     * Discriminant: type de transfert
     */
    protected String discTypeTransfert;

    private Integer numeroOrdre;

    /**
     *
     */
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     */
    public Depot getDepot() {
        return this.depot;
    }

    public void setDepot(Depot fkSceTraDep) {
        this.depot = fkSceTraDep;
    }

    public Integer getNumeroOrdre() {
        return numeroOrdre;
    }

    public void setNumeroOrdre(Integer numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }
}
