package fr.gouv.justice.cassiopee.invariant.personneevenement.model;

import java.util.Date;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;

public class DispositifPenal implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2601752601355553791L;

    private static final HashCodeEqualsHelper<DispositifPenal> HE_HELPER = HashCodeEqualsHelper.of(DispositifPenal.class, DispositifPenal::getDisPenalDateCondamnationDe, DispositifPenal::getDisPenalDatePrescription);

    /**
     * Date après laquelle le jugement ne peut plus être exécutée. Elle est saisie par le BEX. Elle peut changer
     * (manuellement) en fonction des voies de recours exercées.
     */
    private Date disPenalDatePrescription;

    /**
     * Date à laquelle le jugement devient définitif.
     */
    private Date disPenalDateCondamnationDe;

    public Date getDisPenalDateCondamnationDe() {
        return disPenalDateCondamnationDe;
    }

    public void setDisPenalDateCondamnationDe(Date disPenalDateCondamnationDe) {
        this.disPenalDateCondamnationDe = disPenalDateCondamnationDe;
    }

    public Date getDisPenalDatePrescription() {
        return disPenalDatePrescription;
    }

    public void setDisPenalDatePrescription(Date disPenalDatePrescription) {
        this.disPenalDatePrescription = disPenalDatePrescription;
    }

    @Override
    public boolean equals(Object obj){
        return HE_HELPER.areEqual(this, obj);
    }

    @Override
    public int hashCode() {
        return HE_HELPER.getHashCode(this);
    }

}
