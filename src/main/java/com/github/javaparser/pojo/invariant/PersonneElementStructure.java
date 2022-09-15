package fr.gouv.justice.cassiopee.invariant.personne.model;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.commons.metier.model.Adresse;
import fr.gouv.justice.cassiopee.invariant.personne.service.enumeration.TypePersonneEnum;
import fr.gouv.justice.cassiopee.referentiel.codification.model.CategoriePenale;
import fr.gouv.justice.cassiopee.referentiel.structure.model.ElementStructure;
import fr.gouv.justice.cassiopee.referentiel.structure.service.enumeration.TypeElementStructureEnum;

/**
 * Personne r�f�renc�e parmi les �l�ments de structure : <br/>
 * - parmi les administrations (pour le cas des Administrations poursuivantes -classe mod�lis�e dans le composant
 * Personne affaire) ;<br/>
 * - parmi les CPAM (pour le cas des Intervenants -classe mod�lis�e dans le composant Personne affaire).<br/>
 * - parmi les DDASS (Tuteur d'un mineur- Tuteur est une classe mod�lis�e dans le composant Personne Affaire)
 */
public class PersonneElementStructure extends Personne {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = -5522583577019796782L;

    /** �l�ment de structure repr�sent� par cette personne */
    private ElementStructure personneElementStructure;

    private String email ;

    /**
     * Retourne l'�l�ment de structure repr�sent� par cette personne
     *
     * @return l'�l�ment de structure repr�sent� par cette personne
     */
    public ElementStructure getPersonneElementStructure() {
        return personneElementStructure;
    }

    /**
     * Positionne l'�l�ment de structure de la personne
     *
     * @param personneElementStructure
     *            l'�l�ment de structure de la personne
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
     * D�termine si une personne Element de Structure est un CPAM
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
        // les elements de structure n'ont pas de cat�gorie p�nale
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
