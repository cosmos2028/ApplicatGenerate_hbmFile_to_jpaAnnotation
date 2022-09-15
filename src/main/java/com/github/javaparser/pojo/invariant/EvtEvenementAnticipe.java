package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.io.Serializable;
import java.util.Set;

import fr.gouv.justice.cassiopee.service.accueil.model.TacheAFaireAnticipe;

/**
 * Classe mappant la table temporaire servant au traitement des evenements anticipés.
 *
 * @author gfiuser
 */
@SuppressWarnings("nls")
public class EvtEvenementAnticipe implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2405703747884142534L;
    private long id;
    private long evenementId;
    private long codeTraitement;
    private String dateTraitement;
    private long personneEvenementId;
    private String rgAppliquee;
    private String codeTgi;
    private String numParquet;
    private Long codeErreur;

    private Set<TacheAFaireAnticipe> tacheAFaireSet;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<TacheAFaireAnticipe> getTacheAFaireSet() {
        return tacheAFaireSet;
    }

    public void setTacheAFaireSet(Set<TacheAFaireAnticipe> tacheAFaireSet) {
        this.tacheAFaireSet = tacheAFaireSet;
    }

    public String getCodeTgi() {
        return codeTgi;
    }

    public void setCodeTgi(String codeTgi) {
        this.codeTgi = codeTgi;
    }

    public String getRgAppliquee() {
        return rgAppliquee;
    }

    public void setRgAppliquee(String rgAppliquee) {
        this.rgAppliquee = rgAppliquee;
    }

    public long getPersonneEvenementId() {
        return personneEvenementId;
    }

    public void setPersonneEvenementId(long personneEvenementId) {
        this.personneEvenementId = personneEvenementId;
    }

    public long getEvenementId() {
        return evenementId;
    }

    public void setEvenementId(long evenementId) {
        this.evenementId = evenementId;
    }

    public long getCodeTraitement() {
        return codeTraitement;
    }

    public void setCodeTraitement(long codeTraitement) {
        this.codeTraitement = codeTraitement;
    }

    public String getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(String dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public void setNumParquet(String numParquet) {
        this.numParquet = numParquet;
    }

    public String getNumParquet() {
        return numParquet;
    }

    public void setCodeErreur(Long codeErreur) {
        this.codeErreur = codeErreur;
    }

    public Long getCodeErreur() {
        return codeErreur;
    }

}
