package com.akses.blk.samarinda;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
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
import com.akses.blk.samarinda.Model.Kejuruan;
import com.akses.blk.samarinda.Model.SubKejuruan;
import com.akses.blk.samarinda.Util.Handler;
import com.ipaulpro.afilechooser.utils.FileUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
            spnKabupaten, spnPendidikan, spnIdKejuruan, spnKejuruan, spnSubKejuruan;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private Button buttonPilihFile, buttonUpload;
    private TextView textViewPath;

    private ArrayList<Kejuruan> kejuruanList;
    private ArrayList<SubKejuruan> subKejuruanList;
    private ArrayList<Kejuruan> idKejuruanList;
    private String kejuruanNama, idKejuruan;

    private String URL_ID_KEJURUAN = "http://aksesblk-samarinda.com/aksesblksamarinda/kejuruan/get_kejuruan_id.php?nama_kejuruan=";
    private String URL_KEJURUAN = "http://aksesblk-samarinda.com/aksesblksamarinda/kejuruan/kejuruan.php";
    private String URL_SUB_KEJURUAN = "http://aksesblk-samarinda.com/aksesblksamarinda/kejuruan/subKejuruan.php?id_kejuruan=";

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

        txtNoTelp = (EditText) findViewById(R.id.txt_notelp);
        txtNoTelp.setText("");

        txtEmail = (EditText) findViewById(R.id.txt_email);
        txtEmail.setText("");


        txtJurusan = (EditText) findViewById(R.id.txt_jurusan);
        txtJurusan.setText("");

        txtAsal = (EditText) findViewById(R.id.txt_asal_sekolah);
        txtAsal.setText("");

        spnProvinsi = (Spinner) findViewById(R.id.spnProvinsi);
        spnKabupaten = (Spinner) findViewById(R.id.spnKabupaten);

        spnAgama = (Spinner) findViewById(R.id.spnAgama);
        spnPendidikan = (Spinner) findViewById(R.id.spnPendidikan);

        spnKejuruan = (Spinner) findViewById(R.id.spnKejuruan);
        spnSubKejuruan = (Spinner) findViewById(R.id.spnSubKejuruan);
        spnProgram = (Spinner) findViewById(R.id.spnProgram);
        spnIdKejuruan = (Spinner) findViewById(R.id.spnIdKejuruan);

        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);


        buttonPilihFile = (Button) findViewById(R.id.buttonPilihFile);
        buttonPilihFile.setOnClickListener(this);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        buttonUpload.setOnClickListener(this);

        kejuruanList = new ArrayList<Kejuruan>();
        idKejuruanList = new ArrayList<Kejuruan>();
        subKejuruanList = new ArrayList<SubKejuruan>();

        ArrayAdapter dataAgama = ArrayAdapter.createFromResource(this, R.array.agama, android.R.layout.simple_spinner_item);
        dataAgama.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnAgama.setAdapter(dataAgama);

        ArrayAdapter dataPendidikan = ArrayAdapter.createFromResource(this, R.array.pendidikan, android.R.layout.simple_spinner_item);
        dataPendidikan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPendidikan.setAdapter(dataPendidikan);

        new GetKejuruan().execute();
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

                idKejuruanList.clear();
                new GetIdKejuruanFromServer().execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnSubKejuruan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnIdKejuruan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                subKejuruanList.clear();
                new GetSubKejuruanFromServer().execute();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void populateSpinnerKejuruan() {

        List<String> lableKejuruan = new ArrayList<>();
        for (int i = 0; i < kejuruanList.size(); i++) {

            lableKejuruan.add(kejuruanList.get(i).getNama_kejuruan());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> adapterKejuruan = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lableKejuruan);
        adapterKejuruan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnKejuruan.setAdapter(adapterKejuruan);
    }

    private void populateSpinnerIdKejuruan() {

        List<String> idLable = new ArrayList<>();
        for (int i = 0; i < idKejuruanList.size(); i++) {
            idLable.add(idKejuruanList.get(i).getId_kejuruan());
        }

        ArrayAdapter<String> idKejuruanAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, idLable);
        idKejuruanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnIdKejuruan.setAdapter(idKejuruanAdapter);
    }

    private void populateSpinnerSubKejuruan() {

        List<String> subKejuruanLable = new ArrayList<>();
        for (int i = 0; i < subKejuruanList.size(); i++) {
            subKejuruanLable.add(subKejuruanList.get(i).getNama_sub());
        }

        ArrayAdapter<String> subKejuruanAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subKejuruanLable);
        subKejuruanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSubKejuruan.setAdapter(subKejuruanAdapter);
    }

    private class GetKejuruan extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            m_ProgressDialog = new ProgressDialog(FormPelatihanBaru.this);
            m_ProgressDialog.setMessage("Fetching Data");
            m_ProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Handler jsonParserKejuruan = new Handler();
            String jsonKejuruan = jsonParserKejuruan.makeServiceCall(URL_KEJURUAN, Handler.GET);
            Log.e("Response: ", "> " + jsonKejuruan);

            if (jsonKejuruan != null) {

                try {

                    JSONObject jsonObjectKejuruan = new JSONObject(jsonKejuruan);
                    if (jsonObjectKejuruan != null) {

                        JSONArray kejuruan = jsonObjectKejuruan.getJSONArray("kejuruan");
                        for (int i = 0; i < kejuruan.length(); i++) {

                            JSONObject kejObj = (JSONObject) kejuruan.get(i);
                            Kejuruan keju = new Kejuruan(
                                    kejObj.getString("id_kejuruan"),
                                    kejObj.getString("nama_kejuruan"));
                            kejuruanList.add(keju);
                        }
                    }
                } catch (JSONException eKejuruan) {

                    eKejuruan.printStackTrace();
                }
            } else {

                Log.e("JSON Data", "Didn't receive any data from server!");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (m_ProgressDialog.isShowing())
                m_ProgressDialog.dismiss();
            populateSpinnerKejuruan();
        }
    }

    private class GetIdKejuruanFromServer extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            idKejuruan = spnKejuruan.getSelectedItem().toString();
            Handler jsonParserIdKejuruan = new Handler();
            String jsonIdKejuruan = jsonParserIdKejuruan.makeServiceCall(URL_ID_KEJURUAN + URLEncoder.encode(idKejuruan), Handler.GET);
            Log.e("Response: ", "> " + jsonIdKejuruan);

            if (jsonIdKejuruan != null) {

                try {

                    JSONObject jsonObjectIdKejuruan = new JSONObject(jsonIdKejuruan);
                    if (jsonObjectIdKejuruan != null) {

                        JSONArray idKejuruan = jsonObjectIdKejuruan.getJSONArray("id");
                        for (int i = 0; i < idKejuruan.length(); i++) {

                            JSONObject idObj = (JSONObject) idKejuruan.get(i);
                            Kejuruan idKeju = new Kejuruan(
                                    idObj.getString("id_kejuruan"),
                                    idObj.getString("nama_kejuruan"));

                            idKejuruanList.add(idKeju);
                        }
                    }
                } catch (JSONException eIdKejuruan) {

                    eIdKejuruan.printStackTrace();
                }
            } else {

                Log.e("JSON Data", "Didn't receive any data from server!");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            populateSpinnerIdKejuruan();
        }
    }

    private class GetSubKejuruanFromServer extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            kejuruanNama = spnIdKejuruan.getSelectedItem().toString();
            Handler jsonParserSubKejuruan = new Handler();
            String jsonSubKejuruan = jsonParserSubKejuruan.makeServiceCall(URL_SUB_KEJURUAN + URLEncoder.encode(kejuruanNama), Handler.GET);
            Log.e("Response: ", "> " + jsonSubKejuruan);

            if (jsonSubKejuruan != null) {

                try {

                    JSONObject jsonObjectSubKejuruan = new JSONObject(jsonSubKejuruan);
                    if (jsonObjectSubKejuruan != null) {

                        JSONArray subKejuruan = jsonObjectSubKejuruan.getJSONArray("sub");
                        for (int i = 0; i < subKejuruan.length(); i++) {

                            JSONObject subObj = (JSONObject) subKejuruan.get(i);
                            SubKejuruan subKeju = new SubKejuruan(subObj.getString("nama_sub"));
                            subKejuruanList.add(subKeju);
                        }
                    }
                } catch (JSONException eSubKejuruan) {

                    eSubKejuruan.printStackTrace();
                }
            } else {

                Log.e("JSON Data", "Didn't receive any data from server!");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            populateSpinnerSubKejuruan();
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
