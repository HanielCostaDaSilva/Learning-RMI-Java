package com.gugawag.rpc.banco.model;

import java.io.Serializable;

public class Conta implements Serializable{
    private String numero;
    private double saldo;

    public Conta(){}
    public Conta(String numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }
    

    /**
     * @return String return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return double return the saldo
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}
