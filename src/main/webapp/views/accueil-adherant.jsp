<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des livres</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-4">
    <h2 class="mb-4 text-primary">Liste des livres</h2>
    <div class="row">
        <c:choose>
            <c:when test="${empty livres}">
                <div class="col-12">
                    <div class="alert alert-info text-center">Aucun livre disponible pour le moment.</div>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach var="livre" items="${livres}">
                    <div class="col-md-4 mb-4">
                        <div class="card h-100">
                            <div class="card-body">
                                <h5 class="card-title">${livre.titre}</h5>
                                <p class="card-text">
                                    Auteur : ${livre.auteur}<br>
                                    Edition : ${livre.edition}<br>
                                    ISBN : ${livre.ISBN}<br>
                                    Âge limite : ${livre.ageLimite}
                                </p>
                                <form action="reserver-livre" method="get">
                                    <input type="hidden" name="idLivre" value="${livre.idLivre}" />
                                    <c:choose>
                                        <c:when test="${autorisations[livre.idLivre]}">
                                            <button type="submit" class="btn btn-success">Réserver</button>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-danger">Vous ne pouvez pas réserver ce livre</span>
                                        </c:otherwise>
                                    </c:choose>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Pagination -->
    <nav>
        <ul class="pagination justify-content-center">
            <c:if test="${page > 1}">
                <li class="page-item">
                    <a class="page-link" href="?page=${page - 1}">Précédent</a>
                </li>
            </c:if>
            <li class="page-item disabled">
                <span class="page-link">Page ${page}</span>
            </li>
            <c:if test="${hasNextPage}">
                <li class="page-item">
                    <a class="page-link" href="?page=${page + 1}">Suivant</a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>
</body>
</html>