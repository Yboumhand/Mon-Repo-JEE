<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.sql.*, com.gestion.util.DatabaseConnection, java.util.*" %>

<%
    // Génération du code sportif
    Random random = new Random();
    int numero = random.nextInt(1001);
    String codeSportif = "SPR-" + numero;
    
    // Récupération de la liste des sportifs
    List<Map<String, String>> sportifs = new ArrayList<>();
    try (Connection conn = DatabaseConnection.getConnection()) {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Sportif");
        while (rs.next()) {
            Map<String, String> sportif = new HashMap<>();
            sportif.put("code", rs.getString("code"));
            sportif.put("nom", rs.getString("nom"));
            sportif.put("prenom", rs.getString("prenom"));
            sportif.put("age", rs.getString("age"));
            sportif.put("sexe", rs.getString("sexe"));
            sportif.put("pays", rs.getString("pays"));
            sportif.put("discipline", rs.getString("discipline"));
            sportifs.add(sportif);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    request.setAttribute("sportifs", sportifs);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inscription Sportif</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header bg-primary text-white">
                <h3>Formulaire d'inscription</h3>
            </div>
            <div class="card-body">
                <form action="ajouterSportif" method="post">
                    <div class="mb-3">
                        <label class="form-label">Code :</label>
                        <input type="text" class="form-control" name="code" value="<%= codeSportif %>" readonly>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Nom :</label>
                        <input type="text" class="form-control" name="nom" required>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Prénom :</label>
                        <input type="text" class="form-control" name="prenom" required>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Age :</label>
                        <input type="number" class="form-control" name="age" required>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Sexe :</label><br>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="sexe" value="Féminin" required>
                            <label class="form-check-label">Féminin</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="sexe" value="Masculin" required>
                            <label class="form-check-label">Masculin</label>
                        </div>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Pays :</label>
                        <select class="form-select" name="pays" required>
                            <option value="Belgique">Belgique</option>
                            <option value="France">France</option>
                            <option value="Maroc">Maroc</option>
                            <option value="Espagne">Espagne</option>
                        </select>
                    </div>
                    
                    <div class="mb-3">
                        <label class="form-label">Discipline :</label><br>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" name="discipline" value="Marathon">
                            <label class="form-check-label">Marathon</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" name="discipline" value="Natation">
                            <label class="form-check-label">Natation</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" name="discipline" value="Sprint">
                            <label class="form-check-label">Sprint</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="checkbox" name="discipline" value="Saut">
                            <label class="form-check-label">Saut</label>
                        </div>
                    </div>
                    
                    <button type="submit" class="btn btn-warning">Enregistrer</button>
                </form>
            </div>
        </div>
        
        <div class="card mt-4">
            <div class="card-header bg-secondary text-white">
                <h4>Liste des sportifs</h4>
            </div>
            <div class="card-body">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Code</th>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Age</th>
                            <th>Sexe</th>
                            <th>Pays</th>
                            <th>Disciplines</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="sportif" items="${sportifs}">
                            <tr>
                                <td>${sportif.code}</td>
                                <td>${sportif.nom}</td>
                                <td>${sportif.prenom}</td>
                                <td>${sportif.age}</td>
                                <td>${sportif.sexe}</td>
                                <td>${sportif.pays}</td>
                                <td>${sportif.discipline}</td>
                                <td>
                                    <a href="supprimerSportif?code=${sportif.code}" 
                                       class="btn btn-danger btn-sm"
                                       onclick="return confirm('Voulez-vous vraiment supprimer ce sportif ?')">
                                        Supprimer
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>