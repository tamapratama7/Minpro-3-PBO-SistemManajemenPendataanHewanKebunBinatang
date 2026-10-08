/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import model.Hewan;
import model.PerawatanHewan;
/**
 *
 * @author ASUS
 */
public class Validator {
    private Validator() {
    }
    
    public static boolean cekAngka(String teks){
        if (teks == null){
            return false;
        }
        try{
            Integer.parseInt(teks.trim());
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    
    public static boolean cekAngkaNegatif (String teks){
        if(!cekAngka(teks)){
            return false;
        }
        return Integer.parseInt(teks.trim()) >= 0;
    }
    
    private static final DateTimeFormatter FORMAT_TANGGAL = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
    public static void cekTanggal(String tanggal){
        try{
            LocalDate tanggalPerawatan = LocalDate.parse(tanggal.trim(), FORMAT_TANGGAL);
            if (tanggalPerawatan.isAfter(LocalDate.now())){
                throw new IllegalArgumentException("Tanggal perawatan tidak boleh lebih dari hari ini");
            }
        } catch (DateTimeParseException e){
            throw new IllegalArgumentException("Tanggal perawatan tidak valid: " + tanggal);
        }
    }
    
    public static boolean cekId(ArrayList<Hewan> daftarHewan, int id){
        for (Hewan h : daftarHewan){
            if (h.getId() == id){
                return true;
            }
        }
        return false;
    }
    
    public static boolean cekDesimal(String teks){
        if (teks == null){
            return false;
        }
        try{
            Double.parseDouble(teks.trim());
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
    
    public static boolean cekDesimalNegatif (String teks){
        if(!cekDesimal(teks)){
            return false;
        }
        return Double.parseDouble(teks.trim()) >= 0;
    }
    
    public static boolean cekIdPerawatan(ArrayList<Hewan> daftarHewan, int idPerawatan) {
        return cekIdPerawatan(daftarHewan, idPerawatan, -1);
    }
    
    
    public static boolean cekIdPerawatan(ArrayList<Hewan> daftarHewan, int idPerawatan, int idHewanDikecualikan) {
        for (Hewan hewan : daftarHewan) {
            if (hewan.getId() == idHewanDikecualikan) {
                continue;
            }
            if (hewan.getPerawatan().getIdPerawatan() == idPerawatan) {
                return true;
            }
        }
        return false;
    }
 
}

    