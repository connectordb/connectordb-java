package com.connectordb.client;


import com.google.gson.annotations.SerializedName;

public class User {
    public String name;
    public String email;
    public String description;
    public String icon;

    @SerializedName("public")
    public boolean ispublic;
    public String role;
}
