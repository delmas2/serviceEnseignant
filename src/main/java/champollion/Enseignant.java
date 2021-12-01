package champollion;

import java.util.ArrayList;

public class Enseignant extends Personne {

    // TODO : rajouter les autres méthodes présentes dans le diagramme UML
    private final ArrayList<ServicePrevu> servicesPrevList = new ArrayList<>();
    private final ArrayList<Intervention> interventionsPlanList = new ArrayList<>();
    
    public Enseignant(String nom, String email) {
        super(nom, email);
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures équivalent TD" Pour le calcul : 1 heure
     * de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut 0,75h
     * "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
        // TODO: Implémenter cette méthode
        int somme = 0;
        for (ServicePrevu s : servicesPrevList){
            somme = (int) Math.round(somme + (s.getVolumeTP()*0.75) + (s.getVolumeCM()*1.5) + s.getVolumeTD());
        }
        return somme;
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE spécifiée en "heures équivalent TD" Pour
     * le calcul : 1 heure de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure
     * de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
        int somme = 0;
        for (ServicePrevu s : servicesPrevList){
            if (s.getUe() == ue){
                somme = (int) Math.round(somme + (s.getVolumeTP()*0.75) + (s.getVolumeCM()*1.5) + s.getVolumeTD());
            }
        }
        return somme;
        // TODO: Implémenter cette méthode
    }    
    

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magitral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
         servicesPrevList.add(new ServicePrevu(volumeCM,volumeTD,volumeTP,ue,this));
        }
        // TODO: Implémenter cette méthode
  
    

    public void ajouteIntervention(Intervention inter) throws Exception {
        if(inter.getIntervenant() == this){
            interventionsPlanList.add(inter);
        }else{
            throw new Exception("Cette intervention ne correspond pas à cet enseignant");
        }
    }

    public boolean enSousService() throws Exception {
        int nbHeuresPrevues = heuresPrevues();
        double sumNbHeuresRealisees = sommeInterventionsPlanifiees();

        return (nbHeuresPrevues - sumNbHeuresRealisees) > 0;
    }
    
    
    public double sommeInterventionsPlanifiees() throws Exception {
        double sumNbHeuresRealisees = 0;

        for (Intervention inter : interventionsPlanList){
            if (!inter.isAnnulee()) sumNbHeuresRealisees = sumNbHeuresRealisees + inter.dureeEquivalentTD();
        }
        return Math.round(sumNbHeuresRealisees);
    }


    public int resteAPlanifier(UE ue, TypeIntervention type) throws Exception {
        double sommeInter = 0;
        if (enSousService()){

            for(Intervention inter : interventionsPlanList){
                if (inter.getType().equals(type) && inter.getMatiere().equals(ue)){
                    sommeInter = sommeInter + inter.dureeEquivalentTD();
                }
            }

            double sommeServ = 0;

            for (ServicePrevu servicePrevu : servicesPrevList) {
                if (servicePrevu.getUe().equals(ue)) {
                    switch (type) {
                        case TP:
                            sommeServ += servicePrevu.getVolumeTP()*0.75;
                            break;
                        case TD:
                            sommeServ += servicePrevu.getVolumeTD();
                            break;
                        case CM:
                            sommeServ += servicePrevu.getVolumeCM()*1.5;
                            break;
                    }
                }
            }

            sommeInter -= Math.round(sommeServ);
        }
        return (int) Math.abs(Math.round(sommeInter));
    }

    public ArrayList<ServicePrevu> getServicesPrevusList() {
        return servicesPrevList;
    }

    public ArrayList<Intervention> getInterventionsPlanifieesList() {
        return interventionsPlanList;
    }
}
