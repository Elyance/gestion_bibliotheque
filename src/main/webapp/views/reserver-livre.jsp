<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Réserver un Livre</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
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
        .form-group { margin-bottom: 18px; }
        .form-group label { display: block; margin-bottom: 6px; font-weight: bold; }
        .form-group input, .form-group select {
            width: 100%; padding: 8px 10px; border: 1px solid #bbb; border-radius: 4px;
        }
        .form-actions { text-align: center; }
        .form-actions button {
            padding: 10px 30px; background: #007bff; color: #fff; border: none;
            border-radius: 4px; font-size: 16px; cursor: pointer;
        }
        .form-actions button:hover { background: #0056b3; }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Réserver un Livre</h2>
    <c:if test="${not empty success}">
        <div class="alert alert-success text-center">${success}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger text-center">${error}</div>
    </c:if>
    <form action="do-reservation" method="post" autocomplete="off">
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
        <div class="form-group">
            <label for="dateReservation">Date de réservation</label>
            <input type="date" id="dateReservation" name="dateReservation" required>
        </div>
        <div class="form-actions">
            <button type="submit">Réserver</button>
        </div>
    </form>
</div>
</body>
</html>
