# Translate & Belajar — Offline

Aplikasi Android **offline** (tanpa internet sama sekali) untuk menerjemahkan dan
belajar bahasa Jepang, Indonesia, dan Inggris. Dibuat untuk Nihongo Santai
(proyek belajar bahasa Jepang level N5).

## Isi aplikasi

Satu file HTML mandiri (`app/index.html`) yang dibungkus WebView Android.
Tidak ada CDN, tidak ada font eksternal, tidak ada panggilan jaringan saat
aplikasi berjalan. Semua data ada di dalam aplikasi.

- **Terjemah** — kamus 209 kata (Jepang + romaji + Indonesia + Inggris),
  terjemahan kata-per-kata dengan deteksi bahasa otomatis, pencocokan input
  romaji (ketik "arigatou" → ありがとう), favorit ⭐ dan riwayat (localStorage).
- **Belajar** — 19 pelajaran berurutan ala Duolingo: Hiragana (5), Katakana (5),
  Kosakata N5 (5), Tata bahasa dasar (4). Tiap pelajaran 10 soal.
  Jenis latihan: pilihan ganda (Jepang→Indonesia, Indonesia→Jepang,
  romaji→kana), jodohkan kata, isi titik-titik, susun kata.
  Gamifikasi: XP (+10 per jawaban benar, bonus +20 pelajaran sempurna),
  3 hati per pelajaran, streak harian, level berdasarkan total XP.
  Progres tersimpan di localStorage (offline).
  Audio memakai TTS bawaan perangkat (Web Speech API) kalau tersedia.
- **Frasa** — 51 frasa perjalanan (sapaan, bandara, hotel, restoran, arah,
  belanja, darurat) dalam 3 bahasa + romaji, ketuk untuk menyalin.

**Jujur:** aplikasi menerjemahkan dari kamus + buku frasa bawaan, bukan mesin
neural — kalimat bebas yang panjang hanya diterjemahkan kata per kata.
Penilaian pengucapan suara dan leaderboard TIDAK ada (keduanya butuh server
dan internet).

## Struktur repo

```
app/index.html                  # aplikasi web mandiri (sumber kebenaran)
android/                        # proyek Android (WebView wrapper)
  settings.gradle
  build.gradle
  gradle.properties
  app/build.gradle
  app/src/main/AndroidManifest.xml
  app/src/main/java/com/danryu/translateoffline/MainActivity.java
  app/src/main/assets/index.html   # salinan app/index.html untuk APK
  app/src/main/res/values/strings.xml
```

## Cara install di HP

1. Salin file `translate-offline-debug.apk` (hasil build, lihat di bawah) ke HP Android.
2. Di HP: izinkan "Install aplikasi tak dikenal" untuk aplikasi yang membuka
   file APK (misalnya Files / Chrome) — hanya untuk install ini.
3. Ketuk file APK → Install. Aplikasi **Translate & Belajar** muncul di layar utama.
4. Setelah itu aplikasi jalan **tanpa internet** (mode pesawat pun bisa).

APK ini adalah build **debug** (tidak ditandatangani rilis) — cukup untuk
pemakaian pribadi.

## Cara build APK (dari source)

Butuh: JDK 17, Android SDK (platform 35, build-tools 35.0.0), Gradle 8.7.

```bash
export JAVA_HOME=/path/ke/jdk17
export ANDROID_HOME=/path/ke/android-sdk
export GRADLE_USER_HOME=/path/ke/gradle-home   # kalau perlu proxy, isi gradle.properties di sini
cd android
gradle :app:assembleDebug
```

Hasil: `android/app/build/outputs/apk/debug/app-debug.apk`.

**Build terverifikasi 14 Nov 2026:** APK debug 33.336 byte, package
`com.danryu.translateoffline` v1.0, label "Translate & Belajar",
`assets/index.html` di dalam APK identik dengan `app/index.html`
(SHA256 APK: `3840b3d29b460e5a9772af2b74233f4fe98531e09fe87887a6399947a8aced77`).

Setiap mengubah `app/index.html`, salin lagi ke
`android/app/src/main/assets/index.html` sebelum build.

## Lisensi

MIT — bebas dipakai dan diubah.
