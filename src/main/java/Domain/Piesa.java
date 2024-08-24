package Domain;

public class Piesa extends Entity {
    private String formatie;
    private String titlu;
    private String genMuzical;
    private String durata;

    public Piesa(int id, String formatie, String titlu, String genMuzical, String durata) {
        super(id);
        this.formatie = formatie;
        this.titlu = titlu;
        this.genMuzical = genMuzical;
        this.durata = durata;
    }
    ///getteri
    public String getFormatie() {

        return this.formatie;
    }
    public String getTitlu() {

        return this.titlu;
    }
    public String getGenMuzical() {

        return this.genMuzical;
    }
    public String getDurata() {

        return this.durata;
    }
    ///setteri
    public void setFormatie(String formatie) {

        this.formatie = formatie;
    }
    public void setTitlu(String titlu) {

        this.titlu = titlu;
    }
    public void setGenMuzical(String genMuzical) {

        this.genMuzical = genMuzical;
    }
    public void setDurata(String durata) {

        this.durata = durata;
    }
    @Override
    public String toString() {
        return  " id: " + id + " formatie: " + formatie +
                " titlu: " + titlu +
                " gen_muzical: " + genMuzical +
                " durata: " + durata;
    }
}
