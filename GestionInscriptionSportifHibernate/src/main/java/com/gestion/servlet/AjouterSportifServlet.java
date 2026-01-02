package com.gestion.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.gestion.model.Sportif;
import com.gestion.util.HibernateUtil;

@WebServlet("/ajouterSportif")
public class AjouterSportifServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        String code = request.getParameter("code");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        int age = Integer.parseInt(request.getParameter("age"));
        String sexe = request.getParameter("sexe");
        String pays = request.getParameter("pays");
        String[] disciplinesArray = request.getParameterValues("discipline");
        
        String discipline = "";
        if (disciplinesArray != null) {
            discipline = String.join(", ", disciplinesArray);
        }
        
        Sportif sportif = new Sportif(code, nom, prenom, age, sexe, pays, discipline);
        
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(sportif);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        response.sendRedirect("index.jsp");
    }
}