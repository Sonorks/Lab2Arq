/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.controlador;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author CASA555
 */
public class Captcha {

    /**
     * Creates a new instance of Captcha
     */
    public Captcha() {
    }
    
    public void check(ActionEvent e){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Your Captcha Is Correct !",null));
    }
    
}
