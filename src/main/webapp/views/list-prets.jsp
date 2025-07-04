<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title th:text="${titre}">Liste des prêts</title>
    <link rel="stylesheet" href="/static/css/style.css" />
</head>
<body>
    <h1 th:text="${titre}">Liste des prêts</h1>

    <table border="1" cellspacing="0" cellpadding="8">
        <thead>
            <tr>
                <th>ID</th>
                <th>Utilisateur</th>
                <th>Exemplaire</th>
                <th>Date début</th>
                <th>Date fin</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <tr th:each="pret : ${prets}">
            <td th:text="${pret.id}">1</td>
            <td th:text="${pret.utilisateur.nom}">Nom utilisateur</td>
            <td th:text="${pret.exemplaire.livre.titre}">Titre livre</td>
            <td th:text="${pret.dateDebut}">2025-07-01</td>
            <td th:text="${pret.dateFin}">2025-07-15</td>
            <td th:text="${pret.estExpire() ? 'Expiré' : 'En cours'}">En cours</td>
            <td>
                <form th:action="@{/demande-prolongement}" method="post">
                    <input type="hidden" name="pretId" th:value="${pret.id}" />
                    <button type="submit">Prolonger</button>
                </form>
            </td>
        </tr>
        </tbody>
    </table>
</body>
</html>
