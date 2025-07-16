<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion Bibliothèque - Accueil</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container d-flex flex-column justify-content-center align-items-center vh-100">
        <div class="card shadow-lg p-4" style="min-width: 350px;">
            <h2 class="text-center mb-4">Bienvenue à la Bibliothèque</h2>
            <p class="text-center mb-4">Veuillez choisir votre mode de connexion :</p>
            <div class="d-grid gap-3">
                <a href="login-admin" class="btn btn-primary btn-lg">
                    <i class="bi bi-person-badge"></i> Connexion Admin
                </a>
                <a href="login-adherant" class="btn btn-success btn-lg">
                    <i class="bi bi-person"></i> Connexion Adhérant
                </a>
            </div>
        </div>
    </div>
    <!-- Optionnel : Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</body>
</html>