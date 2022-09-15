package fr.gouv.justice.cassiopee.invariant.affaire.model;

import fr.gouv.justice.cassiopee.invariant.affaire.service.enumeration.TypeAffaireEnum;

/**
 * Cette classe traite des affaires en charge du juge des libert�s et de la d�tention concernant la r�tention
 * administrative. Pour ces affaires, il n'y a pas d'infraction.
 */
public class AffaireAdministrativeJLD extends Affaire {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 8981665843579338514L;

    /**
     * @see fr.gouv.justice.cassiopee.invariant.affaire.model.Affaire#getTypeAffaire()
     */
    @Override
    public TypeAffaireEnum getTypeAffaire() {
        return TypeAffaireEnum.ADMINISTRATIVE_JLD;
    }

}
