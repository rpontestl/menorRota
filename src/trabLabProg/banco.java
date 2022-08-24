package trabLabProg;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;

/**
 * A Java MySQL INSERT example.
 * Demonstrates the use of a SQL INSERT statement against a
 * MySQL database, called from a Java program, using a
 * Java Statement (not a PreparedStatement).
 *
 * Created by Alvin Alexander, http://devdaily.com
 */
public class banco
{
    Connection con;
    Statement statement;


    ResultSet resultSet;
    public banco()
    {
        try
        {
            // create a mysql database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bancodeteste","rafapontes","abcdefgh");
            statement = con.createStatement();
            //resultSet = statement.executeQuery("insert into simulacoes VALUES('REC','SDU','REC-BSB-SDU',12313.23,'23:25:12','2022-08-24');");
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
    public void salvar(String trajeto[]) throws SQLException {
        String query = " insert into simulacoes (origin, destiny, roat, distance, time, date)"
                + " values (?, ?, ?, ?, ?, ?)";
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
        java.sql.Time startTime = new java.sql.Time(System.currentTimeMillis());

        // criando o mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString (1, trajeto[0]);
        preparedStmt.setString (2, trajeto[1]);
        preparedStmt.setString (3, trajeto[2]);
        preparedStmt.setFloat  (4,Float.valueOf(trajeto[3]).floatValue());
        preparedStmt.setTime(5, startTime);
        preparedStmt.setDate   (6, startDate);

        //PreparedStatement preparedStmt = con.prepareStatement("insert into simulacoes VALUES('REC','SDU','REC-BSB-SDU',12313.23,'23:25:12','2022-08-24');");
        preparedStmt.execute();
    }
}
