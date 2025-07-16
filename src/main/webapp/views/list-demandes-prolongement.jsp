<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Liste des demandes de prolongement</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="bg-light">
    <div class="container mt-5">
        <h1 class="mb-4">Liste des demandes de prolongement</h1>

        <!-- En attente -->
        <h3 class="mt-4 mb-2 text-warning">Demandes en attente</h3>
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Adhérant</th>
                    <th>Livre</th>
                    <th>Date de demande</th>
                    <th>Statut</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="demande" items="${demandesEnAttente}">
                    <tr>
                        <td>${demande.idDemande}</td>
                        <td>${demande.adherant.personne.nom}</td>
                        <td>${demande.pret.exemplaire.livre.titre}</td>
                        <td>${demande.dateDemande}</td>
                        <td>
                            <span class="badge bg-warning text-dark">En attente</span>
                        </td>
                        <td>
                            <a href="validation-demande-prolongement?idDemande=${demande.idDemande}" class="btn btn-success btn-sm">Valider</a>
                            <form action="refuser-demande-prolongement" method="post" style="display:inline;">
                                <input type="hidden" name="idDemande" value="${demande.idDemande}" />
                                <button type="submit" class="btn btn-danger btn-sm">Refuser</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Validées -->
        <h3 class="mt-5 mb-2 text-success">Demandes validées</h3>
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Adhérant</th>
                    <th>Livre</th>
                    <th>Date de demande</th>
                    <th>Statut</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="demande" items="${demandesValidees}">
                    <tr>
                        <td>${demande.idDemande}</td>
                        <td>${demande.adherant.personne.nom}</td>
                        <td>${demande.pret.exemplaire.livre.titre}</td>
                        <td>${demande.dateDemande}</td>
                        <td>
                            <span class="badge bg-success">Validée</span>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Refusées -->
        <h3 class="mt-5 mb-2 text-danger">Demandes refusées</h3>
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Adhérant</th>
                    <th>Livre</th>
                    <th>Date de demande</th>
                    <th>Statut</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="demande" items="${demandesRefusees}">
                    <tr>
                        <td>${demande.idDemande}</td>
                        <td>${demande.adherant.personne.nom}</td>
                        <td>${demande.pret.exemplaire.livre.titre}</td>
                        <td>${demande.dateDemande}</td>
                        <td>
                            <span class="badge bg-danger">Refusée</span>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>