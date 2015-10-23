package tn.resto.map.entities;

/**
 *
 * @author Aditsan Kadmus
 *
 */

public class Restaurant {
       private int id;
       private String nom;
       private double note;

    public Restaurant() {
    }

    public Restaurant(int id, String nom, double note) {
        this.id = id;
        this.nom = nom;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Restaurant other = (Restaurant) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 5;
        return hash;
    }

    public String toString() {
        return id+"\n"+nom+"\n"+note;
    }

       
}
