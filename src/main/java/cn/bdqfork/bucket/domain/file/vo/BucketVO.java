package cn.bdqfork.bucket.domain.file.vo;

import javax.validation.constraints.NotNull;

public class BucketVO {
     /**
     * 名字
     */
    @NotNull
    private String name;

    /**
     * 所属用户id
     */
    @NotNull
    private Long userId;

    /**
     * 权限，0为私有，1为公共读，2为公共读写
     */
    @NotNull
    private Byte privilege;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Byte getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Byte privilege) {
        this.privilege = privilege;
    }

}
