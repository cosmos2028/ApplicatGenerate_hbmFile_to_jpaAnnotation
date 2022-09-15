package fr.gouv.justice.cassiopee.invariant.personneevenement.model;

import java.util.Date;

public class RefPemDi {

    private String typeDi;

    private Long ident;

    private Date dateDi;

    public String getTypeDi() {
        return typeDi;
    }

    public void setTypeDi(String typeDi) {
        this.typeDi = typeDi;
    }

    public Long getIdent() {
        return ident;
    }

    public void setIdent(Long ident) {
        this.ident = ident;
    }

    public Date getDateDi() {
        return dateDi;
    }

    public void setDateDi(Date dateDi) {
        this.dateDi = dateDi;
    }
}
