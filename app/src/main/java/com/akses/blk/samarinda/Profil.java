package com.akses.blk.samarinda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.akses.blk.samarinda.R;

public class Profil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        String text = " <font  color=\"#000\"; face=\"Serif\" class=\"contoh1\"><h3>Profil BLK Samarinda</h3>\n" +
                "<p align=\"justify\" color=\"#ffffff\"; face=\"Serif\" class=\"contoh1\">Sejarah Balai Latihan kerja Samarinda yang selanjutnya disebut BLK Samarinda \n" +
                "adalah Unit Pelaksana Teknis Pusat (UPTP) di bidang latihan kerja\n" +
                "  industri yang berada di bawah dan bertanggung jawab kepada \n" +
                "  Direktur Jenderal Pembinaan Pelatihan dan Produktivitas. BLK Samarinda \n" +
                "  wilayah kerja di Empat Propinsi yang diantaranya adalah Propinsi \n" +
                "  Kalimantan Timur, Propinsi Kalimantan Barat, Propinsi Kalimantan \n" +
                "  Selatan, dan Propinsi Kalimantan Tengah. Adapun dasar hukum pendirian \n" +
                "  Balai Latihan Kerja yang selanjutnya disebut BLK, yaitu Peraturan \n" +
                "  Menteri Tenaga Kerja dan Transmigrasi R.I. Nomor: PER.06/MEN/III/2006 \n" +
                "  tentang Organisasi dan Tata Kerja Unit Pelaksana Teknis di Lingkungan \n" +
                "  Departemen Tenaga Kerja dan Transmigrasi tanggal 15 Maret 2006, \n" +
                "  yang telah diubah dengan Peraturan Menteri Tenaga Kerja dan \n" +
                "  Transmigrasi RI Nomor : PER.16/MEN/VII/2007 tanggal 09 Juli 2007, dan \n" +
                "  selanjutnya dirubah dengan Peraturan Menteri Tenaga Kerja dan \n" +
                "  Transmigrasi RI Nomor : PER.07/MEN/IV/2011 tanggal 29 April 2011. \n" +
                "  <br>Sejak berdirinya BLK Samarinda hingga saat ini telah mengalami \n" +
                "  beberapa kali perubahan nomenklatur mengikuti perkembangan dunia \n" +
                "  kerja dan kebutuhan pelatihan. Adapun perubahan tersebut adalah \n" +
                "  sebagai berikut : <ol><li align=\"justify\">Tahun 1976-1980 (Pusat Latihan Kejuruan Industri \n" +
                "  dan Manajemen Kehutanan atau PLKIMK);</li><li align=\"justify\"> Tahun 1981-1989 (Balai Latihan \n" +
                "  Kerja Industri atau BLKI);</li><li> Tahun 1990-1995 (Balai Latihan Kerja atau BLK); </li>\n" +
                "  <li align=\"justify\">Tahun 1995-2015 (Balai Latihan Kerja Industri atau BLKI).</li> \n" +
                "  <lialign=\"justify\">Tahun 2015 - Sekarang (Balai Latihan Kerja atau BLK)</li> </ol>\n" +
                "  Adapun maksud dan tujuan didirikannya BLK adalah untuk \n" +
                "  <ol>\n" +
                "    <li align=\"justify\">Menghasilkan tenaga kerja Indonesia terutama karyawan industri, \n" +
                "  dan para pencari kerja yang berkualitas dan kompetitif melalui pelatihan, \n" +
                "      sertifikasi kompetensi, dan penempatan tenaga kerja; </li>\n" +
                "    <li align=\"justify\">Mewujudkan kemandirian institusi dalam pengelolaan sumberdaya \n" +
                "      pelatihan secara Profesional dan Transparan.</li> </ol>\n" +
                "  <p align=\"justify\">BLK mempunyai tugas \n" +
                "  pokok melaksanakan program Pelatihan Tenaga Kerja, \n" +
                "  Uji Coba Program Pelatihan, Uji Kompetensi, serta Pemberdayaan \n" +
                "  Lembaga Pelatihan dibidang industri. Dalam melaksanakan tugasnya \n" +
                "  tersebut BLK Samarinda memiliki fungsi sebagai berikut : \n" +
                "  <ol>\n" +
                "    <li align=\"justify\">Penyusunan rencana, program dan anggaran, evaluasi dan pelaporan; </li>\n" +
                "    <li align=\"justify\">Pelaksanaan pelatihan tenaga kerja; </li>\n" +
                "    <li align=\"justify\">Pelaksanaan uji coba program pelatihan dan uji kompetensi tenaga kerja; </li>\n" +
                "<li align=\"justify\">Pelaksanaan evaluasi program pelatihan kerja, pemasaran, uji kompetensi, kerjasama kelembagaan dan penyusunan laporan;\n" +
                "    <li align=\"justify\">Pelaksanaan urusan tata usaha dan rumah tangga.</li></ol>\n" +
                " </font>";

        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.loadData("<style type=\"text/css\"> .contoh1 {font-color: #FF000000 ;font-size:14px; line-height: 1.5em; text-align : justify;}</style><body bgcolor=\"#c9ffc4\" text=\"#ffffff\" >" +  text, "text/html", "UTF-8");

    }
}
