package trabLabProg;
import java.sql.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.sql.ResultSet;

public class banco
{
    Connection con;
    Statement statement;

    Map<String,aeroporto> listaAeroportos;
    //ResultSet resultSet;
    public banco()
    {
        try
        {
            // create a mysql database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/trabLabProg","rafapontes","abcdefgh");
            statement = con.createStatement();
            listaAeroportos = new HashMap<>();
            ResultSet rs = statement.executeQuery("select * from AIRPORTS;");

            while(rs.next()){
                aeroporto novoAeroporto = new aeroporto(rs.getString("name"),rs.getString("city"),rs.getString("state"),rs.getString("initials"),rs.getString("latitude"),rs.getString("longitude"));
                listaAeroportos.put(rs.getString("initials"),novoAeroporto);
            }

        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
    public void salvar(String origem, String destino, String trajeto[]) throws SQLException {
        String query = " insert into listaConsultas (origem, destino, rota, distancia, horario, dia)"
                + " values (?, ?, ?, ?, ?, ?)";
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
        java.sql.Time startTime = new java.sql.Time(System.currentTimeMillis());

        // criando o mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString (1, origem);
        preparedStmt.setString (2, destino);
        preparedStmt.setString (3, trajeto[0]);
        preparedStmt.setInt  (4,Integer.parseInt(trajeto[1]));
        preparedStmt.setTime(5, startTime);
        preparedStmt.setDate   (6, startDate);

        preparedStmt.execute();
    }
    public boolean isNotAnAirport(String name){
        boolean valid = listaAeroportos.containsKey(name);
        if(!valid) System.out.print("\nSigla incorreta, consulte os aeroportos validos\n");
        return !valid;
    }

    public String[] calcularRota(String aeroOrigem, String aeroDestino ){
        String[] rota = new String[2];
        dijkstra grafo = new dijkstra(this.listaAeroportos);
        rota[0] = grafo.caminhoDijkstra(aeroOrigem,aeroDestino);
        rota[1] = String.valueOf(grafo.getDistance(aeroDestino));
        return rota;
    }
    public void imprimir(){
        StringBuilder ans;
        System.out.print("\nSigla           | Munic??pio                   | Estado\n");
        System.out.print("----------------------------------------------------------------\n");

        for(aeroporto obj : listaAeroportos.values()){
                ans = new StringBuilder(obj.getSigla() + "             | " + obj.getCidade());
                for(int j = obj.getCidade().length(); j < 28 ; j++) ans.append(" ");//System.out.print(" ");
                ans.append("| "+obj.getEstado());
                System.out.printf("%s\n", ans.toString());
        }
        System.out.print("\n\n");
    }
}
