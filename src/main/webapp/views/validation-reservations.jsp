<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Validation des réservations</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
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
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Adhérant</th>
                    <th>Exemplaire</th>
                    <th>Date réservation</th>
                    <th>Date demande</th>
                    <th>Action</th>
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
                            <form action="valider-reservation" method="post">
                                <input type="hidden" name="idReservation" value="${res.idReservation}" />
                                <button type="submit" class="btn btn-success btn-sm">Valider</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>
