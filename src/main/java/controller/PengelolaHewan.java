/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author ASUS
 */

import java.util.ArrayList;
import model.Hewan;
import model.PerawatanHewan;
import model.HewanAir;
import model.HewanDarat;

public class PengelolaHewan {

    private ArrayList<Hewan> daftarHewan;
    private static int nextId = 1;
    private static int nextIdPerawatan = 101;
    
    //Constructor
    public PengelolaHewan() {
        daftarHewan = new ArrayList<>();
        dataDummy();
    }
    
    public int idHewanAuto() {
        return nextId++;
    }
    
    public int idPerawatanAuto() {
        return nextIdPerawatan++;
    }
    
    private void dataDummy(){
        daftarHewan.add(new HewanDarat(nextId++, "Leo", "Singa", 5, "Savana", new PerawatanHewan(nextIdPerawatan++, "Vaksinasi", "01-01-2026"), 80.0));
        daftarHewan.add(new HewanDarat(nextId++, "Kiko", "Gajah", 8, "Hutan Tropis", new PerawatanHewan(nextIdPerawatan++, "Pemeriksaan Rutin", "15-02-2026"), 40.0));
        daftarHewan.add(new HewanAir(nextId++, "Nemo", "Lumba-lumba", 4, "Kolam Laut Buatan", new PerawatanHewan(nextIdPerawatan++, "Pemeriksaan Sirip", "20-03-2026"), 200.0));
    }
    
    private Hewan cariHewan(int id){
        for (Hewan h : daftarHewan){
            if (h.getId() == id){
                return h;
            }
        }
        return null;
    }
    
    public Hewan cariHewanBerdasarkanId(int id) {
        return cariHewan(id);
    }
    
    public boolean cekId(int id){
        return Validator.cekId(daftarHewan, id);
    }
    
    public boolean cekIdPerawatan(int idPerawatan) {
        return Validator.cekIdPerawatan(daftarHewan, idPerawatan);
    }
    
    public boolean cekIdPerawatan(int idPerawatan, int idHewanDikecualikan) {
        return Validator.cekIdPerawatan(daftarHewan, idPerawatan, idHewanDikecualikan);
    }
    //C
    public boolean tambahHewan(Hewan hewan) {
        if (cekId(hewan.getId())){
            return false;
        }
        daftarHewan.add(hewan);
        return true;
    }
    
    //R
    public ArrayList<Hewan> getDaftarHewan() {
        return daftarHewan;
    }
    
    //U
    public boolean ubahHewan(int id, String nama, String jenis, int umur, String habitat, model.PerawatanHewan perawatan) {
        Hewan hewan = cariHewan(id);
        if (hewan == null){
            return false;
        }
        hewan.setNama(nama);
        hewan.setJenis(jenis);
        hewan.setUmur(umur);
        hewan.setHabitat(habitat);
        hewan.setPerawatan(perawatan);
        return true;
    }
    
    //D
    public boolean hapusHewan(int id) {
    Hewan hewan = cariHewan(id);
        if (hewan == null){
            return false;
        }
        daftarHewan.remove(hewan);
        return true;
    }
}

