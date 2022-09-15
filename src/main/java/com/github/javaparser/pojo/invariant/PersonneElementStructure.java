package fr.gouv.justice.cassiopee.invariant.personne.model;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.commons.metier.model.Adresse;
import fr.gouv.justice.cassiopee.invariant.personne.service.enumeration.TypePersonneEnum;
import fr.gouv.justice.cassiopee.referentiel.codification.model.CategoriePenale;
import fr.gouv.justice.cassiopee.referentiel.structure.model.ElementStructure;
import fr.gouv.justice.cassiopee.referentiel.structure.service.enumeration.TypeElementStructureEnum;

/**
 * Personne référencée parmi les éléments de structure : <br/>
 * - parmi les administrations (pour le cas des Administrations poursuivantes -classe modélisée dans le composant
 * Personne affaire) ;<br/>
 * - parmi les CPAM (pour le cas des Intervenants -classe modélisée dans le composant Personne affaire).<br/>
 * - parmi les DDASS (Tuteur d'un mineur- Tuteur est une classe modélisée dans le composant Personne Affaire)
 */
public class PersonneElementStructure extends Personne {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = -5522583577019796782L;

    /** élément de structure représenté par cette personne */
    private ElementStructure personneElementStructure;

    private String email ;

    /**
     * Retourne l'élément de structure représenté par cette personne
     *
     * @return l'élément de structure représenté par cette personne
     */
    public ElementStructure getPersonneElementStructure() {
        return personneElementStructure;
    }

    /**
     * Positionne l'élément de structure de la personne
     *
     * @param personneElementStructure
     *            l'élément de structure de la personne
     */
    public void setPersonneElementStructure(ElementStructure personneElementStructure) {
        this.personneElementStructure = personneElementStructure;
    }

    @Override
    public String calculeLibelle() {
        return personneElementStructure.getLibelle();
    }

    @Override
    public TypePersonneEnum getTypePersonne() {
        return TypePersonneEnum.ELEMENT_STRUCTURE;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean isGenreFeminin() {
        return false;
    }

    /**
     * Détermine si une personne Element de Structure est un CPAM
     *
     * @return retourne vrai si CPAM sinon faux
     */
    @RegleDeGestion(RG.PERM088)
    public boolean isCPAM() {
        return this.personneElementStructure.getTypeElementStructure().getCode().equals(
                TypeElementStructureEnum.CPAM.getCode());

    }

    @Override
    public CategoriePenale getCategoriePenale() {
        // les elements de structure n'ont pas de catégorie pénale
        return null;
    }

    @Override
    public Adresse getAdresseResidence() {
        return this.personneElementStructure.getAdresse();
    }

    @Override
    public String getLibellePersonnePourNoeudSynthese() {
        return personneElementStructure.getLibelleCourt();
    }

}
