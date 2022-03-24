/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author amado
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Formation {
    private int id_ref ;
    private String titre ;
    private Date date ;
    private String description ;
    private String lien ;
    private int prix ;

    public Formation() {
    }

    public Formation(String titre, Date date, String description, String lien, int prix) {
        this.titre = titre;
        this.date = date;
        this.description = description;
        this.lien = lien;
        this.prix = prix;
    }

    public Formation(int id_ref, String titre, Date date, String description, String lien, int prix) {
        this(titre, date, description, lien, prix);
        this.id_ref = id_ref;    
    }

    public int getId_ref() {
        return id_ref;
    }

    public void setId_ref(int id_ref) {
        this.id_ref = id_ref;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLien() {
        return lien;
    }

    public void setLieu(String lien) {
        this.lien = lien;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
    
    
}

