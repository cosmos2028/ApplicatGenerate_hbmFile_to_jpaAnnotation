package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Peine prononc�e pendant le jugement ou l'ordonnance p�nale ou l'ordonnance d'homologation de peines (= �v�nement
 * portant dispositif) ou requise dans la ROP ou la CRPC. Fait partie des peines encourues, s'il s'agit de peines
 * principales ou compl�mentaires ou de peines alternatives sp�ciales. Dans le cas contraire, il s'agit des alternatives
 * g�n�rales. Une peine prononc�e (ou requise) ou un groupe de peines prononc�es (ou requise) concerne une infraction ou
 * un groupe d'infractions. Les groupes de peines li�s aux groupes d'infractions concernent les crimes et d�lits non
 * sp�cifiques. R�gle diff�rente en mati�re de contravention (ou de crime ou d�lit sp�cifique), o� chaque contravention
 * (ou crime ou d�lit sp�cifique) est li�e � son amende (ou � sa peine).
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
