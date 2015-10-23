/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.rt.entities;

/**
 *
 * @author pc casino
 */
public class BonPlan {

    private int id;
    private String description;
    private String dateDebut;
    private String dateFin;

    public BonPlan() {
    }

    public BonPlan(String description, String dateDebut, String dateFin) {
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut =  dateDebut;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String toString() {
        return "BonPlan{" + "id=" + id + ", description=" + description + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + '}';
    }

    BonPlan getBonPlan() {
        return null;
    }

}
