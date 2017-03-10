package com.connectordb.client;



public class Stream extends BaseObject {

    protected boolean downlink;
    protected boolean ephemeral;

    protected String datatype;
    protected String schema;

    public boolean getDownlink() {
        return downlink;
    }
    public boolean getEphemeral() {
        return ephemeral;
    }
    public String getDatatype() {
        return datatype;
    }
    public String getSchema() {
        return schema;
    }

    public void setDownlink(boolean downlink) {
        this.downlink = downlink;
        updates.put("downlink",downlink);
    }
    public void setEphemeral(boolean ephemeral) {
        this.ephemeral = ephemeral;
        updates.put("ephemeral",ephemeral);
    }
    public void setDatatype(String datatype) {
        this.datatype = datatype;
        updates.put("datatype",datatype);
    }
    public void setSchema(String schema) {
        this.schema = schema;
        updates.put("schema",schema);
    }

}
