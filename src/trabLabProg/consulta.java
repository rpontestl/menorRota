package trabLabProg;

import java.sql.SQLException;
import java.util.Scanner;

public class consulta {
    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        banco bancoDados = new banco();
        aeroportosList lista = new aeroportosList("/home/rafaelpontes/IdeaProjects/menorRota/src/trabLabProg/planilhaAeroportos");

        //Loop do sistema
        while(true){
            Integer opcao;

            //menu principal
            do{
                System.out.print("\nQual acao deseja realizar? \n[1]Simular rota [2]Consultar aeroportos [3]Sair\n");
                opcao = input.nextInt();
            }while(!((opcao == 1)|| (opcao == 2)|| (opcao == 3)));

            //Sair
            if(opcao == 3) break;

            //Printar lista de aeroportos
            else if(opcao == 2){
                lista.imprimir();
            }
            //Simular
            else{
                //menu secundário
                do{
                    System.out.print("Qual acao deseja realizar? \n[1]Escolher origem [2]Consultar aeroportos [3]Voltar para o menu\n");
                    opcao = input.nextInt();
                }while(!((opcao == 1)|| (opcao == 2)|| (opcao == 3)));

                //Voltar ao loop principal
                if(opcao == 3);

                //imprimir lista de aeroportos
                else if(opcao == 2) lista.imprimir();

                //forcecer nome dos aeroportos
                else{
                    String nomeOrigem, nomeDestino;
                    System.out.print("Digite a sigla do aeroporto de origem: ");
                    nomeOrigem = input.next();
                    System.out.print("\nDigite a sigla do aeroporto de destino: ");
                    nomeDestino = input.next();

                    String rotaCompleta[] = lista.calcularRota(nomeOrigem, nomeDestino);

                    //salvar no banco de dados a rota calculada
                    bancoDados.salvar(rotaCompleta);
                    System.out.printf("\nMelhor rota: %s, distancia %s\n", rotaCompleta[2],rotaCompleta[3]);
                }
            }
        }
        System.out.print("Obrigado por usar nossos serviços, volte sempre.");
    }
}