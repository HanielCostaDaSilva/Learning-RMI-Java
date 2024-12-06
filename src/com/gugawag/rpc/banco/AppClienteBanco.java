package com.gugawag.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import com.gugawag.rpc.banco.model.Conta;

/******
 * Código inicial criado por Gustavo Wagner.
 * repositório: https://github.com/gugawag/BancoRPC
 */
public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
       
/*         if (args.length != 1) {
            System.out.println("Uso correto: java com.gugawag.rpc.banco.AppClienteBanco <127.0.0.1>");
            System.exit(0);
        } */

        //String ipServidor = args[0] ; // IP passado como argumento
        String ipServidor = "127.0.0.1" ;
        Registry registry = LocateRegistry.getRegistry(ipServidor, 1099); 

        // Faz o lookup do serviço remoto
        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

        menu();
        try (Scanner entrada = new Scanner(System.in)) {
            int opcao = entrada.nextInt();

            while (opcao != 9) {
                switch (opcao) {
                    case 1: {
                        System.out.println("Digite o número da conta:");
                        String conta = entrada.next();
                        System.out.println("Saldo: " + banco.saldo(conta));
                        break;
                    }
                    case 2: {
                        System.out.println("Quantidade de contas: " + banco.quantidadeContas());
                        break;
                    }
                    case 3: {
                        System.out.println("Digite o número da conta:");
                        String conta = entrada.next();
                        System.out.println("Digite o saldo da conta:");
                        double saldo = entrada.nextDouble();
                        System.out.println(banco.cadastrar(new Conta(conta, saldo)));
                        break;
                    }
                    case 4: {
                        System.out.println("Digite o número da conta:");
                        String conta = entrada.next();
                        Conta contaPesquisada = banco.pesquisar(conta);
                        if (contaPesquisada != null) {
                            System.out.println("Conta encontrada: " + contaPesquisada);
                        } else {
                            System.out.println("Conta não encontrada.");
                        }
                        break;
                    }
                    case 5: {
                        System.out.println("Digite o número da conta:");
                        String conta = entrada.next();
                        System.out.println(banco.remover(conta));
                        break;
                    }
                    default: {
                        System.out.println("Opção inválida.");
                        break;
                    }
                }
                menu();
                opcao = entrada.nextInt();
            }
        }
    }

    public static void menu() {
        System.out.println("\n=== BANCO RMI ===");
        System.out.println("\n=== HANIEL COSTA DA SILVA ===");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Cadastrar Conta");
        System.out.println("4 - Pesquisar Conta");
        System.out.println("5 - Remover Conta");
        System.out.println("9 - Sair");
        System.out.print("Escolha uma opção: ");
    }
}
