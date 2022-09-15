package fr.gouv.justice.cassiopee.invariant.edition.model;

import fr.gouv.justice.cassiopee.processus.parquet.model.IdentificationParquet;

public class EditionDemandeDiffereeExportCsv extends EditionDemandeDifferee {

    /** Identifiant parquet de l'affaire concerné par l'export */
    private IdentificationParquet identificationParquet;

    /** Ensemble de criteres utlises pour certains demandes (echeancier, recherche,...) */
    private String criteres;

    /** Ensemble des resultats de la recherche utlises pour certains demandes (recherche comptes bancaires, véhicule...) */
    private String resultatsRecherche;

    /**
     * @return the identificationParquet
     */
    public IdentificationParquet getIdentificationParquet() {
        return identificationParquet;
    }

    /**
     * @param identificationParquet
     *            the identificationParquet to set
     */
    public void setIdentificationParquet(IdentificationParquet identificationParquet) {
        this.identificationParquet = identificationParquet;
    }

    /**
     * @return the criteres
     */
    public String getCriteres() {
        return criteres;
    }

    /**
     * @param criteres
     *            the criteres to set
     */
    public void setCriteres(String criteres) {
        this.criteres = criteres;
    }

    /**
     * @return the resultatsRecherche
     */
    public String getResultatsRecherche() {
        return resultatsRecherche;
    }

    /**
     * @param resultatsRecherche
     *            the resultatsRecherche to set
     */
    public void setResultatsRecherche(String resultatsRecherche) {
        this.resultatsRecherche = resultatsRecherche;
    }

    @Override
    public EditionDemandeDiffereeEnum getEditionDemandeDiffereeEnum() {
        return EditionDemandeDiffereeEnum.EDITION_DEMANDE_DIFFEREE_EXPORT_CSV;
    }

}
