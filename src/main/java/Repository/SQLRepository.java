package Repository;


import Domain.Piesa;
import org.sqlite.SQLiteDataSource;


import java.sql.*;
import java.util.ArrayList;

public class SQLRepository extends MRepository<Piesa> {
    private String dbLocation;
    private Connection connection;

    public SQLRepository(String dbLocation) throws ExceptionRepository {
        super();
        this.dbLocation = "jdbc:sqlite:" + dbLocation;
        openConnection();
        createTable();
        loadDataFromDb();
        initData();
    }

    private void initData() throws ExceptionRepository {
        if (entities.size() == 0) {
            add(new Piesa(1, "The Beatles", "Hey Jude", "Rock", "7:11"));
            add(new Piesa(2, "The Beatles", "Yesterday", "Rock", "2:05"));
            add(new Piesa(3, "The Beatles", "Let It Be", "Rock", "3:52"));
            add(new Piesa(4, "The Beatles", "Come Together", "Rock", "4:18"));
            add(new Piesa(5, "The Beatles", "Help!", "Rock", "2:18"));
            add(new Piesa(6, "The Beatles", "Here Comes the Sun", "Rock", "3:05"));
            add(new Piesa(7, "The Beatles", "In My Life", "Rock", "2:28"));
            add(new Piesa(8, "The Weeknd", "Blinding Lights", "Pop", "3:20"));
            add(new Piesa(9, "The Weeknd", "Save Your Tears", "Pop", "3:35"));
            add(new Piesa(10, "The Weeknd", "In Your Eyes", "Pop", "3:57"));
            add(new Piesa(11, "The Weeknd", "Heartless", "Pop", "3:18"));
            add(new Piesa(12, "The Weeknd", "After Hours", "Pop", "6:01"));
            add(new Piesa(13, "Travis Scott", "Goosebumps", "Hip-Hop", "4:03"));
            add(new Piesa(14, "Travis Scott", "Sicko Mode", "Hip-Hop", "5:12"));
            add(new Piesa(15, "Travis Scott", "Highest in the Room", "Hip-Hop", "2:55"));
            add(new Piesa(16, "Travis Scott", "Butterfly Effect", "Hip-Hop", "3:10"));
            add(new Piesa(17, "Travis Scott", "Antidote", "Hip-Hop", "4:23"));
        }
    }

    private void loadDataFromDb() {
        this.entities.addAll(getAll());
    }

    private void createTable() {
        try (final Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS piesa " +
                    "(id int, formatie nvarchar(50), " +
                    "titlu nvarchar(50), genMuzical nvarchar(50), " +
                    "durata nvarchar(50))");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void openConnection() {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl(dbLocation);
        try {
            if (connection == null || connection.isClosed())
                connection = ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public ArrayList<Piesa> getAll() {
        ArrayList<Piesa> piese = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM piesa");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Piesa a = new Piesa(rs.getInt("id"),
                        rs.getString("formatie"),
                        rs.getString("titlu"),
                        rs.getString("genMuzical"),
                        rs.getString("durata"));
                piese.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return piese;
    }
    @Override
    public void add(Piesa p) throws ExceptionRepository {
        super.add(p);
        try (final Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("INSERT INTO piesa VALUES (" + p.getId() + ", '" +
                    p.getFormatie() + "', '" +
                    p.getTitlu() + "', '" +
                    p.getGenMuzical() + "', '" +
                    p.getDurata()+ "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void remove(int id) {
        super.remove(id);
        try (final Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM piesa WHERE id = " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
