package main;

import entidades.*;

public class TP5_RelacionesUML {
    public static void main(String[] args) {

        // --- Prueba 1: Pasaporte – Foto – Titular ---
        Titular titular = new Titular("Juan Perez", "12345678");
        Pasaporte pasaporte = new Pasaporte("A12345", "01/01/2025", "foto.jpg", "jpg");
        pasaporte.setTitular(titular);

        System.out.println("=== Pasaporte / Titular ===");
        System.out.println("Titular del pasaporte: " + pasaporte.getTitular().getNombre());
        System.out.println("Foto del pasaporte: " + pasaporte.getFoto().getImagen());
        System.out.println("Pasaporte del titular: " + titular.getPasaporte().getNumero());

        // --- Prueba 2: Celular – Batería – Usuario ---
        Bateria bateria = new Bateria("Litio-5000", 5000);
        Celular celular = new Celular("123456789012345", "Samsung", "Galaxy S21", bateria);
        Usuario usuario = new Usuario("Ana Gomez", "87654321");
        celular.setUsuario(usuario);

        System.out.println("\n=== Celular / Usuario ===");
        System.out.println("Usuario del celular: " + celular.getUsuario().getNombre());
        System.out.println("Celular del usuario: " + usuario.getCelular().getModelo());
        System.out.println("Modelo de la batería: " + celular.getBateria().getModelo());
    }
}
