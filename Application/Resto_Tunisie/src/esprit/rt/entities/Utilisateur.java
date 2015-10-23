package esprit.rt.entities;

public class Utilisateur {
    
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String date_naiss;
    private int num_tel;
    private String adresse;
    private String region;
    private String image;
    private char sexe;
    private String question_securite;
    private String reponse_securite;
    private String description;
 

    public Utilisateur() {
    }

    public Utilisateur(int id, String nom, String prenom, String email, String password, String date_naiss, int num_tel, String adresse, String region, String image, char sexe, String question_securite, String reponse_securite, String description) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.date_naiss = date_naiss;
        this.num_tel = num_tel;
        this.adresse = adresse;
        this.region = region;
        this.image = image;
        this.sexe = sexe;
        this.question_securite = question_securite;
        this.reponse_securite = reponse_securite;
        this.description = description;
    }

 





    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDate_naiss() {
        return date_naiss;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getRegion() {
        return region;
    }

    public String getImage() {
        return image;
    }

    public char getSexe() {
        return sexe;
    }

    public String getQuestion_securite() {
        return question_securite;
    }

    public String getReponse_securite() {
        return reponse_securite;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDate_naiss(String date_naiss) {
        this.date_naiss = date_naiss;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setSexe(char sexe) {
        this.sexe = sexe;
    }

    public void setQuestion_securite(String question_securite) {
        this.question_securite = question_securite;
    }

    public void setReponse_securite(String reponse_securite) {
        this.reponse_securite = reponse_securite;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return  nom ;  }

    

    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id;
        return hash;
    }


    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utilisateur other = (Utilisateur) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
