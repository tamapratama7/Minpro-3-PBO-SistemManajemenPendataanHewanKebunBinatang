/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public final class HewanDarat extends Hewan implements Pelari{
    private double kecepatanLariKmJam;
    
    public HewanDarat(int id, String nama, String jenis, int umur, String habitat, PerawatanHewan perawatan, double kecepatanLariKmJam) {
        super(id, nama, jenis, umur, habitat, perawatan);
        setKecepatanLariKmJam(kecepatanLariKmJam);
    }
    
    public double getKecepatanLariKmJam() {
        return kecepatanLariKmJam;
    }
     
    public void setKecepatanLariKmJam(double kecepatanLariKmJam) {
        if (kecepatanLariKmJam < 0) {
            throw new IllegalArgumentException("Kecepatan lari tidak boleh negatif.");
        }
        this.kecepatanLariKmJam = kecepatanLariKmJam;
    }
    
    @Override
    public void tampilkanInfoLengkap() {
        super.tampilkanInfoLengkap();
        System.out.println("Info Tambahan : Hewan darat");
    }

    
    @Override
    public String getLabel() {
        return "kecepatan lari (km/jam)";
    }
 
    @Override
    public void setNilai(double nilai) {
        setKecepatanLariKmJam(nilai);
    }
    
    @Override
    public void tampilkanKemampuan(){
        System.out.println(berlari());
    }
    
    @Override
    public String berlari(){
        return nama + " berlari dengan kecepatan " + kecepatanLariKmJam + " km/jm.";
    }
}
