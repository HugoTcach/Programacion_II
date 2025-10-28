package com.mycompany.tp7_herenciapolimorfismo.pagos;

public class Transferencia implements Pagable {
    private String banco;

    public Transferencia(String banco) {
        this.banco = banco;
    }

    @Override
    public void pagar(double monto) {
        System.out.println("Pagando $" + monto + " por transferencia bancaria al banco " + banco);
    }
}
