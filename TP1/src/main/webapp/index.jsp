<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editer un contact - ENSA Khouribga</title>
    <!-- Lien vers Bootstrap -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <style>
        body {
            background-color: #f5f5f5;
            padding: 20px;
        }
        .form-container {
            background: white;
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 30px;
            max-width: 900px;
            margin: 0 auto;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .form-title {
            font-size: 24px;
            color: #333;
            margin-bottom: 25px;
            font-weight: normal;
        }
        .checkbox-section {
            background-color: #f9f9f9;
            border: 1px solid #e0e0e0;
            padding: 12px 15px;
            margin-bottom: 25px;
            border-radius: 3px;
        }
        .checkbox-section label {
            margin: 0;
            font-weight: normal;
        }
        .form-label {
            color: #666;
            font-size: 14px;
            margin-bottom: 5px;
        }
        .form-control, .form-select {
            border: 1px solid #ccc;
            border-radius: 3px;
            font-size: 14px;
        }
        .btn-save {
            background-color: #007bff;
            border: none;
            color: white;
            padding: 8px 20px;
            font-size: 14px;
        }
        .btn-save:hover {
            background-color: #0056b3;
        }
        .btn-cancel {
            background-color: #6c757d;
            border: none;
            color: white;
            padding: 8px 20px;
            font-size: 14px;
        }
        .btn-cancel:hover {
            background-color: #5a6268;
        }
        .button-section {
            background-color: #f9f9f9;
            border-top: 1px solid #e0e0e0;
            padding: 15px;
            margin: 30px -30px -30px -30px;
            text-align: right;
            border-radius: 0 0 4px 4px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-container">
            <!-- Titre du formulaire -->
            <h1 class="form-title">Editer un contact</h1>

            <!-- Formulaire -->
            <form action="submit" method="POST">
                
                <!-- Checkbox Contact principal -->
                <div class="checkbox-section">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="contactPrincipal" name="contactPrincipal" value="oui">
                        <label class="form-check-label" for="contactPrincipal">
                            Contact principal
                        </label>
                    </div>
                </div>

                <!-- Ligne 1 : Titre, Fonction, Langue -->
                <div class="row mb-3">
                    <!-- Titre -->
                    <div class="col-md-4">
                        <label for="titre" class="form-label">Titre</label>
                        <select class="form-select" id="titre" name="titre">
                            <option value="M.">M.</option>
                            <option value="Mlle.">Mlle.</option>
                        </select>
                    </div>

                    <!-- Fonction -->
                    <div class="col-md-4">
                        <label for="fonction" class="form-label">Fonction</label>
                        <input type="text" class="form-control" id="fonction" name="fonction" 
                               placeholder="">
                    </div>

                    <!-- Langue -->
                    <div class="col-md-4">
                        <label for="langue" class="form-label">Langue</label>
                        <select class="form-select" id="langue" name="langue">
                            <option value="FranÃ§ais" selected>FranÃ§ais (Belgique)</option>
                            <option value="Arabe">Arabe</option>
                            <option value="Anglais">Anglais</option>
                        </select>
                    </div>
                </div>

                <!-- Ligne 2 : PrÃ©nom, Nom, Email -->
                <div class="row mb-3">
                    <!-- PrÃ©nom -->
                    <div class="col-md-4">
                        <label for="prenom" class="form-label">PrÃ©nom</label>
                        <input type="text" class="form-control" id="prenom" name="prenom" 
                               placeholder="">
                    </div>

                    <!-- Nom -->
                    <div class="col-md-4">
                        <label for="nom" class="form-label">Nom</label>
                        <input type="text" class="form-control" id="nom" name="nom" 
                               placeholder="">
                    </div>

                    <!-- Email -->
                    <div class="col-md-4">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" 
                               placeholder="">
                    </div>
                </div>

                <!-- Ligne 3 : Gsm, TÃ©lÃ©phone, Fax -->
                <div class="row mb-3">
                    <!-- Gsm -->
                    <div class="col-md-4">
                        <label for="gsm" class="form-label">Gsm</label>
                        <input type="tel" class="form-control" id="gsm" name="gsm" 
                               placeholder="">
                    </div>

                    <!-- TÃ©lÃ©phone -->
                    <div class="col-md-4">
                        <label for="telephone" class="form-label">TÃ©lÃ©phone</label>
                        <input type="tel" class="form-control" id="telephone" name="telephone" 
                               placeholder="">
                    </div>

                    <!-- Fax -->
                    <div class="col-md-4">
                        <label for="fax" class="form-label">Fax</label>
                        <input type="tel" class="form-control" id="fax" name="fax" 
                               placeholder="">
                    </div>
                </div>

                <!-- Note contact (textarea pleine largeur) -->
                <div class="mb-3">
                    <label for="noteContact" class="form-label">Note contact</label>
                    <textarea class="form-control" id="noteContact" name="noteContact" 
                              rows="4" placeholder=""></textarea>
                </div>

                <!-- Section des boutons -->
                <div class="button-section">
                    <button type="submit" class="btn btn-save">
                        â Sauvegarder
                    </button>
                    <button type="reset" class="btn btn-cancel">
                        Annuler
                    </button>
                </div>

            </form>
        </div>
    </div>
</body>
</html>