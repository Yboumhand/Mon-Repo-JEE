package com.gestion.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    
    static {
        try {
            // Charger explicitement le fichier de configuration
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();
            
            System.out.println("✅ SessionFactory créée avec succès !");
            
        } catch (Throwable ex) {
            System.err.println("❌ Erreur lors de la création de SessionFactory");
            System.err.println("Message d'erreur: " + ex.getMessage());
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            throw new IllegalStateException("SessionFactory n'a pas été initialisée !");
        }
        return sessionFactory;
    }
    
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}