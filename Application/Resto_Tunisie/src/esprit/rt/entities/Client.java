package esprit.rt.entities;



import java.util.Date;

/**
 *
 * @author Pingouins de désert
 * 
 */

public class Client extends Utilisateur{
    
    private int id;
    private char estBloque;
    
    public Client() {
    }
    
    public Client(int id, char estBloque, String nom, String prenom, String email, String password, String dateNaissance, int numTel, String adresse, String region,String image, char sexe, String questionSecurite, String reponseSecurite, String description) {
        super(id,nom, prenom, email, password, dateNaissance, numTel, adresse, region,image, sexe, questionSecurite, reponseSecurite, description);
        this.id = id;
        this.estBloque = estBloque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getEstBloque() {
        return estBloque;
    }

    public void setEstBloque(char estBloque) {
        this.estBloque = estBloque;
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
        final Client other = (Client) obj;
        if (this.id == other.id)
            return false;
        return true;
    }

  
    
    public String toString() {
        return "Client{" + "id=" + id + ", estBloque=" + estBloque + '}';
    }
    
   
    
    
    
}
