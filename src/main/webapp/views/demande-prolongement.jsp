<!-- filepath: /home/elyance/Documents/S4/Servlet/gestion_bibliotheque/src/main/webapp/views/form-demande-prolongement.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Demande de prolongement</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h2>Demande de prolongement</h2>
    <form method="post" action="demande-prolongement">
        <input type="hidden" name="idPret" value="${pret.idPret}" />
        <div class="mb-3">
            <label for="nbJourDemande" class="form-label">Nombre de jours demandés :</label>
            <input type="number" class="form-control" id="nbJourDemande" name="nbJourDemande" min="1" required>
        </div>
        <div class="mb-3">
            <label for="dateDemande" class="form-label">Date de demande :</label>
            <input type="date" class="form-control" id="dateDemande" name="dateDemande" value="${currentDate}" required>
        </div>
        <button type="submit" class="btn btn-primary">Envoyer la demande</button>
        <a href="mes-prets" class="btn btn-secondary">Annuler</a>
    </form>
</div>
</body>
</html>