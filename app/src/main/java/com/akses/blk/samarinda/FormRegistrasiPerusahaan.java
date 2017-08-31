package com.akses.blk.samarinda;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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

public class FormRegistrasiPerusahaan extends AppCompatActivity implements View.OnClickListener {

    private static final int STORAGE_PERMISSION_CODE = 123;
    private static final int REQUEST_CHOOSER = 1234;
    int serverResponseCode = 0;
    ProgressDialog dialog = null;
    private EditText txtUsernamePerusahaan;
    private EditText txtPassword1Perusahaan;
    private EditText txtPassword2Perusahaan;
    private EditText txtNamaPerusahaan, txtAlamatPerusahaan, txtTelepon,
            txtFax, txtEmailPerusahaan, txtWebsite, txtProfil, txtPj, txtJabatan,
            txtHp, txtKet, txtSektor, txtBidang, txtJenis;
    private TextView txtPathLogo;
    private Button buttonPilihFileLogo, buttonUploadPerusahaan;
    private Spinner spnJenis, spnSektor, spnBidang, spnProvinsi, spnKab;
    private ProgressDialog m_ProgressDialog;
    private AccessServiceAPI m_AccessServiceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_perusahaan);
        //Requesting storage permission
        requestStoragePermission();
        init();
    }

    protected void init(){

        txtPathLogo = (TextView)findViewById(R.id.textViewPathLogo);

        txtUsernamePerusahaan = (EditText) findViewById(R.id.txt_username_perusahaan);
        txtPassword1Perusahaan = (EditText) findViewById(R.id.txt_pwd_perusahaan);
        txtPassword2Perusahaan = (EditText) findViewById(R.id.txt_konfirmasi_perusahaan);
        txtNamaPerusahaan = (EditText) findViewById(R.id.txt_nama_perusahaan);

        spnJenis = (Spinner) findViewById(R.id.spnJenis);
        spnSektor = (Spinner) findViewById(R.id.spnSektor);
        spnBidang = (Spinner) findViewById(R.id.spnBidang);

        txtAlamatPerusahaan = (EditText) findViewById(R.id.txt_alamat_perusahaan);
        spnProvinsi = (Spinner) findViewById(R.id.spnProvinsi);
        spnKab = (Spinner) findViewById(R.id.spnKabupaten);
        txtTelepon = (EditText) findViewById(R.id.txt_telp_perusahaan);
        txtFax = (EditText) findViewById(R.id.txt_fax_perusahaan);
        txtEmailPerusahaan = (EditText) findViewById(R.id.txt_email_perusahaan);
        txtWebsite = (EditText) findViewById(R.id.txt_web_perusahaan);
        txtProfil = (EditText) findViewById(R.id.txt_profil_perusahaan);
        txtPj = (EditText) findViewById(R.id.txt_pj);
        txtJabatan = (EditText) findViewById(R.id.txt_jabatan);
        txtHp = (EditText) findViewById(R.id.txt_telp_hp);
        txtKet = (EditText) findViewById(R.id.txt_ket_lain);

        buttonPilihFileLogo = (Button) findViewById(R.id.buttonPilihLogoPerusahaan);
        buttonPilihFileLogo.setOnClickListener(this);
        buttonUploadPerusahaan = (Button) findViewById(R.id.buttonUploadPerusahaan);
        buttonUploadPerusahaan.setOnClickListener(this);

        ArrayAdapter dataJenis = ArrayAdapter.createFromResource(this, R.array.jenis, android.R.layout.simple_spinner_item);
        dataJenis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnJenis.setAdapter(dataJenis);

        ArrayAdapter dataSektor = ArrayAdapter.createFromResource(this, R.array.sektor, android.R.layout.simple_spinner_item);
        dataSektor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSektor.setAdapter(dataSektor);

        ArrayAdapter dataBidang = ArrayAdapter.createFromResource(this, R.array.bidang, android.R.layout.simple_spinner_item);
        dataBidang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBidang.setAdapter(dataBidang);

        spnProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_aceh,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 1) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_bali,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 2) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_bangka_belitung,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 3) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_banten,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 4) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_bengkulu,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 5) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_yogyakarta,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 6) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_jakarta,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 7) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_gorontalo,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 8) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_jambi,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 9) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_jawa_barat,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 10) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_jawa_tengah,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 11) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_jawa_timur,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 12) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_kalimantan_barat,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 13) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_kalimantan_selatan,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 14) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_kalimantan_tengah,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 15) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_kalimantan_timur,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 16) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_kalimantan_utara,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 17) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_kepulauan_riau,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 18) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_lampung,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 19) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_maluku,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 20) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_maluku_utara,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 21) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_ntt,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 22) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_ntb,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 23) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getApplication(), R.array.prov_papua,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 24) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_papua_barat,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 25) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getApplication(), R.array.prov_riau,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 26) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sulawesi_barat,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 27) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sulawesi_selatan,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 28) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sulawesi_tengah,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 29) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sulawesi_tenggara,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 30) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sulawesi_utara,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 31) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sumatera_barat,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 32) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sumatera_selatan,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 33) {
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sumatera_utara,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPilihLogoPerusahaan:

                Intent getContentIntent = FileUtils.createGetContentIntent();
                Intent intent = Intent
                        .createChooser(getContentIntent, "Pilih file");
                startActivityForResult(intent, REQUEST_CHOOSER);

                break;
            case R.id.buttonUploadPerusahaan:

                final String path = txtPathLogo.getText().toString();

                if (path != null && FileUtils.isLocal(path)) {
                    File file = new File(path);
                }

                if ("".equals(txtUsernamePerusahaan.getText().toString())) {
                    txtUsernamePerusahaan.setError("Username Tidak Boleh Kosong");
                    txtUsernamePerusahaan.requestFocus();
                    return;
                }

                if ("".equals(txtPassword1Perusahaan.getText().toString())) {
                    txtPassword1Perusahaan.setError("Password Tidak Boleh Kosong");
                    txtPassword1Perusahaan.requestFocus();
                    return;
                }

                if ("".equals(txtPassword2Perusahaan.getText().toString())) {
                    txtPassword2Perusahaan.setError("Harap Isi Konfirmasi Password");
                    txtPassword2Perusahaan.requestFocus();
                    return;
                }

                if ("".equals(txtNamaPerusahaan.getText().toString())) {
                    txtNamaPerusahaan.setError("Nama Perusahaan Tidak Boleh Kosong");
                    txtNamaPerusahaan.requestFocus();
                    return;
                }

                if ("".equals(txtAlamatPerusahaan.getText().toString())) {
                    txtAlamatPerusahaan.setError("Alamat Perusahaan Tidak Boleh Kosong");
                    txtAlamatPerusahaan.requestFocus();
                    return;
                }

                if ("".equals(txtTelepon.getText().toString())) {
                    txtTelepon.setError("Telepon Perusahaan Tidak Boleh Kosong");
                    txtTelepon.requestFocus();
                    return;
                }

                if ("".equals(txtFax.getText().toString())) {
                    txtFax.setError("Fax Perusahaan Tidak Boleh Kosong");
                    txtFax.requestFocus();
                    return;
                }

                if ("".equals(txtEmailPerusahaan.getText().toString())) {
                    txtEmailPerusahaan.setError("Email Perusahaan Tidak Boleh Kosong");
                    txtEmailPerusahaan.requestFocus();
                    return;
                }

                if ("".equals(txtWebsite.getText().toString())) {
                    txtWebsite.setError("Website Perusahaan Tidak Boleh Kosong");
                    txtWebsite.requestFocus();
                    return;
                }

                if ("".equals(txtProfil.getText().toString())) {
                    txtProfil.setError("Profil Perusahaan Tidak Boleh Kosong");
                    txtProfil.requestFocus();
                    return;
                }

                if ("".equals(txtPj.getText().toString())) {
                    txtPj.setError("Penanggung Jawab Perusahaan Tidak Boleh Kosong");
                    txtPj.requestFocus();
                    return;
                }

                if ("".equals(txtJabatan.getText().toString())) {
                    txtJabatan.setError("Jabatan Perusahaan Tidak Boleh Kosong");
                    txtJabatan.requestFocus();
                    return;
                }

                if ("".equals(txtHp.getText().toString())) {
                    txtHp.setError("Nomor Hape Perusahaan Tidak Boleh Kosong");
                    txtHp.requestFocus();
                    return;
                }

                if ("".equals(txtKet.getText().toString())) {
                    txtKet.setError("Keterangan lain Tidak Boleh Kosong");
                    txtKet.requestFocus();
                    return;
                }

                if ("".equals(txtPathLogo.getText().toString())) {
                    txtPathLogo.setError("Harap Upload File Logo Perusahaan");
                    txtPathLogo.requestFocus();
                    return;
                }

                if (!txtPassword1Perusahaan.getText().toString().equals(txtPassword2Perusahaan.getText().toString())){

                    txtPassword2Perusahaan.setError("Konfirmasi Password Tidak sama");
                    txtPassword2Perusahaan.requestFocus();
                    return;
                }

                dialog = ProgressDialog.show(FormRegistrasiPerusahaan.this, "",
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
                        String username = txtUsernamePerusahaan.getText().toString();

                        String password = txtPassword1Perusahaan.getText().toString();

                        String nama_perusahaan = txtNamaPerusahaan.getText().toString();

                        String jenis = spnJenis.getSelectedItem().toString();

                        String sektor = spnSektor.getSelectedItem().toString();

                        String bidang = spnBidang.getSelectedItem().toString();

                        String alamat_perusahaan = txtAlamatPerusahaan.getText().toString();

                        String provinsi = spnProvinsi.getSelectedItem().toString();

                        String kab = spnKab.getSelectedItem().toString();

                        String telepon = txtTelepon.getText().toString();

                        String fax = txtFax.getText().toString();

                        String email = txtEmailPerusahaan.getText().toString();

                        String website = txtWebsite.getText().toString();

                        String profil_perusahaan = txtProfil.getText().toString();

                        String pj = txtPj.getText().toString();

                        String jabatan = txtJabatan.getText().toString();

                        String hp = txtHp.getText().toString();

                        String ket_lain = txtKet.getText().toString();

                        response = uploadFile(path, username, password, nama_perusahaan, jenis,
                                sektor, bidang, alamat_perusahaan, provinsi,
                                kab, telepon, fax, email, website,
                                profil_perusahaan, pj, jabatan, hp, ket_lain);
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
                    txtPathLogo.setText(path);
                }
                break;
        }
    }

    public int uploadFile(String sourceFileUri, String username, String password, String nama_perusahaan, String jenis, String sektor,
                          String bidang, String alamat_perusahaan, String provinsi, String kab, String telepon,
                          String fax, String email, String website, String profil_perusahaan, String pj , String jabatan
                          , String hp , String ket_lain) {

        // ip komputer server
        String upLoadServerUri = "http://aksesblk-samarinda.com/aksesblksamarinda/blk/perusahaanRegistrasi.php";
        String fileName = sourceFileUri;

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
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
            dos.writeBytes("Content-Disposition: form-data; name=\"username\""
                    + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(username);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            // untuk parameter keterangan
            dos.writeBytes("Content-Disposition: form-data; name=\"password\""
                    + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(password);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            // jika ingin menambahkan parameter baru, silahkan buat baris baru
            // lagi seperti berikut
            dos.writeBytes("Content-Disposition: form-data; name=\"nama_perusahaan\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(nama_perusahaan);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"jenis\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(jenis);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"sektor\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(sektor);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"bidang\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(bidang);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"alamat_perusahaan\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(alamat_perusahaan);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"provinsi\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(provinsi);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"kab\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(kab);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"telepon\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(telepon);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"fax\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(fax);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"email\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(email);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"website\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(website);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"profil_perusahaan\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(profil_perusahaan);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"pj\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(pj);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"jabatan\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(jabatan);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"hp\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(hp);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"ket_lain\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(ket_lain);
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
                        Toast.makeText(FormRegistrasiPerusahaan.this, "Data Anda Telah dikirim ke Database BLK Samarinda",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            // close the streams //
            fileInputStream.close();
            dos.flush();
            dos.close();

        } catch (MalformedURLException ex) {
            dialog.dismiss();
            ex.printStackTrace();
            Toast.makeText(FormRegistrasiPerusahaan.this, "MalformedURLException",
                    Toast.LENGTH_SHORT).show();
            Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
        } catch (Exception e) {
            dialog.dismiss();
            e.printStackTrace();
            Toast.makeText(FormRegistrasiPerusahaan.this, "Exception : " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            Log.e("Upload ","Exception : " + e.getMessage(), e);
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
