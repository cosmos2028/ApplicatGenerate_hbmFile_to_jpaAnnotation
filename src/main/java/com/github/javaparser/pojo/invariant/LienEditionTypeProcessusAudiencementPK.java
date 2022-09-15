/**
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.edition.model;

import java.io.Serializable;

/**
 * clé composé
 */
@SuppressWarnings("nls")
public class LienEditionTypeProcessusAudiencementPK implements Serializable {

    private static final long serialVersionUID = 846189465136661L;

    private EditionType editionType;

    private ProcessusAudiencement processusAudiencement;

    /**
     * @return retourne editionType.
     */
    public EditionType getEditionType() {
        return editionType;
    }

    /**
     * @param editionType
     *            affecte editionType
     */
    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    /**
     * @return retourne processusAudiencement.
     */
    public ProcessusAudiencement getProcessusAudiencement() {
        return processusAudiencement;
    }

    /**
     * @param processusAudiencement
     *            affecte processusAudiencement
     */
    public void setProcessusAudiencement(ProcessusAudiencement processusAudiencement) {
        this.processusAudiencement = processusAudiencement;
    }

    @Override
    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof LienEditionTypeProcessusAudiencementPK))
            return false;
        LienEditionTypeProcessusAudiencementPK castOther = (LienEditionTypeProcessusAudiencementPK) other;

        return (this.getEditionType() == castOther.getEditionType())
                || (this.getEditionType() != null && castOther.getEditionType() != null && this.getEditionType()
                        .equals(castOther.getEditionType()))
                && (this.getProcessusAudiencement() == castOther.getProcessusAudiencement())
                || (this.getProcessusAudiencement() != null && castOther.getProcessusAudiencement() != null && this
                        .getProcessusAudiencement().equals(castOther.getProcessusAudiencement()));
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 37 * result + this.getEditionType().hashCode();
        result = 37 * result + this.getProcessusAudiencement().hashCode();
        return result;
    }
}
