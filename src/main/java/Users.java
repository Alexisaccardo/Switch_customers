import java.sql.SQLException;
import java.util.Scanner;

public class Users {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*****GESTION DE REGISTROS*****");

        String error_null = "No se admiten datos vacios o nulos";
        String error_search = "No se encontraron registros";

        boolean inicio = true;

        while (inicio) {

            System.out.println("1. Registrar clientes");
            System.out.println("2. Editar clientes");
            System.out.println("3. Consultar un cliente");
            System.out.println("4. Consultar todos los clientes");
            System.out.println("5. Eliminar clientes");
            System.out.println("6. terminar");

            System.out.println("Ingrese un numero entre 1 - 6: ");

            int respuesta = Integer.parseInt(scanner.nextLine());

            switch (respuesta) {
                case 1:

                    System.out.println("Deseas registrar un cliente?: ");
                    String n = scanner.nextLine();

                    while (n.equals("si")) {

                        System.out.print("Ingrese documento del cliente: ");
                        String document = scanner.nextLine();

                        System.out.print("Ingrese nombre del cliente: ");
                        String name = scanner.nextLine();

                        System.out.print("Ingrese su numero de celular: ");
                        String cellphone = scanner.nextLine();

                        System.out.print("Ingrese su direccion: ");
                        String address = scanner.nextLine();

                        System.out.print("Deseas registrar otro cliente?: ");
                        n = scanner.nextLine();

                        if (document == null || document.equals("") || document.length() < 0 || name == null || name.equals("") || name.length() < 0 ||
                                cellphone == null || cellphone.equals("") || cellphone.length() < 0 || address == null || address.equals("") ||
                                address.length() < 0){

                            System.out.println(error_null);
                        }else {

                            BD bd = new BD();
                            Customers customers = new Customers(document, name, cellphone, address);
                            BD.Insert(customers); //
                        }
                    }
                        break;

                        case 2:

                            System.out.print("Ingrese documento del cliente que deseas editar: ");
                            String document = scanner.nextLine();

                            System.out.print("Ingrese el nombre actualizado del cliente: ");
                            String name = scanner.nextLine();

                            System.out.print("Ingrese el numero de celular actualizado: ");
                            String cellphone = scanner.nextLine();

                            System.out.print("Ingrese su direccion actualizada: ");
                            String address = scanner.nextLine();

                            if (document == null || document.equals("") || document.length() < 0 || name == null || name.equals("") || name.length() < 0 ||
                                    cellphone == null || cellphone.equals("") || cellphone.length() < 0 || address == null || address.equals("") ||
                                    address.length() < 0) {

                                System.out.println(error_null);
                            }else {
                                BD bd = new BD();
                                Customers customers = new Customers(document, name, cellphone, address);
                                BD.Edit(customers);
                            }

                            break;

                        case 3:
                            System.out.println("Ingrese documento del cliente a consultar: ");
                            document = scanner.nextLine();

                            System.out.println("Este es el registro del codigo ingresado: ");

                            BD bd = new BD();
                            BD.Search_customer(document);


                            break;

                        case 4:

                            bd = new BD();
                            BD.Search();

                            break;

                        case 5:
                            System.out.println("Ingrese el numero de documento del cliente que deseas eliminar: ");
                            document = scanner.nextLine();

                            bd = new BD();
                            BD.Delete(document);

                            break;

                        case 6:

                            System.out.println("Finalizando...");

                            inicio = false;

                            break;

                        default:
                            System.out.println("Ingrese un caso valido");
                    }
            }
        }
    }

