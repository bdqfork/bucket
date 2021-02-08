package cn.bdqfork.bucket.domain.file.vo;

public class ShowDirectoryCondition {
    
    private Long bucketId;

    private Long parentId;

    public Long getBucketId() {
        return bucketId;
    }

    public void setBucketId(Long bucketId) {
        this.bucketId = bucketId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
