<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Clients</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            padding: 30px;
        }
        
        .container {
            max-width: 1100px;
            margin: 0 auto;
            background: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        
        h1 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 10px;
            font-size: 2.2em;
        }
        
        .subtitle {
            text-align: center;
            color: #7f8c8d;
            margin-bottom: 30px;
            font-size: 1.1em;
        }
        
        /* MESSAGES */
        .message {
            padding: 12px 20px;
            border-radius: 5px;
            margin-bottom: 25px;
            font-size: 0.95em;
        }
        
        .success {
            background-color: #d4edda;
            color: #155724;
            border-left: 4px solid #28a745;
        }
        
        .error {
            background-color: #f8d7da;
            color: #721c24;
            border-left: 4px solid #dc3545;
        }
        
        /* FORMULAIRE */
        .form-section {
            background: #fafafa;
            padding: 30px;
            border-radius: 8px;
            margin-bottom: 40px;
            border: 1px solid #e0e0e0;
        }
        
        .form-section h2 {
            color: #34495e;
            margin-bottom: 25px;
            font-size: 1.5em;
            border-bottom: 2px solid #3498db;
            padding-bottom: 10px;
        }
        
        .form-grid {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 20px;
            margin-bottom: 25px;
        }
        
        .form-group {
            display: flex;
            flex-direction: column;
        }
        
        .form-group.full-width {
            grid-column: span 2;
        }
        
        label {
            margin-bottom: 8px;
            color: #2c3e50;
            font-weight: 600;
            font-size: 0.95em;
        }
        
        label .required {
            color: #e74c3c;
        }
        
        input[type="text"],
        input[type="number"],
        input[type="date"] {
            padding: 12px 15px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 1em;
            transition: border-color 0.3s;
        }
        
        input[type="text"]:focus,
        input[type="number"]:focus,
        input[type="date"]:focus {
            outline: none;
            border-color: #3498db;
            box-shadow: 0 0 0 3px rgba(52, 152, 219, 0.1);
        }
        
        .btn-submit {
            background: #3498db;
            color: white;
            padding: 14px 40px;
            border: none;
            border-radius: 5px;
            font-size: 1.05em;
            font-weight: 600;
            cursor: pointer;
            transition: background 0.3s;
            width: 100%;
        }
        
        .btn-submit:hover {
            background: #2980b9;
        }
        
        .btn-submit:active {
            transform: translateY(1px);
        }
        
        /* TABLEAU */
        .table-section {
            margin-top: 40px;
        }
        
        .table-section h2 {
            color: #34495e;
            margin-bottom: 20px;
            font-size: 1.5em;
            border-bottom: 2px solid #3498db;
            padding-bottom: 10px;
        }
        
        .table-wrapper {
            overflow-x: auto;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
        }
        
        thead {
            background: #34495e;
            color: white;
        }
        
        th {
            padding: 15px 12px;
            text-align: center;
            font-weight: 600;
            font-size: 1em;
            border-right: 1px solid #4a5f7f;
        }
        
        th:last-child {
            border-right: none;
        }
        
        tbody tr {
            border-bottom: 1px solid #e0e0e0;
        }
        
        tbody tr:hover {
            background-color: #f8f9fa;
        }
        
        tbody tr:last-child {
            border-bottom: none;
        }
        
        td {
            padding: 12px;
            text-align: center;
            color: #2c3e50;
            font-size: 0.95em;
        }
        
        .btn-delete {
            color: #3498db;
            text-decoration: none;
            font-weight: 600;
            padding: 6px 12px;
            border-radius: 4px;
            transition: all 0.2s;
            display: inline-block;
        }
        
        .btn-delete:hover {
            color: #2980b9;
            background: #ecf0f1;
        }
        
        .empty-state {
            text-align: center;
            padding: 60px 20px;
            color: #95a5a6;
        }
        
        .empty-state-icon {
            font-size: 3em;
            margin-bottom: 15px;
        }
        
        .empty-state p {
            font-size: 1.1em;
        }
        
        @media (max-width: 768px) {
            .form-grid {
                grid-template-columns: 1fr;
            }
            
            .form-group.full-width {
                grid-column: span 1;
            }
            
            .container {
                padding: 20px;
            }
            
            table {
                font-size: 0.85em;
            }
            
            th, td {
                padding: 10px 8px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- TITRE -->
        <h1>Gestion des Clients</h1>
        <p class="subtitle">Système de gestion des informations des clients de la société</p>
        
        <!-- MESSAGES -->
        <c:if test="${not empty success}">
            <div class="message success">
                ✓ ${success}
            </div>
        </c:if>
        
        <c:if test="${not empty error}">
            <div class="message error">
                ✗ ${error}
            </div>
        </c:if>
        
        <!-- FORMULAIRE D'AJOUT -->
        <div class="form-section">
            <h2>Ajouter un nouveau client</h2>
            <form action="${pageContext.request.contextPath}/addClient" method="post">
                <div class="form-grid">
                    <div class="form-group">
                        <label for="nom">
                            Nom <span class="required">*</span>
                        </label>
                        <input type="text" 
                               id="nom" 
                               name="nom" 
                               placeholder="Ex: Alami" 
                               required>
                    </div>
                    
                    <div class="form-group">
                        <label for="prenom">
                            Prénom <span class="required">*</span>
                        </label>
                        <input type="text" 
                               id="prenom" 
                               name="prenom" 
                               placeholder="Ex: Adil" 
                               required>
                    </div>
                    
                    <div class="form-group">
                        <label for="dateNaissance">
                            Date de Naissance <span class="required">*</span>
                        </label>
                        <input type="date" 
                               id="dateNaissance" 
                               name="dateNaissance" 
                               required>
                    </div>
                    
                    <div class="form-group">
                        <label for="age">
                            Âge <span class="required">*</span>
                        </label>
                        <input type="number" 
                               id="age" 
                               name="age" 
                               placeholder="Ex: 23" 
                               min="1" 
                               max="120" 
                               required>
                    </div>
                    
                    <div class="form-group">
                        <label for="salaire">
                            Salaire (DH)
                        </label>
                        <input type="number" 
                               id="salaire" 
                               name="salaire" 
                               placeholder="Ex: 5000" 
                               min="0" 
                               step="0.01">
                    </div>
                    
                    <div class="form-group">
                        <label for="ville">
                            Ville
                        </label>
                        <input type="text" 
                               id="ville" 
                               name="ville" 
                               placeholder="Ex: Casa">
                    </div>
                    
                    <div class="form-group full-width">
                        <button type="submit" class="btn-submit">
                            Enregistrer le Client
                        </button>
                    </div>
                </div>
            </form>
        </div>
        
        <!-- LISTE DES CLIENTS -->
        <div class="table-section">
            <h2>Liste des clients 
                <c:if test="${not empty clients}">
                    (${clients.size()})
                </c:if>
            </h2>
            
            <c:choose>
                <c:when test="${not empty clients}">
                    <div class="table-wrapper">
                        <table>
                            <thead>
                                <tr>
                                    <th>Matricule</th>
                                    <th>Nom</th>
                                    <th>Prénom</th>
                                    <th>Date Naissance</th>
                                    <th>Salaire</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="client" items="${clients}">
                                    <tr>
                                        <td>${client.code}</td>
                                        <td>${client.nom}</td>
                                        <td>${client.prenom}</td>
                                        <td>${client.dateNaissance != null ? client.dateNaissance : '-'}</td>
                                        <td>${client.salaire != null ? client.salaire : '-'}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/deleteClient?code=${client.code}" 
                                               class="btn-delete"
                                               onclick="return confirm('Voulez-vous vraiment supprimer ${client.nom} ${client.prenom} ?')">
                                                Supprimer
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="empty-state">
                        <div class="empty-state-icon">📋</div>
                        <p>Aucun client enregistré pour le moment</p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>