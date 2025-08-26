// 1. Registro de Estudiantes
class Estudiante {
    String nombre;
    String apellido;
    String curso;
    int calificacion;

    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre + " " + apellido);
        System.out.println("Curso: " + curso);
        System.out.println("Calificación: " + calificacion);
    }

    public void subirCalificacion(int puntos) {
        calificacion += puntos;
    }

    public void bajarCalificacion(int puntos) {
        calificacion -= puntos;
    }
}

// 2. Registro de Mascotas
class Mascota {
    String nombre;
    String especie;
    int edad;

    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Especie: " + especie);
        System.out.println("Edad: " + edad);
    }

    public void cumplirAnios() {
        edad++;
    }
}

// 3. Encapsulamiento con la Clase Libro
class Libro {
    private String titulo;
    private String autor;
    private int anioPublicacion;

    public Libro(String titulo, String autor, int anioPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        setAnioPublicacion(anioPublicacion);
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnioPublicacion() { return anioPublicacion; }

    public void setAnioPublicacion(int anio) {
        if (anio > 0) {
            anioPublicacion = anio;
        } else {
            System.out.println("Año de publicación inválido.");
        }
    }

    public void mostrarInfo() {
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Año de Publicación: " + anioPublicacion);
    }
}

// 4. Gestión de Gallinas en Granja Digital
class Gallina {
    int idGallina;
    int edad;
    int huevosPuestos;

    public Gallina(int idGallina) {
        this.idGallina = idGallina;
        this.edad = 0;
        this.huevosPuestos = 0;
    }

    public void ponerHuevo() {
        huevosPuestos++;
    }

    public void envejecer() {
        edad++;
    }

    public void mostrarEstado() {
        System.out.println("Gallina ID: " + idGallina);
        System.out.println("Edad: " + edad);
        System.out.println("Huevos puestos: " + huevosPuestos);
    }
}

// 5. Simulación de Nave Espacial
class NaveEspacial {
    String nombre;
    int combustible;
    final int MAX_COMBUSTIBLE = 100;

    public NaveEspacial(String nombre, int combustible) {
        this.nombre = nombre;
        this.combustible = combustible;
    }

    public void despegar() {
        if (combustible > 0) {
            System.out.println(nombre + " ha despegado.");
            combustible--;
        } else {
            System.out.println("No hay combustible suficiente para despegar.");
        }
    }

    public void avanzar(int distancia) {
        if (combustible >= distancia) {
            combustible -= distancia;
            System.out.println(nombre + " avanzó " + distancia + " unidades.");
        } else {
            System.out.println("No hay combustible suficiente para avanzar " + distancia + " unidades.");
        }
    }

    public void recargarCombustible(int cantidad) {
        if (combustible + cantidad <= MAX_COMBUSTIBLE) {
            combustible += cantidad;
            System.out.println(nombre + " recargó " + cantidad + " unidades de combustible.");
        } else {
            System.out.println("No se puede superar el límite de combustible.");
        }
    }

    public void mostrarEstado() {
        System.out.println("Nave: " + nombre);
        System.out.println("Combustible: " + combustible);
    }
}

// Clase principal para probar todos los ejercicios
public class Main {
    public static void main(String[] args) {

        // 1. Estudiante
        Estudiante est1 = new Estudiante();
        est1.nombre = "Juan";
        est1.apellido = "Pérez";
        est1.curso = "Matemática";
        est1.calificacion = 8;
        est1.mostrarInfo();
        est1.subirCalificacion(2);
        est1.bajarCalificacion(1);
        System.out.println("Después de ajustes:");
        est1.mostrarInfo();

        // 2. Mascota
        Mascota m1 = new Mascota();
        m1.nombre = "Fido";
        m1.especie = "Perro";
        m1.edad = 3;
        m1.mostrarInfo();
        m1.cumplirAnios();
        System.out.println("Después de cumplir años:");
        m1.mostrarInfo();

        // 3. Libro
        Libro libro1 = new Libro("Java Básico", "Ana López", 2022);
        libro1.setAnioPublicacion(-5); // inválido
        libro1.setAnioPublicacion(2023); // válido
        libro1.mostrarInfo();

        // 4. Gallinas
        Gallina g1 = new Gallina(1);
        Gallina g2 = new Gallina(2);
        g1.ponerHuevo();
        g1.envejecer();
        g2.ponerHuevo();
        g2.ponerHuevo();
        g2.envejecer();
        g1.mostrarEstado();
        g2.mostrarEstado();

        // 5. Nave Espacial
        NaveEspacial nave1 = new NaveEspacial("Apollo", 50);
        nave1.avanzar(30); // combustible insuficiente
        nave1.recargarCombustible(40);
        nave1.avanzar(30); // ahora sí
        nave1.mostrarEstado();
    }
}
