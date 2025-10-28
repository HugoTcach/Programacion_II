package com.mycompany.tp7_herenciapolimorfismo.pagos;

public class Efectivo implements Pagable {

    @Override
    public void pagar(double monto) {
        System.out.println("Pagando $" + monto + " en efectivo.");
    }
}
