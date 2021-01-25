package cn.bdqfork.bucket.domain.file.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 */
@TableName("bucket")
public class Bucket extends Model<Bucket> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名字
     */
    @TableField("name")
    private String name;

    /**
     * 所属用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 权限，0为私有，1为公共读，2为公共读写
     */
    @TableField("privilege")
    private Byte privilege;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;

    /**
     * 最近修改时间
     */
    @TableField("last_modified_time")
    private Date lastModifiedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}