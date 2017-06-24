package com.example.asus.sampleblki;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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

import com.example.asus.sampleblki.Api.AccessServiceAPI;
import com.ipaulpro.afilechooser.utils.FileUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ASUS on 16/05/2017.
 */

public class FormPencariKerja extends AppCompatActivity {

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
    private Button buttonPilihFilePhoto, buttonPilihFileCV;
    private TextView txtViewPhoto, txtViewCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pencari_kerja);
        requestStoragePermission();
        init();
        txtUsername = (EditText) findViewById(R.id.txt_username);
        txtPassword1 = (EditText) findViewById(R.id.txt_pwd);
        txtPassword2 = (EditText) findViewById(R.id.txt_konfirmasi);
        txtNoktp = (EditText) findViewById(R.id.txt_noktp);
        txtNamaPencariKerja = (EditText) findViewById(R.id.txt_nama_pencari_kerja);
//        txtJkPencari = (EditText) findViewById(R.id.txt_jk_pencari_kerja);
        txtTtlPencari = (EditText) findViewById(R.id.txt_ttl_pencari_kerja);
        txtAsalSekolah = (EditText) findViewById(R.id.txt_asal_sekolah);
        txtPendidikan = (EditText)findViewById(R.id.txt_pendidikan);
        txtKetrampilan = (EditText)findViewById(R.id.txt_ketrampilan);
        txtPengalaman = (EditText)findViewById(R.id.txt_pengalaman);
        m_AccessServiceAPI = new AccessServiceAPI();
        spnProvinsi = (Spinner)findViewById(R.id.spnProvinsiKerja);
        spnKab = (Spinner)findViewById(R.id.spnKabupatenKerja);
        radioAlumniGroup = (RadioGroup)findViewById(R.id.radioAlumni);
        radioSexGroup = (RadioGroup)findViewById(R.id.radioJenisKelamin);


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


