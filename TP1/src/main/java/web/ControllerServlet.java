package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/submit")
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. CONFIGURATION DE L'ENCODAGE
        // Important pour gérer les caractères accentués (é, à, ç, etc.)
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        // 2. RÉCUPÉRATION DES DONNÉES DU FORMULAIRE
        // Chaque getParameter() correspond à un attribut name="" dans le HTML
        
        // Checkbox - retourne "oui" si cochée, null si non cochée
        String contactPrincipal = request.getParameter("contactPrincipal");
        boolean estContactPrincipal = "oui".equals(contactPrincipal);
        
        // Champs select
        String titre = request.getParameter("titre");
        String langue = request.getParameter("langue");
        
        // Champs texte simples
        String fonction = request.getParameter("fonction");
        String prenom = request.getParameter("prenom");
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        
        // Champs téléphone
        String gsm = request.getParameter("gsm");
        String telephone = request.getParameter("telephone");
        String fax = request.getParameter("fax");
        
        // Zone de texte
        String noteContact = request.getParameter("noteContact");
        
        // 3. GÉNÉRATION DE LA PAGE HTML DE RÉPONSE
        // PrintWriter permet d'écrire du HTML directement dans la réponse HTTP
        PrintWriter out = response.getWriter();
        
        // Construction de la page HTML
        out.println("<!DOCTYPE html>");
        out.println("<html lang='fr'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Contact Enregistré</title>");
        out.println("    <link rel='stylesheet' href='css/bootstrap.min.css'>");
        out.println("    <style>");
        out.println("        body {");
        out.println("            background-color: #f5f5f5;");
        out.println("            padding: 40px 20px;");
        out.println("        }");
        out.println("        .result-container {");
        out.println("            background: white;");
        out.println("            border: 1px solid #ddd;");
        out.println("            border-radius: 4px;");
        out.println("            padding: 40px;");
        out.println("            max-width: 800px;");
        out.println("            margin: 0 auto;");
        out.println("            box-shadow: 0 2px 8px rgba(0,0,0,0.1);");
        out.println("        }");
        out.println("        .success-header {");
        out.println("            background-color: #d4edda;");
        out.println("            border: 1px solid #c3e6cb;");
        out.println("            color: #155724;");
        out.println("            padding: 15px;");
        out.println("            border-radius: 4px;");
        out.println("            margin-bottom: 30px;");
        out.println("            text-align: center;");
        out.println("        }");
        out.println("        .info-table {");
        out.println("            width: 100%;");
        out.println("            border-collapse: collapse;");
        out.println("        }");
        out.println("        .info-table td {");
        out.println("            padding: 12px;");
        out.println("            border-bottom: 1px solid #eee;");
        out.println("        }");
        out.println("        .info-label {");
        out.println("            font-weight: bold;");
        out.println("            color: #555;");
        out.println("            width: 200px;");
        out.println("        }");
        out.println("        .info-value {");
        out.println("            color: #333;");
        out.println("        }");
        out.println("        .badge-principal {");
        out.println("            background-color: #007bff;");
        out.println("            color: white;");
        out.println("            padding: 4px 10px;");
        out.println("            border-radius: 3px;");
        out.println("            font-size: 12px;");
        out.println("        }");
        out.println("        .btn-back {");
        out.println("            background-color: #007bff;");
        out.println("            color: white;");
        out.println("            padding: 10px 25px;");
        out.println("            border: none;");
        out.println("            border-radius: 3px;");
        out.println("            text-decoration: none;");
        out.println("            display: inline-block;");
        out.println("            margin-top: 20px;");
        out.println("        }");
        out.println("        .btn-back:hover {");
        out.println("            background-color: #0056b3;");
        out.println("            color: white;");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");
        out.println("        <div class='result-container'>");
        
        // En-tête de succès
        out.println("            <div class='success-header'>");
        out.println("                <h4 style='margin: 0;'>✓ Contact enregistré avec succès</h4>");
        out.println("            </div>");
        
        out.println("            <h2 style='margin-bottom: 25px; color: #333;'>Informations du contact</h2>");
        
        // Tableau des informations
        out.println("            <table class='info-table'>");
        
        // Contact principal
        if (estContactPrincipal) {
            out.println("            <tr>");
            out.println("                <td class='info-label'>Statut :</td>");
            out.println("                <td class='info-value'><span class='badge-principal'>Contact Principal</span></td>");
            out.println("            </tr>");
        }
        
        // Titre
        out.println("            <tr>");
        out.println("                <td class='info-label'>Titre :</td>");
        out.println("                <td class='info-value'>" + (titre != null ? titre : "-") + "</td>");
        out.println("            </tr>");
        
        // Nom complet
        out.println("            <tr>");
        out.println("                <td class='info-label'>Nom complet :</td>");
        out.println("                <td class='info-value'>" + (titre != null ? titre + " " : "") + 
                    (prenom != null ? prenom : "") + " " + 
                    (nom != null ? nom : "") + "</td>");
        out.println("            </tr>");
        
        // Fonction
        out.println("            <tr>");
        out.println("                <td class='info-label'>Fonction :</td>");
        out.println("                <td class='info-value'>" + 
                    (fonction != null && !fonction.isEmpty() ? fonction : "Non renseignée") + "</td>");
        out.println("            </tr>");
        
        // Langue
        out.println("            <tr>");
        out.println("                <td class='info-label'>Langue :</td>");
        out.println("                <td class='info-value'>" + (langue != null ? langue : "-") + "</td>");
        out.println("            </tr>");
        
        // Email
        out.println("            <tr>");
        out.println("                <td class='info-label'>Email :</td>");
        out.println("                <td class='info-value'>" + 
                    (email != null && !email.isEmpty() ? email : "Non renseigné") + "</td>");
        out.println("            </tr>");
        
        // Gsm
        out.println("            <tr>");
        out.println("                <td class='info-label'>GSM :</td>");
        out.println("                <td class='info-value'>" + 
                    (gsm != null && !gsm.isEmpty() ? gsm : "Non renseigné") + "</td>");
        out.println("            </tr>");
        
        // Téléphone
        out.println("            <tr>");
        out.println("                <td class='info-label'>Téléphone :</td>");
        out.println("                <td class='info-value'>" + 
                    (telephone != null && !telephone.isEmpty() ? telephone : "Non renseigné") + "</td>");
        out.println("            </tr>");
        
        // Fax
        out.println("            <tr>");
        out.println("                <td class='info-label'>Fax :</td>");
        out.println("                <td class='info-value'>" + 
                    (fax != null && !fax.isEmpty() ? fax : "Non renseigné") + "</td>");
        out.println("            </tr>");
        
        // Note contact
        out.println("            <tr>");
        out.println("                <td class='info-label'>Note contact :</td>");
        out.println("                <td class='info-value'>" + 
                    (noteContact != null && !noteContact.isEmpty() ? noteContact.replace("\n", "<br>") : "Aucune note") + "</td>");
        out.println("            </tr>");
        
        out.println("            </table>");
        
        // Bouton retour
        out.println("            <div class='text-center'>");
        out.println("                <a href='index.html' class='btn-back'>← Retour au formulaire</a>");
        out.println("            </div>");
        
        // Footer
        out.println("            <div class='text-center' style='margin-top: 30px;'>");
        out.println("                <small style='color: #999;'>© 2024 ENSA Khouribga - TP POO JAVA</small>");
        out.println("            </div>");
        
        out.println("        </div>");
        out.println("    </div>");
        out.println("</body>");
        out.println("</html>");
        
        // Fermeture du flux
        out.close();
    }
    
    /**
     * Méthode doGet : Gère les accès directs en GET
     * Redirige vers la page d'accueil pour éviter les erreurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Redirection vers le formulaire
        response.sendRedirect("index.html");
    }
}