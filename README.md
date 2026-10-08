# SISTEM MANAJEMEN PENDATAAN HEWAN KEBUN BINATANG

Nama  : Noor Hamsyah Pratama  
NIM  : 2509116046  
Kelas  : B'2025  

## A. Deskripsi Singkat Program  
Program ini adalah aplikasi untuk mengelola data hewan di sebuah kebun binatang. Setiap hewan memiliki data umum (ID, nama, jenis, umur, habitat) dan data perawatan (jenis perawatan dan tanggal), serta dikategorikan menjadi dua jenis: Hewan Darat (punya atribut kecepatan lari) dan Hewan Air (punya atribut kedalaman renang). Seluruh hewan diturunkan dari abstract class `Hewan`, dan kemampuan masing-masing kategori didefinisikan melalui interface `Pelari` dan `Perenang`. Program menyediakan operasi CRUD lengkap: Tambah, Lihat, Ubah, dan Hapus data hewan, lengkap dengan validasi input di setiap tahap.

## B. Struktur Package  
Program dipecah ke dalam empat package dengan pola MVC:  

```
src
├── main
│   └── Main.java
├── controller
│   ├── PengelolaHewan.java
│   └── Validator.java
├── model
│   ├── Hewan.java            (abstract class)
│   ├── HewanDarat.java       (extends Hewan, implements Pelari)
│   ├── HewanAir.java         (extends Hewan, implements Perenang)
│   ├── PerawatanHewan.java
│   ├── Pelari.java           (interface)
│   └── Perenang.java         (interface)
└── view
    └── View.java
```

<p align="center">
  <img width="312" height="362" alt="Screenshot 2026-10-08 214748" src="https://github.com/user-attachments/assets/7a88f5aa-5050-472e-8f93-f8055775658f" />
</p>  

| Package | Isi | Tugas |  
|---|---|---|  
| `model` | `Hewan`, `HewanDarat`, `HewanAir`, `PerawatanHewan`, `Pelari`, `Perenang` | Struktur data beserta validasi dasar per-objek (sepert nama tidak boleh kosong, umur tidak boleh negatif, dll). Package ini tidak tahu apa-apa soal tampilan atau alur menu. |  
| `view` | `View` | Seluruh interaksi dengan pengguna: menampilkan menu, membaca input (`Scanner`), dan menampilkan hasil. | 
| `controller` | `PengelolaHewan`, `Validator` | `PengelolaHewan` menyimpan `ArrayList<Hewan>`, membuat ID otomatis, dan menjalankan operasi CRUD. `Validator` menangani validasi yang butuh melihat seluruh data atau format input (ID perawatan unik, tanggal tidak boleh di masa depan, cek angka/desimal). |   
| `main` | `Main` | Titik masuk program. Membuat objek `PengelolaHewan` dan `View`, lalu menjalankan perulangan menu. |  

## C. Alur Program  
Berikut ada tampilan Menu Utama:
<p align="center"> 
  <img width="350" height="187" alt="image" src="https://github.com/user-attachments/assets/d5cf416e-49ac-4afa-ae0d-98809d60b33a" />
</p>  

Lalu, Dibawah ini adalah Alur Program:  
### 1. Tambah Data Hewan 

<p align="center">  
  <img width="425" height="397" alt="image" src="https://github.com/user-attachments/assets/3999ca21-a1d9-4b95-902d-9aca68dafa4e" />
</p>  

ID hewan dibuat otomatis lewat `idHewanAuto()` sebelum lanjut ke input data umum (nama, jenis, umur, habitat). Pengguna kemudian memilih kategori antara Hewan Darat atau Hewan Air, yang menentukan atribut khusus apa yang ditanyakan selanjutnya (kecepatan lari / kedalaman renang). Terakhir, data perawatan diminta, dengan ID perawatan dibuat otomatis lewat `idPerawatanAuto()` dan tanggal divalidasi lewat `Validator.cekTanggal()` supaya tidak boleh di masa depan. Kalau semua lolos, objek HewanDarat/HewanAir baru dibuat dan disimpan.

