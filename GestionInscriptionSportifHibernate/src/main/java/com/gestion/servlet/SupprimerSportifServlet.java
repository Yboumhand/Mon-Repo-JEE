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

@WebServlet("/supprimerSportif")
public class SupprimerSportifServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String code = request.getParameter("code");
        
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            Sportif sportif = (Sportif) session.get(Sportif.class, code);
            
            if (sportif != null) {
                session.delete(sportif);
            }
            
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