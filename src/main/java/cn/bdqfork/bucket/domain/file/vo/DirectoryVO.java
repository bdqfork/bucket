package cn.bdqfork.bucket.domain.file.vo;

public class DirectoryVO {
    
     /**
     * 目录名称
     */
    private String name;

    /**
     * 所属桶的id
     */
    private Long bucketId;

    /**
     * 父目录id，-1为根目录
     */
    private Long parentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
