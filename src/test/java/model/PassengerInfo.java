package model;

public class PassengerInfo {

    private String yon;
    private String nerden;
    private String nereye;
    private String ad;
    private String soyad;
    private String cinsiyet;
    private String dogumTarihi;
    private String mail;
    private String tc;
    private String telefon;

    public PassengerInfo(String yon, String nerden, String nereye, String ad, String soyad, String cinsiyet, String dogumTarihi, String mail, String tc, String telefon) {
        this.yon = yon;
        this.nerden = nerden;
        this.nereye = nereye;
        this.ad = ad;
        this.soyad = soyad;
        this.cinsiyet = cinsiyet;
        this.dogumTarihi = dogumTarihi;
        this.mail = mail;
        this.tc = tc;
        this.telefon = telefon;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getYon() {
        return yon;
    }

    public void setYon(String yon) {
        this.yon = yon;
    }

    public String getNerden() {
        return nerden;
    }

    public void setNerden(String nerden) {
        this.nerden = nerden;
    }

    public String getNereye() {
        return nereye;
    }

    public void setNereye(String nereye) {
        this.nereye = nereye;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(String dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
