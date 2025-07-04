<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Livres</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="mb-4">📚 Liste des Livres</h2>

    <div class="mb-3">
        <input type="text" class="form-control" id="filtre" placeholder="Filtrer par titre, auteur ou catégorie..." onkeyup="filtrerLivres()">
    </div>

    <c:if test="${empty livres}">
        <div class="alert alert-warning" role="alert">
            Aucun livre disponible pour le moment.
        </div>
    </c:if>

    <c:if test="${not empty livres}">
        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Titre</th>
                    <th>Auteur</th>
                    <th>ISBN</th>
                    <th>Édition</th>
                    <th>Âge Limite</th>
                    <th>Catégories</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="livre" items="${livres}">
                    <tr>
                        <td>${livre.titre}</td>
                        <td>${livre.auteur}</td>
                        <td>${livre.ISBN}</td>
                        <td>${livre.edition}</td>
                        <td>${livre.ageLimite}</td>
                        <td>
                            <c:if test="${livre.categories != null && !empty livre.categories}">
                                <c:forEach var="categorie" items="${livre.categories}">
                                    <span class="badge bg-info text-dark">${categorie.nomCategorie}</span>
                                </c:forEach>
                            </c:if>
                            <c:if test="${livre.categories == null || empty livre.categories}">
                                <span class="text-muted">Aucune catégorie</span>
                            </c:if>
                        </td>
                        <td>
                            <form action="preter-livre" method="get">
                                <input type="hidden" name="idLivre" value="${livre.idLivre}" />
                                <button type="submit" class="btn btn-success">Prêter</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

<!-- JS pour le filtre -->
<script>
    function filtrerLivres() {
        const filtre = document.getElementById("filtre").value.toLowerCase();
        const lignes = document.querySelectorAll("tbody tr");

        lignes.forEach(row => {
            const texte = row.textContent.toLowerCase();
            row.style.display = texte.includes(filtre) ? "" : "none";
        });
    }
</script>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
