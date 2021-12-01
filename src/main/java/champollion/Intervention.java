/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package champollion;

import java.util.Date;

/**
 *
 * @author ldelma05
 */
public class Intervention {
    private Date debut;
    public int heureDebut;
    private int duree;
    private boolean annulee;
    private TypeIntervention type;
    private final Enseignant intervenant;
    private final Salle lieu;
    private final UE matiere;
    

    public Intervention(Date debut, int duree, boolean annulee, int heureDebut, TypeIntervention type, Salle lieu, UE matiere, Enseignant intervenant) {
        this.debut = debut;
        this.duree = duree;
        this.heureDebut = heureDebut;
        this.annulee = false;
        this.type = type;
        this.intervenant = intervenant;
        this.lieu = lieu;
        this.matiere = matiere;
       
    }

        public double dureeEquivalentTD() throws Exception {
        switch (type){
            case TP :
                return getDuree() * 0.75 ;
            case TD :
                return this.getDuree();
            case CM :
                return this.getDuree() * 1.5;
            default:
                throw new Exception("Type Incorrect");
        }
    }

    public Date getDebut() {
        return debut;
    }

    public int getDuree() {
        return duree;
    }

    public int getHeureDebut() {
        return heureDebut;
    }

    public boolean isAnnulee() {
        return annulee;
    }

    public TypeIntervention getType() {
        return type;
    }

    public Enseignant getIntervenant() {
        return intervenant;
    }

    public Salle getLieu() {
        return lieu;
    }

    public UE getMatiere() {
        return matiere;
    }

    

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setAnnulee(boolean annulee) {
        this.annulee = annulee;
    }

    public void setType(TypeIntervention type) {
        this.type = type;
    }

    

    
    
}

