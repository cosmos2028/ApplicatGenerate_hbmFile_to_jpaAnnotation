package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.invariant.personne.model.AvocatPersonneAffaire;
import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;

/**
 * La classe LienEmetteur permet de retrouver l'émetteur d'un événement. Un événement a un et un seul émetteur qui peut
 * être :
 * <ul>
 * <li>un avocat représentant une personne de l'affaire</li>
 * <li>une personne de l'affaire</li>
 * <li>un élément de structure</li>
 * <li>un agent, (c'est-à-dire un UtilisateurService) qui appartient à un service qui a un type d?élément de structure
 * d?appartenance, (C'est la plupart du temps un magistrat mais pas toujours : ex le greffier en chef du service des
 * scellés est émetteur des événements concernant les scellés)</li>
 * <li>une personne qualifiée (c'est-à-dire un Acteur Externe)</li>
 * <li>un Service</li> Note sur l'implémentation: ici nous sommes légèrements limités par les contraintes du modèle et
 * les limtes d'Hibernate. On ne peut pas sous-classer LienEmetteur qui est un "component" de Evenement, c'est-à-dire
 * qu'il est sauvegardé dans la table Evenement.
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
     * Indique si l'émetteur est une Personne. Cette méthode est nommée avec un article pour éviter toute confusion avec
     * un getter.
     *
     * @return true si le LienEmetteur pointe vers une Personne, false sinon.
     */
    public boolean estUnePersonne() {
        return (this.getPersonne() != null);
    }

    /**
     * ! Indique si le LienEmetteur pointe sur un AvocatPersonne. Méthode nommée avec un article pour éviter toute
     * confusion avec getter.
     *
     * @return true si le lien point sur un avocat
     */
    public boolean estUnAvocat() {
        return (this.getAvocat() != null);
    }

    /**
     * retourne l'emetteur méthode de convenance générique
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
        // mise à blanc de tous les champs
        // cette action est nécessaire pour gerer le changement de type de
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
