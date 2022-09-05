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
    public void salvar(String trajeto[]) throws SQLException {
        String query = " insert into listaConsultas (origem, destino, rota, distancia, horario, dia)"
                + " values (?, ?, ?, ?, ?, ?)";
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
        java.sql.Time startTime = new java.sql.Time(System.currentTimeMillis());

        // criando o mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString (1, trajeto[0]);
        preparedStmt.setString (2, trajeto[1]);
        preparedStmt.setString (3, trajeto[2]);
        preparedStmt.setFloat  (4,Integer.valueOf(trajeto[3]));
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
        String[] rota = new String[4];
        rota[0] = aeroOrigem;
        rota[1] = aeroDestino;
        dijkstra grafo = new dijkstra(this.listaAeroportos);
        rota[2] = grafo.caminhoDijkstra(aeroOrigem,aeroDestino);
        rota[3] = String.valueOf(grafo.getDistance(aeroDestino));
        return rota;
    }
    public void imprimir(){
        System.out.print("\nSigla            Cidade                      Estado\n");
        System.out.print("-------------------------------------------------------\n");

        for(aeroporto obj : listaAeroportos.values()){
            System.out.printf("%s              ",obj.getSigla());
            String s = obj.getCidade();
            System.out.printf("%s",s);
            for(int j = s.length(); j < 28 ; j++) System.out.print(" ");
            System.out.printf("%s\n",obj.getEstado());
        }
        System.out.print("\n\n");
    }
}
