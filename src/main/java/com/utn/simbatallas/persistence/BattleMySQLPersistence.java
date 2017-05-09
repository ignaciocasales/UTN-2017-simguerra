package main.java.com.utn.simbatallas.persistence;

import main.java.com.utn.simbatallas.domain.BattleField;
import main.java.com.utn.simbatallas.domain.MessageSuccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by ignacio on 04/05/17.
 * <p>
 * Persistencia de las batallas en la base de datos
 */
public class BattleMySQLPersistence extends DataBase {

    private static BattleMySQLPersistence instance;
    private Connection connection;

    private BattleMySQLPersistence() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Se establece la connection con la base de datos
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mysql",
                    "root",
                    "123"
            );

            // Se crea la base de datos y la tabla si no existe
            Statement st = connection.createStatement();
            st.execute("CREATE DATABASE " +
                    "/*!32312 IF NOT EXISTS*/`simguerra` /*!40100" +
                    " DEFAULT CHARACTER SET latin1 */;");
            st.execute(""
                    + "CREATE TABLE IF NOT EXISTS `simguerra`.`battlesresults` ("
                    + "  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,"
                    + "  `winner` VARCHAR(50) NOT NULL,"
                    + "  `battlefield` VARCHAR(50) NOT NULL,"
                    + "  `totalunits` INT NOT NULL,"
                    + "  `unitslost` INT NOT NULL,"
                    + "  `looser` VARCHAR(50) NOT NULL,"
                    + "  `unitsdefeated` INT NOT NULL,"
                    + "  CONSTRAINT `pk_battleresult` PRIMARY KEY (`id`)"
                    + ");");
            connection.close();
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/simguerra",
                    "root",
                    "123"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static BattleMySQLPersistence getInstance() {
        if (instance == null) {
            instance = new BattleMySQLPersistence();
        }
        return instance;
    }

    public void addBattleResult(BattleField b) {
        try {
            String sql = "INSERT INTO battlesresults " +
                    "(winner, battlefield, totalunits, unitslost, looser, unitsdefeated)" +
                    " VALUES (?,?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, b.getWinner().getArmyName().toUpperCase());
            st.setString(2, b.getName());
            st.setInt(3, b.getWinner().getUnitList().size());
            st.setInt(4, (b.getWinner().getUnitList().size()) - (b.getWinner().getAliveUnits()));
            st.setString(5, b.getWinner().getEnemy().getArmyName().toUpperCase());
            st.setInt(6, (b.getWinner().getEnemy().getUnitList().size()) - (b.getWinner().getEnemy().getAliveUnits()));
            st.execute();

            this.setChanged();

            MessageSuccess msgs = new MessageSuccess("Resultados de partida almacenados con éxito");

            notifyObservers(msgs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String[]> getAllBattlesResults() {
        try {
            String sql = "SELECT * FROM battlesresults;";

            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);

            List<String[]> list = new ArrayList<>();

            while (rs.next()) {
                String[] s = new String[7];
                s[0] = String.valueOf(rs.getInt("id"));
                s[1] = rs.getString("winner");
                s[2] = rs.getString("battlefield");
                s[3] = String.valueOf(rs.getInt("totalunits"));
                s[4] = String.valueOf(rs.getInt("unitslost"));
                s[5] = rs.getString("looser");
                s[6] = String.valueOf(rs.getInt("unitsdefeated"));

                list.add(s);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof BattleField) {
            BattleField b = (BattleField) o;

            addBattleResult(b);
        }
    }
}
