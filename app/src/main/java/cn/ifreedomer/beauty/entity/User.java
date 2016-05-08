package cn.ifreedomer.beauty.entity;

import java.io.Serializable;

/**
 * @author:eavawu
 * @date: 5/1/16.
 * @todo:用户类
 */
public class User implements Serializable{
    private Long id;
    private String name;
    private String avatar;
    private String phone;
    private Integer sex;
    private String signature;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                ", signature='" + signature + '\'' +
                '}';
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
