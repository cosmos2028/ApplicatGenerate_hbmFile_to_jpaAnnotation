package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.invariant.personne.model.AvocatPersonneAffaire;
import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;

/**
 * La classe LienEmetteur permet de retrouver l'�metteur d'un �v�nement. Un �v�nement a un et un seul �metteur qui peut
 * �tre :
 * <ul>
 * <li>un avocat repr�sentant une personne de l'affaire</li>
 * <li>une personne de l'affaire</li>
 * <li>un �l�ment de structure</li>
 * <li>un agent, (c'est-�-dire un UtilisateurService) qui appartient � un service qui a un type d?�l�ment de structure
 * d?appartenance, (C'est la plupart du temps un magistrat mais pas toujours : ex le greffier en chef du service des
 * scell�s est �metteur des �v�nements concernant les scell�s)</li>
 * <li>une personne qualifi�e (c'est-�-dire un Acteur Externe)</li>
 * <li>un Service</li> Note sur l'impl�mentation: ici nous sommes l�g�rements limit�s par les contraintes du mod�le et
 * les limtes d'Hibernate. On ne peut pas sous-classer LienEmetteur qui est un "component" de Evenement, c'est-�-dire
 * qu'il est sauvegard� dans la table Evenement.
 */
public class LienEmetteur extends AbstractLienCorrespondant<Emetteur> {

    private static final long serialVersionUID = -8412533449519942904L;

    private Personne personne;

    private AvocatPersonneAffaire avocat;

    /**
     * @return retourne avocat.
     */
    public AvocatPersonneAffaire getAvocat() {
        return avocat;
    }

    /**
     * @param avocat
     *            affecte avocat
     */
    public void setAvocat(AvocatPersonneAffaire avocat) {
        this.avocat = avocat;
    }

    /**
     * @return retourne personne.
     */
    public Personne getPersonne() {
        return personne;
    }

    /**
     * @param personne
     *            affecte personne
     */
    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    /**
     * Indique si l'�metteur est une Personne. Cette m�thode est nomm�e avec un article pour �viter toute confusion avec
     * un getter.
     *
     * @return true si le LienEmetteur pointe vers une Personne, false sinon.
     */
    public boolean estUnePersonne() {
        return (this.getPersonne() != null);
    }

    /**
     * ! Indique si le LienEmetteur pointe sur un AvocatPersonne. M�thode nomm�e avec un article pour �viter toute
     * confusion avec getter.
     *
     * @return true si le lien point sur un avocat
     */
    public boolean estUnAvocat() {
        return (this.getAvocat() != null);
    }

    /**
     * retourne l'emetteur m�thode de convenance g�n�rique
     *
     * @return emetteur
     */
    @Override
    public Emetteur getCorrespondant() {
        if (estUnePersonne())
            return this.getPersonne();
        if (estUnAvocat())
            return this.getAvocat();

        return super.getCorrespondant();
    }

    @Override
    public void setCorrespondant(Emetteur correspondant) {
        // mise � blanc de tous les champs
        // cette action est n�cessaire pour gerer le changement de type de
        // correspondant
        this.personne = null;
        this.avocat = null;
        this.miseABlanc();

        if (correspondant instanceof Personne) {
            this.personne = (Personne) correspondant;
            return;
        }
        if (correspondant instanceof AvocatPersonneAffaire) {
            this.avocat = (AvocatPersonneAffaire) correspondant;
            return;

        }

        super.setCorrespondant(correspondant);
    }

    @Override
    public boolean estUnTypeElementStructure() {
        if (estUnePersonne())
            return false;
        if (estUnAvocat())
            return false;

        return super.estUnTypeElementStructure();
    }

}
