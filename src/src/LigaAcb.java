import java.util.Scanner;

public class LigaAcb {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriba el nombre de la clasificacion:");
        String str = sc.nextLine();
        Clasificacion clasificacion = new Clasificacion(str);
        ClasificacionFileDao clasificacionDAO = new ClasificacionFileDao("./");



        while (true) {
            System.out.println("1. Añadir equipo");
            System.out.println("2. Mostrar clasificación");
            System.out.println("3. Guardar clasificación");
            System.out.println("4. Cargar clasificación");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del equipo: ");
                    String nombre = sc.nextLine();
                    System.out.print("Victorias: ");
                    int victorias = sc.nextInt();
                    System.out.print("Derrotas: ");
                    int derrotas = sc.nextInt();
                    System.out.print("Puntos a favor: ");
                    int puntosAfavor = sc.nextInt();
                    System.out.print("Puntos en contra: ");
                    int puntosEnContra = sc.nextInt();

                    Equipo equipo = new Equipo(nombre, victorias, derrotas, puntosAfavor, puntosEnContra);
                    clasificacion.addEquipo(equipo);
                    break;

                case 2:
                    System.out.println(clasificacion);
                    break;

                case 3:
                    System.out.println();
                    System.out.println();
                    clasificacionDAO.save(clasificacion);
                    System.out.println("Clasificación guardada.");
                    System.out.println();
                    System.out.println();
                    break;

                case 4:
                    System.out.println();
                    System.out.println();
                    clasificacion = clasificacionDAO.get(clasificacion.getCompeticion());
                    System.out.println("Clasificación cargada.");
                    System.out.println();
                    System.out.println();
                    break;

                case 5:
                    System.out.println("¿Está seguro de que quiere salir? (s/n)");
                    String confirmacion = sc.nextLine();
                    if (confirmacion.equalsIgnoreCase("s")) {
                        System.exit(0);
                    }
                    break;

                default:
                    System.out.println();
                    System.out.println();
                    System.out.println("Opción no válida.");
                    System.out.println();
                    System.out.println();
            }
        }
    }
}


//        Equipo e1 = new Equipo("Real Madrid", 18, 5, 1920, 1695);
//        Equipo e2 = new Equipo("FC Barcelona", 22, 2, 1785, 1585);
//        Equipo e3 = new Equipo("Manresa", 15, 7, 1903, 1808);
//        Equipo e4 = new Equipo("Valencia", 14, 8, 1834, 1760);
//        Equipo e5 = new Equipo("Obradoiro", 23, 1, 2834, 1560);
//        Equipo e6 = new Equipo("Obradoiro", 26, 1, 2834, 1560);
//
//        EquipoFileDao equipoFileDao = new EquipoFileDao("Liga ACB.dat");
//        equipoFileDao.save(e1);
//        equipoFileDao.save(e2);
//        equipoFileDao.save(e3);
//        equipoFileDao.save(e4);
//        equipoFileDao.save(e5);
//        equipoFileDao.update(e6);
//
//        equipoFileDao.delete(new Equipo("Real Madrid"));
//
//        System.out.println(equipoFileDao.get("Obradoiro"));
//
//
//        equipoFileDao.getAll().forEach(System.out::println);

