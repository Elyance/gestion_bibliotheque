<!-- filepath: /home/elyance/Documents/S4/Servlet/gestion_bibliotheque/src/main/webapp/views/validation-demande-prolongement.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Validation de la demande de prolongement</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h2>Valider la demande de prolongement</h2>
    <form method="post" action="valider-demande-prolongement">
        <input type="hidden" name="idDemande" value="${demande.idDemande}" />
        <div class="mb-3">
            <label for="dateValidation" class="form-label">Date et heure de validation :</label>
            <input type="datetime-local" class="form-control" id="dateValidation" name="dateValidation" required>
        </div>
        <button type="submit" class="btn btn-success">Valider et créer le prêt</button>
        <a href="list-demandes-prolongement" class="btn btn-secondary">Annuler</a>
    </form>

    <c:if test="${not empty error}">
        <div class="alert alert-danger mt-3">${error}</div>
    </c:if>

    <script>
        // Définir la date et heure actuelle par défaut
        document.getElementById('dateValidation').value = new Date().toISOString().slice(0, 16);
    </script>
</div>
</body>
</html>