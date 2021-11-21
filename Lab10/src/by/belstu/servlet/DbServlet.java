package by.belstu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbServlet extends HttpServlet {
    private static Connection connection;
    private final String SELECT_ALL = "SELECT * FROM danil_table";
    private final String SELECT_WHERE = "SELECT * FROM danil_table WHERE value1 = ?";

    @Override
    public void init() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "123qweASD");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String whereParam = request.getParameter("where");

        if (whereParam == null) {
            try {
                PrintWriter pw = response.getWriter();

                try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL);
                     ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        pw.println(rs.getString("value1") + ": " + rs.getString("value2"));
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                PrintWriter pw = response.getWriter();

                try (PreparedStatement stmt = connection.prepareStatement(SELECT_WHERE)) {
                    stmt.setString(1, whereParam);
                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            pw.println(rs.getString("value1") + ": " + rs.getString("value2"));
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try (CallableStatement callableStatement = connection.prepareCall("call insert_data(?, ?, ?)")) {
            String param1 = request.getParameter("key");
            String param2 = request.getParameter("value");
            String param3Str = request.getParameter("parm");

            var param3 = Integer.parseInt(param3Str);
            if (param1 == null || param2 == null) {
                throw new Exception("add params");
            }

            callableStatement.setString(1, param1);
            callableStatement.setString(2, param2);
            callableStatement.setInt(3, param3);

            var result = callableStatement.executeQuery();

            result.next();

            var resultNumb = result.getInt("numb");

            System.out.println("Result number" +resultNumb);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            PrintWriter pw = response.getWriter();

            try (PreparedStatement stmt = connection.prepareStatement("select get_data()");
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    pw.println(rs.getObject(1));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}