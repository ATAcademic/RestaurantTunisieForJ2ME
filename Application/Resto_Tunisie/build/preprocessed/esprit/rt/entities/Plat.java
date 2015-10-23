package esprit.rt.entities;



/**
 *
 * @author Pingouins de désert
 * 
 */

public class Plat {
    
    private int id;
    private String nom;
    private String description;
    private String type;
    private float prix;
    private Menu menu;

    public Plat() {
    }

    public Plat(String nom, String description, String type, float prix) {
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.prix = prix;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

  
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Plat other = (Plat) obj;
        if (this.id == other.id)
            return false;
        return true;
    }


    public String toString() {
        return nom+" "+prix+" DT";
    }
    
    public String secondaryToString()
    {
        return "Plat{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", type=" + type + ", prix=" + prix + ", menu=" + menu + '}';
    }
    
}
