package fr.gouv.justice.cassiopee.invariant.infraction.model;

/**
 * Détail du lien entre une victime et une infraction.
 */
public class LienVictimeInfraction extends LienPersonneInfraction {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = 4792074656350889336L;

    @Override
    protected TypeLienPersonneInfractionEnum getTypeLienPersonneInfraction() {
        return TypeLienPersonneInfractionEnum.VICTIME_INFRACTION;
    }

    /**
     * indique s'il s'agit d'un lien victime infraction
     *
     * @param lienPersonneInfraction
     *            lien personne infraction
     * @return true s'il s'agit d'un lien victime infraction
     */
    public static boolean isLienVictimeInfraction(LienPersonneInfraction lienPersonneInfraction) {
        return TypeLienPersonneInfractionEnum.VICTIME_INFRACTION.equals(lienPersonneInfraction
                .getTypeLienPersonneInfraction());
    }
}
