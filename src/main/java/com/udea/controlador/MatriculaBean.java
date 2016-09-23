package com.udea.controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CASA555
 */

import com.udea.ejb.MatriculaFacadeLocal;
import com.udea.modelo.Matricula;
import com.udea.modelo.MatriculaPK;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
public class MatriculaBean implements Serializable {
    @EJB 
    private MatriculaFacadeLocal matriculaFacade;
    private UIComponent botonValidarMatricula;
    public UIComponent getBotonValidarMatricula(){
        return botonValidarMatricula;
    }
    public void setBotonValidarMatricula(UIComponent boton){
        botonValidarMatricula = boton;
    }
    private boolean disable = true;
    private String documento;
    private String tipoDocumento;
    private int nivel;
    private String semestre;
    private int creditos;
    private String programa;
    /**
     * Creates a new instance of MatriculaBean
     */
    public MatriculaBean() {
    }

    public MatriculaFacadeLocal getMatriculaFacade() {
        return matriculaFacade;
    }

    public void setMatriculaFacade(MatriculaFacadeLocal matriculaFacade) {
        this.matriculaFacade = matriculaFacade;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }
    
    public String guardarMatricula(){
        Matricula mat = new Matricula();
        MatriculaPK matPK = new MatriculaPK();
        mat.setCreditos(creditos);
        matPK.setEstudiante(documento);
        matPK.setSemestre(semestre);
        mat.setMatriculaPK(matPK);
        this.matriculaFacade.create(mat);
        return "Matricula Realizada";
    }
    
    public String validarCreditos(){

        if (creditos < 8){
            FacesMessage message = new FacesMessage("Creditos Insuficientes");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(botonValidarMatricula.getClientId(context),message);
            return "Creditos insuficientes";
        }
        else if(creditos > 24){
            FacesMessage message = new FacesMessage("Limite de creditos superados");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(botonValidarMatricula.getClientId(context),message);
            return "Limite de creditos superados";
        }
        else {
            disable = false;
            System.out.println("Validados los creditos");
            return "Cantidad de creditos correctos";
        }
    }
    //Para el cambio de idioma
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    public Locale getLocale(){
        return locale;
    }
    public String getLanguage(){
        return locale.getLanguage();
    }
    public void changeLanguage(String language){
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
    }
}
