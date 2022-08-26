package trabLabProg;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class aeroportosList {

    Map<String,aeroporto> listaAeroportos = new HashMap<>();

    public aeroportosList(String nomeArquivo){
        File file = new File(nomeArquivo);
        Scanner input = new Scanner(System.in);
        Scanner sc = null;

        try{
            sc = new Scanner(file);
            String palavras[] = new String[9];
            String line = sc.nextLine();
            while(sc.hasNextLine()){
                line = sc.nextLine();
                palavras = line.split(",");
                aeroporto novoAeroporto = new aeroporto(palavras[3],palavras[4],palavras[5],palavras[1],palavras[6],palavras[7]);
                listaAeroportos.put(palavras[1],novoAeroporto);
            }

        }
        catch (IOException e){
            System.out.print(e.getMessage());
        }
        finally {
            if(sc != null) {
                sc.close();
            }
        }
    }
    public String[] calcularRota(String aeroOrigem, String aeroDestino ){
        float dist = 0;
        String rota[] = new String[4];
        rota[0] = aeroOrigem;
        rota[1] = aeroDestino;
        rota[2] = aeroOrigem +"-"+ aeroDestino;
        rota[3] = String.valueOf((listaAeroportos.get(aeroOrigem)).distanciaDiretaEntreAeroportos(listaAeroportos.get(aeroDestino)));
        return rota;
    }
    public aeroporto getAeroporto(String sigla){
        return this.listaAeroportos.get(sigla);
    }
    public void setAdjacencias(){
        for (aeroporto y: listaAeroportos.values()) {
            for(aeroporto x: listaAeroportos.values()){
                if(x!= y){
                    y.addNovaAdjacencia(x);
                }
            }
        }
    };
    public void imprimir(){
        System.out.print("\nSigla            Cidade                      Estado\n");
        System.out.print("-------------------------------------------------------\n");

        for(aeroporto obj : listaAeroportos.values()){
            System.out.printf("%s               ",obj.getSigla());
            String s = obj.getCidade();
            System.out.printf("%s",s);
            for(int j = s.length(); j < 27 ; j++) System.out.print(" ");
            System.out.printf("%s\n",obj.getEstado());
        }
        System.out.print("\n\n");
    }
}
