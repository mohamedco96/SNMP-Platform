/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.snmp_client;

import java.util.Date;

/**
 *
 * @author msabagh
 */
public class IssueInfo {

    private String Node_Name;
    private String Node_IP;
    private String Description;
    private Date timestamp;

    /**
     * @return the Node_Name
     */
    public String getNode_Name() {
        return Node_Name;
    }

    /**
     * @param Node_Name the Node_Name to set
     */
    public void setNode_Name(String Node_Name) {
        this.Node_Name = Node_Name;
    }

    /**
     * @return the Node_IP
     */
    public String getNode_IP() {
        return Node_IP;
    }

    /**
     * @param Node_IP the Node_IP to set
     */
    public void setNode_IP(String Node_IP) {
        this.Node_IP = Node_IP;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
