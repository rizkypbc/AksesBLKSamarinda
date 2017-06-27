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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.sampleblki.Api.AccessServiceAPI;
import com.ipaulpro.afilechooser.utils.FileUtils;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ASUS on 16/05/2017.
 */

public class FormPerusahaan extends AppCompatActivity {

    private EditText txtUsernamePerusahaan;
    private EditText txtPassword1Perusahaan;
    private EditText txtPassword2Perusahaan;
    private EditText txtNamaPerusahaan, txtAlamatPerusahaan, txtTelepon,
            txtFax, txtEmail, txtWebsite, txtProfil, txtPj, txtJabatan,
            txtHp, txtKet, txtSektor, txtBidang, txtJenis;
    private TextView txtPathLogo;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private Button buttonPilihFileLogo;
    private Spinner spnJenis, spnSektor, spnBidang, spnProvinsi, spnKab;
    private ProgressDialog m_ProgressDialog;
    private AccessServiceAPI m_AccessServiceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_perusahaan);
        requestStoragePermission();
        init();
        txtUsernamePerusahaan = (EditText) findViewById(R.id.txt_username_perusahaan);
        txtPassword1Perusahaan = (EditText) findViewById(R.id.txt_pwd_perusahaan);
        txtPassword2Perusahaan = (EditText) findViewById(R.id.txt_konfirmasi_perusahaan);
        txtNamaPerusahaan = (EditText) findViewById(R.id.txt_nama_perusahaan);
//        spnJenis = (Spinner) findViewById(R.id.spnJenis);
        txtJenis = (EditText) findViewById(R.id.txt_jenis);
//        spnSektor = (Spinner) findViewById(R.id.spnSektor);
//        spnBidang = (Spinner) findViewById(R.id.spnBidang);
        txtAlamatPerusahaan = (EditText) findViewById(R.id.txt_alamat_perusahaan);
        spnProvinsi = (Spinner) findViewById(R.id.spnProvinsi);
        spnKab = (Spinner) findViewById(R.id.spnKabupaten);
        txtTelepon = (EditText) findViewById(R.id.txt_telp_perusahaan);
        txtFax = (EditText) findViewById(R.id.txt_fax_perusahaan);
        txtEmail = (EditText) findViewById(R.id.txt_email_perusahaan);
        txtWebsite = (EditText) findViewById(R.id.txt_web_perusahaan);
        txtProfil = (EditText) findViewById(R.id.txt_profil_perusahaan);
        txtPj = (EditText) findViewById(R.id.txt_pj);
        txtJabatan = (EditText) findViewById(R.id.txt_jabatan);
        txtHp = (EditText) findViewById(R.id.txt_telp_hp);
        txtKet = (EditText) findViewById(R.id.txt_ket_lain);
        txtSektor = (EditText) findViewById(R.id.txt_sektor);
        txtBidang = (EditText) findViewById(R.id.txt_bidang);
//        txtAlamatPerusahaan = (EditText) findViewById(R.id.txt_alamat_perusahaan);
        m_AccessServiceAPI = new AccessServiceAPI();

        spnProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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

