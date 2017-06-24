package com.example.asus.sampleblki;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
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

import com.example.asus.sampleblki.Api.AccessServiceAPI;
import com.ipaulpro.afilechooser.utils.FileUtils;

import static com.example.asus.sampleblki.R.id.parent;


import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 09/05/2017.
 */

public class FormPelatihan extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
    private static final int STORAGE_PERMISSION_CODE = 123;
    private Button buttonPilihFile;
    private TextView textViewPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pelatihan);
        requestStoragePermission();
        init();
//        txtId = (EditText)findViewById(R.id.txt_id);
        txtNama = (EditText) findViewById(R.id.txt_nama);
//        txtJK = (EditText) findViewById(R.id.txt_jk);
        txtTTL = (EditText) findViewById(R.id.txt_ttl);
        txtAlamat = (EditText) findViewById(R.id.txt_alamat);
//        txtProvinsi = (EditText) findViewById(R.id.txt_provinsi);
        spnProvinsi = (Spinner) findViewById(R.id.spnProvinsi);
//        txtKab = (EditText) findViewById(R.id.txt_kab_kota);
        spnKabupaten = (Spinner) findViewById(R.id.spnKabupaten);
        txtNoTelp = (EditText) findViewById(R.id.txt_notelp);
        txtEmail = (EditText) findViewById(R.id.txt_email);
//        txtAgama = (EditText) findViewById(R.id.txt_agama);
        spnAgama = (Spinner) findViewById(R.id.spnAgama);
//        txtPendidikan = (EditText) findViewById(R.id.txt_pendidikan);
        spnPendidikan = (Spinner) findViewById(R.id.spnPendidikan);
//        txtJurusan = (EditText) findViewById(R.id.txt_jurusan);
        spnJurusan = (Spinner) findViewById(R.id.spnJurusan);
        txtAsal = (EditText) findViewById(R.id.txt_asal_sekolah);
//        txtKejuruan = (EditText) findViewById(R.id.txt_kejuruan);
        spnKejuruan = (Spinner) findViewById(R.id.spnKejuruan);
//        txtSubKejuruan = (EditText) findViewById(R.id.txt_sub_kejuruan);
        spnSubKejuruan = (Spinner) findViewById(R.id.spnSubKejuruan);
        spnProgram = (Spinner) findViewById(R.id.spnProgram);
//        txtProgram = (EditText) findViewById(R.id.txt_program);
//        txtUrlPhoto = (EditText) findViewById(R.id.txt_url_photo);
        m_AccessServiceAPI = new AccessServiceAPI();

        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);

//        spnProgram.setOnItemSelectedListener(this);
        spnProvinsi.setOnItemSelectedListener(this);
//        spnAgama.setOnItemSelectedListener(this);
//        spnKabupaten.setOnItemSelectedListener(this);
//        spnPendidikan.setOnItemSelectedListener(this);
//        spnJurusan.setOnItemSelectedListener(this);
//        spnKejuruan.setOnItemSelectedListener(this);
//        spnSubKejuruan.setOnItemSelectedListener(this);

//        ArrayList categories = new ArrayList();
//        categories.add("Automobile");
//        categories.add("Business Services");
//        categories.add("Computers");
//        categories.add("Education");
//        categories.add("Personal");
//        categories.add("Travel");




//        ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories);
        ArrayAdapter dataProgram = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);
        dataProgram.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnProgram.setAdapter(dataProgram);

//        ArrayAdapter dataProvinsi = ArrayAdapter.createFromResource(this, R.array.provinsi, android.R.layout.simple_spinner_item);
//        dataProvinsi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spnProvinsi.setAdapter(dataProvinsi);

        ArrayAdapter dataAgama = ArrayAdapter.createFromResource(this, R.array.agama, android.R.layout.simple_spinner_item);
        dataAgama.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnAgama.setAdapter(dataAgama);