### 2. Lihat Data Hewan  

<p align="center">  
  <img width="412" height="477" alt="image" src="https://github.com/user-attachments/assets/09e5d6e9-e7d7-44bb-b61a-46d83b617b5f" />
  <img width="366" height="420" alt="image" src="https://github.com/user-attachments/assets/2a1b0ec1-0f27-4b46-be66-a13e003dfe3b" />
</p> 

Program akan menampilkan semua data hewan satu per satu dengan memanggil method `tampilkanInfoLengkap()` dan `tampilkanKemampuan()` pada setiap hewan di dalam daftar. Karena HewanDarat dan HewanAir masing-masing punya versi method ini sendiri, tampilan yang muncul otomatis beda sesuai jenis hewannya. Kalau Hewan Darat yang muncul info hewan darat dan kemampuan berlari, kalau Hewan Air yang muncul info hewan air dan kemampuan berenang.  

### 3. Ubah Data Hewan   

<p align="center">  
  <img width="472" height="306" alt="image" src="https://github.com/user-attachments/assets/b45a85e2-3b01-4b17-a879-9a51841dde1d" />
</p> 

Setelah ID hewan diverifikasi ada lewat `cekId()`, objek lama diambil lewat `cariHewanBerdasarkanId()` untuk mendeteksi label atribut khusus lewat `getLabel()`, sehingga program otomatis menanyakan "kecepatan lari" atau "kedalaman renang" sesuai jenis hewannya. Pengguna lalu mengisi data umum baru (nama, jenis, umur, habitat) dan data perawatan baru, dengan pengecekan ID perawatan yang mengecualikan data milik hewan itu sendiri dan tanggal divalidasi lewat `Validator.cekTanggal()`. Data umum dan perawatan disimpan lewat `ubahHewan()`, sedangkan atribut khusus disimpan lewat `setNilai()`. ID hewan tidak berubah.  

### 4. Hapus Data Hewan

<p align="center"> 
  <img width="341" height="115" alt="image" src="https://github.com/user-attachments/assets/49366c83-6c37-4f81-b435-2508ff5a9cde" />
</p>  

Input ID langsung diteruskan ke `hapusHewan()`, yang menangani sendiri pencarian sekaligus penghapusan objek dari daftarHewan. Kalau ID tidak ditemukan, method ini mengembalikan false dan pesan error ditampilkan.  

### 5. Keluar  

<p align="center"> 
  <img width="258" height="57" alt="image" src="https://github.com/user-attachments/assets/54c0935b-1b58-418d-8bf8-46197bbb279a" />
</p>  

Perulangan do-while berhenti, `input.close()` dipanggil, program selesai.

## D. Encapsulation  
Atribut di class `model` disembunyikan dari luar class sehingga hanya bisa diakses lewat getter dan setter `public`:  

- `HewanDarat` (`kecepatanLariKmJam`), `HewanAir` (`kedalamanRenangMeter`), dan `PerawatanHewan` (`idPerawatan`, `jenisPerawatan`, `tanggal`) memakai modifier `private`.  
- Atribut umum di `Hewan` (`id`, `nama`, `jenis`, `umur`, `habitat`, `perawatan`) memakai modifier `protected`, supaya subclass bisa memakainya langsung (misalnya `nama` dipakai di   `berlari()` dan `berenang()`), tetapi class lain di luar hierarki tetap harus lewat getter/setter.  
- `id` dan `idPerawatan` dibuat `final` sehingga tidak bisa diubah setelah objek dibuat.

Setiap setter juga dibekali validasi, bukan sekadar menyimpan nilai mentah. Berikut adalah salah satu contoh di `Hewan.getNama` dan `Hewan.setNama()`:  

<p align="center">  
  <img width="792" height="233" alt="image" src="https://github.com/user-attachments/assets/abdc1b4c-8411-404e-9219-def42307e2eb" />
</p>   

Contoh lain di `HewanDarat.setKecepatanLariKmJam()` yang menolak nilai negatif, dan `PerawatanHewan.setTanggal()` yang menolak format selain `dd-mm-yyyy`.  

