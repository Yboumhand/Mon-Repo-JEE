<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Factures - ENSA Khouribga</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <style>
        body {
            background: linear-gradient(135deg, #1e3c72 0%, #2a5298 100%);
            min-height: 100vh;
            padding: 30px 0;
        }
        .form-container {
            background: white;
            border-radius: 10px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.3);
            padding: 35px;
            max-width: 900px;
            margin: 0 auto;
        }
        .form-header {
            text-align: center;
            margin-bottom: 30px;
            padding-bottom: 20px;
            border-bottom: 3px solid #1e3c72;
        }
        .form-header h2 {
            color: #1e3c72;
            font-weight: bold;
        }
        .section-title {
            background: linear-gradient(135deg, #1e3c72 0%, #2a5298 100%);
            color: white;
            padding: 10px 15px;
            border-radius: 5px;
            margin: 25px 0 15px 0;
            font-weight: bold;
        }
        .form-label {
            font-weight: 600;
            color: #333;
        }
        .required {
            color: red;
        }
        .readonly-field {
            background-color: #e9ecef;
        }
        .btn-generate {
            background: linear-gradient(135deg, #1e3c72 0%, #2a5298 100%);
            border: none;
            color: white;
            padding: 12px 40px;
            font-weight: bold;
            transition: transform 0.2s;
        }
        .btn-generate:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(30, 60, 114, 0.4);
        }
        .product-row {
            background-color: #f8f9fa;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 10px;
            border-left: 4px solid #1e3c72;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-container">
            <!-- En-tête -->
            <div class="form-header">
                <h2>🧾 Création de Facture</h2>
                <p class="text-muted mb-0">ENSA Khouribga - Module JEE</p>
            </div>

            <!-- Formulaire -->
            <form action="Facture.jsp" method="POST">
                
                <!-- Section Numéro de Facture (Auto-généré) -->
                <div class="section-title">
                    📄 Numéro de Facture
                </div>
                <div class="row mb-3">
                    <div class="col-md-6">
                        <label for="numFacture" class="form-label">Facture N° <span class="required">*</span></label>
                        <% 
                            // Génération automatique du numéro de facture : FA-XXX (XXX entre 1 et 1000)
                            int numeroAleatoire = (int)(Math.random() * 1000) + 1;
                            String numeroFacture = "FA-" + numeroAleatoire;
                        %>
                        <input type="text" class="form-control readonly-field" id="numFacture" 
                               name="numFacture" value="<%= numeroFacture %>" readonly required>
                        <small class="text-muted">Généré automatiquement</small>
                    </div>
                </div>

                <!-- Section Informations Client -->
                <div class="section-title">
                    👤 Informations du Client
                </div>
                <div class="row mb-3">
                    <div class="col-md-6">
                        <label for="nomClient" class="form-label">Nom Client <span class="required">*</span></label>
                        <input type="text" class="form-control" id="nomClient" name="nomClient" 
                               placeholder="Entrez le nom du client" required>
                    </div>
                    <div class="col-md-6">
                        <label for="emailClient" class="form-label">Email Client <span class="required">*</span></label>
                        <input type="email" class="form-control" id="emailClient" name="emailClient" 
                               placeholder="exemple@email.com" required>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="adresseClient" class="form-label">Adresse Client <span class="required">*</span></label>
                    <textarea class="form-control" id="adresseClient" name="adresseClient" 
                              rows="2" placeholder="Adresse complète du client" required></textarea>
                </div>

                <!-- Section Produits -->
                <div class="section-title">
                    🛒 Articles de la Facture
                </div>

                <!-- Produit 1 : Ordinateur portable -->
                <div class="product-row">
                    <h6 class="mb-3" style="color: #1e3c72;">Produit 1</h6>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="desc1" class="form-label">Description <span class="required">*</span></label>
                            <input type="text" class="form-control" id="desc1" name="desc1" 
                                   value="Ordinateur portable, Macbook Pro M2" required>
                        </div>
                        <div class="col-md-3">
                            <label for="qte1" class="form-label">Quantité <span class="required">*</span></label>
                            <input type="number" class="form-control" id="qte1" name="qte1" 
                                   min="1" value="1" required>
                        </div>
                        <div class="col-md-3">
                            <label for="prix1" class="form-label">Prix Unitaire (DH) <span class="required">*</span></label>
                            <input type="number" class="form-control" id="prix1" name="prix1" 
                                   step="0.01" min="0" value="15000" required>
                        </div>
                    </div>
                </div>

                <!-- Produit 2 : Imprimante -->
                <div class="product-row">
                    <h6 class="mb-3" style="color: #1e3c72;">Produit 2</h6>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="desc2" class="form-label">Description <span class="required">*</span></label>
                            <input type="text" class="form-control" id="desc2" name="desc2" 
                                   value="Imprimante Hp Pro 7740" required>
                        </div>
                        <div class="col-md-3">
                            <label for="qte2" class="form-label">Quantité <span class="required">*</span></label>
                            <input type="number" class="form-control" id="qte2" name="qte2" 
                                   min="1" value="2" required>
                        </div>
                        <div class="col-md-3">
                            <label for="prix2" class="form-label">Prix Unitaire (DH) <span class="required">*</span></label>
                            <input type="number" class="form-control" id="prix2" name="prix2" 
                                   step="0.01" min="0" value="3500" required>
                        </div>
                    </div>
                </div>

                <!-- Produit 3 : Disque dur -->
                <div class="product-row">
                    <h6 class="mb-3" style="color: #1e3c72;">Produit 3</h6>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="desc3" class="form-label">Description <span class="required">*</span></label>
                            <input type="text" class="form-control" id="desc3" name="desc3" 
                                   value="Disque dur SSD 500GO" required>
                        </div>
                        <div class="col-md-3">
                            <label for="qte3" class="form-label">Quantité <span class="required">*</span></label>
                            <input type="number" class="form-control" id="qte3" name="qte3" 
                                   min="1" value="3" required>
                        </div>
                        <div class="col-md-3">
                            <label for="prix3" class="form-label">Prix Unitaire (DH) <span class="required">*</span></label>
                            <input type="number" class="form-control" id="prix3" name="prix3" 
                                   step="0.01" min="0" value="800" required>
                        </div>
                    </div>
                </div>

                <!-- Section Remise -->
                <div class="section-title">
                    💰 Conditions Financières
                </div>
                <div class="row mb-3">
                    <div class="col-md-4">
                        <label for="remise" class="form-label">Remise (DH)</label>
                        <input type="number" class="form-control" id="remise" name="remise" 
                               step="0.01" min="0" value="0" placeholder="0.00">
                    </div>
                </div>

                <!-- Boutons -->
                <div class="d-grid gap-2 mt-4">
                    <button type="submit" class="btn btn-generate">
                        ✅ Générer la Facture
                    </button>
                    <button type="reset" class="btn btn-outline-secondary">
                        🔄 Réinitialiser
                    </button>
                </div>

            </form>

            <!-- Footer -->
            <div class="text-center mt-4">
                <small class="text-muted">© 2024 ENSA Khouribga - TP JEE1</small>
            </div>
        </div>
    </div>
</body>
</html>