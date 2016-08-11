package com.handtours.service.api.domain.core.req;

/**
 * @author Jiangli
 *
 *         CreatedTime  2016/7/18 0018 10:51
 */
public class DeleteReq<ID> extends Req {
    private Boolean isLogicalDeletion =true;
    private ID id;
    private Long lastUpdateTimeTs;

    public Long getLastUpdateTimeTs() {
        return lastUpdateTimeTs;
    }

    public void setLastUpdateTimeTs(Long lastUpdateTimeTs) {
        this.lastUpdateTimeTs = lastUpdateTimeTs;
    }

    public DeleteReq() {
    }

    public DeleteReq(ID id) {
        this.id = id;
    }

    public Boolean getLogicalDeletion() {
        return isLogicalDeletion;
    }

    public void setLogicalDeletion(Boolean logicalDeletion) {
        isLogicalDeletion = logicalDeletion;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
