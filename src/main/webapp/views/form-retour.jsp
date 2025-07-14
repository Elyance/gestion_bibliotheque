<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Retour de livre</title>
    <!-- ✅ Lien vers Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

    <div class="container mt-5">
        <div class="card shadow">
            <div class="card-header bg-success text-white">
                <h3 class="mb-0">Retour de livre</h3>
            </div>
            <div class="card-body">

                <form action="${pageContext.request.contextPath}/retourner" method="post">
                    <!-- Champ caché -->
                    <input type="hidden" name="idPret" value="${idPret}" />

                    <div class="mb-3">
                        <label for="dateRetour" class="form-label">Date de retour :</label>
                        <input type="date" id="dateRetour" name="dateRetour" class="form-control" required />
                    </div>

                    <button type="submit" class="btn btn-success">
                        <i class="bi bi-check-circle"></i> Valider le retour
                    </button>
                </form>

            </div>
        </div>
    </div>

    <!-- ✅ Bootstrap JS (optionnel, pour certains composants) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- ✅ Bootstrap Icons (si tu utilises les icônes comme bi-check-circle) -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</body>
</html>
