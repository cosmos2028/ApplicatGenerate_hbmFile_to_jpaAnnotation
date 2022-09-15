package fr.gouv.justice.cassiopee.invariant.affaire.model;

import java.util.Set;

import fr.gouv.justice.cassiopee.commons.codification.model.DateCodification;

/**
 * SousTypeAffaire<br>
 * Le sous type de dossier �tablit le domaine sur lequel porte l'affaire trait�e par le JE. Pour une affaire civile JE,
 * le sous-type peut �tre :<br>
 * <lu> <li>AE pour assistance �ducative,</li> <li>TPS pour tutelle aux prestations sociales,</li> <li>PJM pour
 * protection jeune majeur.</li> <li>AGBF pour protection jeune majeur.</li> </lu>
 */
public class SousTypeAffaire extends DateCodification {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 8697285259291311854L;

    /**
     * La collection d'affaires JE auxquelles le sous type est li�.
     */
    private Set<Affaire> affaires;

    /**
     * Retourne la collection d'affaires JE auxquelles le sous type est li�.
     */
    public Set<Affaire> getAffaires() {
        return this.affaires;
    }

    /**
     * Affecte la collection d'affaires JE auxquelles le sous type est li�.
     *
     * @param affaires
     */
    public void setAffaires(Set<Affaire> affaires) {
        this.affaires = affaires;
    }

}
