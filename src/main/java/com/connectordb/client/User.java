package com.connectordb.client;


import com.google.gson.annotations.SerializedName;

public class User extends BaseObject {
    private String email;

    @SerializedName("public")
    private boolean ispublic;
    private String role;

    public String getEmail() {
        return email;
    }
    public String getRole() {
        return role;
    }
    public boolean getPublic() {
        return ispublic;
    }

    public void setRole(String role) {
        this.role = role;
        updates.put("role",role);
    }
    public void setPublic(boolean ispublic) {
        this.ispublic = ispublic;
        updates.put("public",ispublic);
    }
    public void setEmail(String email) {
        this.email = email;
        updates.put("email",email);
    }
}
