package com.example.testdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        resp.getWriter().append("Servlet at: ").append(req.getContextPath());

        String user = "root";
        String pass  = "Ayushi@123";
        String jdbcUrl = "jdbc:mysql://localhost:3306/hb-05-many-to-many?useSSL=false&amp;serverTimezone=UTC";
        String driver = "com.mysql.jdbc.Driver";

        try {

            PrintWriter out = resp.getWriter();
            out.println("Connecting to database : " + jdbcUrl);
            Class.forName(driver);
            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);

            out.println("Success!");

            myConn.close();

        }
        catch (Exception ex){
            ex.printStackTrace();
            throw new ServletException(ex);
        }
    }

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/hb-05-many-to-many?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "Ayushi@123";
        try {
            System.out.println("Connecting to database " + jdbcUrl);
            Connection myConn = DriverManager.getConnection(jdbcUrl,user,pass);

            System.out.println("connection success!!!!");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
