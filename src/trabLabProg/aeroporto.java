package trabLabProg;

public class aeroporto {
    private String nome;
    private String cidade;
    private String estado;
    private String sigla;
    private double longitude;
    private double latitude;

    //public Map<String,Integer> adjacencias = new HashMap<>();
    public aeroporto(String Nome, String Cidade , String Estado, String Sigla, String Latitude, String Longitude){
        this.nome = Nome;
        this.cidade = Cidade;
        this.estado = Estado;
        this.sigla = Sigla;
        this.longitude = Math.toRadians(Double.valueOf(Longitude).doubleValue());
        this.latitude = Math.toRadians(Double.valueOf(Latitude).doubleValue());
    }

    public String getCidade(){
        return cidade;
    }
    public String getEstado(){
        return estado;
    }
    public String getSigla(){
        return sigla;
    }


    public int distanciaDiretaEntreAeroportos(aeroporto destino){
        int distancia;
        double  deltaLongitude = destino.longitude - this.longitude;
        double a = Math.acos(Math.sin(destino.latitude)*Math.sin(this.latitude)+Math.cos(destino.latitude)*Math.cos(this.latitude)*Math.cos(deltaLongitude));
        distancia = (int)(6371*a);

        return distancia;
    }
}
