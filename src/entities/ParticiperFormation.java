/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author amado
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class ParticiperFormation {
    private int id_P ;
    private int id_user ;
    private int id_ref ;

    public ParticiperFormation() {
    }

    public ParticiperFormation(int id_user, int id_ref) {
        this.id_user = id_user;
        this.id_ref = id_ref;
    }

    public ParticiperFormation(int id_P, int id_user, int id_ref) {
        this(id_user, id_ref);
        this.id_P = id_P;      
    }

    public int getId_P() {
        return id_P;
    }

    public void setId_P(int id_P) {
        this.id_P = id_P;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_ref() {
        return id_ref;
    }

    public void setId_ref(int id_ref) {
        this.id_ref = id_ref;
    }
    
    
}

