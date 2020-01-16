/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iantreza.latihanmvcjdbc.model;

import iantreza.latihanmvcjdbc.database.KingBarbershopDatabase;
import iantreza.latihanmvcjdbc.entity.Pelanggan;
import iantreza.latihanmvcjdbc.error.PelangganException;
import iantreza.latihanmvcjdbc.event.PelangganListener;
import iantreza.latihanmvcjdbc.service.PelangganDao;
import java.sql.SQLException;

/**
 *
 * @author IantReza
 */
public class PelangganModel {
    private int id;
    private String nama;
    private String alamat;
    private String telepon;
    private String email;
    
    private PelangganListener listener;

    public PelangganListener getListener() {
        return listener;
    }

    public void setListener(PelangganListener listener) {
        this.listener = listener;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        fireOnChange();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String Nama) {
        this.nama = Nama;
        fireOnChange();
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String Alamat) {
        this.alamat = Alamat;
        fireOnChange();
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String Telepon) {
        this.telepon = Telepon;
        fireOnChange();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = Email;
        fireOnChange();
    }
    
    protected void fireOnChange(){
        if(listener!=null){
            listener.onChange(this);
        }
    }
    
    protected void fireOnInsert(Pelanggan pelanggan){
        if(listener!=null){
            listener.onInsert(pelanggan);
        }
    }
    
    protected void fireOnUpdate(Pelanggan pelanggan){
        if(listener!=null){
            listener.onUpdate(pelanggan);
        }
    }
    
    protected void fireOnDelete(){
        if(listener!=null){
            listener.onDelete();
        }
    }
    
   
    public void insertPelanggan() throws SQLException, PelangganException{
       
       PelangganDao dao = KingBarbershopDatabase.getPelangganDao();
       Pelanggan pelanggan = new Pelanggan();
       pelanggan.setNama(nama);
       pelanggan.setAlamat(alamat);
       pelanggan.setTelepon(telepon);
       pelanggan.setEmail(email);
       
       dao.insertPelanggan(pelanggan);
       fireOnInsert(pelanggan);
    }
    
    public void updatePelanggan() throws SQLException, PelangganException{
        
       PelangganDao dao = KingBarbershopDatabase.getPelangganDao();
       Pelanggan pelanggan = new Pelanggan();
       pelanggan.setNama(nama);
       pelanggan.setAlamat(alamat);
       pelanggan.setTelepon(telepon);
       pelanggan.setEmail(email);
       pelanggan.setId(id);
       
       dao.updatePelanggan(pelanggan);
       fireOnUpdate(pelanggan);
    }
    
    public void deletePelanggan() throws SQLException, PelangganException{
        
       PelangganDao dao = KingBarbershopDatabase.getPelangganDao();
       dao.deletePelanggan(id);
       fireOnDelete();
    }
    
    public void resetPelanggan(){
        setId(0);
        setNama("");
        setAlamat("");
        setTelepon("");
        setEmail("");
    }
    
}
