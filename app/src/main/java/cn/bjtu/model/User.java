package cn.bjtu.model;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by wsg on 2017/6/23.
 */

public class User extends BmobUser {
    private String brief;  // 一句话简介
    private String intro; // 个人介绍，详细
    private BmobFile image; //头像
    private Boolean  male;//true:男,false：女   注意：Boolean而不是boolean
    private Integer age; // 年龄 注意： Integer,而不是int

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public BmobFile getImage() {
        return image;
    }

    public void setImage(BmobFile image) {
        this.image = image;
    }

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
