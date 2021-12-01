/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package champollion;

import java.util.ArrayList;

/**
 *
 * @author ldelma05
 */
class Salle {
    
    public String intitule;
    public int capacite;
    public ArrayList<Intervention> occupations = new ArrayList<>();

    public Salle(String intitule, int capacite) {
        this.intitule = intitule;
        this.capacite = capacite;
    }

    public String getIntitule() {
        return intitule;
    }

    public int getCapacite() {
        return capacite;
    }

    public ArrayList<Intervention> getOccupations() {
        return occupations;
    }

}
