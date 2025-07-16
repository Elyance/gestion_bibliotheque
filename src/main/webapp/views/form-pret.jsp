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
    </style>
</head>
<body>
<div class="form-container">
    <h2>Enregistrer un Prêt</h2>
    <c:if test="${not empty succesInsertion}">
        <div style="color: #155724; background: #d4edda; border: 1px solid #155724; padding: 10px; border-radius: 4px; margin-bottom: 18px; text-align: center;">
            ${succesInsertion}
        </div>
    </c:if>
    <c:if test="${not empty erreurInsertion}">
        <div style="color: #b30000; background: #ffeaea; border: 1px solid #b30000; padding: 10px; border-radius: 4px; margin-bottom: 18px; text-align: center;">
            ${erreurInsertion}
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
            <input type="hidden" name="idLivre" value="${livre.idLivre}" />
            <label for="numeroExemplaire">Numéro de l'exemplaire</label>
            <input type="text" id="numeroExemplaire" name="numeroExemplaire" list="listeExemplaires" placeholder="Entrer le numéro de l'exemplaire" required>
            <datalist id="listeExemplaires">
                <c:forEach var="ex" items="${exemplaires}">
                    <option value="${ex.numero}"></option>
                </c:forEach>
            </datalist>
        </div>
        <div class="form-actions">
            <button type="submit">Valider le prêt</button>
        </div>
    </form>
</div>
<script>
document.addEventListener('DOMContentLoaded', function() {
    const input = document.getElementById('numeroExemplaire');
    const suggestions = document.getElementById('suggestions');
    input.addEventListener('input', function() {
        const query = input.value;
        if (query.length < 2) {
            suggestions.innerHTML = '';
            return;
        }
        fetch('/api/exemplaires/suggest?numero=' + encodeURIComponent(query))
            .then(res => res.json())
            .then(data => {
                suggestions.innerHTML = '';
                data.forEach(item => {
                    const div = document.createElement('div');
                    div.textContent = item.numero;
                    div.style.cursor = 'pointer';
                    div.onclick = () => {
                        input.value = item.numero;
                        suggestions.innerHTML = '';
                    };
                    suggestions.appendChild(div);
                });
            });
    });
    document.addEventListener('click', function(e) {
        if (e.target !== input) suggestions.innerHTML = '';
    });
});
</script>
</body>
</html>