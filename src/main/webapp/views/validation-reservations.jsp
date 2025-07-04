<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Validation des réservations</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        .btn-container {
            display: flex;
            gap: 5px;
            justify-content: center;
        }
        .btn-sm {
            padding: 0.25rem 0.5rem;
            font-size: 0.875rem;
        }
    </style>
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="mb-4">Réservations à valider</h2>
    
    <c:if test="${not empty success}">
        <div class="alert alert-success text-center">${success}</div>
    </c:if>
    
    <c:if test="${not empty error}">
        <div class="alert alert-danger text-center">${error}</div>
    </c:if>
    
    <c:if test="${empty reservations}">
        <div class="alert alert-info text-center">Aucune réservation à valider.</div>
    </c:if>
    
    <c:if test="${not empty reservations}">
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Adhérant</th>
                    <th>Exemplaire</th>
                    <th>Date réservation</th>
                    <th>Date demande</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="res" items="${reservations}">
                    <tr>
                        <td>${res.idReservation}</td>
                        <td>${res.adherant.idAdherant}</td>
                        <td>${res.exemplaire.numero}</td>
                        <td>${res.dateReservation}</td>
                        <td>${res.date_}</td>
                        <td>
                            <div class="btn-container">
                                <form action="valider-reservation" method="post" style="display: inline;">
                                    <input type="hidden" name="idReservation" value="${res.idReservation}" />
                                    <button type="submit" class="btn btn-success btn-sm" 
                                            onclick="return confirm('Êtes-vous sûr de vouloir valider cette réservation ?')">
                                        <i class="bi bi-check-circle"></i> Valider
                                    </button>
                                </form>
                                
                                <form action="refuser-reservation" method="post" style="display: inline;">
                                    <input type="hidden" name="idReservation" value="${res.idReservation}" />
                                    <button type="submit" class="btn btn-danger btn-sm" 
                                            onclick="return confirm('Êtes-vous sûr de vouloir refuser cette réservation ?')">
                                        <i class="bi bi-x-circle"></i> Refuser
                                    </button>
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    
    <div class="mt-4">
        <a href="/" class="btn btn-secondary">Retour à l'accueil</a>
    </div>
</div>

<!-- Bootstrap Icons (optionnel pour les icônes) -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
</body>
</html>