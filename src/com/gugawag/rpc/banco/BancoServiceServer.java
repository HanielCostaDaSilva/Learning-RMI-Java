package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import com.gugawag.rpc.banco.model.Conta;
import com.gugawag.rpc.banco.model.ContaNull;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private List<Conta> contas;

    public BancoServiceServer() throws RemoteException {
        this.contas = new ArrayList<>();
        this.contas.addAll(0,Arrays.asList(
            new Conta("1", 100.0),
            new Conta("2", 156.0),
            new Conta("3", 950.0)
            )
            );
        
    }

    @Override
    public double saldo(String numero) throws RemoteException {
        Conta c = this.pesquisar(numero);
        return c.getSaldo();
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return contas.size();
    }

    @Override
    public String cadastrar(Conta conta) throws RemoteException{
        if (conta.getNumero().equals("0")){
            return "Número informado inválido";
        } 
        String numero = conta.getNumero();
        // checa se já existe uma conta com o número cadastrado
        if (pesquisar(numero).getNumero().equals(numero)){
            return "Conta já cadastrada";
        }
        else{
            this.contas.add(conta);
            return "Conta cadastrada com sucesso!";
        }
    }
    
    @Override
    public String remover(String numeroConta) throws RemoteException {
        Conta conta = this.pesquisar(numeroConta);
        
        // checa se achou a conta com o número cadastrado
        if (!conta.getNumero().equals(numeroConta)){
            return "Conta não encontrada";
        }
        else{
            this.contas.remove(conta);
            return "Conta removida!";
        }
    }
    @Override
    public Conta pesquisar(String numeroConta) throws RemoteException {
        for( Conta c : this.contas){
            if (c.getNumero().equals(numeroConta)) return c;
        }
        return new ContaNull();
        }


}
