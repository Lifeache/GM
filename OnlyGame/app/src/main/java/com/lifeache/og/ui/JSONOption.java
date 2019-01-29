package com.lifeache.og.ui;

public class JSONOption
{
    String name;
    String value;
    Class type;

    public JSONOption(){
        
    }
    
    public JSONOption(String name, String value)
    {
        this.name = name;
        this.value = value;
    }

    public void setType(Class type)
    {
        this.type = type;
    }

    public Class getType()
    {
        return type;
    }


    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }}
