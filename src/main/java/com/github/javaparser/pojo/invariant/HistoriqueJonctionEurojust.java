package fr.gouv.justice.cassiopee.invariant.eurojust.model.formulaire;

import java.io.Serializable;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;

public class HistoriqueJonctionEurojust implements Serializable {

    private static final long serialVersionUID = -2950293636025876815L;

    private static final HashCodeEqualsHelper<HistoriqueJonctionEurojust> HE_HELPER = HashCodeEqualsHelper.of(HistoriqueJonctionEurojust.class, HistoriqueJonctionEurojust::getAffaireDestinationId,
            HistoriqueJonctionEurojust::getAffaireOrigineId, HistoriqueJonctionEurojust::getFormulaireId, HistoriqueJonctionEurojust::getServiceProprietaireId);


    private Long formulaireId;
    private Long affaireOrigineId;
    private Long affaireDestinationId;
    private Long serviceProprietaireId;
    public Long getFormulaireId() {
        return formulaireId;
    }
    public void setFormulaireId(Long formulaireId) {
        this.formulaireId = formulaireId;
    }
    public Long getAffaireOrigineId() {
        return affaireOrigineId;
    }
    public void setAffaireOrigineId(Long affaireOrigineId) {
        this.affaireOrigineId = affaireOrigineId;
    }
    public Long getAffaireDestinationId() {
        return affaireDestinationId;
    }
    public void setAffaireDestinationId(Long affaireDestinationId) {
        this.affaireDestinationId = affaireDestinationId;
    }
    public Long getServiceProprietaireId() {
        return serviceProprietaireId;
    }
    public void setServiceProprietaireId(Long serviceProprietaireId) {
        this.serviceProprietaireId = serviceProprietaireId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((affaireDestinationId == null) ? 0 : affaireDestinationId.hashCode());
        result = prime * result + ((affaireOrigineId == null) ? 0 : affaireOrigineId.hashCode());
        result = prime * result + ((formulaireId == null) ? 0 : formulaireId.hashCode());
        result = prime * result + ((serviceProprietaireId == null) ? 0 : serviceProprietaireId.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object other) {
        return HE_HELPER.areEqual(this, other);
    }



}
