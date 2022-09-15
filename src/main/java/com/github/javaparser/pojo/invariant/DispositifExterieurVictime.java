/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model;

import fr.gouv.justice.cassiopee.invariant.exterieur.service.enumeration.TypeDispositifExterieurEnum;

/**
 * Le dispositif extérieur d'une victime ne comporte que la décision pour laquelle la victime effectue une requête (cas
 * des demandes de restitution de scellés). Ce dispositif n'est jamais recopié automatiquement par le système mais
 * toujours créé manuellement par l'utilisateur.
 */
public class DispositifExterieurVictime extends DispositifExterieur implements java.io.Serializable {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = -6609894260977298686L;

    @Override
    public TypeDispositifExterieurEnum getTypeDispositifExterieurEnum() {
        return TypeDispositifExterieurEnum.VICTIME;
    }
}
