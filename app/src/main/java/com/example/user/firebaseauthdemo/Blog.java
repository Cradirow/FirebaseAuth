package com.example.user.firebaseauthdemo;

/**
 * Created by HYB on 2017. 10. 13..
 */

public class Blog
{
    private String title;
    private String desc;
    private String image;
    private String nickname;

    public Blog()
    {

    }

    public Blog(String title, String desc, String image)
    {
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    public Blog(String title, String desc, String image, String nickname)
    {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.nickname = nickname;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

}
