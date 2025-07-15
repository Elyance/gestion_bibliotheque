<!-- filepath: /home/elyance/Documents/S4/Servlet/gestion_bibliotheque/src/main/webapp/views/list-pret-adherant.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mes prêts</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h2>Mes prêts</h2>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID Prêt</th>
                <th>Livre</th>
                <th>Exemplaire</th>
                <th>Date début</th>
                <th>Date fin</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="pret" items="${prets}">
                <tr>
                    <td>${pret.idPret}</td>
                    <td>${pret.exemplaire.livre.titre}</td>
                    <td>${pret.exemplaire.numero}</td>
                    <td>${pret.dateDebut}</td>
                    <td>${pret.dateFin}</td>
                    <td>
                        <c:if test="${pret.retour == null}">
                            <a href="demande-prolongement?idPret=${pret.idPret}" class="btn btn-warning btn-sm">Prolonger</a>
                        </c:if>
                        <c:if test="${pret.retour != null}">
                            <span class="text-success">Retourné</span>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Liste des demandes de prolongement -->
    <h3 class="mt-5">Mes demandes de prolongement</h3>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>ID Demande</th>
                <th>Livre</th>
                <th>Exemplaire</th>
                <th>Date demande</th>
                <th>Nombre de jours demandés</th>
                <th>Statut</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="demande" items="${demandesProlongement}">
                <tr>
                    <td>${demande.idDemande}</td>
                    <td>${demande.pret.exemplaire.livre.titre}</td>
                    <td>${demande.pret.exemplaire.numero}</td>
                    <td><fmt:formatDate value="${demande.dateDemande}" pattern="dd/MM/yyyy"/></td>
                    <td>${demande.nbJourDemande}</td>
                    <td>
                        <c:set var="statutActuel" value="${statutsDemandes[demande.idDemande]}"/>
                        <c:choose>
                            <c:when test="${statutActuel != null && statutActuel.statut.nomStatut eq 'En attente'}">
                                <span class="badge bg-warning text-dark">En attente</span>
                            </c:when>
                            <c:when test="${statutActuel != null && statutActuel.statut.nomStatut eq 'Validé'}">
                                <span class="badge bg-success">Acceptée</span>
                            </c:when>
                            <c:when test="${statutActuel != null && statutActuel.statut.nomStatut eq 'Refusé'}">
                                <span class="badge bg-danger">Refusée</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge bg-secondary">Inconnu</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>