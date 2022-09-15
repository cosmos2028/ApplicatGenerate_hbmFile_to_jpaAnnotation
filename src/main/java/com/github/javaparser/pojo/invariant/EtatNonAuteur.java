package fr.gouv.justice.cassiopee.invariant.personne.model;

import fr.gouv.justice.cassiopee.commons.codification.model.Codification;

/**
 * Etats possibles des personnes autre que Auteur. Les �tats de l'auteur ne sont pas pr�sents, car g�r�s par la table
 * PER_ETAT_AUTEUR.
 */
public class EtatNonAuteur extends Codification {

    private static final long serialVersionUID = -6518581355596058557L;

    /*
     * Pr�cision si l'�tat est une partie civile.
     */
    private boolean etatPartieCivile;

    private boolean etatRL;

    private boolean etatAdministration;

    public boolean isEtatPartieCivile() {
        return etatPartieCivile;
    }

    public void setEtatPartieCivile(boolean etatPartieCivile) {
        this.etatPartieCivile = etatPartieCivile;
    }

    public boolean isetatRL() {
        return etatRL;
    }

    public void setetatRL(boolean etatRL) {
        this.etatRL = etatRL;
    }

    public boolean isetatAdministration() {
        return etatAdministration;
    }

    public void setetatAdministration(boolean etatAdministration) {
        this.etatAdministration = etatAdministration;
    }

}
