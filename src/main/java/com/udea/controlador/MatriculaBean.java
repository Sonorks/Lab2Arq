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

import com.udea.ejb.EstudianteFacadeLocal;
import com.udea.ejb.MatriculaFacadeLocal;
import com.udea.modelo.Estudiante;
import com.udea.modelo.EstudiantePK;
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
    @EJB
    private EstudianteFacadeLocal estudianteFacade;
    private UIComponent botonValidarMatricula;
    private UIComponent botonValidarEstudiante;
    public UIComponent getBotonValidarMatricula(){
        return botonValidarMatricula;
    }
    public UIComponent getBotonValidarEstudiante(){
        return botonValidarEstudiante;
    }
    public void setBotonValidarMatricula(UIComponent boton){
        botonValidarMatricula = boton;
    }
    public void setBotonValidarEstudiante(UIComponent boton){
        botonValidarEstudiante = boton;
    }
    private boolean disableMatricula = true;
    private boolean disableEstudiante = true;

    
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
    public EstudianteFacadeLocal getEstudianteFacade() {
        return estudianteFacade;
    }

    public void setEstudianteFacade(EstudianteFacadeLocal estudianteFacade) {
        this.estudianteFacade = estudianteFacade;
    }
    public boolean isDisableMatricula() {
        return disableMatricula;
    }

    public void setDisableMatricula(boolean disableMatricula) {
        this.disableMatricula = disableMatricula;
    }
    public boolean isDisableEstudiante() {
        return disableEstudiante;
    }

    public void setDisableEstudiante(boolean disableEstudiante) {
        this.disableEstudiante = disableEstudiante;
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
    public String guardarEstudiante(){
        Estudiante es = new Estudiante();
        EstudiantePK esPK = new EstudiantePK();
        esPK.setDocumento(documento);
        esPK.setTipoDocumento(tipoDocumento);
        es.setEstudiantePK(esPK);
        es.setNivel(nivel);
        es.setPrograma(programa);
        this.estudianteFacade.create(es);
        return "Estudiante guardado";
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
            if(semestre.length() == 5 ){
                disableMatricula = false;
                System.out.println("Validados los creditos");
                return "Cantidad de creditos correctos";
            }
            else {
                FacesMessage message = new FacesMessage("El semestre debe ser de 5 digitos (EJ: 20162)");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(botonValidarMatricula.getClientId(context),message);
                return "El semestre debe ser de 5 digitos (EJ: 20162)"; 
            }
        }
    }
    public String validarEstudiante(){
            if(documento.length()==10 || documento.length()==7 || documento.length()==11){
                if(tipoDocumento.equals("cc") || tipoDocumento.equals("ti") || tipoDocumento.equals("ce")){
                    disableEstudiante = false;
                    return "Datos validados";
                }
                else{
                    FacesMessage message = new FacesMessage("El tipo de documento corresponde a: cc = cedula, ti = tarjeta de identidad, ce= cedula extranjeria");
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(botonValidarEstudiante.getClientId(context),message);
                    return "El tipo de documento corresponde a: cc = cedula, ti = tarjeta de identidad, ce= cedula extranjeria";
                }
            }
            else{
                 FacesMessage message = new FacesMessage("Por favor revise el numero de su documento. CC = 10 o 7 digitos, TI = 11 digitos, CE = 7 digitos");
                 FacesContext context = FacesContext.getCurrentInstance();
                 context.addMessage(botonValidarEstudiante.getClientId(context),message);
                 return "Por favor revise el numero de su documento. CC = 10 o 7 digitos, TI = 11 digitos, CE = 7 digitos";
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
