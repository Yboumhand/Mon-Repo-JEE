<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Facture - ENSA Khouribga</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <style>
        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 30px 0;
        }
        .facture-container {
            background: white;
            border-radius: 10px;
            box-shadow: 0 15px 40px rgba(0,0,0,0.3);
            padding: 40px;
            max-width: 900px;
            margin: 0 auto;
        }
        .facture-header {
            border-bottom: 3px solid #667eea;
            padding-bottom: 20px;
            margin-bottom: 30px;
        }
        .facture-title {
            color: #667eea;
            font-weight: bold;
            font-size: 32px;
            margin: 0;
        }
        .facture-number {
            color: #764ba2;
            font-size: 18px;
            font-weight: 600;
        }
        .section-title {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 10px 15px;
            border-radius: 5px;
            margin: 25px 0 15px 0;
            font-weight: bold;
        }
        .info-label {
            font-weight: bold;
            color: #555;
        }
        .readonly-field {
            background-color: #f8f9fa;
            border: 1px solid #dee2e6;
            cursor: not-allowed;
        }
        .table-products {
            margin: 20px 0;
        }
        .table-products thead {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }
        .table-products th {
            padding: 12px;
            font-weight: 600;
        }
        .table-products td {
            padding: 12px;
            vertical-align: middle;
        }
        .totals-section {
            background-color: #f8f9fa;
            border: 2px solid #667eea;
            border-radius: 8px;
            padding: 20px;
            margin-top: 20px;
        }
        .total-row {
            display: flex;
            justify-content: space-between;
            padding: 8px 0;
            border-bottom: 1px solid #dee2e6;
        }
        .total-row:last-child {
            border-bottom: none;
        }
        .total-label {
            font-weight: 600;
            color: #333;
        }
        .total-value {
            font-weight: bold;
            color: #667eea;
        }
        .total-ttc {
            font-size: 24px;
            color: #764ba2;
            margin-top: 10px;
            padding-top: 10px;
            border-top: 3px solid #667eea;
        }
        .payment-info {
            background-color: #fff3cd;
            border: 1px solid #ffc107;
            border-radius: 5px;
            padding: 15px;
            margin-top: 20px;
        }
        .btn-back {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border: none;
            color: white;
            padding: 12px 40px;
            font-weight: bold;
        }
        .btn-print {
            background: #28a745;
            border: none;
            color: white;
            padding: 12px 40px;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <%
        // RÉCUPÉRATION DES DONNÉES DU FORMULAIRE
        
        
        // Informations de base
        String numFacture = request.getParameter("numFacture");
        String nomClient = request.getParameter("nomClient");
        String adresseClient = request.getParameter("adresseClient");
        String emailClient = request.getParameter("emailClient");
        
        // Produit 1
        String desc1 = request.getParameter("desc1");
        int qte1 = Integer.parseInt(request.getParameter("qte1"));
        double prix1 = Double.parseDouble(request.getParameter("prix1"));
        
        // Produit 2
        String desc2 = request.getParameter("desc2");
        int qte2 = Integer.parseInt(request.getParameter("qte2"));
        double prix2 = Double.parseDouble(request.getParameter("prix2"));
        
        // Produit 3
        String desc3 = request.getParameter("desc3");
        int qte3 = Integer.parseInt(request.getParameter("qte3"));
        double prix3 = Double.parseDouble(request.getParameter("prix3"));
        
        // Remise
        String remiseStr = request.getParameter("remise");
        double remise = (remiseStr != null && !remiseStr.isEmpty()) ? Double.parseDouble(remiseStr) : 0.0;
        
        // Total HT pour chaque produit = Quantité × Prix Unitaire
        double totalHT1 = qte1 * prix1;
        double totalHT2 = qte2 * prix2;
        double totalHT3 = qte3 * prix3;
        
        // Total net HT = Somme de tous les totaux HT
        double totalNetHT = totalHT1 + totalHT2 + totalHT3;
        
        // TVA = 20% du Total net HT
        double tva = totalNetHT * 0.20;
        
        // Total TTC = Total net HT + TVA - Remise
        double totalTTC = totalNetHT + tva - remise;
    %>

    <div class="container">
        <div class="facture-container">
            
            <!-- En-tête de la facture -->
            <div class="facture-header">
                <div class="d-flex justify-content-between align-items-center">
                    <div>
                        <h1 class="facture-title">🧾 FACTURE</h1>
                        <p class="text-muted mb-0">ENSA Khouribga</p>
                    </div>
                    <div class="text-end">
                        <p class="facture-number mb-1">Facture N° : <%= numFacture %></p>
                        <p class="text-muted mb-0">Date : <%= new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()) %></p>
                    </div>
                </div>
            </div>

            <!-- Section Client -->
            <div class="section-title">
                👤 Informations du Client
            </div>
            <div class="row mb-3">
                <div class="col-md-6">
                    <label class="form-label info-label">Nom Client</label>
                    <input type="text" class="form-control readonly-field" value="<%= nomClient %>" readonly>
                </div>
                <div class="col-md-6">
                    <label class="form-label info-label">Email Client</label>
                    <input type="text" class="form-control readonly-field" value="<%= emailClient %>" readonly>
                </div>
            </div>
            <div class="mb-3">
                <label class="form-label info-label">Adresse Client</label>
                <textarea class="form-control readonly-field" rows="2" readonly><%= adresseClient %></textarea>
            </div>

            <!-- Section Produits -->
            <div class="section-title">
                📦 Détails des Articles
            </div>
            <table class="table table-bordered table-products">
                <thead>
                    <tr>
                        <th>Description</th>
                        <th style="width: 100px;">Qté</th>
                        <th style="width: 150px;">Prix Unitaire (DH)</th>
                        <th style="width: 150px;">Total HT (DH)</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Produit 1 -->
                    <tr>
                        <td><%= desc1 %></td>
                        <td class="text-center"><%= qte1 %></td>
                        <td class="text-end"><%= String.format("%.2f", prix1) %></td>
                        <td class="text-end"><strong><%= String.format("%.2f", totalHT1) %></strong></td>
                    </tr>
                    <!-- Produit 2 -->
                    <tr>
                        <td><%= desc2 %></td>
                        <td class="text-center"><%= qte2 %></td>
                        <td class="text-end"><%= String.format("%.2f", prix2) %></td>
                        <td class="text-end"><strong><%= String.format("%.2f", totalHT2) %></strong></td>
                    </tr>
                    <!-- Produit 3 -->
                    <tr>
                        <td><%= desc3 %></td>
                        <td class="text-center"><%= qte3 %></td>
                        <td class="text-end"><%= String.format("%.2f", prix3) %></td>
                        <td class="text-end"><strong><%= String.format("%.2f", totalHT3) %></strong></td>
                    </tr>
                </tbody>
            </table>

            <!-- Section Totaux -->
            <div class="row">
                <div class="col-md-6">
                    <!-- Conditions de paiement -->
                    <div class="section-title">
                        💳 Conditions de paiement
                    </div>
                    <div class="payment-info">
                        <p class="mb-0"><strong>Les modes de paiement acceptés incluent :</strong></p>
                        <ul class="mb-0 mt-2">
                            <li>Le chèque</li>
                            <li>Le virement bancaire</li>
                            <li>La carte de crédit</li>
                        </ul>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="totals-section">
                        <h5 class="mb-3" style="color: #667eea;">💰 Récapitulatif</h5>
                        
                        <!-- Total net HT -->
                        <div class="total-row">
                            <span class="total-label">Total net HT :</span>
                            <span class="total-value"><%= String.format("%.2f", totalNetHT) %> DH</span>
                        </div>
                        
                        <!-- TVA 20% -->
                        <div class="total-row">
                            <span class="total-label">TVA (20%) :</span>
                            <span class="total-value"><%= String.format("%.2f", tva) %> DH</span>
                        </div>
                        
                        <!-- Remise -->
                        <div class="total-row">
                            <span class="total-label">Remise :</span>
                            <span class="total-value">- <%= String.format("%.2f", remise) %> DH</span>
                        </div>
                        
                        <!-- Total TTC -->
                        <div class="total-row total-ttc">
                            <span class="total-label">Total TTC :</span>
                            <span class="total-value"><%= String.format("%.2f", totalTTC) %> DH</span>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Boutons d'action -->
            <div class="d-flex gap-2 justify-content-center mt-4">
                <a href="index.jsp" class="btn btn-back">
                    ⬅️ Nouvelle Facture
                </a>
                <button onclick="window.print()" class="btn btn-print">
                    🖨️ Imprimer
                </button>
            </div>

            <!-- Footer -->
            <div class="text-center mt-4 pt-3 border-top">
                <small class="text-muted">© 2024 ENSA Khouribga - TP JEE1 - Facture générée automatiquement</small>
            </div>

        </div>
    </div>
</body>
</html>