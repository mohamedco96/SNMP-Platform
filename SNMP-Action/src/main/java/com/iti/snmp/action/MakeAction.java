/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.snmp.action;

import static com.iti.snmp.action.email.sendMail;
import com.snmp.database.DataBaseDAO;
import com.snmp.entities.Action;
import com.snmp.entities.Alarms;
import java.util.ArrayList;

/**
 *
 * @author asalah
 */
public class MakeAction {

    public static void main(String[] args) throws Exception {
        email email = new email();
        nxSms sms = new nxSms();
        DataBaseDAO dataBaseDAO = new DataBaseDAO();

        while (true) {
            ArrayList<Alarms> listOfAlarms = dataBaseDAO.getAllAlarms();
            
            for (int i = 0; i < listOfAlarms.size(); i++) {
                Alarms alarms = listOfAlarms.get(i);
                if (listOfAlarms.get(i).isStatus() == true) {
                    ArrayList<Action> listOfAction = dataBaseDAO.getAllAction(listOfAlarms.get(i).getNode_id(), listOfAlarms.get(i).getAlarm_type());
                    for (int j = 0; j < listOfAction.size(); j++) {
                        if (listOfAction.get(j).getAction().equals("email")) {
                            System.out.println("### Send Email To " + listOfAction.get(j).getDes() + " And Alarm Type is " + listOfAction.get(j).getAlarm_type());
//                        email.sendMail(listOfAction.get(j).getDes(),"There is a problem in Node ID "+ listOfAction.get(j).getNode_id()+",Alarm Type is " +listOfAction.get(j).getAlarm_type());
                            boolean result = dataBaseDAO.update(alarms);
                        } else if (listOfAction.get(j).getAction().equals("sms")) {
                            System.out.println("### Send SMS To " + listOfAction.get(j).getDes() + " And Alarm Type is " + listOfAction.get(j).getAlarm_type());
//                            sms.sendSMS(listOfAction.get(j).getDes(), "Alarm Type " + listOfAction.get(j).getAlarm_type());
                            boolean result = dataBaseDAO.update(alarms);
                        } else if (listOfAction.get(j).getAction().equals("script")) {
                            System.out.println("### Run Script for " + listOfAction.get(j).getNode_id() + " And Alarm Type is " + listOfAction.get(j).getAlarm_type());
                            boolean result = dataBaseDAO.update(alarms);
                        }
                    }
                }
            }
        }
    }
}
