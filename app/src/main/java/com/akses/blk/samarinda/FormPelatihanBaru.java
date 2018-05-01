package com.akses.blk.samarinda;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.akses.blk.samarinda.Api.AccessServiceAPI;
import com.ipaulpro.afilechooser.utils.FileUtils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ASUS on 29/06/2017.
 */

public class FormPelatihanBaru extends AppCompatActivity implements View.OnClickListener {


    private static final int STORAGE_PERMISSION_CODE = 123;
    private static final int REQUEST_CHOOSER = 1234;
    int sp1, sp2;
    int serverResponseCode = 0;
    ProgressDialog dialog = null;
    private EditText txtNama;
    private EditText txtJK;
    private EditText txtTTL;
    private EditText txtAlamat;
    private EditText txtProvinsi;
    private EditText txtKab;
    private EditText txtNoTelp;
    private EditText txtEmail;
    private EditText txtAgama;
    private EditText txtPendidikan;
    private EditText txtJurusan;
    private EditText txtAsal;
    private EditText txtKejuruan;
    private EditText txtSubKejuruan;
    private EditText txtProgram;
    private EditText txtUrlPhoto;
    private ProgressDialog m_ProgressDialog;
    private AccessServiceAPI m_AccessServiceAPI;
    private Spinner spnProgram, spnProvinsi, spnAgama,
            spnKabupaten, spnPendidikan, spnJurusan, spnKejuruan, spnSubKejuruan;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private Button buttonPilihFile, buttonUpload;
    private TextView textViewPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pelatihan);
        //Requesting storage permission
        requestStoragePermission();
        init();
    }

    protected void init() {

        textViewPath = (TextView) findViewById(R.id.textViewPath);
        textViewPath.setText("");

        txtNama = (EditText) findViewById(R.id.txt_nama);
        txtNama.setText("");

        txtTTL = (EditText) findViewById(R.id.txt_ttl);
        txtTTL.setText("");

        txtAlamat = (EditText) findViewById(R.id.txt_alamat);
        txtAlamat.setText("");

        spnProvinsi = (Spinner) findViewById(R.id.spnProvinsi);

        spnKabupaten = (Spinner) findViewById(R.id.spnKabupaten);

        txtNoTelp = (EditText) findViewById(R.id.txt_notelp);
        txtNoTelp.setText("");

        txtEmail = (EditText) findViewById(R.id.txt_email);
        txtEmail.setText("");

        spnAgama = (Spinner) findViewById(R.id.spnAgama);

        spnPendidikan = (Spinner) findViewById(R.id.spnPendidikan);

        txtJurusan = (EditText) findViewById(R.id.txt_jurusan);
        txtJurusan.setText("");

        txtAsal = (EditText) findViewById(R.id.txt_asal_sekolah);
        txtAsal.setText("");

        spnKejuruan = (Spinner) findViewById(R.id.spnKejuruan);

        spnSubKejuruan = (Spinner) findViewById(R.id.spnSubKejuruan);

        spnProgram = (Spinner) findViewById(R.id.spnProgram);

        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);


        buttonPilihFile = (Button) findViewById(R.id.buttonPilihFile);
        buttonPilihFile.setOnClickListener(this);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        buttonUpload.setOnClickListener(this);

        ArrayAdapter dataAgama = ArrayAdapter.createFromResource(this, R.array.agama, android.R.layout.simple_spinner_item);
        dataAgama.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnAgama.setAdapter(dataAgama);

        ArrayAdapter dataPendidikan = ArrayAdapter.createFromResource(this, R.array.pendidikan, android.R.layout.simple_spinner_item);
        dataPendidikan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPendidikan.setAdapter(dataPendidikan);


        spnProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_aceh,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 1) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_bali,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 2) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_bangka_belitung,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 3) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_banten,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 4) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_bengkulu,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 5) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_yogyakarta,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 6) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_jakarta,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 7) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_gorontalo,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 8) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_jambi,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 9) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_jawa_barat,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 10) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_jawa_tengah,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 11) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_jawa_timur,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 12) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_kalimantan_barat,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 13) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_kalimantan_selatan,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 14) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_kalimantan_tengah,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 15) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_kalimantan_timur,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 16) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_kalimantan_utara,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 17) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_kepulauan_riau,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 18) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_lampung,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 19) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_maluku,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 20) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_maluku_utara,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 21) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_ntt,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 22) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_ntb,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 23) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getApplication(), R.array.prov_papua,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 24) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_papua_barat,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 25) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getApplication(), R.array.prov_riau,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 26) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sulawesi_barat,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 27) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sulawesi_selatan,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 28) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sulawesi_tengah,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 29) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sulawesi_tenggara,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 30) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sulawesi_utara,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 31) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sumatera_barat,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 32) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sumatera_selatan,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                } else if (position == 33) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sumatera_utara,
                                    android.R.layout.simple_spinner_item);
                    spnKabupaten.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnKejuruan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String province = (String) parent.getItemAtPosition(position);

                if (province.matches("Agribisnis")) {
                    sp1 = 0;
                    populateDist();
                } else if (province.matches("Bangunan")) {
                    sp1 = 1;
                    populateDist();
                } else if (province.matches("Bisnis dan Manajemen")) {
                    sp1 = 2;
                    populateDist();
                } else if (province.matches("Garmen Apparel")) {
                    sp1 = 3;
                    populateDist();
                } else if (province.matches("Processing")) {
                    sp1 = 4;
                    populateDist();
                } else if (province.matches("Refrigeration")) {
                    sp1 = 5;
                    populateDist();
                } else if (province.matches("Teknik Elektronika")) {
                    sp1 = 6;
                    populateDist();
                } else if (province.matches("Teknik Las")) {
                    sp1 = 7;
                    populateDist();
                } else if (province.matches("Teknik Listrik")) {
                    sp1 = 8;
                    populateDist();
                } else if (province.matches("Teknik Manufaktur")) {
                    sp1 = 9;
                    populateDist();
                } else if (province.matches("Teknik Otomotif")) {
                    sp1 = 10;
                    populateDist();
                } else if (province.matches("Teknologi Informasi Komunikasi")) {
                    sp1 = 11;
                    populateDist();
                } else if (province.matches("Aviation")) {
                    sp1 = 12;
                    populateDist();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnSubKejuruan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinDist = (String) parent.getItemAtPosition(position);

                if (spinDist.matches("Agribisnis Produksi Peternakan") && sp1 == 0) {
                    sp2 = 0;
                    populateLocal();
                } else if (spinDist.matches("Furniture") && sp1 == 1) {
                    sp2 = 1;
                    populateLocal();
                } else if (spinDist.matches("Gambar Bangunan") && sp1 == 1) {
                    sp2 = 2;
                    populateLocal();
                } else if (spinDist.matches("Surver dan Pemetaan") && sp1 == 1) {
                    sp2 = 3;
                    populateLocal();
                } else if (spinDist.matches("Administrasi Perkantoran") && sp1 == 2) {
                    sp2 = 4;
                    populateLocal();
                } else if (spinDist.matches("Sekretaris") && sp1 == 2) {
                    sp2 = 5;
                    populateLocal();
                } else if (spinDist.matches("Junior Administrative") && sp1 == 2) {
                    sp2 = 27;
                    populateLocal();
                } else if (spinDist.matches("Menjahit (Knitting, Woven)") && sp1 == 3) {
                    sp2 = 6;
                    populateLocal();
                } else if (spinDist.matches("Teknik Bordir") && sp1 == 3) {
                    sp2 = 7;
                    populateLocal();
                } else if (spinDist.matches("Pengolahan Hasil Perikanan") && sp1 == 4) {
                    sp2 = 8;
                    populateLocal();
                } else if (spinDist.matches("Ac Ruangan") && sp1 == 5) {
                    sp2 = 9;
                    populateLocal();
                } else if (spinDist.matches("Telekomunikasi") && sp1 == 6) {
                    sp2 = 10;
                    populateLocal();
                } else if (spinDist.matches("Las Industri - Listrik") && sp1 == 7) {
                    sp2 = 11;
                    populateLocal();
                } else if (spinDist.matches("Instalasi Penerangan") && sp1 == 8) {
                    sp2 = 12;
                    populateLocal();
                } else if (spinDist.matches("Mesin Prosuksi") && sp1 == 9) {
                    sp2 = 13;
                    populateLocal();
                } else if (spinDist.matches("AC Cold Storage") && sp1 == 10) {
                    sp2 = 14;
                    populateLocal();
                } else if (spinDist.matches("Teknik Alat Berat") && sp1 == 10) {
                    sp2 = 15;
                    populateLocal();
                } else if (spinDist.matches("Teknik Kendaraan Ringan - Bensin") && sp1 == 10) {
                    sp2 = 16;
                    populateLocal();
                } else if (spinDist.matches("Teknik Sepeda Motor") && sp1 == 10) {
                    sp2 = 17;
                    populateLocal();
                } else if (spinDist.matches("Teknik Kendaraan Ringan") && sp1 == 10) {
                    sp2 = 23;
                    populateLocal();
                } else if (spinDist.matches("Database") && sp1 == 11) {
                    sp2 = 18;
                    populateLocal();
                } else if (spinDist.matches("Graphic Design") && sp1 == 11) {
                    sp2 = 19;
                    populateLocal();
                } else if (spinDist.matches("Operator komputer") && sp1 == 11) {
                    sp2 = 20;
                    populateLocal();
                } else if (spinDist.matches("Technical Support") && sp1 == 11) {
                    sp2 = 21;
                    populateLocal();
                } else if (spinDist.matches("Web Administrator") && sp1 == 11) {
                    sp2 = 22;
                    populateLocal();
                } else if (spinDist.matches("Aviation Security") && sp1 == 12) {
                    sp2 = 24;
                    populateLocal();
                } else if (spinDist.matches("Marshalling") && sp1 == 12) {
                    sp2 = 25;
                    populateLocal();
                } else if (spinDist.matches("Passasi") && sp1 == 12) {
                    sp2 = 26;
                    populateLocal();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    void populateDist() {
        if (sp1 == 0) {
            String[] ag = {"Agribisnis Produksi Peternakan",
            };
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ag);
            spnSubKejuruan.setAdapter(adapter);
        } else if (sp1 == 1) {
            String[] ec = {"Furniture", "Gambar Bangunan", "Surver dan Pemetaan",
            };
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ec);
            spnSubKejuruan.setAdapter(adapter);
        }
        //Free State
        else if (sp1 == 2) {
            String[] fs = {"Administrasi Perkantoran", "Sekretaris", "Junior Administrative"};

            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fs);
            spnSubKejuruan.setAdapter(adapter2);
        } else if (sp1 == 3) {
            String[] ga = {"Menjahit (Knitting, Woven)", "Teknik Bordir"};
            ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ga);
            spnSubKejuruan.setAdapter(adapter3);
        } else if (sp1 == 4) {
            String[] prs = {"Pengolahan Hasil Perikanan"};

            ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, prs);
            spnSubKejuruan.setAdapter(adapter4);
        } else if (sp1 == 5) {
            String[] rfg = {"Ac Ruangan"};

            ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, rfg);
            spnSubKejuruan.setAdapter(adapter5);
        } else if (sp1 == 6) {
            String[] te = {"Telekomunikasi"};

            ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, te);
            spnSubKejuruan.setAdapter(adapter6);
        } else if (sp1 == 7) {
            String[] tl = {"Las Industri - Listrik"};

            ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tl);
            spnSubKejuruan.setAdapter(adapter7);
        } else if (sp1 == 8) {
            String[] tlt = {"Instalasi Penerangan"};

            ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tlt);
            spnSubKejuruan.setAdapter(adapter8);
        } else if (sp1 == 9) {
            String[] tm = {"Mesin Prosuksi"};

            ArrayAdapter<String> adapter9 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tm);
            spnSubKejuruan.setAdapter(adapter9);
        } else if (sp1 == 10) {
            String[] to = {"AC Cold Storage", "Teknik Alat Berat", "Teknik Kendaraan Ringan - Bensin", "Teknik Sepeda Motor", "Teknik Kendaraan Ringan"};

            ArrayAdapter<String> adapter10 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, to);
            spnSubKejuruan.setAdapter(adapter10);
        } else if (sp1 == 11) {
            String[] tik = {"Database", "Graphic Design", "Operator komputer", "Technical Support", "Web Administrator"};

            ArrayAdapter<String> adapter11 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tik);
            spnSubKejuruan.setAdapter(adapter11);
        } else if (sp1 == 12) {
            String[] avi = {"Aviation Security", "Marshalling", "Passasi"};

            ArrayAdapter<String> adapter12 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, avi);
            spnSubKejuruan.setAdapter(adapter12);
        }
    }

    void populateLocal() {
        //Baffalo City
        if (sp2 == 0) {
            String[] pbk = {"Pelatihan Berbasis Kompetensi"};

            ArrayAdapter<String> adapterL0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pbk);
            spnProgram.setAdapter(adapterL0);
        } else if (sp2 == 1) {
            String[] mf = {"Meubelair & Finishing"};

            ArrayAdapter<String> adapterL1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mf);
            spnProgram.setAdapter(adapterL1);
        }
        //Nelson Mandela Bay
        else if (sp2 == 2) {
            String[] da = {"Drafter arsitektur"};

            ArrayAdapter<String> adapterL2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, da);
            spnProgram.setAdapter(adapterL2);
        } else if (sp2 == 3) {
            String[] ju = {"Juru Ukur (Surveyor)"};

            ArrayAdapter<String> adapterL3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ju);
            spnProgram.setAdapter(adapterL3);
        } else if (sp2 == 4) {
            String[] ap = {"Administrasi Perkantoran"};

            ArrayAdapter<String> adapterL4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ap);
            spnProgram.setAdapter(adapterL4);
        } else if (sp2 == 5) {
            String[] sk = {"Sekretaris"};
            ArrayAdapter<String> adapterL5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sk);
            spnProgram.setAdapter(adapterL5);
        } else if (sp2 == 6) {
            String[] pkw = {"Penjahit Pakaian Wanita"};
            ArrayAdapter<String> adapterL6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pkw);
            spnProgram.setAdapter(adapterL6);
        } else if (sp2 == 7) {
            String[] apw = {"Asisten Penjahit Wanita"};
            ArrayAdapter<String> adapterL7 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, apw);
            spnProgram.setAdapter(adapterL7);
        } else if (sp2 == 8) {
            String[] pbk1 = {"Pelatihan Berbasis Kompetensi"};
            ArrayAdapter<String> adapterL8 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pbk1);
            spnProgram.setAdapter(adapterL8);
        } else if (sp2 == 9) {
            String[] tpa = {"Teknisi Pendingin AC Split", "Teknisi Refrigerasi Komersial"};
            ArrayAdapter<String> adapterL9 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tpa);
            spnProgram.setAdapter(adapterL9);
        } else if (sp2 == 10) {
            String[] th = {"Teknisi Handphone"};
            ArrayAdapter<String> adapterL10 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, th);
            spnProgram.setAdapter(adapterL10);
        } else if (sp2 == 11) {
            String[] pbk2 = {"Pelatihan Berbasis Kompetensi"};
            ArrayAdapter<String> adapterL11 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pbk2);
            spnProgram.setAdapter(adapterL11);
        } else if (sp2 == 12) {
            String[] li = {"Listrik Industri", "Pelatihan Berbasis Kompetensi"};
            ArrayAdapter<String> adapterL12 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, li);
            spnProgram.setAdapter(adapterL12);
        } else if (sp2 == 13) {
            String[] pbk3 = {"Pelatihan Berbasis Kompetensi"};
            ArrayAdapter<String> adapterL13 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pbk3);
            spnProgram.setAdapter(adapterL13);
        } else if (sp2 == 14) {
            String[] pbk4 = {"Pelatihan Berbasis Kompetensi"};
            ArrayAdapter<String> adapterL14 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pbk4);
            spnProgram.setAdapter(adapterL14);
        } else if (sp2 == 15) {
            String[] mab = {"Mekanik Alat Berat", "Pelatihan Berbasis Kompetensi", "Swadana", "Teknik Alat Berat (Operator Excavator)"};
            ArrayAdapter<String> adapterL15 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mab);
            spnProgram.setAdapter(adapterL15);
        } else if (sp2 == 16) {
            String[] oht = {"Over Houl Tune Up Diesel", "Pelatihan Berbasis Kompetensi", "Tune Up Bensin dan Diesel"};
            ArrayAdapter<String> adapterL16 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, oht);
            spnProgram.setAdapter(adapterL16);
        } else if (sp2 == 17) {
            String[] mjsp = {"Mekanik Junior Sepeda Motor", "Pelatihan Berbasis Kompetensi"};
            ArrayAdapter<String> adapterL17 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mjsp);
            spnProgram.setAdapter(adapterL17);
        } else if (sp2 == 19) {
            String[] pbk5 = {"Pelatihan Berbasis Kompetensi"};
            ArrayAdapter<String> adapterL18 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pbk5);
            spnProgram.setAdapter(adapterL18);
        } else if (sp2 == 20) {
            String[] pbk6 = {"Pelatihan Berbasis Kompetensi"};
            ArrayAdapter<String> adapterL19 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pbk6);
            spnProgram.setAdapter(adapterL19);
        } else if (sp2 == 21) {
            String[] op = {"Operator Komputer", "Pelatihan Berbasis Kompetensi"};
            ArrayAdapter<String> adapterL20 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, op);
            spnProgram.setAdapter(adapterL20);
        } else if (sp2 == 22) {
            String[] pbk7 = {"Pelatihan Berbasis Kompetensi"};
            ArrayAdapter<String> adapterL21 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pbk7);
            spnProgram.setAdapter(adapterL21);
        } else if (sp2 == 23) {
            String[] pbk8 = {"Body Repair & Painting"};
            ArrayAdapter<String> adapterL22 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pbk8);
            spnProgram.setAdapter(adapterL22);
        } else if (sp2 == 24) {
            String[] pbk9 = {"Aviation Security"};
            ArrayAdapter<String> adapterL23 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pbk9);
            spnProgram.setAdapter(adapterL23);
        } else if (sp2 == 25) {
            String[] pbk9 = {"Marshalling"};
            ArrayAdapter<String> adapterL23 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pbk9);
            spnProgram.setAdapter(adapterL23);
        } else if (sp2 == 26) {
            String[] pbk9 = {"Passasi"};
            ArrayAdapter<String> adapterL23 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pbk9);
            spnProgram.setAdapter(adapterL23);
        } else if (sp2 == 27) {
            String[] pbk9 = {"Junior Administrative"};
            ArrayAdapter<String> adapterL27 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pbk9);
            spnProgram.setAdapter(adapterL27);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonPilihFile:

                Intent getContentIntent = FileUtils.createGetContentIntent();
                Intent intent = Intent
                        .createChooser(getContentIntent, "Pilih file");
                startActivityForResult(intent, REQUEST_CHOOSER);

                break;
            case R.id.buttonUpload:

                final String path = textViewPath.getText().toString();

                if (path != null && FileUtils.isLocal(path)) {
                    File file = new File(path);
                }

                if ("".equals(txtNama.getText().toString())) {
                    txtNama.setError("Nama Tidak Boleh Kosong");
                    txtNama.requestFocus();
                    return;
                }

                if ("".equals(txtTTL.getText().toString())) {
                    txtTTL.setError("Tempat Tanggal Lahir Tidak Boleh Kosong");
                    txtTTL.requestFocus();
                    return;
                }

                if ("".equals(txtAlamat.getText().toString())) {
                    txtAlamat.setError("Alamat Tidak Boleh Kosong");
                    txtAlamat.requestFocus();
                    return;
                }

                if ("".equals(txtNoTelp.getText().toString())) {
                    txtNoTelp.setError("No Telp Tidak Boleh Kosong");
                    txtNoTelp.requestFocus();
                    return;
                }

                if ("".equals(txtEmail.getText().toString())) {
                    txtEmail.setError("Email Tidak Boleh Kosong");
                    txtEmail.requestFocus();
                    return;
                }

                if ("".equals(txtJurusan.getText().toString())) {
                    txtJurusan.setError("Jurusan Tidak Boleh Kosong");
                    txtJurusan.requestFocus();
                    return;
                }

                if ("".equals(txtAsal.getText().toString())) {
                    txtAsal.setError("Asal Sekolah Tidak Boleh Kosong");
                    txtAsal.requestFocus();
                    return;
                }

                if ("".equals(textViewPath.getText().toString())) {
                    textViewPath.setError("Pilih Photo");
                    textViewPath.requestFocus();
                    return;
                }

                dialog = ProgressDialog.show(FormPelatihanBaru.this, "",
                        "Mohon Tunggu Sebentar, Sedang Proses Mengirim Data Anda Ke Database BLK Samarinda...", true);

                new Thread(new Runnable() {
                    public void run() {
                        runOnUiThread(new Runnable() {
                            public void run() {
//                                textViewKeterangan.setText("Proses Upload");
                            }
                        });

                        int response = 0;
//
                        String nama = txtNama.getText().toString();


                        int selectedId = radioSexGroup.getCheckedRadioButtonId();
                        radioSexButton = (RadioButton) findViewById(selectedId);
                        String jk = radioSexButton.getText().toString();

                        String ttl = txtTTL.getText().toString();

                        String alamat = txtAlamat.getText().toString();

                        String provinsi = spnProvinsi.getSelectedItem().toString();

                        String kab_kota = spnKabupaten.getSelectedItem().toString();

                        String notelp = txtNoTelp.getText().toString();

                        String email = txtEmail.getText().toString();

                        String agama = spnAgama.getSelectedItem().toString();

                        String pendidikan = spnPendidikan.getSelectedItem().toString();

                        String jurusan = txtJurusan.getText().toString();

                        String asal_sekolah = txtAsal.getText().toString();

                        String kejuruan = spnKejuruan.getSelectedItem().toString();

                        String sub_kejuruan = spnSubKejuruan.getSelectedItem().toString();

                        String program = spnProgram.getSelectedItem().toString();

                        response = uploadFile(path, nama, jk, ttl, alamat,
                                provinsi, kab_kota, notelp, email, agama,
                                pendidikan, jurusan, asal_sekolah, kejuruan, sub_kejuruan,
                                program);
                        System.out.println("RES : " + response);


                    }

                }).start();
                break;
            default:

                break;
        }


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case REQUEST_CHOOSER:
                if (resultCode == RESULT_OK) {

                    final Uri uri = data.getData();
                    final String path = FileUtils.getPath(this, uri);
                    textViewPath.setText(path);
                }
                break;
        }
    }

    public int uploadFile(String sourceFileUri, String nama, String jk, String ttl, String alamat, String provinsi,
                          String kab_kota, String notelp, String email, String agama, String pendidikan,
                          String jurusan, String asal_sekolah, String kejuruan, String sub_kejuruan, String program) {

        // ip komputer server
        String upLoadServerUri = "http://aksesblk-samarinda.com/aksesblksamarinda/blk/pelatihan.php";
        String fileName = sourceFileUri;

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
//        int maxBufferSize = 1 * 1024 * 1024;
        int maxBufferSize = 1 * 1500;
        File sourceFile = new File(sourceFileUri);
        if (!sourceFile.isFile()) {
            Log.e("uploadFile", "Source File Does not exist");
            return 0;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            URL url = new URL(upLoadServerUri);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true); // Allow Inputs
            conn.setDoOutput(true); // Allow Outputs
            conn.setUseCaches(false); // Don't use a Cached Copy
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary=" + boundary);
            conn.setRequestProperty("uploaded_file", fileName);
            dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            // untuk parameter keterangan
            dos.writeBytes("Content-Disposition: form-data; name=\"nama\""
                    + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(nama);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            // untuk parameter keterangan
            dos.writeBytes("Content-Disposition: form-data; name=\"jk\""
                    + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(jk);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            // jika ingin menambahkan parameter baru, silahkan buat baris baru
            // lagi seperti berikut
            dos.writeBytes("Content-Disposition: form-data; name=\"ttl\"" +
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(ttl);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"alamat\"" +
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(alamat);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"provinsi\"" +
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(provinsi);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"kab_kota\"" +
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(kab_kota);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"notelp\"" +
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(notelp);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"email\"" +
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(email);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"agama\"" +
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(agama);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"pendidikan\"" +
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(pendidikan);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"jurusan\"" +
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(jurusan);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"asal_sekolah\"" +
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(asal_sekolah);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"kejuruan\"" +
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(kejuruan);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"sub_kejuruan\"" +
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(sub_kejuruan);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"program\"" +
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(program);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                    + fileName + "\"" + lineEnd);
            dos.writeBytes(lineEnd);
            // create a buffer of maximum size
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];
            // read file and write it into form...
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }


            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            serverResponseCode = conn.getResponseCode();
            String serverResponseMessage = conn.getResponseMessage();


            if (serverResponseCode == 200) {
                runOnUiThread(new Runnable() {
                    public void run() {
//                        textViewKeterangan.setText("Upload Berhasil");


                        LayoutInflater inflater = getLayoutInflater();
                        View toastLayout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_layout));

                        Toast toast = new Toast(getApplicationContext());

                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(toastLayout);
                        toast.show();
                        toast.show();
                        toast.show();
//                        Toast.makeText(FormPelatihanBaru.this, "Data Anda Telah dikirim ke Database BLK Samarinda",
//                                Toast.LENGTH_LONG).show();

                    }

                });

            } else {

                Toast.makeText(FormPelatihanBaru.this, "Gagal Input Data", Toast.LENGTH_LONG).show();
            }

            // close the streams //

            fileInputStream.close();

            dos.flush();
            dos.close();

        } catch (MalformedURLException ex) {
            dialog.dismiss();
            ex.printStackTrace();
            Toast.makeText(FormPelatihanBaru.this, "MalformedURLException",
                    Toast.LENGTH_SHORT).show();
            Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
        } catch (Exception e) {
            dialog.dismiss();
            e.printStackTrace();
            Toast.makeText(FormPelatihanBaru.this, "Exception : " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            Log.e("Upload ", "Exception : " + e.getMessage(), e);
        }

        dialog.dismiss();
        finish();
        return serverResponseCode;

    }

    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }
}
