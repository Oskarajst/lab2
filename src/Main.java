
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author oskar
 */
public class Main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        boolean programaAbierto = true;
        Caso casoActual = null;

        String menu ="\n1. Nuevo caso" +
                "\n2. Registrar Ubicacion" +
                "\n3. Consultar Ubicaciones" +
                "\n4. Consultar una ubicacion" +
                "\n5. Modificar una ubicacion" +
                "\n6. Descartar Ubicacion" +
                "\n7. Registrar Pista" +
                "\n8. Consultar Pistas" +
                "\n9. Buscar Pista" +
                "\n10. Modificar Pista" +
                "\n11. Eliminar Pista" +
                "\n12. Mostrar reporte de investigacion" +
                "\n13. Salir" ;

        try {

            while (programaAbierto) {

                System.out.print(menu);

                int opcion = pedirNumero(scanner, "", 1, 13, false);

                switch (opcion) {

                    case 1: {

                        String nombreCaso = pedirTexto(
                                scanner,
                                "Ingrese el nombre del caso: ",
                                "El nombre del caso no puede estar vacio."
                        );

                        if (cancelar(nombreCaso)) {
                            operacionCancelada(scanner);
                            break;
                        }

                        String codigoCaso = pedirTexto(
                                scanner,
                                "Ingrese el codigo del caso: ",
                                "El codigo del caso no puede estar vacio."
                        );

                        if (cancelar(codigoCaso)) {
                            operacionCancelada(scanner);
                            break;
                        }

                        String nombreDetective = pedirTexto(
                                scanner,
                                "Ingrese el detective del caso: ",
                                "El nombre del detective del caso no puede estar vacio."
                        );

                        if (cancelar(nombreDetective)) {
                            operacionCancelada(scanner);
                            break;
                        }

                        casoActual = new Caso(
                                nombreCaso,
                                codigoCaso,
                                nombreDetective
                        );

                        System.out.println("Caso registrado.");
                        pausar(scanner);

                        break;
                    }

                    case 2: {

                        if (!hayCaso(casoActual, scanner)) {
                            break;
                        }

                        String codigo = pedirTexto(
                                scanner,
                                "Ingrese el código de la ubicacion: ",
                                "El código de la ubicación no puede estar vacio."
                        );

                        if (cancelar(codigo)) {
                            operacionCancelada(scanner);
                            break;
                        }

                        String nombre = pedirTexto(
                                scanner,
                                "Ingrese el nombre de la ubicacion: ",
                                "El nombre de la ubicación no puede estar vacio."
                        );

                        if (cancelar(nombre)) {
                            operacionCancelada(scanner);
                            break;
                        }

                        String direccion = pedirTexto(
                                scanner,
                                "Ingrese la dirección de la ubicacion: ",
                                "La direccion de la ubicación no puede estar vacia."
                        );

                        if (cancelar(direccion)) {
                            operacionCancelada(scanner);
                            break;
                        }

                        int nivelRiesgo = pedirNumero(
                                scanner,
                                "Ingrese el nivel de riesgo de la ubicacion (1-10): ",
                                1,
                                10,
                                true
                        );

                        if (nivelRiesgo == -1) {
                            operacionCancelada(scanner);
                            break;
                        }

                        String estado = pedirTexto(
                                scanner,
                                "Ingrese el estado de la ubicacion: ",
                                "El estado de la ubicación no puede estar vacio."
                        );

                        if (cancelar(estado)) {
                            operacionCancelada(scanner);
                            break;
                        }

                        int posicionUbicacion;

                        while (true) {

                            posicionUbicacion = pedirNumero(
                                    scanner,
                                    "Ingrese la posicion en la que desea registrar la ubicación (1-5): ",
                                    1,
                                    5,
                                    true
                            );

                            if (posicionUbicacion == -1) {
                                break;
                            }

                            if (casoActual.obtenerUbicacion(posicionUbicacion - 1) != null) {
                                System.out.println("Ya existe una ubicacion en esa posicion.");
                            } else {
                                break;
                            }
                        }

                        if (posicionUbicacion == -1) {
                            operacionCancelada(scanner);
                            break;
                        }

                        Ubicacion ubicacionNueva = new Ubicacion(
                                codigo,
                                nombre,
                                direccion,
                                nivelRiesgo,
                                estado
                        );

                        if (casoActual.registrarUbicacion(
                                posicionUbicacion - 1,
                                ubicacionNueva)) {

                            System.out.println("Ubicación registrada.");

                        } else {

                            System.out.println("No se pudo registrar la ubicacion.");
                        }

                        pausar(scanner);

                        break;
                    }

                    case 3: {

                        if (!hayCaso(casoActual, scanner)) {
                            break;
                        }

                        if (casoActual.cantidadUbicacionesRegistradas() == 0) {

                            System.out.println("No hay ubicaciones registradas.");

                        } else {

                            System.out.println("Ubicaciones registradas:");
                            casoActual.mostrarUbicaciones();
                        }

                        pausar(scanner);

                        break;
                    }

                    case 4: {

                        if (!hayCaso(casoActual, scanner)) {
                            break;
                        }

                        int posicionUbicacion;

                        while (true) {

                            posicionUbicacion = pedirNumero(
                                    scanner,
                                    "Ingrese la posicion de la ubicacion que desea consultar (1-5): ",
                                    1,
                                    5,
                                    true
                            );

                            if (posicionUbicacion == -1) {
                                break;
                            }

                            if (casoActual.obtenerUbicacion(posicionUbicacion - 1) == null) {

                                System.out.println("No se encontró ninguna ubicacion en la posicion ingresada.");
                                System.out.println("Si desea cancelar escriba '-1'.");

                            } else {
                                break;
                            }
                        }

                        if (posicionUbicacion == -1) {
                            operacionCancelada(scanner);
                            break;
                        }

                        Ubicacion ubicacionEncontrada =
                                casoActual.obtenerUbicacion(posicionUbicacion - 1);

                        System.out.println("Ubicacion encontrada:");
                        System.out.println(ubicacionEncontrada);

                        pausar(scanner);

                        break;
                    }

                    case 5: {

                        if (!hayCaso(casoActual, scanner)) {
                            break;
                        }

                        int posicionUbicacion;

                        while (true) {

                            posicionUbicacion = pedirNumero(
                                    scanner,
                                    "Ingrese la posición de la ubicación que desea modificar (1-5): ",
                                    1,
                                    5,
                                    true
                            );

                            if (posicionUbicacion == -1) {
                                break;
                            }

                            if (casoActual.obtenerUbicacion(posicionUbicacion - 1) == null) {

                                System.out.println("No se encontro ninguna ubicacion en la posicion ingresada.");

                            } else {
                                break;
                            }
                        }

                        if (posicionUbicacion == -1) {
                            operacionCancelada(scanner);
                            break;
                        }

                        int nuevoNivelRiesgo = pedirNumero(
                                scanner,
                                "Ingrese el nuevo nivel de riesgo de la ubicacion (1-10): ",
                                1,
                                10,
                                true
                        );

                        if (nuevoNivelRiesgo == -1) {
                            operacionCancelada(scanner);
                            break;
                        }

                        String nuevoEstado = pedirTexto(
                                scanner,
                                "Ingrese el nuevo estado de la ubicacion: ",
                                "El estado de la ubicación no puede estar vacío."
                        );

                        if (cancelar(nuevoEstado)) {
                            operacionCancelada(scanner);
                            break;
                        }

                        if (casoActual.modificarUbicacion(
                                posicionUbicacion - 1,
                                nuevoNivelRiesgo,
                                nuevoEstado)) {

                            System.out.println("Ubicacion modificada exitosamente.");

                        } else {

                            System.out.println("No se pudo modificar la ubicacion.");
                        }

                        pausar(scanner);

                        break;
                    }

                    case 6: {

                        if (!hayCaso(casoActual, scanner)) {
                            break;
                        }

                        int posicionUbicacion;

                        while (true) {

                            posicionUbicacion = pedirNumero(
                                    scanner,
                                    "Ingrese la posicion de la ubicacion que desea descartar (1-5): ",
                                    1,
                                    5,
                                    true
                            );

                            if (posicionUbicacion == -1) {
                                break;
                            }

                            if (casoActual.obtenerUbicacion(posicionUbicacion - 1) == null) {

                                System.out.println();
                                System.out.println("No se encontro ninguna ubicacion en la posicion ingresada.");

                            } else {
                                break;
                            }
                        }

                        if (posicionUbicacion == -1) {
                            operacionCancelada(scanner);
                            break;
                        }

                        if (casoActual.descartarUbicacion(posicionUbicacion - 1)) {

                            System.out.println("Ubicacion descartada.");

                        } else {

                            System.out.println("No se pudo descartar la ubicacion.");
                        }

                        pausar(scanner);

                        break;
                    }

                    case 7: {

                        if (!hayCaso(casoActual, scanner)) {
                            break;
                        }

                        String codigoPista;

                        while (true) {

                            codigoPista = pedirTexto(
                                    scanner,
                                    "Ingrese el codigo de la pista que desea registrar: ",
                                    "El codigo de la pista no puede estar vacio."
                            );

                            if (cancelar(codigoPista)) {
                                break;
                            }

                            if (casoActual.obtenerPista(codigoPista) != null) {

                                System.out.println("Ya existe una pista con el codigo ingresado.");

                            } else {
                                break;
                            }
                        }

                        if (cancelar(codigoPista)) {
                            operacionCancelada(scanner);
                            break;
                        }

                        String descripcionPista = pedirTexto(
                                scanner,
                                "Ingrese la descripcion de la pista: ",
                                "La descripcion de la pista no puede estar vacia."
                        );

                        if (cancelar(descripcionPista)) {
                            operacionCancelada(scanner);
                            break;
                        }

                        String tipoEvidencia = pedirTexto(
                                scanner,
                                "Ingrese el tipo de evidencia de la pista: ",
                                "El tipo de evidencia de la pista no puede estar vacio."
                        );

                        if (cancelar(tipoEvidencia)) {
                            operacionCancelada(scanner);
                            break;
                        }

                        int nivelImportancia = pedirNumero(
                                scanner,
                                "Ingrese el nivel de importancia de la pista (1-10): ",
                                1,
                                10,
                                true
                        );

                        if (nivelImportancia == -1) {
                            operacionCancelada(scanner);
                            break;
                        }

                        int nivelConfiabilidad = pedirNumero(
                                scanner,
                                "Ingrese el nivel de confiabilidad de la pista (0-100): ",
                                0,
                                100,
                                true
                        );

                        if (nivelConfiabilidad == -1) {
                            operacionCancelada(scanner);
                            break;
                        }

                        Pista pistaNueva = new Pista(
                                codigoPista,
                                descripcionPista,
                                tipoEvidencia,
                                nivelImportancia,
                                nivelConfiabilidad
                        );

                        if (casoActual.registrarPista(pistaNueva)) {

                            System.out.println("Pista registrada.");

                        } else {

                            System.out.println("No se pudo registrar la pista.");
                        }

                        pausar(scanner);

                        break;
                    }

                    case 8: {

                        if (!hayCaso(casoActual, scanner)) {
                            break;
                        }

                        System.out.println("Pistas registradas:");
               
                        if (!casoActual.mostrarPistas()) {
                            System.out.println("No hay pistas registradas.");
                        }

                        pausar(scanner);

                        break;
                    }

                    case 9: {

                        if (!hayCaso(casoActual, scanner)) {
                            break;
                        }

                        String codigoPista;

                        while (true) {

                            codigoPista = pedirTexto(
                                    scanner,
                                    "Ingrese el codigo de la pista que desea buscar: ",
                                    "El codigo de la pista no puede estar vacio."
                            );

                            if (cancelar(codigoPista)) {
                                break;
                            }

                            if (casoActual.obtenerPista(codigoPista) == null) {

                                System.out.println("No se encontro ninguna pista con el codigo ingresado.");

                            } else {
                                break;
                            }
                        }

                        if (cancelar(codigoPista)) {
                            operacionCancelada(scanner);
                            break;
                        }

                        Pista pistaEncontrada =
                                casoActual.obtenerPista(codigoPista);

                        System.out.println("Pista encontrada:");
                        System.out.println(pistaEncontrada);

                        pausar(scanner);

                        break;
                    }

                    case 10: {

                        if (!hayCaso(casoActual, scanner)) {
                            break;
                        }

                        String codigoPista;

                        while (true) {

                            codigoPista = pedirTexto(
                                    scanner,
                                    "Ingrese el codigo de la pista que desea modificar: ",
                                    "El codigo de la pista no puede estar vacio."
                            );

                            if (cancelar(codigoPista)) {
                                break;
                            }

                            if (casoActual.obtenerPista(codigoPista) == null) {

                                System.out.println("No se encontro ninguna pista con el codigo ingresado.");

                            } else {
                                break;
                            }
                        }

                        if (cancelar(codigoPista)) {
                            operacionCancelada(scanner);
                            break;
                        }

                        Pista pistaEncontrada =
                                casoActual.obtenerPista(codigoPista);

                        System.out.println("Pista encontrada:");
                        System.out.println(pistaEncontrada);

                        String descripcionPista = pedirTexto(
                                scanner,
                                "Ingrese la nueva descripción de la pista: ",
                                "La descripcion de la pista no puede estar vacia."
                        );

                        if (cancelar(descripcionPista)) {
                            operacionCancelada(scanner);
                            break;
                        }

                        String tipoEvidencia = pedirTexto(
                                scanner,
                                "Ingrese el nuevo tipo de evidencia de la pista: ",
                                "El tipo de evidencia de la pista no puede estar vacio."
                        );

                        if (cancelar(tipoEvidencia)) {
                            operacionCancelada(scanner);
                            break;
                        }

                        int nivelImportancia = pedirNumero(
                                scanner,
                                "Ingrese el nuevo nivel de importancia de la pista (1-10): ",
                                1,
                                10,
                                true
                        );

                        if (nivelImportancia == -1) {
                            operacionCancelada(scanner);
                            break;
                        }

                        int nivelConfiabilidad = pedirNumero(
                                scanner,
                                "Ingrese el nuevo nivel de confiabilidad de la pista (0-100): ",
                                0,
                                100,
                                true
                        );

                        if (nivelConfiabilidad == -1) {
                            operacionCancelada(scanner);
                            break;
                        }

                        if (casoActual.modificarPista(
                                codigoPista,
                                descripcionPista,
                                tipoEvidencia,
                                nivelImportancia,
                                nivelConfiabilidad)) {

                            System.out.println("Pista modificada exitosamente.");

                        } else {

                            System.out.println("No se pudo modificar la pista.");
                        }

                        pausar(scanner);

                        break;
                    }

                    case 11: {

                        if (!hayCaso(casoActual, scanner)) {
                            break;
                        }

                        String codigoPista;

                        while (true) {

                            codigoPista = pedirTexto(
                                    scanner,
                                    "Ingrese el código de la pista que desea eliminar: ",
                                    "El codigo de la pista no puede estar vacio."
                            );

                            if (cancelar(codigoPista)) {
                                break;
                            }

                            if (casoActual.obtenerPista(codigoPista) == null) {

                                System.out.println();
                                System.out.println("No se encontro ninguna pista con el codigo ingresado.");

                            } else {
                                break;
                            }
                        }

                        if (cancelar(codigoPista)) {
                            operacionCancelada(scanner);
                            break;
                        }

                        if (casoActual.eliminarPista(codigoPista)) {

                            System.out.println("Pista eliminada exitosamente.");

                        } else {

                            System.out.println("No se pudo eliminar la pista.");
                        }

                        pausar(scanner);

                        break;
                    }

                    case 12: {

                        if (!hayCaso(casoActual, scanner)) {
                            break;
                        }

                        int cantidadUbicaciones =
                                casoActual.cantidadUbicacionesRegistradas();

                        int cantidadEspaciosUbicaciones =
                                casoActual.cantidadDeEspaciosDeUbicaciones();

                        int cantidadPistas =
                                casoActual.cantidadPistasRegistradas();

                        Ubicacion ubicacionMasPeligrosa =
                                casoActual.ubicacionMasPeligrosa();

                        Pista pistaMasConfiable =
                                casoActual.pistaMasConfiable();

                        Pista pistaMasImportante =
                                casoActual.pistaMasImportante();

                        double promedioNivelImportancia =
                                casoActual.promedioNivelImportancia();

                        System.out.println("Cantidad de ubicaciones registradas: "+ cantidadUbicaciones
                        );

                        System.out.println(
                                "Cantidad de espacios disponibles para registrar ubicaciones: "
                                        + cantidadEspaciosUbicaciones
                        );

                        System.out.println(
                                "Cantidad de pistas registradas: "
                                        + cantidadPistas
                        );

                        if (ubicacionMasPeligrosa != null) {


                            System.out.println("Ubicacion más peligrosa: ");
                            System.out.println(ubicacionMasPeligrosa);

                        } else {

                            System.out.println(
                                    "Ubicacion más peligrosa: No hay ubicaciones registradas."
                            );
                        }

                        if (pistaMasConfiable != null) {

   
                            System.out.println("Pista más confiable: ");
                            System.out.println(pistaMasConfiable);

                        } else {

                            System.out.println(
                                    "Pista más confiable: No hay pistas registradas."
                            );
                        }

                        if (pistaMasImportante != null) {

                            System.out.println("Pista más importante: ");
                            System.out.println(pistaMasImportante);

                        } else {

                            System.out.println(
                                    "Pista más importante: No hay pistas registradas."
                            );
                        }

                        System.out.println(
                                "Promedio del nivel de importancia de las pistas: "
                                        + promedioNivelImportancia
                        );


                        pausar(scanner);

                        break;
                    }

                    case 13: {

                        programaAbierto = false;

                        break;
                    }
                }
            }

        } finally {
            scanner.close();
        }

        System.out.println("PROGRAMA FINALIZADO");
    }


    public static String pedirTexto(
            Scanner scanner,
            String mensaje,
            String mensajeError) {

        while (true) {

            System.out.print(mensaje);

            String texto = scanner.nextLine().trim();

            if (!texto.isEmpty()) {
                return texto;
            }

            System.out.println("Error: " + mensajeError);
            System.out.println("Si desea cancelar escriba 'cancelar'.");
            System.out.println("Intente de nuevo.");
        }
    }


    public static int pedirNumero(
            Scanner scanner,
            String mensaje,
            int minimo,
            int maximo,
            boolean permitirCancelar) {

        while (true) {

            try {

                if (!mensaje.isEmpty()) {
                    System.out.print(mensaje);
                }

                int numero = scanner.nextInt();
                scanner.nextLine();

                if (permitirCancelar && numero == -1) {
                    return -1;
                }

                if (numero < minimo || numero > maximo) {
                    throw new IllegalArgumentException();
                }

                return numero;

            } catch (IllegalArgumentException e) {

                System.out.println(
                        "Ingrese un numero entre "
                                + minimo + " y " + maximo + "."
                );

                if (permitirCancelar) {
                    System.out.println(
                            "Si desea cancelar escriba '-1'."
                    );
                }

            } catch (InputMismatchException e) {

                scanner.nextLine();


                System.out.println(
                        "Error: Ingrese un numero válido."
                );

                if (permitirCancelar) {
                    System.out.println(
                            "Si desea cancelar escriba '-1'."
                    );
                }
            }
        }
    }


    public static boolean cancelar(String texto) {
        return texto.equalsIgnoreCase("cancelar");
    }


    public static boolean hayCaso(
            Caso casoActual,
            Scanner scanner) {

        if (casoActual == null) {

            System.out.println(
                    "No hay un caso activo. Por favor, cree un nuevo caso primero."
            );

            pausar(scanner);

            return false;
        }

        return true;
    }

    public static void operacionCancelada(Scanner scanner) {


        System.out.println(
                "Operación cancelada." 
        );

        pausar(scanner);
    }

    public static void pausar(Scanner scanner) {


        System.out.println(
                "PRESIONA ENTER PARA REGRESAR AL MENU."
        );

        scanner.nextLine();
    }
}


