# HIGO.id Automation Test Report

## 1. Issue Identification

Dalam proses analisis dan pembuatan automation test untuk website `higo.id` dan `blog.higo.id`, ditemukan beberapa poin teknis yang perlu diperhatikan:

### a. UI/UX & Fungsional
*   **Case Sensitivity pada Dropdown**: Ditemukan ketidaksesuaian *casing* pada opsi dropdown layanan di halaman Contact Us (e.g., di UI tertulis "HIGOspot", namun pada skrip awal terdeteksi sebagai "HIGOSpot"). Hal ini dapat menyebabkan kegagalan validasi jika data input tidak sama persis dengan opsi visual.
*   **Validation Alerts**: Alert validasi form (seperti "Isi nama kamu") berfungsi dengan baik, namun struktur DOM untuk error message ini perlu penanganan locator yang spesifik agar tidak tertukar antar field.

### b. Teknis & Automation Scope
*   **Locator Strategy**: Penggunaan `CSS Selector` pada beberapa elemen input form Contact Us diganti menjadi `XPath` untuk fleksibilitas dan akurasi yang lebih baik, terutama saat menangani elemen yang memiliki atribut dinamis atau hierarki yang kompleks.
*   **Handling Sync (Waits)**: Website memiliki transisi dan animasi loading. Penggunaan *Explicit Waits* (`ExpectedConditions.visibilityOf...`) sangat krusial dibandingkan *Implicit Waits* untuk mencegah *Flaky Tests* saat navigasi antar halaman atau interaksi menu dropdown.
*   **Window Handling**: Navigasi ke sub-domain seperti `blog.higo.id` membuka tab/window baru, sehingga testing harus menangani *switch window handle* secara tepat untuk memverifikasi halaman tujuan.

---

## 2. Automation Testing

Berikut adalah detail implementasi automation testing yang telah disusun:

### Teknologi yang Digunakan
*   **Bahasa Pemrograman**: Java
*   **Framework**: Selenium WebDriver
*   **Test Runner**: TestNG
*   **Build Tool**: Maven

### Struktur Project (Page Object Model)
Penerapan *Page Object Model (POM)* memisahkan logika tes (assertion) dari representasi elemen UI (locators & actions), sehingga kode lebih rapi dan mudah di-*maintain*.

*   **`com.higo.id.base`**: Mengatur inisialisasi WebDriver (`setUp`), navigasi awal, dan `tearDown` (menutup browser).
*   **`com.higo.id.pages`**:
    *   `HigoHomePage.java`: Menangani navigasi menu utama, pengecekan logo, dan redirect ke layanan.
    *   `HigoContactPage.java`: Menangani form interaksi (input text, select dropdown, submit) dan pengambilan pesan error.
*   **`com.higo.id.tests`**:
    *   `HigoHomePageTest.java`: Verifikasi load halaman utama dan navigasi menu.
    *   `HigoContactTest.java`:
        *   **Positive Test**: Mengisi form dengan data dummy valid dan submit.
        *   **Negative Test**: Submit form kosong dan memverifikasi kemunculan alert error.

### Cara Menjalankan Test
1.  **Prasyarat**: Pastikan Java (JDK) dan Maven sudah terinstal.
2.  **Command Line**:
    Jalankan perintah berikut di terminal root project:
    ```bash
    mvn clean test
    ```
    Untuk menjalankan test class spesifik:
    ```bash
    mvn test -Dtest=HigoContactTest
    ```

### Cakupan Test (Test Scenarios)
1.  **Home Page Navigation**: Memastikan user bisa berpindah dari Home ke halaman Services, About Us, dan Blog.
2.  **Contact Us Form Validation**:
    *   Skenario Negatif: Memastikan sistem menolak input kosong dengan pesan error yang sesuai.
    *   Skenario Positif: Memastikan user bisa mengirim pesan (simulasi input valid).
3.  **Cross-domain Navigation**: Verifikasi perpindahan dari `higo.id` ke `blog.higo.id`.
