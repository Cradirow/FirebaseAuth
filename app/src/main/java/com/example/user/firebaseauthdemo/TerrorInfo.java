package com.example.user.firebaseauthdemo;

/**
 * Created by HANSUNG on 2017-12-13.
 */

public class TerrorInfo
{
    public String attacktype1_txt;
    public String city;
    public String country_txt;
    public Double eventid;
    public String gname;
    public int iday;
    public int imonth;
    public int iyear;
    public float latitude;

    public TerrorInfo()
    {}

    public String getAttacktype1_txt()
    {
        return attacktype1_txt;
    }

    public void setAttacktype1_txt(String attacktype1_txt)
    {
        this.attacktype1_txt = attacktype1_txt;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCountry_txt()
    {
        return country_txt;
    }

    public void setCountry_txt(String country_txt)
    {
        this.country_txt = country_txt;
    }

    public Double getEventid()
    {
        return eventid;
    }

    public void setEventid(Double eventid)
    {
        this.eventid = eventid;
    }

    public String getGname()
    {
        return gname;
    }

    public void setGname(String gname)
    {
        this.gname = gname;
    }

    public int getIday()
    {
        return iday;
    }

    public void setIday(int iday)
    {
        this.iday = iday;
    }

    public int getImonth()
    {
        return imonth;
    }

    public void setImonth(int imonth)
    {
        this.imonth = imonth;
    }

    public int getIyear()
    {
        return iyear;
    }

    public void setIyear(int iyear)
    {
        this.iyear = iyear;
    }

    public float getLatitude()
    {
        return latitude;
    }

    public void setLatitude(float latitude)
    {
        this.latitude = latitude;
    }

    public float getLongitude()
    {
        return longitude;
    }

    public void setLongitude(float longitude)
    {
        this.longitude = longitude;
    }

    public int getNkill()
    {
        return nkill;
    }

    public void setNkill(int nkill)
    {
        this.nkill = nkill;
    }

    public int getNwound()
    {
        return nwound;
    }

    public void setNwound(int nwound)
    {
        this.nwound = nwound;
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public String getTargtype1_txt()
    {
        return targtype1_txt;
    }

    public void setTargtype1_txt(String targtype1_txt)
    {
        this.targtype1_txt = targtype1_txt;
    }

    public float longitude;
    public int nkill;
    public int nwound;
    public String summary;
    public String targtype1_txt;

    public TerrorInfo(String attacktype1_txt, String city, String country_txt, Double eventid, String gname, int iday, int imonth, int iyear, float latitude, float longitude, int nkill, int nwound, String summary, String targtype1_txt)
    {
        this.attacktype1_txt = attacktype1_txt;
        this.city = city;
        this.country_txt = country_txt;
        this.eventid = eventid;
        this.gname = gname;
        this.iday = iday;
        this.imonth = imonth;
        this.iyear = iyear;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nkill = nkill;
        this.nwound = nwound;
        this.summary = summary;
        this.targtype1_txt = targtype1_txt;
    }
}
