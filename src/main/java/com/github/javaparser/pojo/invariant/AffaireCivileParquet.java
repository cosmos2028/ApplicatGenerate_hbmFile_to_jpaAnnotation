package fr.gouv.justice.cassiopee.invariant.affaire.model;

import fr.gouv.justice.cassiopee.invariant.affaire.service.enumeration.TypeAffaireEnum;

/**
 * Cette classe regroupe uniquement les affaires civiles autres que celles trait�es par le JE. Affaires civiles du
 * Parquet : Il s'agit par exemple des affaires relatives aux changements de nom, aux adoptions, aux cr�ations de d�bits
 * de boissons. Citation en D.I. : Affaire civile devant juridiction p�nale (affaire faisant suite � un non-lieu au
 * p�nal et une d�tention provisoire dans une affaire p�nale non li�e � l?affaire civile de citation en dommages
 * int�r�ts), pour laquelle on ne va pas jusqu'au jugement.
 */
public class AffaireCivileParquet extends Affaire {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = -1001090508350192124L;

    /**
     * @see fr.gouv.justice.cassiopee.invariant.affaire.model.Affaire#getTypeAffaire()
     */
    @Override
    public TypeAffaireEnum getTypeAffaire() {
        return TypeAffaireEnum.CIVILE_PARQUET;
    }

}
