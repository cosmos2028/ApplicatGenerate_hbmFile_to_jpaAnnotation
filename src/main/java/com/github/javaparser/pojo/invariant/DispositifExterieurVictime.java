/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model;

import fr.gouv.justice.cassiopee.invariant.exterieur.service.enumeration.TypeDispositifExterieurEnum;

/**
 * Le dispositif ext�rieur d'une victime ne comporte que la d�cision pour laquelle la victime effectue une requ�te (cas
 * des demandes de restitution de scell�s). Ce dispositif n'est jamais recopi� automatiquement par le syst�me mais
 * toujours cr�� manuellement par l'utilisateur.
 */
public class DispositifExterieurVictime extends DispositifExterieur implements java.io.Serializable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = -6609894260977298686L;

    @Override
    public TypeDispositifExterieurEnum getTypeDispositifExterieurEnum() {
        return TypeDispositifExterieurEnum.VICTIME;
    }
}
