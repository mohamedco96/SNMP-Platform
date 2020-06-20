/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.snmp_client;

/**
 *
 * @author msabagh
 */
public class SnmpTrapInfo {
//    private SnmpTrapTypes TrapType;
        private String TrapType;

    private IssueInfo issueInfo;
    private boolean state;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    /**
     * @return the TrapType
     */
//    public SnmpTrapTypes getTrapType() {
            public String getTrapType() {

        return TrapType;
    }

    /**
     * @param TrapType the TrapType to set
     */
//    public void setTrapType(SnmpTrapTypes TrapType) {
        public void setTrapType(String TrapType) {

        this.TrapType = TrapType;
    }

    /**
     * @return the issueInfo
     */
    public IssueInfo getIssueInfo() {
        return issueInfo;
    }

    /**
     * @param issueInfo the issueInfo to set
     */
    public void setIssueInfo(IssueInfo issueInfo) {
        this.issueInfo = issueInfo;
    }

 
    
    
}
