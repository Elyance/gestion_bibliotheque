<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inscription Adhérent</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <h2 class="mb-4 text-primary">Formulaire d'abonnement d'un adhérent</h2>
        <!-- Zone d'affichage des messages -->
        <c:if test="${not empty message}">
            <div class="mb-3">
                <span class="fw-bold ${success ? 'text-success' : 'text-danger'}">
                    ${message}
                </span>
            </div>
        </c:if>
        <form action="doAbonnement" method="post">
            <div class="mb-3">
                <label for="dateDebut" class="form-label">Date début :</label>
                <input type="date" id="dateDebut" name="dateDebut" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="dateFin" class="form-label">Date fin :</label>
                <input type="date" id="dateFin" name="dateFin" class="form-control" required>
            </div>

            <button type="submit" class="btn btn-primary">S'abonner</button>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
