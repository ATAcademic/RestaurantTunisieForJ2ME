package esprit.rt.entities;



/**
 *
 * @author Pingouins de désert
 * 
 */

public class Menu {
    
    private int id;
    private String nom;
    private String description;
    private Restaurant restaurant;

    public Menu() {
    }

    public Menu(String nom, String description) {
        this.nom = nom;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

   
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Menu other = (Menu) obj;
        if (this.id != other.id)
            return false;
        return true;
    }

    
    public String toString() {
        return  nom ;
    }
    
}