Penerapan lain dari encapsulation terlihat dari akses yang sengaja dibatasi:  

- `cariHewan()` di `PengelolaHewan` dibuat `private` karena hanya dipakai secara internal. Untuk kebutuhan luar disediakan `cariHewanBerdasarkanId()` sebagai pintu masuk `public`.  
- `daftarHewan` di `PengelolaHewan` dibuat `private`, hanya bisa diakses lewat method CRUD dan `getDaftarHewan()`.  
- `nextId` dan `nextIdPerawatan` dibuat `private static`, sehingga penomoran ID hanya bisa berjalan lewat `idHewanAuto()` dan `idPerawatanAuto()`.
- Constructor `Validator()` dibuat `private` karena class ini murni kumpulan method `static` yang tidak boleh diinstansiasi.  
- `Scanner input` di `View` dibuat `private final`, begitu juga helper `bacaString()` dan `bacaDouble()` yang hanya dipakai di dalam `View`.

## E. Inheritance 
`Hewan` bertindak sebagai superclass yang mewariskan atribut dan method umum (id, nama, jenis, umur, habitat, perawatan, beserta getter/setter-nya) ke dua **subclass**, yaitu `HewanDarat` dan `HewanAir`.

Superclass:  
<p align="center">  
  <img width="322" height="27" alt="image" src="https://github.com/user-attachments/assets/a1906cd3-0a98-4a39-aa8a-ce112dfa7f19" />
</p>

Subclass:  
<p align="center"> 
  <img width="651" height="20" alt="image" src="https://github.com/user-attachments/assets/baa22ffd-7d6e-4c0b-a839-74d287f7a44b" />
  <img width="646" height="27" alt="image" src="https://github.com/user-attachments/assets/0d794f46-1ed7-4697-9e54-6020fa5b9b04" />
</p>  

Kedua subclass tidak menulis ulang atribut umum dari nol. Constructor-nya cukup memanggil super(...) untuk mengisi bagian yang diwariskan, baru kemudian mengisi atribut khususnya sendiri. Contoh di HewanDarat:  

<p align="center">   
  <img width="1347" height="92" alt="image" src="https://github.com/user-attachments/assets/c886f935-5ec1-48f1-82c5-123d45aa9e08" />
</p>  

Kedua subclass dideklarasikan `final` karena memang tidak dirancang untuk diwariskan lagi.  

## F. Polymorphism  
Method Overriding - Subclass menimpa method dari superclass/interface dengan perilakunya sendiri:  

| Method | `HewanDarat` | `HewanAir` |
|---|---|---|
| `tampilkanInfoLengkap()` | Memanggil `super.tampilkanInfoLengkap()` lalu menambah "Info Tambahan : Hewan darat" | Memanggil `super.tampilkanInfoLengkap()` lalu menambah "Info Tambahan : Hewan air" |
| `getLabel()` | `"kecepatan lari (km/jam)"` | `"kedalaman renang (meter)"` |
| `setNilai(double)` | Memanggil `setKecepatanLariKmJam()` | Memanggil `setKedalamanRenangMeter()` |
| `tampilkanKemampuan()` | Mencetak hasil `berlari()` | Mencetak hasil `berenang()` |  

Method Overloading - `cekIdPerawatan()` memiliki dua versi dengan jumlah parameter berbeda, baik di `Validator` maupun di `PengelolaHewan`:  

<p align="center">
  <img width="1128" height="367" alt="image" src="https://github.com/user-attachments/assets/784cd013-8dc2-4788-a208-3c8493599b06" />
</p>

Versi 2 parameter hanya memanggil versi 3 parameter dengan `idHewanDikecualikan` diisi `-1`. Nilai `-1` dipilih karena ID hewan asli tidak mungkin negatif, sehingga tidak ada hewan yang benar-benar dikecualikan dan semua hewan tetap dicek. Dengan begitu logika pengecekan cukup ditulis sekali. Versi 3 parameter dipakai di menu Ubah, supaya hewan yang sedang diubah tidak dianggap bentrok dengan ID perawatannya sendiri.  

