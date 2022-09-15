/*
 * Ce document est la propri�t� d�Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */

package fr.gouv.justice.cassiopee.invariant.edition.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

public class Document implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -5069874232161392850L;

    /** Identifiant du document qui est l'identifiant de d'�dition */
    private Long editionId;

    /** le contenu du document */
    private Blob fichierBlob;

    /** la date de modification */
    private Date dateModification;

    /** l'edition */
    private Edition edition;

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public Blob getFichierBlob() {
        return fichierBlob;
    }

    public void setFichierBlob(Blob fichierBlob) {
        this.fichierBlob = fichierBlob;
    }

    public Long getEditionId() {
        return editionId;
    }

    public void setEditionId(Long id) {
        this.editionId = id;
    }

}
