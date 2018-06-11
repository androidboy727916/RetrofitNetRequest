package com.cn.perfect.bean;

/**
 * Description：
 * Created on 2018/6/11 0011
 * Author : 郭
 */
public class UserBean implements java.io.Serializable {
    private static final long serialVersionUID = -7223833307806067742L;
    /**
     * 定义本类的私有变量
     */
    private Integer id;
    private String mobile;
    private String nickName;
    private String realName;
    private String headImg;
    private String email;
    private Integer sex;
    private String cardNo;
    private String birthday;
    private Integer userSource;
    private String channelId;
    private Integer userRole;

    public UserBean(Integer id, String realName, String headImg, Integer userRole) {
        this.id = id;
        this.realName = realName;
        this.headImg = headImg;
        this.userRole = userRole;
    }

    /**
     * 本类的实例化方法
     */
    public UserBean() {
    }

    /**
     * id的赋值方法
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * mobile的赋值方法
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * sex的赋值方法
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * birthday的赋值方法
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * id的取值方法
     */
    public Integer getId() {
        return id;
    }

    /**
     * mobile的取值方法
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * sex的取值方法
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * birthday的取值方法
     */
    public String getBirthday() {
        return birthday;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getUserSource() {
        return userSource;
    }

    public void setUserSource(Integer userSource) {
        this.userSource = userSource;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", mobile='" + mobile + '\'' +
                ", nickName='" + nickName + '\'' +
                ", realName='" + realName + '\'' +
                ", headImg='" + headImg + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", cardNo='" + cardNo + '\'' +
                ", birthday='" + birthday + '\'' +
                ", userSource=" + userSource +
                ", channelId='" + channelId + '\'' +
                ", userRole=" + userRole +
                '}';
    }

}
