package esprit.rt.entities;

/**
 *
 * @author Trabelsi Achraf
 */
public class Restaurant {

    private int id;
    private String nom;
    private String region;
    private String adresse;
    private String type;
    private int numTel;
    private int note;
    private String cordonnee;
    private String description;
    private String url;
    private int id_restaurateur;
    

    public Restaurant() {
    }

    public Restaurant(int id, String nom, String region, String adresse, String type, int numTel, int note, String cordonnee, String description, String url, int id_restaurateur) {
        this.id = id;
        this.nom = nom;
        this.region = region;
        this.adresse = adresse;
        this.type = type;
        this.numTel = numTel;
        this.note = note;
        this.cordonnee = cordonnee;
        this.description = description;
        this.url = url;
        this.id_restaurateur = id_restaurateur;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void setCordonnee(String cordonnee) {
        this.cordonnee = cordonnee;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId_restaurateur(int id_restaurateur) {
        this.id_restaurateur = id_restaurateur;
    }

    

    public int getId() {
        return id;
    }

    public String getCordonnee() {
        return cordonnee;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public int getId_restaurateur() {
        return id_restaurateur;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public String getAdresse() {
        return adresse;
    }

    public String getType() {
        return type;
    }

    public int getNumTel() {
        return numTel;
    }

    public int getNote() {
        return note;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumTel(String numTel) {
        this.numTel = Integer.parseInt(numTel);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setNote(String note) {
        this.note = Integer.parseInt(note);
    }

    public String toString() {
        return "Restaurant{" + "id=" + id + ", nom=" + nom + ", region=" + region + ", adresse=" + adresse + ", type=" + type + ", numTel=" + numTel + ", note=" + note + '}';
    }

    Restaurant getRestaurant() {
        return null;
    }

}