//        ArrayAdapter dataKabupaten = ArrayAdapter.createFromResource(this, R.array.kabupaten, android.R.layout.simple_spinner_item);
//        dataKabupaten.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spnKabupaten.setAdapter(dataKabupaten);

        ArrayAdapter dataPendidikan = ArrayAdapter.createFromResource(this, R.array.pendidikan, android.R.layout.simple_spinner_item);
        dataPendidikan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPendidikan.setAdapter(dataPendidikan);

        ArrayAdapter dataJurusan = ArrayAdapter.createFromResource(this, R.array.jurusan, android.R.layout.simple_spinner_item);
        dataJurusan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnJurusan.setAdapter(dataJurusan);

        ArrayAdapter dataKejuruan = ArrayAdapter.createFromResource(this, R.array.kejuruan, android.R.layout.simple_spinner_item);
        dataKejuruan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnKejuruan.setAdapter(dataKejuruan);

        ArrayAdapter dataSubKejuruan = ArrayAdapter.createFromResource(this, R.array.sub_kejuruan, android.R.layout.simple_spinner_item);
        dataSubKejuruan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSubKejuruan.setAdapter(dataSubKejuruan);
    }

    protected void init(){
        textViewPath = (TextView) findViewById(R.id.textViewPath);
        textViewPath.setText("");

        buttonPilihFile = (Button) findViewById(R.id.buttonPilihFile);
        buttonPilihFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getContentIntent = FileUtils.createGetContentIntent();
                Intent intent = Intent
                        .createChooser(getContentIntent, "Pilih file");
                startActivityForResult(intent, REQUEST_CHOOSER);
            }
        });

    }

    private static final int REQUEST_CHOOSER = 1234;

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



    public void btnRegister_Click(View v) {
        //Validate Input
        if ("".equals(txtNama.getText().toString())) {
            txtNama.setError("Nama Tidak Boleh Kosong");
            return;

        }

        if ("".equals(txtTTL.getText().toString())) {
            txtTTL.setError("Tempat Tanggal Lahir Tidak Boleh Kosong");
            return;

        }

        if ("".equals(txtNoTelp.getText().toString())) {
            txtNoTelp.setError("No Telp Tidak Boleh Kosong");
            return;

        }

        int selectedId = radioSexGroup.getCheckedRadioButtonId();

        radioSexButton = (RadioButton) findViewById(selectedId);

        new TaskRegister().execute(txtNama.getText().toString(),
                radioSexButton.getText().toString(), txtTTL.getText().toString(), txtAlamat.getText().toString(),
                spnProgram.getSelectedItem().toString(), spnKabupaten.getSelectedItem().toString(), txtNoTelp.getText().toString(),
                txtEmail.getText().toString(), spnAgama.getSelectedItem().toString(), spnPendidikan.getSelectedItem().toString(),
                spnJurusan.getSelectedItem().toString(), txtAsal.getText().toString(), spnKejuruan.getSelectedItem().toString(),
                spnSubKejuruan.getSelectedItem().toString(), spnProgram.getSelectedItem().toString(), textViewPath.getText().toString());

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        parent.getItemAtPosition(position);
        if (position == 0){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_aceh,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 1){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_bali,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 2){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_bangka_belitung,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 3){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_banten,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 4){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_bengkulu,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 5){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_yogyakarta,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 6){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_jakarta,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 7){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_gorontalo,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 8){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_jambi,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 9){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_jawa_barat,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 10){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_jawa_tengah,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 11){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_jawa_timur,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 12){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_kalimantan_barat,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 13){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_kalimantan_selatan,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 14){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_kalimantan_tengah,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 15){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_kalimantan_timur,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 16){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_kepulauan_riau,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 17){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_lampung,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 18){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_maluku,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 19){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_maluku_utara,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 20){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_ntb,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 21){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_ntt,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 22){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_papua,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 23){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_papua_barat,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 24){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_riau,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 25){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_sulawesi_barat,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 26){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_sulawesi_selatan,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 27){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_sulawesi_tengah,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 28){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_sulawesi_tenggara,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 29){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_sulawesi_utara,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 30){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_sumatera_barat,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 31){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_sumatera_selatan,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        } else if (position == 32){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.prov_sumatera_utara,
                            android.R.layout.simple_spinner_item);
            spnKabupaten.setAdapter(adapter);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public class TaskRegister extends AsyncTask<String, Void, Integer> {


        protected void onPreExecute() {
            super.onPreExecute();
            m_ProgressDialog = ProgressDialog.show(FormPelatihan.this, "Please Wait", "Registration processing...", true);
        }


        @Override
        protected Integer doInBackground(String... params) {
            Map<String, String> postParam = new HashMap<>();
            postParam.put("action", "add");
//            postParam.put("id", params[0]);
            postParam.put("nama", params[0]);
            postParam.put("jk", params[1]);
            postParam.put("ttl", params[2]);
            postParam.put("alamat", params[3]);
            postParam.put("provinsi", params[4]);
            postParam.put("kab_kota", params[5]);
            postParam.put("notelp", params[6]);
            postParam.put("email", params[7]);
            postParam.put("agama", params[8]);
            postParam.put("pendidikan", params[9]);
            postParam.put("jurusan", params[10]);
            postParam.put("asal_sekolah", params[11]);
            postParam.put("kejuruan", params[12]);
            postParam.put("sub_kejuruan", params[13]);
            postParam.put("program", params[14]);
            postParam.put("urlphoto", params[15]);
            try {

                String jsonString = m_AccessServiceAPI.getJSONStringWithParam_POST(Common.SERVICE_API_URL_PELATIHAN, postParam);
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
                Toast.makeText(FormPelatihan.this, "Registration success", Toast.LENGTH_LONG).show();
                Intent i = new Intent();
//                i.putExtra("id", txtId.getText().toString());
                i.putExtra("nama", txtNama.getText().toString());
//                i.putExtra("jjk", txtJK.getText().toString());
                i.putExtra("jjk", radioSexButton.getText().toString());
                i.putExtra("ttl", txtTTL.getText().toString());
                i.putExtra("alamat", txtAlamat.getText().toString());
//                i.putExtra("provinsi", txtProvinsi.getText().toString());
                i.putExtra("provinsi", spnProvinsi.getSelectedItem().toString());
//                i.putExtra("kab_kota", txtKab.getText().toString());
                i.putExtra("kab_kota", spnKabupaten.getSelectedItem().toString());
                i.putExtra("notelp", txtNoTelp.getText().toString());
                i.putExtra("email", txtEmail.getText().toString());
//                i.putExtra("agama", txtAgama.getText().toString());
                i.putExtra("agama", spnAgama.getSelectedItem().toString());
//                i.putExtra("pendidikan", txtPendidikan.getText().toString());
                i.putExtra("pendidikan", spnPendidikan.getSelectedItem().toString());
//                i.putExtra("jurusan", txtJurusan.getText().toString());
                i.putExtra("jurusan", spnJurusan.getSelectedItem().toString());
                i.putExtra("asal_sekolah", txtAsal.getText().toString());
//                i.putExtra("kejuruan", txtKejuruan.getText().toString());
                i.putExtra("kejuruan", spnKejuruan.getSelectedItem().toString());
//                i.putExtra("sub_kejuruan", txtSubKejuruan.getText().toString());
                i.putExtra("sub_kejuruan", spnSubKejuruan.getSelectedItem().toString());
//                i.putExtra("program", txtProgram.getText().toString());
                i.putExtra("program", spnProgram.getSelectedItem().toString());
//                i.putExtra("urlphoto", txtUrlPhoto.getText().toString());
                i.putExtra("urlphoto", textViewPath.getText().toString());
                setResult(1, i);
                finish();
            } else {
                Toast.makeText(FormPelatihan.this, "Registration fail!", Toast.LENGTH_LONG).show();

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
