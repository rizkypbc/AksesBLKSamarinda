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

public class FormPencaker extends AppCompatActivity implements View.OnClickListener {

    private EditText txtUsername;
    private EditText txtPassword1;
    private EditText txtPassword2;
    private EditText txtNoktp;
    private EditText txtNamaPencariKerja;
    private EditText txtJkPencari;
    private EditText txtTtlPencari;
    private EditText txtAsalSekolah, txtPendidikan, txtKetrampilan, txtPengalaman;
    private ProgressDialog m_ProgressDialog;
    private AccessServiceAPI m_AccessServiceAPI;
    private Spinner spnProvinsi, spnKab;
    private RadioGroup radioSexGroup, radioAlumniGroup;
    private RadioButton radioSexButton, radioAlumniButton;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private Button buttonPilihFilePhoto, buttonPilihFileCV, buttonUploadPencaker;
    private TextView txtViewPathPhoto, txtViewPathCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pencari_kerja);
        //Requesting storage permission
        requestStoragePermission();
        init();
    }

    protected void init(){

        txtViewPathPhoto = (TextView)findViewById(R.id.textViewPathPhoto);
        txtViewPathCV = (TextView)findViewById(R.id.textViewPathCv);

        txtUsername = (EditText) findViewById(R.id.txt_username);


        txtPassword1 = (EditText) findViewById(R.id.txt_pwd);
        txtPassword2 = (EditText) findViewById(R.id.txt_konfirmasi);
        txtNoktp = (EditText) findViewById(R.id.txt_noktp);
        txtNamaPencariKerja = (EditText) findViewById(R.id.txt_nama_pencari_kerja);
//        txtJkPencari = (EditText) findViewById(R.id.txt_jk_pencari_kerja);
        txtTtlPencari = (EditText) findViewById(R.id.txt_ttl_pencari_kerja);
        txtAsalSekolah = (EditText) findViewById(R.id.txt_asal_sekolah_pencaker);
        txtPendidikan = (EditText)findViewById(R.id.txt_pendidikan);
        txtKetrampilan = (EditText)findViewById(R.id.txt_ketrampilan);
        txtPengalaman = (EditText)findViewById(R.id.txt_pengalaman);
        m_AccessServiceAPI = new AccessServiceAPI();
        spnProvinsi = (Spinner)findViewById(R.id.spnProvinsiKerja);
        spnKab = (Spinner)findViewById(R.id.spnKabupatenKerja);
        radioAlumniGroup = (RadioGroup)findViewById(R.id.radioAlumni);
        radioSexGroup = (RadioGroup)findViewById(R.id.radioJenisKelamin);

        buttonPilihFilePhoto = (Button) findViewById(R.id.buttonPilihFilePhoto);
        buttonPilihFilePhoto.setOnClickListener(this);

        buttonPilihFileCV = (Button) findViewById(R.id.buttonPilihFileCv);
        buttonPilihFileCV.setOnClickListener(this);

        buttonUploadPencaker = (Button) findViewById(R.id.buttonUploadPencaker);
        buttonUploadPencaker.setOnClickListener(this);

        spnProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position);
                if (position == 0){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_aceh,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 1){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_bali,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 2){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_bangka_belitung,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 3){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_banten,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 4){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_bengkulu,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 5){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_yogyakarta,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 6){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_jakarta,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 7){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_gorontalo,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 8){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_jambi,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 9){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_jawa_barat,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 10){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_jawa_tengah,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 11){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_jawa_timur,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 12){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_kalimantan_barat,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 13){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_kalimantan_selatan,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 14){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_kalimantan_tengah,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 15){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_kalimantan_timur,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 16){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_kepulauan_riau,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 17){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_lampung,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 18){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_maluku,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 19){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_maluku_utara,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 20){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_ntb,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 21){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_ntt,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 22){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getApplication(), R.array.prov_papua,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 23){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_papua_barat,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 24){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getApplication(), R.array.prov_riau,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 25){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sulawesi_barat,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 26){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sulawesi_selatan,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 27){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sulawesi_tengah,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 28){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sulawesi_tenggara,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 29){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sulawesi_utara,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 30){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sumatera_barat,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 31){
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter
                            .createFromResource(getBaseContext(), R.array.prov_sumatera_selatan,
                                    android.R.layout.simple_spinner_item);
                    spnKab.setAdapter(adapter);
                } else if (position == 32){
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

    private static final int REQUEST_CHOOSER = 1234;
    private static final int REQUEST_CHOOSER_CV = 12345;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPilihFilePhoto:

                Intent getContentIntent = FileUtils.createGetContentIntent();
                Intent intent = Intent
                        .createChooser(getContentIntent, "Pilih file");
                startActivityForResult(intent, REQUEST_CHOOSER);

                break;

            case R.id.buttonPilihFileCv:


                Intent getContentIntent1 = FileUtils.createGetContentIntent();
                Intent intent1 = Intent
                        .createChooser(getContentIntent1, "Pilih file");
                startActivityForResult(intent1, REQUEST_CHOOSER_CV);

                break;

            case R.id.buttonUploadPencaker:

                final String path = txtViewPathPhoto.getText().toString();
                final String path1 = txtViewPathCV.getText().toString();

                if (path != null && FileUtils.isLocal(path)) {
                    File file = new File(path);
                    File file1 = new File(path1);
                }

                if ("".equals(txtUsername.getText().toString())) {
                    txtUsername.setError("Username Tidak Boleh Kosong");
                    txtUsername.requestFocus();
                    return;

                }

                dialog = ProgressDialog.show(FormPencaker.this, "",
                        "Mohon Tunggu Sebentar, Sedang Proses Mengirim Data Anda Ke Database BLK Samarinda...", true);

                new Thread(new Runnable() {
                    public void run() {
                        runOnUiThread(new Runnable() {
                            public void run() {
//                                textViewKeterangan.setText("Proses Upload");
                            }
                        });

                        int response = 0;
                        int responseFile = 0;
//
                        String username = txtUsername.getText().toString();

                        String password = txtPassword1.getText().toString();

                        String noktp = txtNoktp.getText().toString();

                        String nama_pencari = txtNamaPencariKerja.getText().toString();


                        int selectedId = radioSexGroup.getCheckedRadioButtonId();
                        radioSexButton = (RadioButton) findViewById(selectedId);
                        String jk_pencari = radioSexButton.getText().toString();

                        String ttl_pencari = txtTtlPencari.getText().toString();


                        String provinsi = spnProvinsi.getSelectedItem().toString();

                        String kab = spnKab.getSelectedItem().toString();

                        String asal = txtAsalSekolah.getText().toString();

                        String pendidikan = txtPendidikan.getText().toString();

                        String ketrampilan = txtKetrampilan.getText().toString();

                        String pengalaman = txtPengalaman.getText().toString();

                        int selectedAlumni = radioAlumniGroup.getCheckedRadioButtonId();
                        radioAlumniButton = (RadioButton)findViewById(selectedAlumni);
                        String alumni = radioAlumniButton.getText().toString();


                        response = uploadFile(path, username, password, noktp, nama_pencari,
                                jk_pencari, ttl_pencari, provinsi, kab, asal,
                                pendidikan, ketrampilan, pengalaman, alumni);
                        System.out.println("RES : " + response);
//
                        responseFile = uploadFileCV(path1);
                        System.out.println("RES : " + responseFile);
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
                    txtViewPathPhoto.setText(path);

                }
                break;

            case REQUEST_CHOOSER_CV:
                if (resultCode == RESULT_OK) {


                    final Uri uri1 = data.getData();


                    final String path1 = FileUtils.getPath(this, uri1);
                    txtViewPathCV.setText(path1);
                }
                break;
        }
    }

    int serverResponseCode = 0;
    ProgressDialog dialog = null;

    public int uploadFile(String sourceFileUri, String username, String password,
                          String noktp, String nama_pencari, String jk_pencari, String ttl_pencari,
                          String provinsi, String kab, String asal, String pendidikan,
                          String ketrampilan, String pengalaman, String alumni) {

        // ip komputer server
        String upLoadServerUri = "http://aksesblk-samarinda.com/aksesblksamarinda/blk/pencariNew.php";
        String fileName = sourceFileUri;
//        String fileNameCV = sourceFileUri1;

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize, bytesRead1, bufferSizeCV,  bytesAvailableCV;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(sourceFileUri);
//        File sourceFileCv = new File(sourceFileUri1);
        if (!sourceFile.isFile()) {
            Log.e("uploadFile", "Source File Does not exist");
            return 0;
        }

//        if (!sourceFileCv.isFile()) {
//            Log.e("uploadFile", "Source File Does not exist");
//            return 0;
//        }
        try {
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
//            FileInputStream fileInputStreamCv = new FileInputStream(sourceFileCv);
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
//            conn.setRequestProperty("uploaded_file_cv", fileNameCV);
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
            dos.writeBytes("Content-Disposition: form-data; name=\"noktp\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(noktp);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"nama_pencari\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(nama_pencari);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"jk_pencari\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(jk_pencari);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"ttl_pencari\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(ttl_pencari);
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

            dos.writeBytes("Content-Disposition: form-data; name=\"asal\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(asal);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"pendidikan\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(pendidikan);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"ketrampilan\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(ketrampilan);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"pengalaman\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(pengalaman);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"alumni\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(alumni);
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

//            dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file_cv\";filename=\""
//                    + fileName + "\"" + lineEnd);
//            dos.writeBytes(lineEnd);
//            // create a buffer of maximum size
//            bytesAvailable = fileInputStream.available();
//            bufferSize = Math.min(bytesAvailable, maxBufferSize);
//            buffer = new byte[bufferSize];
//            // read file and write it into form...
//            bytesRead1 = fileInputStreamCv.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }

//            while (bytesRead1 > 0) {
//                dos.write(buffer, 0, bufferSize);
//                bytesAvailable = fileInputStreamCv.available();
//                bufferSize = Math.min(bytesAvailable, maxBufferSize);
//                bytesRead1 = fileInputStreamCv.read(buffer, 0, bufferSize);
//            }

            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            serverResponseCode = conn.getResponseCode();
            String serverResponseMessage = conn.getResponseMessage();

            if (serverResponseCode == 200) {
                runOnUiThread(new Runnable() {
                    public void run() {
//                        textViewKeterangan.setText("Upload Berhasil");
                        Toast.makeText(FormPencaker.this, "Data Anda Telah dikirim ke Database BLK Samarinda",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

            // close the streams //
            fileInputStream.close();
//            fileInputStreamCv.close();
            dos.flush();
            dos.close();

        } catch (MalformedURLException ex) {
            dialog.dismiss();
            ex.printStackTrace();
            Toast.makeText(FormPencaker.this, "MalformedURLException",
                    Toast.LENGTH_SHORT).show();
            Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
        } catch (Exception e) {
            dialog.dismiss();
            e.printStackTrace();
            Toast.makeText(FormPencaker.this, "Exception : " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            Log.e("Upload ","Exception : " + e.getMessage(), e);
        }
        dialog.dismiss();
        finish();
        return serverResponseCode;

    }

    public int uploadFileCV(String sourceFileUriCV) {

        // ip komputer server
        String upLoadServerUri = "http://aksesblk-samarinda.com/aksesblksamarinda/blk/pencariFile.php";
        String fileName = sourceFileUriCV;
//        String fileNameCV = sourceFileUri1;

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize, bytesRead1, bufferSizeCV,  bytesAvailableCV;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(sourceFileUriCV);
//        File sourceFileCv = new File(sourceFileUri1);
        if (!sourceFile.isFile()) {
            Log.e("uploadFile", "Source File Does not exist");
            return 0;
        }

//        if (!sourceFileCv.isFile()) {
//            Log.e("uploadFile", "Source File Does not exist");
//            return 0;
//        }
        try {
            FileInputStream fileInputStream = new FileInputStream(sourceFileUriCV);
//            FileInputStream fileInputStreamCv = new FileInputStream(sourceFileCv);
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


            dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                    + fileName + "\"" + lineEnd);
            dos.writeBytes(lineEnd);
            // create a buffer of maximum size
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];
            // read file and write it into form...
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

//            dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file_cv\";filename=\""
//                    + fileName + "\"" + lineEnd);
//            dos.writeBytes(lineEnd);
//            // create a buffer of maximum size
//            bytesAvailable = fileInputStream.available();
//            bufferSize = Math.min(bytesAvailable, maxBufferSize);
//            buffer = new byte[bufferSize];
//            // read file and write it into form...
//            bytesRead1 = fileInputStreamCv.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }

//            while (bytesRead1 > 0) {
//                dos.write(buffer, 0, bufferSize);
//                bytesAvailable = fileInputStreamCv.available();
//                bufferSize = Math.min(bytesAvailable, maxBufferSize);
//                bytesRead1 = fileInputStreamCv.read(buffer, 0, bufferSize);
//            }

            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            serverResponseCode = conn.getResponseCode();
            String serverResponseMessage = conn.getResponseMessage();

            if (serverResponseCode == 200) {
                runOnUiThread(new Runnable() {
                    public void run() {
//                        textViewKeterangan.setText("Upload Berhasil");
                        Toast.makeText(FormPencaker.this, "Data Anda Telah dikirim ke Database BLK Samarinda",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

            // close the streams //
            fileInputStream.close();
//            fileInputStreamCv.close();
            dos.flush();
            dos.close();

        } catch (MalformedURLException ex) {
            dialog.dismiss();
            ex.printStackTrace();
            Toast.makeText(FormPencaker.this, "MalformedURLException",
                    Toast.LENGTH_SHORT).show();
            Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
        } catch (Exception e) {
            dialog.dismiss();
            e.printStackTrace();
            Toast.makeText(FormPencaker.this, "Exception : " + e.getMessage(),
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
