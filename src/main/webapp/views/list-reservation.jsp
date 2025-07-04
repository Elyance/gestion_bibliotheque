<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des réservations</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <style>
        .status-badge {
            font-size: 0.75rem;
            padding: 0.25rem 0.5rem;
        }
        .status-en-attente {
            background-color: #ffc107;
            color: #000;
        }
        .status-valide {
            background-color: #198754;
            color: #fff;
        }
        .status-refuse {
            background-color: #dc3545;
            color: #fff;
        }
        .filter-container {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
        }
        .table-container {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .table th {
            background-color: #343a40;
            color: white;
            border: none;
        }
        .table td {
            vertical-align: middle;
        }
        .reservation-id {
            font-weight: bold;
            color: #0d6efd;
        }
    </style>
</head>
<body class="bg-light">
<div class="container-fluid mt-4">
    <div class="row">
        <div class="col-12">
            <h2 class="mb-4">
                <i class="bi bi-list-ul"></i> Liste des réservations
            </h2>
            
            <!-- Filtres -->
            <div class="filter-container">
                <form method="get" action="liste-reservations">
                    <div class="row">
                        <div class="col-md-4">
                            <label for="statut" class="form-label">Filtrer par statut :</label>
                            <select name="statut" id="statut" class="form-select">
                                <option value="tous" ${statutFilter eq 'tous' or empty statutFilter ? 'selected' : ''}>Tous les statuts</option>
                                <option value="en attente" ${statutFilter eq 'en attente' ? 'selected' : ''}>En attente</option>
                                <option value="valide" ${statutFilter eq 'valide' ? 'selected' : ''}>Validé</option>
                                <option value="refuse" ${statutFilter eq 'refuse' ? 'selected' : ''}>Refusé</option>
                            </select>
                        </div>
                        <div class="col-md-4">
                            <label for="adherant" class="form-label">Rechercher par adhérant :</label>
                            <input type="text" name="adherant" id="adherant" class="form-control" 
                                   value="${adherantFilter}" placeholder="Nom de l'adhérant">
                        </div>
                        <div class="col-md-4 d-flex align-items-end">
                            <button type="submit" class="btn btn-primary me-2">
                                <i class="bi bi-search"></i> Filtrer
                            </button>
                            <a href="liste-reservations" class="btn btn-secondary">
                                <i class="bi bi-arrow-clockwise"></i> Réinitialiser
                            </a>
                        </div>
                    </div>
                </form>
            </div>
            
            <!-- Résultats -->
            <div class="table-container">
                <c:if test="${empty reservations}">
                    <div class="alert alert-info text-center m-3">
                        <i class="bi bi-info-circle"></i> Aucune réservation trouvée.
                    </div>
                </c:if>
                
                <c:if test="${not empty reservations}">
                    <div class="p-3">
                        <p class="mb-3 text-muted">
                            <i class="bi bi-info-circle"></i> 
                            <strong>${reservations.size()}</strong> réservation(s) trouvée(s)
                        </p>
                    </div>
                    
                    <div class="table-responsive">
                        <table class="table table-hover mb-0">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Adhérant</th>
                                    <th>Livre</th>
                                    <th>Exemplaire</th>
                                    <th>Date demande</th>
                                    <th>Date réservation</th>
                                    <th>Statut</th>
                                    <th>Admin</th>
                                    <th>Date traitement</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="res" items="${reservations}">
                                    <tr>
                                        <td class="reservation-id">#${res.idReservation}</td>
                                        <td>
                                            <c:if test="${res.adherant != null and res.adherant.personne != null}">
                                                <div class="fw-bold">${res.adherant.personne.nom} ${res.adherant.personne.prenom}</div>
                                                <small class="text-muted">ID: ${res.adherant.idAdherant}</small>
                                            </c:if>
                                            <c:if test="${res.adherant == null or res.adherant.personne == null}">
                                                <span class="text-muted">N/A</span>
                                            </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${res.exemplaire != null and res.exemplaire.livre != null}">
                                                <div class="fw-bold">${res.exemplaire.livre.titre}</div>
                                                <small class="text-muted">par ${res.exemplaire.livre.auteur}</small>
                                            </c:if>
                                            <c:if test="${res.exemplaire == null or res.exemplaire.livre == null}">
                                                <span class="text-muted">N/A</span>
                                            </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${res.exemplaire != null}">
                                                <span class="badge bg-info">${res.exemplaire.numero}</span>
                                            </c:if>
                                            <c:if test="${res.exemplaire == null}">
                                                <span class="text-muted">N/A</span>
                                            </c:if>
                                        </td>
                                        <td>
                                            <fmt:formatDate value="${res.date_}" pattern="dd/MM/yyyy"/>
                                        </td>
                                        <td>
                                            <fmt:formatDate value="${res.dateReservation}" pattern="dd/MM/yyyy"/>
                                        </td>
                                        <td>
                                            <c:set var="statutActuel" value="${statutsActuels[res.idReservation]}"/>
                                            <c:if test="${statutActuel != null and statutActuel.statut != null}">
                                                <c:choose>
                                                    <c:when test="${statutActuel.statut.nomStatut eq 'En attente'}">
                                                        <span class="badge status-badge status-en-attente">
                                                            <i class="bi bi-clock"></i> En attente
                                                        </span>
                                                    </c:when>
                                                    <c:when test="${statutActuel.statut.nomStatut eq 'Validé'}">
                                                        <span class="badge status-badge status-valide">
                                                            <i class="bi bi-check-circle"></i> Validé
                                                        </span>
                                                    </c:when>
                                                    <c:when test="${statutActuel.statut.nomStatut eq 'Refusé'}">
                                                        <span class="badge status-badge status-refuse">
                                                            <i class="bi bi-x-circle"></i> Refusé
                                                        </span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="badge bg-secondary status-badge">
                                                            ${statutActuel.statut.nomStatut}
                                                        </span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:if>
                                            <c:if test="${statutActuel == null or statutActuel.statut == null}">
                                                <span class="badge bg-secondary status-badge">
                                                    <i class="bi bi-question-circle"></i> Inconnu
                                                </span>
                                            </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${res.admin != null and res.admin.personne != null}">
                                                <div class="fw-bold">${res.admin.personne.nom} ${res.admin.personne.prenom}</div>
                                                <small class="text-muted">ID: ${res.admin.idAdmin}</small>
                                            </c:if>
                                            <c:if test="${res.admin == null or res.admin.personne == null}">
                                                <span class="text-muted">-</span>
                                            </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${statutActuel != null and statutActuel.dateStatut != null}">
                                                <fmt:formatDate value="${statutActuel.dateStatut}" pattern="dd/MM/yyyy"/>
                                            </c:if>
                                            <c:if test="${statutActuel == null or statutActuel.dateStatut == null}">
                                                <span class="text-muted">-</span>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
            
            <!-- Navigation -->
            <div class="mt-4">
                <a href="/" class="btn btn-secondary">
                    <i class="bi bi-house"></i> Retour à l'accueil
                </a>
                <a href="validation-reservations" class="btn btn-primary ms-2">
                    <i class="bi bi-check-square"></i> Valider des réservations
                </a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>