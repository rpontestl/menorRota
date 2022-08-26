package trabLabProg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class aeroporto {
    private String nome;
    private String cidade;
    private String estado;
    private String sigla;
    private double longitude;
    private double latitude;

    public Map<String,Integer> adjacencias = new HashMap<>();
    public aeroporto(String Nome, String Cidade , String Estado, String Sigla, String Latitude, String Longitude){
        this.nome = Nome;
        this.cidade = Cidade;
        this.estado = Estado;
        this.sigla = Sigla;
        this.longitude = Math.toRadians(Double.valueOf(Longitude).doubleValue());
        this.latitude = Math.toRadians(Double.valueOf(Latitude).doubleValue());
    }
    public aeroporto(aeroporto p){
        this.nome = p.nome;
        this.cidade = p.cidade;
        this.estado = p.estado;
        this.sigla = p.sigla;
        this.longitude = p.longitude;
        this.latitude = p.latitude;
    }
    public String getNome(){
        return nome;
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
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }

    public int distanciaDiretaEntreAeroportos(aeroporto destino){
        int distancia;
        double  deltaLongitude = destino.longitude - this.longitude;
        double a = Math.acos(Math.sin(destino.latitude)*Math.sin(this.latitude)+Math.cos(destino.latitude)*Math.cos(this.latitude)*Math.cos(deltaLongitude));
        distancia = (int)(6371*a);

        return distancia;
    }
    public void addNovaAdjacencia(aeroporto destino){
        int dist = this.distanciaDiretaEntreAeroportos(destino);
        adjacencias.put(destino.sigla,dist);
    }
}
