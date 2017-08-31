package com.akses.blk.samarinda;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ipaulpro.afilechooser.utils.FileUtils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FormTambahBerita extends AppCompatActivity implements View.OnClickListener {

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;
    private static final int REQUEST_CHOOSER = 1234;
    TextView textViewPathPhotoBerita, textViewKeterangan;
    EditText editTextJudul, editTextKonten, editTextTgl;
    Button buttonPilihFile, buttonUpload;
    int serverResponseCode = 0;
    ProgressDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_tambah_berita);
        requestStoragePermission();
        init();
    }

    protected void init() {

        textViewPathPhotoBerita = (TextView) findViewById(R.id.textViewPathPhotoBerita);
        textViewPathPhotoBerita.setText("");

//        editTextTgl = (EditText) findViewById(R.id.editTextTglBerita);
//        editTextTgl.setText("");

        editTextJudul = (EditText) findViewById(R.id.editTextJudulBerita);
        editTextJudul.setText("");

        editTextKonten = (EditText) findViewById(R.id.editTextDeskripsiBerita);
        editTextKonten.setText("");
//
//        textViewKeterangan = (TextView) findViewById(R.id.textViewKeterangan);
//        textViewKeterangan.setText("");

        buttonPilihFile = (Button) findViewById(R.id.buttonPilihFilePhotoBerita);
        buttonPilihFile.setOnClickListener(this);
        buttonUpload = (Button) findViewById(R.id.buttonUploadBerita);
        buttonUpload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonPilihFilePhotoBerita:

                Intent getContentIntent = FileUtils.createGetContentIntent();
                Intent intent = Intent
                        .createChooser(getContentIntent, "Pilih file");
                startActivityForResult(intent, REQUEST_CHOOSER);

                break;
            case R.id.buttonUploadBerita:

                final String path = textViewPathPhotoBerita.getText().toString();

                if (path != null && FileUtils.isLocal(path)) {
                    File file = new File(path);
                }

                if ("".equals(editTextJudul.getText().toString())){
                    editTextJudul.setError("Judul Berita Tidak Boleh Kosong");
                    editTextJudul.requestFocus();
                    return;
                }

                if ("".equals(editTextKonten.getText().toString())){
                    editTextKonten.setError("Isi Konten Berita");
                    editTextKonten.requestFocus();
                    return;
                }

                if ("".equals(textViewPathPhotoBerita.getText().toString())){
                    textViewPathPhotoBerita.setError("Harap Upload File");
                    textViewPathPhotoBerita.requestFocus();
                    return;
                }





                dialog = ProgressDialog.show(FormTambahBerita.this, "",
                        "Upload Berita..", true);

                new Thread(new Runnable() {
                    public void run() {
                        runOnUiThread(new Runnable() {
                            public void run() {
//                                textViewKeterangan.setText("Proses Upload");
                            }
                        });

                        int response = 0;
//
                        String judul = editTextJudul.getText().toString();
                        String deskripsi = editTextKonten.getText().toString();
                        response = uploadFile(path, judul, deskripsi);
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
                    textViewPathPhotoBerita.setText(path);
                }
                break;
        }

    }

    public int uploadFile(String sourceFileUri, String judul, String deskripsi) {

        // ip komputer server
        String upLoadServerUri = "http://aksesblk-samarinda.com/aksesblksamarinda/uploadberita/Server.php";
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

//            // untuk parameter keterangan
//            dos.writeBytes("Content-Disposition: form-data; name=\"tanggal\""
//                    + lineEnd);
//            dos.writeBytes(lineEnd);
//            dos.writeBytes(tanggal);
//            dos.writeBytes(lineEnd);
//            dos.writeBytes(twoHyphens + boundary + lineEnd);

            // untuk parameter keterangan
            dos.writeBytes("Content-Disposition: form-data; name=\"judul\""
                    + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(judul);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            // jika ingin menambahkan parameter baru, silahkan buat baris baru
            // lagi seperti berikut
            dos.writeBytes("Content-Disposition: form-data; name=\"deskripsi\""+
                    lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(deskripsi);
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
                        Toast.makeText(FormTambahBerita.this, "Tambah Berita Berhasil.",
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
            Toast.makeText(FormTambahBerita.this, "MalformedURLException",
                    Toast.LENGTH_SHORT).show();
            Log.e("Upload Berita to server", "error: " + ex.getMessage(), ex);
        } catch (Exception e) {
            dialog.dismiss();
            e.printStackTrace();
            Toast.makeText(FormTambahBerita.this, "Exception : " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            Log.e("Upload Berita to server","Exception : " + e.getMessage(), e);
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