## G. Abstraction  
### 1. Abstract class `Hewan`  

`Hewan` dideklarasikan dengan `public abstract class` sehingga tidak bisa diinstansiasi langsung (`new Hewan(...)` akan error), karena yang ada di kebun binatang hanyalah hewan darat atau hewan air.  

<p align="center">
  <img width="340" height="37" alt="Screenshot 2026-10-08 223903" src="https://github.com/user-attachments/assets/33a79df6-7e5e-48a5-bbde-3456a9708b54" />
</p>

### 2. Abstract method di `Hewan`  

Di dalam `Hewan` ada tiga abstract method, yaitu method yang hanya dideklarasikan tanpa isi. `Hewan` hanya menentukan *apa* yang harus ada, sedangkan *bagaimana* caranya diserahkan ke subclass.  

<p align="center">
  <img width="487" height="130" alt="Screenshot 2026-10-08 224034" src="https://github.com/user-attachments/assets/cbd58b15-3647-44c2-a8b3-d635782e3d5f" />
</p>

### 3. Overriding abstract method di subclass  

Setiap subclass wajib mengimplementasikan ketiga method tersebut dengan `@Override`. Contoh `getLabel()` yang isinya berbeda di tiap subclass:  

Hewan Darat  
<p align="center">  
  <img width="392" height="107" alt="image" src="https://github.com/user-attachments/assets/7f68dd9e-3310-4d59-80d9-e544f5830109" />
</p>

Hewan Air  
<p align="center">  
  <img width="395" height="97" alt="image" src="https://github.com/user-attachments/assets/49d2e363-0965-4220-9e4e-d0333fb73041" />
</p>  

Contoh lain `setNilai()` di `HewanDarat`, yang meneruskan nilai ke atribut khususnya:  

<p align="center">   
  <img width="390" height="96" alt="image" src="https://github.com/user-attachments/assets/a428b036-c13f-4823-9c2c-023bdb7e20cd" />
</p>

## H. Penjelasan Letak Penerapan Nilai Tambah  

Ada dua interface di package `model`:  

<p align="center">   
  <img width="272" height="67" alt="image" src="https://github.com/user-attachments/assets/b57bab6a-b445-42fe-a3ef-addb1405c154" />
  <img width="296" height="65" alt="image" src="https://github.com/user-attachments/assets/fd5f2ae7-b7db-4ec8-9101-817d343069a5" />
</p>

Keduanya mendefinisikan kemampuan hewan, bukan jenis hewannya. Itulah alasan kemampuan ini dipisah menjadi interface dan tidak ditaruh di `Hewan`: tidak semua hewan bisa berlari atau berenang, dan di Java sebuah class hanya boleh `extends` satu class tetapi boleh `implements` banyak interface.  

| Class | Implementasi | Hasil method |
|---|---|---|
| `HewanDarat` | `implements Pelari` | `berlari()` mengembalikan `"<nama> berlari dengan kecepatan <x> km/jm."` |
| `HewanAir` | `implements Perenang` | `berenang()` mengembalikan `"<nama> berenang hingga kedalaman <x> meter."` |
 
Method interface ini dipakai di `tampilkanKemampuan()` pada masing-masing class, yang dipanggil oleh menu Lihat Data Hewan:   

Hewan Darat
<p align="center"> 
  <img width="361" height="105" alt="Screenshot 2026-10-08 224846" src="https://github.com/user-attachments/assets/2c49e24a-aac6-4ccb-877c-4e8d29276448" />
</p>

Hewan Air  
<p align="center">   
  <img width="411" height="101" alt="image" src="https://github.com/user-attachments/assets/c4303d51-6838-41de-a7ed-d8b840ccd99a" />
</p>

Dengan rancangan ini, jika suatu saat ada hewan baru yang punya lebih dari satu kemampuan (misalnya hewan amfibi yang bisa berlari sekaligus berenang), class-nya cukup `implements Pelari, Perenang` tanpa mengubah class lain.
  
