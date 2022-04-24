## Deposit and Installment Rest API

A simple rest api for Alami technical test assesment.
Related ERD design can be found at [here.](https://drive.google.com/file/d/1rqgQQqWPPYn50BVTk2Ip4HFJtX5z1bBx/view?usp=sharing)

### Dokumentasi API :

#### Daftar API yang digunakan untuk mengelola anggota kelompok simpan-pinjam

| Path                      | Method | Deskripsi                             |
| ---                       | -----  | ----                                  |
| /api/v1/registration      | POST   | Untuk mendaftarkan anggota            |
| /api/v1/members           | GET    | Untuk melihat semua daftar anggota    |

#### 1. Pendaftaran anggota
  * Path : /api/v1/registration
  * Method     : POST
  * Parameter Body :

    | Name        | Tipe     | Deskripsi       |
    | ---         | -----    | ----              |
    | firstName   | string   | Nama depan dari anggota, minimal 3 kata  |
    | lastName    | string   | Nama belakang dari anggota, minimal 3 kata |
    | dob         | string   | Tanggal lahir anggota, dengan format yyyy-MM-dd  |
    | address     | string   | Alamat dari anggota  |


#### 2. Menampilkan daftar anggota
  * Path : /api/v1/registration
  * Method     : GET
  * Parameter : Tidak tersedia
  * Contoh response


#### Daftar API yang digunakan untuk melakukan transaksi simpan-pinjam

| Path                      | Method | Deskripsi                             |
| ---                       | -----  | ----                                  |
| /api/v1/trx/deposit       | POST   | Digunakan untuk menyimpan atau mengambil dana simpanan        |
| /api/v1/trx/loan          | POST   | Digunakan untuk melakukan pinjaman dana    |
| /api/v1/trx/loan/payment  | POST   | Digunakan untuk pembayaran pinjaman    |
