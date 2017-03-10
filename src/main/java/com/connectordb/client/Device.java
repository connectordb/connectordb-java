package com.connectordb.client;


import com.google.gson.annotations.SerializedName;

public class Device extends BaseObject {

    @SerializedName("public")
    protected boolean ispublic;
    protected String role;

    protected boolean enabled;
    protected String apikey;
    protected boolean visible;

    public String getRole() {
        return role;
    }
    public boolean getPublic() {
        return ispublic;
    }
    public String getApikey() {
        return apikey;
    }
    public boolean getVisible() {
        return visible;
    }

    public void setRole(String role) {
        this.role = role;
        updates.put("role",role);
    }
    public void setPublic(boolean ispublic) {
        this.ispublic = ispublic;
        updates.put("public",ispublic);
    }
    public void resetApikey() {
        this.apikey = "INVALID";
        updates.put("apikey","");
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
        updates.put("visible",visible);
    }

}
