/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmp.daos;


import java.util.ArrayList;


public interface DAO<S> {
     
    S get(int id);
     
    ArrayList<S> getAll();
     
    boolean save(S t);
     
    boolean update(S t);
     
    boolean delete(int id);
}






