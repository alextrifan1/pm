package Domain;

public class PiesaFactory implements IEntityFactory<Piesa>{
    @Override
    public Piesa createEntity(String line) {
        int id = Integer.parseInt(line.split(",")[0]);
        String formatie = line.split(",")[1];
        String titlu = line.split(",")[2];
        String genMuzical = line.split(",")[3];
        String durata = line.split(",")[4];
        return new Piesa(id, formatie, titlu, genMuzical, durata);

    }
    @Override
    public String saveEntity(Piesa p) {
        try {
            return (p.getId() + "," + p.getFormatie() + "," +p.getTitlu()+ "," + p.getGenMuzical() + "," + p.getDurata() + "\n");
        } catch (Exception e) {e.printStackTrace();}return null;
    }
}