package com.mycompany.tp7_herenciapolimorfismo.pagos;

public class TarjetaCredito implements Pagable {
    private String numeroTarjeta;

    public TarjetaCredito(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    @Override
    public void pagar(double monto) {
        System.out.println("Pagando $" + monto + " con tarjeta de crédito: " + numeroTarjeta);
    }
}
