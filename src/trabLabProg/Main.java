package trabLabProg;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        banco bancoDados = new banco();
        //aeroportosList lista = new aeroportosList("/home/rafaelpontes/IdeaProjects/menorRota/src/trabLabProg/planilhaAeroportos");

        //Loop do sistema
        while(true){
            String opcao;

            //menu principal
            do{
                System.out.print("\nQual acao deseja realizar? \n[1]Simular rota [2]Consultar aeroportos [3]Sair\n");
                opcao = input.next();
            }while(!((opcao.equals("1"))||(opcao.equals("2"))|| (opcao.equals("3"))));

            //Sair
            if(opcao.equals("3")) break;

            //Printar lista de aeroportos
            else if(opcao.equals("2")){
                bancoDados.imprimir();
            }
            //Simular
            else{
                //menu secundário
                do{
                    System.out.print("Qual acao deseja realizar? \n[1]Escolher origem [2]Consultar aeroportos [3]Voltar para o menu\n");
                    opcao = input.next();
                }while(!((opcao.equals("1"))||(opcao.equals("2"))|| (opcao.equals("3"))));

                //Voltar ao loop principal
                if(opcao.equals("3"));

                //imprimir lista de aeroportos
                else if(opcao.equals("2")) bancoDados.imprimir();

                //forcecer nome dos aeroportos
                else{
                    String nomeOrigem, nomeDestino;
                    do{
                        System.out.print("Digite a sigla do aeroporto de origem: ");
                        nomeOrigem = input.next();
                    }while(bancoDados.isNotAnAirport(nomeOrigem));

                    do{
                        System.out.print("\nDigite a sigla do aeroporto de destino: ");
                        nomeDestino = input.next();
                    }while(bancoDados.isNotAnAirport(nomeDestino));

                    String[] rotaCompleta = bancoDados.calcularRota(nomeOrigem, nomeDestino);

                    //salvar no banco de dados a rota calculad
                    bancoDados.salvar(rotaCompleta);
                    System.out.printf("\nMelhor rota: %s, distancia %s Km\n", rotaCompleta[2],rotaCompleta[3]);
                }
            }
        }
        System.out.print("Obrigado por usar nossos serviços, volte sempre.");
    }
}