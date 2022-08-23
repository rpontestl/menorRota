package trabLabProg;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class construirList {
    File file = new File("/home/rafaelpontes/IdeaProjects/menorRota/src/trabLabProg/listaAeroportos");
    Scanner input = new Scanner(System.in);
    Scanner sc = null;
    
    try{
        sc = new Scanner(file);
        String palavras[] = new String[9];
        List<aeroporto> listaAeroportos = new ArrayList<aeroporto>();
        String line = sc.nextLine();
        while(sc.hasNextLine()){
            line = sc.nextLine();
            palavras = line.split(",");
            aeroporto novoAeroporto = new aeroporto(palavras[3],palavras[4],palavras[5],palavras[1],palavras[6],palavras[7]);
            listaAeroportos.add(novoAeroporto);
        }
        for(int i = 0; i < listaAeroportos.size(); i++ ){
            System.out.println((listaAeroportos.get(i)).getSigla());
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
