package fr.gouv.justice.cassiopee.invariant.affaire.model;

import fr.gouv.justice.cassiopee.invariant.affaire.service.enumeration.TypeAffaireEnum;

/**
 * Cette classe permet de déterminer la spécificité d'une affaire commerciale du parquet: le numéro de dossier
 * commercial qui est une caracactéristique de l'événement de création de l'affaire commerciale les classes des
 * personnes de l'affaire (personnes physiques et/ou morales d'une affaire commerciale)
 */
public class AffaireCommerciale extends Affaire {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = 4114228701202661437L;

    /**
     * @see fr.gouv.justice.cassiopee.invariant.affaire.model.Affaire#getTypeAffaire()
     */
    @Override
    public TypeAffaireEnum getTypeAffaire() {
        return TypeAffaireEnum.COMMERCIALE;
    }

}
