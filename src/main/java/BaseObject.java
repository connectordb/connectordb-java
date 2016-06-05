package com.connectordb.client;

import java.util.Map;
import java.util.HashMap;

import com.google.gson.annotations.SerializedName;

public class BaseObject {
    protected String name;
    protected String nickname;
    protected String description;
    protected String icon;

    // the map of updates holds values that have changed, which allows
    // sending updates of only the fields which were modified, as is required
    // by the ConnectorDB API
    protected Map<String,Object> updates = new HashMap<String,Object>();

    // Getters and setters are needed to correctly update the updates map

    public String getName() {
        return name;
    }
    public String getNickname() {
        return nickname;
    }
    public String getDescription() {
        return description;
    }
    public String getIcon() {
        return icon;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        updates.put("nickname",nickname);
    }
    public void setDescription(String description) {
        this.description = description;
        updates.put("description",description);
    }
    public void setIcon(String icon) {
        this.icon = icon;
        updates.put("icon",icon);
    }

}
