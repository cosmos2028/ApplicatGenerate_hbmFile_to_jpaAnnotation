package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Peine prononcée pendant le jugement ou l'ordonnance pénale ou l'ordonnance d'homologation de peines (= événement
 * portant dispositif) ou requise dans la ROP ou la CRPC. Fait partie des peines encourues, s'il s'agit de peines
 * principales ou complémentaires ou de peines alternatives spéciales. Dans le cas contraire, il s'agit des alternatives
 * générales. Une peine prononcée (ou requise) ou un groupe de peines prononcées (ou requise) concerne une infraction ou
 * un groupe d'infractions. Les groupes de peines liés aux groupes d'infractions concernent les crimes et délits non
 * spécifiques. Règle différente en matière de contravention (ou de crime ou délit spécifique), où chaque contravention
 * (ou crime ou délit spécifique) est liée à son amende (ou à sa peine).
 */
public class PeinePrononceeOuRequise extends PeineOuMesure {

    /**
     *
     */
    private static final long serialVersionUID = 6471686437209662192L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.PEINE_PRONONCEE_OU_REQUISE;
    }

}
