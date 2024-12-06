package com.gugawag.rpc.banco;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.gugawag.rpc.banco.model.Conta;

public interface BancoServiceIF extends Remote {

    double saldo(String conta) throws RemoteException;
    int quantidadeContas() throws RemoteException;

    String cadastrar(Conta conta) throws RemoteException;
    Conta pesquisar(String numeroConta) throws RemoteException;
    String remover(String numeroConta) throws RemoteException;

}
