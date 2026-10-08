/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import controller.PengelolaHewan;
import view.View;
/**
 *
 * @author ASUS
 */
public class Main {
    public static void main(String[] args) {
        PengelolaHewan pengelola = new PengelolaHewan();
        View view = new View();
        
        int pilihan;
        
        do {
            view.tampilanMenu();
            pilihan = view.bacaAngka("Pilih menu: ");
 
            switch (pilihan) {
                case 1:
                    view.tambahHewan(pengelola);
                    break;
                case 2:
                    view.lihatHewan(pengelola);
                    break;
                case 3:
                    view.ubahHewan(pengelola);
                    break;
                case 4:
                    view.hapusHewan(pengelola);
                    break;
                case 5:
                    System.out.println("\nProgram selesai. Terima kasih!");
                    break;
                default:
                    System.out.println("\nPilihan menu tidak tersedia.");
                    break;
            }
        } while (pilihan !=5);
        
        view.tutupInput();
    }
}

