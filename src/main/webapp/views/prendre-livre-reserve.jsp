<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Transformer réservation en prêt</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="mb-4">Transformer la réservation en prêt</h2>
    <c:if test="${not empty error}">
        <div class="alert alert-danger text-center">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="alert alert-success text-center">${success}</div>
    </c:if>
    <form action="prendre-livre-reserve" method="post" class="card p-4">
        <input type="hidden" name="idReservation" value="${reservation.idReservation}" />
        <div class="mb-3">
            <label class="form-label">Adhérant</label>
            <input type="text" class="form-control" value="${reservation.adherant.personne.nom}" readonly />
        </div>
        <div class="mb-3">
            <label class="form-label">Exemplaire</label>
            <input type="text" class="form-control" value="${reservation.exemplaire.numero}" readonly />
        </div>
        <div class="mb-3">
            <label class="form-label">Type de prêt</label>
            <select name="typePret" class="form-select" required>
                <option value="">-- Choisir le type de prêt --</option>
                <c:forEach var="typePret" items="${typesPret}">
                    <option value="${typePret.idTypePret}">${typePret.nomType}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Valider le prêt</button>
        <a href="validation-reservations" class="btn btn-secondary ms-2">Annuler</a>
    </form>
</div>
</body>
</html>