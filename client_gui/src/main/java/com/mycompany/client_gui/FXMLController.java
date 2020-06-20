package com.mycompany.client_gui;

import com.google.gson.Gson;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import com.iti.snmp_client.*;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
public class FXMLController implements Initializable {
    ObservableList<String> type=FXCollections.observableArrayList("HD_Is_FULL","VOICE_CALL_QUALITY_IS_BAD","NETWORK_CONNECTIVITY_ISSUE");
    private Label label;
   
    @FXML
    private TextField Node_Name;
    @FXML
    private TextField Node_Ip;
    @FXML
    private TextField Alarm_Message;
    @FXML
    private TextField oid;
    @FXML
    private ChoiceBox trap;
    @FXML
    private RadioButton deactive;
    @FXML
    private RadioButton active;
    
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        trap.setItems(type);
        
    }    
    @FXML
    private void send(ActionEvent event) {
                SnmpTrapInfo snmpTrapInfo = new SnmpTrapInfo();

        if(active.isSelected()){
        snmpTrapInfo.setState(false);
        }
        else if(deactive.isSelected()){
        snmpTrapInfo.setState(true);
        
        }
        
        String OID=oid.getText();
        String Node=Node_Name.getText();
        String ip=Node_Ip.getText();
        String alarm_message=Alarm_Message.getText();
        String trap_type=(String) trap.getSelectionModel().getSelectedItem();
        Gson gson = new Gson();
        snmpTrapInfo.setTrapType(trap_type);
        
        IssueInfo issueInfo = new IssueInfo();
        issueInfo.setDescription(alarm_message);
        issueInfo.setNode_IP(ip);
        issueInfo.setNode_Name(Node);
        issueInfo.setTimestamp(new Date());
        snmpTrapInfo.setIssueInfo(issueInfo);
        TrapSender.sendSnmpV2Trap(OID, gson.toJson(snmpTrapInfo), ip, Node);

        
        
    }

   
}
