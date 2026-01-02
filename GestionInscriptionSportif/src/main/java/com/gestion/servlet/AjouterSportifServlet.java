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

@WebServlet("/ajouterSportif")
public class AjouterSportifServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        String code = request.getParameter("code");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String age = request.getParameter("age");
        String sexe = request.getParameter("sexe");
        String pays = request.getParameter("pays");
        String[] disciplinesArray = request.getParameterValues("discipline");
        
        String discipline = "";
        if (disciplinesArray != null) {
            discipline = String.join(", ", disciplinesArray);
        }
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Sportif (code, nom, prenom, age, sexe, pays, discipline) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, code);
            stmt.setString(2, nom);
            stmt.setString(3, prenom);
            stmt.setInt(4, Integer.parseInt(age));
            stmt.setString(5, sexe);
            stmt.setString(6, pays);
            stmt.setString(7, discipline);
            
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        response.sendRedirect("index.jsp");
    }
}