//        ArrayAdapter dataJenis = ArrayAdapter.createFromResource(this, R.array.kejuruan, android.R.layout.simple_spinner_item);
//        dataJenis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spnJenis.setAdapter(dataJenis);
//
//
//        ArrayAdapter dataSektor = ArrayAdapter.createFromResource(this, R.array.sub_kejuruan, android.R.layout.simple_spinner_item);
//        dataSektor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spnSektor.setAdapter(dataSektor);
//
//        ArrayAdapter dataBidang = ArrayAdapter.createFromResource(this, R.array.jurusan, android.R.layout.simple_spinner_item);
//        dataBidang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spnBidang.setAdapter(dataBidang);
//
//        ArrayAdapter dataProvinsi = ArrayAdapter.createFromResource(this, R.array.provinsi, android.R.layout.simple_spinner_item);
//        dataProvinsi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spnProvinsi.setAdapter(dataProvinsi);
//
//
//        ArrayAdapter dataKab = ArrayAdapter.createFromResource(this, R.array.kabupaten, android.R.layout.simple_spinner_item);
//        dataKab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spnKab.setAdapter(dataKab);
    }

    protected void init() {
        txtPathLogo = (TextView) findViewById(R.id.textViewPathLogo);
        txtPathLogo.setText("");

        buttonPilihFileLogo = (Button) findViewById(R.id.buttonPilihLogoPerusahaan);
        buttonPilihFileLogo.setOnClickListener(new View.OnClickListener() {
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
                    txtPathLogo.setText(path);
                }

                break;
        }
    }

    public void btnPerusahaan_Click(View v) {

        if ("".equals(txtUsernamePerusahaan.getText().toString())) {
            txtUsernamePerusahaan.setError("Username Tidak Boleh Kosong");
            return;
        }

        if ("".equals(txtNamaPerusahaan.getText().toString())) {
            txtNamaPerusahaan.setError("Nama Perusahaan Tidak Boleh Kosong");
            return;
        }

        if ("".equals(txtTelepon.getText().toString())) {
            txtTelepon.setError("Telepon Tidak Boleh Kosong");
            return;
        }

        if (txtPassword1Perusahaan.getText().toString().equals(txtPassword2Perusahaan.getText().toString())) {

            new FormPerusahaan.TaskPerusahaan().execute(txtUsernamePerusahaan.getText().toString(),
                    txtPassword1Perusahaan.getText().toString(), txtNamaPerusahaan.getText().toString(),
                    txtJenis.getText().toString(), txtSektor.getText().toString(),
                    txtBidang.getText().toString(), txtAlamatPerusahaan.getText().toString(),
                    spnProvinsi.getSelectedItem().toString(), spnKab.getSelectedItem().toString(),
                    txtTelepon.getText().toString(), txtFax.getText().toString(), txtEmail.getText().toString(),
                    txtWebsite.getText().toString(), txtProfil.getText().toString(), txtPathLogo.getText().toString(),
                    txtPj.getText().toString(), txtJabatan.getText().toString(), txtHp.getText().toString(), txtKet.getText().toString());
        } else {
            txtPassword2Perusahaan.setError("Konfirmasi Password tidak sama");
        }
    }

    public class TaskPerusahaan extends AsyncTask<String, Void, Integer> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            m_ProgressDialog = ProgressDialog.show(FormPerusahaan.this, "Harap Tunggu", "Sedang Berlangsung Proses Registrasi", true);
        }

        @Override
        protected Integer doInBackground(String... params) {
            Map<String, String> postParam = new HashMap<>();
            postParam.put("action", "add");
            postParam.put("username", params[0]);
            postParam.put("password", params[1]);
            postParam.put("nama_perusahaan", params[2]);
            postParam.put("jenis", params[3]);
            postParam.put("sektor", params[4]);
            postParam.put("bidang", params[5]);
            postParam.put("alamat_perusahaan", params[6]);
            postParam.put("provinsi", params[7]);
            postParam.put("kab", params[8]);
            postParam.put("telepon", params[9]);
            postParam.put("fax", params[10]);
            postParam.put("email", params[11]);
            postParam.put("website", params[12]);
            postParam.put("profil_perusahaan", params[13]);
            postParam.put("logo", params[14]);
            postParam.put("pj", params[15]);
            postParam.put("jabatan", params[16]);
            postParam.put("hp", params[17]);
            postParam.put("ket_lain", params[18]);

            try {

                String jsonString = m_AccessServiceAPI.getJSONStringWithParam_POST(Common.SERVICE_API_URL_PERUSAHAAN, postParam);
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
                Toast.makeText(FormPerusahaan.this, "Registrasi Berhasil", Toast.LENGTH_LONG).show();
                Intent i = new Intent();
                i.putExtra("username", txtUsernamePerusahaan.getText().toString());
                i.putExtra("password", txtPassword1Perusahaan.getText().toString());
                i.putExtra("nama_perusahaan", txtNamaPerusahaan.getText().toString());
//                i.putExtra("jenis", spnJenis.getSelectedItem().toString());
//                i.putExtra("sektor", spnSektor.getSelectedItem().toString());
//                i.putExtra("bidang", spnBidang.getSelectedItem().toString());
                i.putExtra("jenis", txtJenis.getText().toString());
                i.putExtra("sektor", txtSektor.getText().toString());
                i.putExtra("bidang", txtBidang.getText().toString());
                i.putExtra("alamat_perusahaan", txtAlamatPerusahaan.getText().toString());
                i.putExtra("provinsi", spnProvinsi.getSelectedItem().toString());
                i.putExtra("kab", spnKab.getSelectedItem().toString());
                i.putExtra("telepon", txtTelepon.getText().toString());
                i.putExtra("fax", txtFax.getText().toString());
                i.putExtra("email", txtEmail.getText().toString());
                i.putExtra("website", txtWebsite.getText().toString());
                i.putExtra("profil_perusahaan", txtProfil.getText().toString());
                i.putExtra("logo", txtPathLogo.getText().toString());
                i.putExtra("pj", txtPj.getText().toString());
                i.putExtra("jabatan", txtJabatan.getText().toString());
                i.putExtra("hp", txtHp.getText().toString());
                i.putExtra("ket_lain", txtKet.getText().toString());
                setResult(1, i);
                finish();
            } else if (integer == Common.RESULT_USER_EXITS) {
                Toast.makeText(FormPerusahaan.this, "Username Telah digunakan", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(FormPerusahaan.this, "Registrasi Gagal", Toast.LENGTH_LONG).show();
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
