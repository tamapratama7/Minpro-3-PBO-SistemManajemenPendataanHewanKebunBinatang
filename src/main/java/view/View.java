/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author ASUS
 */

import java.util.Scanner;
import model.Hewan;
import model.HewanAir;
import model.HewanDarat;
import model.PerawatanHewan;
import controller.PengelolaHewan;
import controller.Validator;

public class View {
    
    public int bacaAngka(String prompt){
        while (true){
            System.out.print(prompt);
            String baris = input.nextLine().trim();
            if (Validator.cekAngkaNegatif(baris)){
                return Integer.parseInt(baris);
            }
            System.out.println("Input harus berupa angka dan tidak boleh negatif, COBA LAGI!!");
        }
    }
    
    private String bacaString(String prompt) {
        System.out.print(prompt);
        return input.nextLine();
    }
    
    private double bacaDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String baris = input.nextLine().trim();
            if (Validator.cekDesimalNegatif(baris)) {
                return Double.parseDouble(baris);
            }
            System.out.println("Input harus angka (boleh desimal) dan tidak boleh negatif, COBA LAGI!.");
        }
    }
    
    private final Scanner input;
    
    public View(){
        this.input = new Scanner(System.in);
    }
    
    public void tutupInput(){
        input.close();
    }
    
    public void tampilanMenu(){
        System.out.println("\n=========================================");
        System.out.println("-<SISTEM PENDATAAN HEWAN KEBUN BINATANG>-");
        System.out.println("=========================================");
        System.out.println("1. Tambah Data Hewan");
        System.out.println("2. Lihat Data Hewan");
        System.out.println("3. Ubah Data Hewan");
        System.out.println("4. Hapus Data Hewan");
        System.out.println("5. Keluar");
        System.out.println("=========================================");
    }

    public void tambahHewan(PengelolaHewan pengelola){       
        System.out.println("\n=== TAMBAH DATA HEWAN ===");

        int idBaru = pengelola.idHewanAuto();
        System.out.println("ID hewan: " + idBaru);

        String namaBaru = bacaString("Masukkan nama hewan: ");
        String jenisBaru = bacaString("Masukkan jenis hewan: ");
        int umurBaru = bacaAngka("Masukkan umur hewan: ");
        String habitatBaru = bacaString("Masukkan habitat hewan: ");

        System.out.println("\nKategori hewan:");
        System.out.println("1. Hewan Darat");
        System.out.println("2. Hewan Air");
        int kategoriBaru = bacaAngka("Pilih kategori (1/2): ");

        if (kategoriBaru != 1 && kategoriBaru != 2) {
            System.out.println("\nKategori tidak dikenali.");
            return;
        }

        double kecepatanLariBaru = 0;
        double kedalamanRenangBaru = 0;

        if (kategoriBaru == 1) {
            kecepatanLariBaru = bacaDouble ("Masukkan kecepatan lari (km/jam): ");
        } else {
            kedalamanRenangBaru = bacaDouble ("Masukkan kedalaman renang maksimal (meter): ");
        }

        System.out.println("\n=== DATA PERAWATAN ===");

        int idPerawatanBaru = pengelola.idPerawatanAuto();
        System.out.println("ID Perawatan: " + idPerawatanBaru);

        String jenisPerawatanBaru = bacaString("Masukkan jenis perawatan: ");
        String tanggalBaru = bacaString("Masukkan tanggal perawatan (dd-mm-yyyy): ");

        try {
            Validator.cekTanggal(tanggalBaru);

            PerawatanHewan perawatanBaru = new PerawatanHewan(
                    idPerawatanBaru,
                    jenisPerawatanBaru,
                    tanggalBaru
            );

            Hewan hewanBaru;
            if (kategoriBaru == 1) {
                hewanBaru = new HewanDarat(
                        idBaru,namaBaru, jenisBaru, umurBaru, habitatBaru,
                        perawatanBaru, kecepatanLariBaru
                );
            } else {
                hewanBaru = new HewanAir(
                        idBaru,namaBaru, jenisBaru, umurBaru, habitatBaru,
                        perawatanBaru, kedalamanRenangBaru
                );
            }

            if (pengelola.tambahHewan(hewanBaru)) {
                System.out.println("\nData hewan dan perawatan berhasil ditambahkan!");
            } else {
                System.out.println("\nGagal menambahkan, ID hewan sudah dipakai.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nData tidak valid: " + e.getMessage());
        }
    }
                    
    public void lihatHewan(PengelolaHewan pengelola){
        System.out.println("\n=== DATA HEWAN ===");

        if (pengelola.getDaftarHewan().isEmpty()) {
            System.out.println("Belum ada data hewan.");

        } else {

            for (final Hewan dataHewan : pengelola.getDaftarHewan()) {

                System.out.println("\n--------------------------");
                dataHewan.tampilkanInfoLengkap();
                dataHewan.tampilkanKemampuan();
                System.out.println("--------------------------");
            }
        }
    }

    public void ubahHewan(PengelolaHewan pengelola){    
        System.out.println("\n=== UBAH DATA HEWAN ===");

        int idUbah = bacaAngka("Masukkan ID hewan yang ingin diubah: ");

        if (!pengelola.cekId(idUbah)) {
            System.out.println("\nID hewan tidak ditemukan.");
            return;
        }

        Hewan hewanLama = pengelola.cariHewanBerdasarkanId(idUbah);

        String namaUbah = bacaString("Masukkan nama hewan baru: ");
        String jenisUbah = bacaString("Masukkan jenis hewan baru: ");
        int umurUbah = bacaAngka("Masukkan umur hewan baru: ");
        String habitatUbah = bacaString("Masukkan habitat hewan baru: ");

        double nilaiAtributKhususUbah = 0;
        String labelAtributKhusus = hewanLama.getLabel();

        if (labelAtributKhusus != null) {
            nilaiAtributKhususUbah = bacaDouble ("Masukkan " + labelAtributKhusus + " baru: ");
        }

        System.out.println("\n=== DATA PERAWATAN BARU ===");

        int idPerawatanUbah = bacaAngka("Masukkan ID perawatan baru: ");

        if (pengelola.cekIdPerawatan(idPerawatanUbah, idUbah)){
            System.out.println("\nID Perawatan sudah dipakai");
        }

        String jenisPerawatanUbah = bacaString("Masukkan jenis perawatan baru: ");
        String tanggalUbah = bacaString("Masukkan tanggal perawatan baru (dd-mm-yyyy): ");

        try {
            Validator.cekTanggal(tanggalUbah);

            PerawatanHewan perawatanUbah = new PerawatanHewan(
                    idPerawatanUbah,
                    jenisPerawatanUbah,
                    tanggalUbah
            );

            boolean berhasilUbah = pengelola.ubahHewan(
                    idUbah,
                    namaUbah,
                    jenisUbah,
                    umurUbah,
                    habitatUbah,
                    perawatanUbah
            );

            if (berhasilUbah) {
                if (labelAtributKhusus != null) {
                    hewanLama.setNilai(nilaiAtributKhususUbah);
                }
                System.out.println("\nData hewan berhasil diubah!");
            } else {
                System.out.println("\nID hewan tidak ditemukan.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nData tidak valid: " + e.getMessage());
        }
    }

    public void hapusHewan(PengelolaHewan pengelola){
        System.out.println("\n=== HAPUS DATA HEWAN ===");

        int idHapus = bacaAngka("Masukkan ID hewan yang ingin dihapus: ");

        boolean berhasilHapus = pengelola.hapusHewan(idHapus);

        if (berhasilHapus) {
            System.out.println("\nData hewan berhasil dihapus!");
        } else {
            System.out.println("\nID hewan tidak ditemukan.");
        }
    }
}
