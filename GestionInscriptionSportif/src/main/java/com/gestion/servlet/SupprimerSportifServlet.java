package com.gestion.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.gestion.util.DatabaseConnection;

@WebServlet("/supprimerSportif")
public class SupprimerSportifServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String code = request.getParameter("code");
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM Sportif WHERE code = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, code);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        response.sendRedirect("index.jsp");
    }
}