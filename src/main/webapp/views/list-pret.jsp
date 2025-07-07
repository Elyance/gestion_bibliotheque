<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Prêts</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <div class="row">
            <div class="col-12">
                <h1 class="mb-4">
                    <i class="bi bi-book-fill"></i> Liste des Prêts
                </h1>
                
                <!-- Vérifier s'il y a des prêts -->
                <c:choose>
                    <c:when test="${empty prets}">
                        <div class="alert alert-info" role="alert">
                            <i class="bi bi-info-circle"></i> Aucun prêt trouvé.
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="table-responsive">
                            <table class="table table-striped table-hover">
                                <thead class="table-dark">
                                    <tr>
                                        <th>ID Prêt</th>
                                        <th>Adhérent</th>
                                        <th>Livre</th>
                                        <th>Date Début</th>
                                        <th>Date Fin</th>
                                        <th>Type Prêt</th>
                                        <th>Statut</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="pret" items="${prets}">
                                        <tr>
                                            <td>${pret.idPret}</td>
                                            <td>
                                                <strong>${pret.adherant.personne.nom}</strong>
                                                <br>
                                                <small class="text-muted">${pret.adherant.personne.mail}</small>
                                            </td>
                                            <td>
                                                <strong>${pret.exemplaire.livre.titre}</strong>
                                                <br>
                                                <small class="text-muted">
                                                    ISBN: ${pret.exemplaire.livre.ISBN}
                                                </small>
                                            </td>
                                            <td>
                                                <fmt:formatDate value="${pret.dateDebut}" pattern="dd/MM/yyyy HH:mm"/>
                                            </td>
                                            <td>
                                                <fmt:formatDate value="${pret.dateFin}" pattern="dd/MM/yyyy HH:mm"/>
                                            </td>
                                            <td>
                                                <span class="badge bg-secondary">${pret.typePret.nomType}</span>
                                            </td>
                                            <td>
                                                <span class="badge bg-warning">En cours</span>
                                            </td>
                                            <td>
                                                <div class="btn-group" role="group">
                                                    <!-- Bouton Prolonger -->
                                                    <a href="${pageContext.request.contextPath}/prolonger/${pret.idPret}" 
                                                       class="btn btn-warning btn-sm" 
                                                       title="Prolonger le prêt">
                                                        <i class="bi bi-calendar-plus"></i> Prolonger
                                                    </a>
                                                    
                                                    <!-- Bouton Retourner -->
                                                    <a href="${pageContext.request.contextPath}/retourner/${pret.idPret}" 
                                                       class="btn btn-success btn-sm" 
                                                       title="Retourner le livre">
                                                        <i class="bi bi-arrow-return-left"></i> Retourner
                                                    </a>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        
                    </c:otherwise>
                </c:choose>
                
                <!-- Bouton retour -->
                <div class="mt-4">
                    <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-secondary">
                        <i class="bi bi-arrow-left"></i> Retour au tableau de bord
                    </a>
                </div>
            </div>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>