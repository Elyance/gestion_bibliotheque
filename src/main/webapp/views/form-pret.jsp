<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulaire de Prêt</title>
    <style>
        .form-container {
            max-width: 500px;
            margin: 40px auto;
            padding: 30px;
            background: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 2px 8px #ccc;
        }
        .form-container h2 {
            text-align: center;
            margin-bottom: 25px;
        }
        .form-group {
            margin-bottom: 18px;
        }
        .form-group label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
        }
        .form-group input, .form-group select {
            width: 100%;
            padding: 8px 10px;
            border: 1px solid #bbb;
            border-radius: 4px;
        }
        .form-actions {
            text-align: center;
        }
        .form-actions button {
            padding: 10px 30px;
            background: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }
        .form-actions button:hover {
            background: #0056b3;
        }
        .alert {
            padding: 10px;
            border-radius: 4px;
            margin-bottom: 18px;
            text-align: center;
        }
        .alert-success {
            color: #155724;
            background: #d4edda;
            border: 1px solid #c3e6cb;
        }
        .alert-error {
            color: #721c24;
            background: #f8d7da;
            border: 1px solid #f5c6cb;
        }
        .alert-warning {
            color: #856404;
            background: #fff3cd;
            border: 1px solid #ffeaa7;
        }
        .btn-secondary {
            background: #6c757d;
            cursor: not-allowed;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Enregistrer un Prêt</h2>
    
    <!-- Messages de succès -->
    <c:if test="${not empty succesInsertion}">
        <div class="alert alert-success">
            ${succesInsertion}
        </div>
    </c:if>
    
    <!-- Messages d'erreur -->
    <c:if test="${not empty erreurInsertion}">
        <div class="alert alert-error">
            ${erreurInsertion}
        </div>
    </c:if>
    
    <c:if test="${not empty error}">
        <div class="alert alert-error">
            ${error}
        </div>
    </c:if>
    
    <form action="ajouterPret" method="post">
        <input type="hidden" name="idLivre" value="${livre.idLivre}" />
        
        <div class="form-group">
            <label for="idAdherant">Adhérant</label>
            <select name="idAdherant" id="idAdherant" required>
                <option value="">-- Sélectionner un adhérant --</option>
                <c:forEach var="adherant" items="${adherants}">
                    <option value="${adherant.idAdherant}">
                        ${adherant.personne.nom} (ID: ${adherant.idAdherant}, N°: ${adherant.numeroAdherant})
                    </option>
                </c:forEach>
            </select>
        </div>
        
        <div class="form-group">
            <label for="idTypePret">Type de Prêt</label>
            <select name="idTypePret" id="idTypePret" required>
                <option value="">-- Sélectionner un type de prêt --</option>
                <c:forEach var="typePret" items="${typesPret}">
                    <option value="${typePret.idTypePret}">
                        ${typePret.nomType}
                    </option>
                </c:forEach>
            </select>
        </div>
        
        <div class="form-group">
            <label for="dateDebut">Date de début</label>
            <input type="datetime-local" name="dateDebut" id="dateDebut" required>
        </div>
        
        <div class="form-group" style="position:relative;">
            <label for="numeroExemplaire">Numéro de l'exemplaire</label>
            <c:choose>
                <c:when test="${empty exemplaires}">
                    <div class="alert alert-warning">
                        <strong>Aucun exemplaire disponible</strong> pour ce livre actuellement.
                        <br>Tous les exemplaires sont en prêt.
                    </div>
                    <input type="text" id="numeroExemplaire" name="numeroExemplaire" 
                           placeholder="Aucun exemplaire disponible" disabled>
                </c:when>
                <c:otherwise>
                    <input type="text" id="numeroExemplaire" name="numeroExemplaire" 
                           list="listeExemplaires" placeholder="Entrer le numéro de l'exemplaire" required>
                    <datalist id="listeExemplaires">
                        <c:forEach var="ex" items="${exemplaires}">
                            <option value="${ex.numero}"></option>
                        </c:forEach>
                    </datalist>
                </c:otherwise>
            </c:choose>
        </div>
        
        <div class="form-actions">
            <c:choose>
                <c:when test="${empty exemplaires}">
                    <button type="button" disabled class="btn-secondary">
                        Aucun exemplaire disponible
                    </button>
                </c:when>
                <c:otherwise>
                    <button type="submit">Valider le prêt</button>
                </c:otherwise>
            </c:choose>
        </div>
    </form>
</div>
</body>
</html>