//        ArrayAdapter dataProvinsi = ArrayAdapter.createFromResource(this, R.array.provinsi, android.R.layout.simple_spinner_item);
//        dataProvinsi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spnProvinsi.setAdapter(dataProvinsi);
//
//
//        ArrayAdapter dataKab = ArrayAdapter.createFromResource(this, R.array.kabupaten, android.R.layout.simple_spinner_item);
//        dataKab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spnKab.setAdapter(dataKab);
    }

    protected void init(){
        txtViewPhoto = (TextView) findViewById(R.id.textViewPathPhoto);
        txtViewPhoto.setText("");

        buttonPilihFilePhoto = (Button) findViewById(R.id.buttonPilihFilePhoto);
        buttonPilihFilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getContentIntent = FileUtils.createGetContentIntent();
                Intent intent = Intent
                        .createChooser(getContentIntent, "Pilih file");
                startActivityForResult(intent, REQUEST_CHOOSER);
            }
        });

        txtViewCV = (TextView) findViewById(R.id.textViewPathCv);
        txtViewCV.setText("");

        buttonPilihFileCV = (Button) findViewById(R.id.buttonPilihFileCv);
        buttonPilihFileCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getContentIntent = FileUtils.createGetContentIntent();
                Intent intent = Intent
                        .createChooser(getContentIntent, "Pilih file");
                startActivityForResult(intent, REQUEST_CHOOSER_CV);
            }
        });

    }

    private static final int REQUEST_CHOOSER = 1234;

    private static final int REQUEST_CHOOSER_CV = 12345;

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case REQUEST_CHOOSER:
                if (resultCode == RESULT_OK) {

                    final Uri uri = data.getData();
                    final String path = FileUtils.getPath(this, uri);
                    txtViewPhoto.setText(path);
                }

                break;


            case REQUEST_CHOOSER_CV:
                if (resultCode == RESULT_OK){

                    final Uri uri = data.getData();
                    final String path = FileUtils.getPath(this, uri);
                    txtViewCV.setText(path);
                }
                break;
        }
    }

    public void btnPencariKerja_Click(View v) {

        if ("".equals(txtUsername.getText().toString())) {
            txtUsername.setError("Username Tidak Boleh Kosong");
            return;
        }

        if ("".equals(txtNamaPencariKerja.getText().toString())) {
            txtNamaPencariKerja.setError("Nama Tidak Boleh Kosong");
            return;
        }

        if ("".equals(txtNoktp.getText().toString())) {
            txtNoktp.setError("No KTP Tidak Boleh Kosong");
            return;
        }


        int selectedJenis = radioSexGroup.getCheckedRadioButtonId();
        radioSexButton = (RadioButton) findViewById(selectedJenis);

        int selectedAlumni = radioAlumniGroup.getCheckedRadioButtonId();
        radioAlumniButton = (RadioButton)findViewById(selectedAlumni);

        if (txtPassword1.getText().toString().equals(txtPassword2.getText().toString())) {

            new TaskPencariKerja().execute(txtUsername.getText().toString(), txtPassword1.getText().toString(),
                    txtNoktp.getText().toString(), txtNamaPencariKerja.getText().toString(),
                    radioSexButton.getText().toString(), txtTtlPencari.getText().toString(),
                    spnProvinsi.getSelectedItem().toString(), spnKab.getSelectedItem().toString(),
                    txtAsalSekolah.getText().toString(), txtPendidikan.getText().toString(),
                    txtKetrampilan.getText().toString(), txtPengalaman.getText().toString(),
                    radioAlumniButton.getText().toString(), txtViewPhoto.getText().toString(),
                    txtViewCV.getText().toString());
        } else {
            txtPassword2.setError("Konfirmasi Password tidak sama");
            return;
        }
    }

    public class TaskPencariKerja extends AsyncTask<String, Void, Integer> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            m_ProgressDialog = ProgressDialog.show(FormPencariKerja.this, "Harap Tunggu", "Sedang Berlangsung Proses Registrasi", true);
        }

        @Override
        protected Integer doInBackground(String... params) {
            Map<String, String> postParam = new HashMap<>();
            postParam.put("action", "add");
            postParam.put("username", params[0]);
            postParam.put("password", params[1]);
            postParam.put("noktp", params[2]);
            postParam.put("nama_pencari", params[3]);
            postParam.put("jk_pencari", params[4]);
            postParam.put("ttl_pencari", params[5]);
            postParam.put("provinsi", params[6]);
            postParam.put("kab", params[7]);
            postParam.put("asal", params[8]);
            postParam.put("pendidikan", params[9]);
            postParam.put("ketrampilan", params[10]);
            postParam.put("pengalaman", params[11]);
            postParam.put("alumni", params[12]);
            postParam.put("urlphoto", params[13]);
            postParam.put("urlcv", params[14]);

            try {

                String jsonString = m_AccessServiceAPI.getJSONStringWithParam_POST(Common.SERVICE_API_URL_KERJA, postParam);
                JSONObject jsonObject = new JSONObject(jsonString);
                return jsonObject.getInt("result");
            } catch (Exception e) {
                e.printStackTrace();
                return Common.RESULT_ERROR;
            }
        }


        public void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            m_ProgressDialog.dismiss();
            if (integer == Common.RESULT_SUCCESS) {
                Toast.makeText(FormPencariKerja.this, "Registrasi Berhasil", Toast.LENGTH_LONG).show();
                Intent i = new Intent();
                i.putExtra("username", txtUsername.getText().toString());
                i.putExtra("password", txtPassword1.getText().toString());
                i.putExtra("noktp", txtNoktp.getText().toString());
                i.putExtra("nama_pencari", txtNamaPencariKerja.getText().toString());
                i.putExtra("jk_pencari", radioSexButton.getText().toString());
                i.putExtra("ttl_pencari", txtTtlPencari.getText().toString());
                i.putExtra("provinsi", spnProvinsi.getSelectedItem().toString());
                i.putExtra("kab", spnKab.getSelectedItem().toString());
                i.putExtra("asal", txtAsalSekolah.getText().toString());
                i.putExtra("pendidikan", txtPendidikan.getText().toString());
                i.putExtra("ketrampilan", txtKetrampilan.getText().toString());
                i.putExtra("pengalaman", txtPengalaman.getText().toString());
                i.putExtra("alumni", radioAlumniButton.getText().toString());
                i.putExtra("urlphoto", txtViewPhoto.getText().toString());
                i.putExtra("urlcv", txtViewCV.getText().toString());

                setResult(1, i);
                finish();
            } else if (integer == Common.RESULT_USER_EXITS) {
                Toast.makeText(FormPencariKerja.this, "Username Telah digunakan", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(FormPencariKerja.this, "Registrasi Gagal", Toast.LENGTH_LONG).show();
            }
        }
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

