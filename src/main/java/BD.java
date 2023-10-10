import java.sql.*;

public class BD {
    public BD() {
    }



    public static void Insert(String document, String name, String cellphone, String address) {

        String error_register = "No se pudo registrar el cliente.";

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/customers";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");


            // Sentencia INSERT
            String sql = "INSERT INTO customer (document, name, cellphone, address) VALUES (?, ?, ?, ?)";

            // Preparar la sentencia
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, document);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, cellphone);
            preparedStatement.setString(4, address);

            // Ejecutar la sentencia
            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Cliente registrado de manera exitosa.");
            } else {
                System.out.println(error_register);
            }

            preparedStatement.close();
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void Edit(String document, String name, String cellphone, String address) throws ClassNotFoundException, SQLException {

        String error_edit = "No se encontro un cliente registrado con el documento ingresado";

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/customers";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        String consulta = "UPDATE customer SET name = ?, cellphone = ?, address = ? WHERE document = ?";
        PreparedStatement preparedStatement = connection2.prepareStatement(consulta);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, cellphone);
        preparedStatement.setString(3, address);
        preparedStatement.setString(4, document);

        int filasActualizadas = preparedStatement.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Los datos del cliente se actualizaron correctamente");
        } else {
            System.out.println(error_edit);
        }

        preparedStatement.close();
        connection2.close();
    }

    public static void Search_customer(String document) throws ClassNotFoundException, SQLException {

        String error_search_customer = "No se encontro un cliente registrado con este documento.";

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/customers";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consultaSQL = "SELECT * FROM customer WHERE document = ?";

        PreparedStatement statement = connection.prepareStatement(consultaSQL);
        statement.setString(1, document); // Establecer el valor del parámetro

        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();

        // Procesar el resultado si existe
        if (resultSet.next()) {

            document = resultSet.getString("document");
            String name = resultSet.getString("name");
            String cellphone = resultSet.getString("cellphone");
            String address = resultSet.getString("address");


            System.out.println("Este es el documento del cliente a consultar: " + document + " Con nombre: " + name + " Celular: " + cellphone + " su direccion: " + address);

        } else {
            System.out.println(error_search_customer);
        }

        // Cerrar recursos
        resultSet.close();
        statement.close();
        connection.close();

    }

    public static void Search() throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/customers";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM customer");

        while(resultSet2.next()){

            String document = resultSet2.getString("document");
            String name = resultSet2.getString("name");
            String cellphone = resultSet2.getString("cellphone");
            String address = resultSet2.getString("address");

            System.out.println("Este es el documento del cliente: " + document + " Con nombre: " + name + " Celular: " + cellphone + " su direccion: " + address);
        }
    }

    public static void Delete(String document) throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/customers";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        String sentenciaSQL = "DELETE FROM customer WHERE document = ?";
        PreparedStatement prepareStatement = connection2.prepareStatement(sentenciaSQL);
        prepareStatement.setString(1, document);
        prepareStatement.executeUpdate();

        System.out.println("Cliente eliminado de manera correcta");
    }
}

