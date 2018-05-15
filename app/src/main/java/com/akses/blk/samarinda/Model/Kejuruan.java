package com.akses.blk.samarinda.Model;

public class Kejuruan {

    private String id_kejuruan;
    private String nama_kejuruan;

    public Kejuruan(String id_kejuruan, String nama_kejuruan) {
        this.id_kejuruan = id_kejuruan;
        this.nama_kejuruan = nama_kejuruan;
    }

    public String getId_kejuruan() {
        return id_kejuruan;
    }

    public void setId_kejuruan(String id_kejuruan) {
        this.id_kejuruan = id_kejuruan;
    }

    public String getNama_kejuruan() {
        return nama_kejuruan;
    }

    public void setNama_kejuruan(String nama_kejuruan) {
        this.nama_kejuruan = nama_kejuruan;
    }
}
