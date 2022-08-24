package trabLabProg;
public class aeroporto {
    private String nome;
    private String cidade;
    private String estado;
    private String sigla;
    private Float longitude;
    private Float latitude;

    public aeroporto(String Nome, String Cidade , String Estado, String Sigla, String Latitude, String Longitude){
        this.nome = Nome;
        this.cidade = Cidade;
        this.estado = Estado;
        this.sigla = Sigla;
        this.longitude = Float.valueOf(Longitude).floatValue();
        this.latitude = Float.valueOf(Latitude).floatValue();;
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
    public Float getLatitude() {
        return latitude;
    }
    public Float getLongitude() {
        return longitude;
    }
}
