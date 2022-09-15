package fr.gouv.justice.cassiopee.invariant.affaire.model;

import fr.gouv.justice.cassiopee.invariant.affaire.service.enumeration.TypeAffaireEnum;

/**
 * Cette classe regroupe uniquement les affaires civiles autres que celles traitées par le JE. Affaires civiles du
 * Parquet : Il s'agit par exemple des affaires relatives aux changements de nom, aux adoptions, aux créations de débits
 * de boissons. Citation en D.I. : Affaire civile devant juridiction pénale (affaire faisant suite à un non-lieu au
 * pénal et une détention provisoire dans une affaire pénale non liée à l?affaire civile de citation en dommages
 * intérêts), pour laquelle on ne va pas jusqu'au jugement.
 */
public class AffaireCivileParquet extends Affaire {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = -1001090508350192124L;

    /**
     * @see fr.gouv.justice.cassiopee.invariant.affaire.model.Affaire#getTypeAffaire()
     */
    @Override
    public TypeAffaireEnum getTypeAffaire() {
        return TypeAffaireEnum.CIVILE_PARQUET;
    }

}
