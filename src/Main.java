import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "RIBERA";
        String pass = "ribera";

        String sql = "INSERT INTO EMPLEADO (id, salario) VALUES (?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = con.prepareStatement(sql)) {

            con.setAutoCommit(false); // asi hago la transacción manual

            try {
                ps.setInt(1, 10); ps.setInt(2, 1500); ps.executeUpdate();
                ps.setInt(1, 11); ps.setInt(2, 1500); ps.executeUpdate();
                ps.setInt(1, 12); ps.setInt(2, 1500); ps.executeUpdate();
                ps.setInt(1, 13); ps.setInt(2, 2000); ps.executeUpdate();
                ps.setInt(1, 14); ps.setInt(2, 2000); ps.executeUpdate();
                ps.setInt(1, 15); ps.setInt(2, 3000); ps.executeUpdate();

                con.commit(); // hago commit
                System.out.println("los empleados insertados correctamente.");

            } catch (SQLException e) {
                System.out.println("error hay que hacer un rollback.");
                con.rollback();
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}