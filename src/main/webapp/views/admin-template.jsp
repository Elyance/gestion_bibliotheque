<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Tableau de bord Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="d-flex">
    <!-- Sidebar -->
    <nav class="bg-dark text-white vh-100 p-3" style="min-width:220px;">
        <h4 class="mb-4">Admin</h4>
        <ul class="nav flex-column">
            <li class="nav-item mb-2">
                <a class="nav-link text-white" href="validation-reservations"><i class="bi bi-check2-square"></i> Validation reservations</a>
            </li>
            <li class="nav-item mb-2">
                <a class="nav-link text-white" href="list-prets"><i class="bi bi-list-ul"></i> Liste des prets</a>
            </li>
            <li class="nav-item mb-2">
                <a class="nav-link text-white" href="list-livres"><i class="bi bi-journal"></i> Gestion des livres</a>
            </li>
            <li class="nav-item mb-2">
                <a class="nav-link text-white" href="list-demandes-prolongement"><i class="bi bi-journal"></i> Prolongements </a>
            </li>
            <li class="nav-item mb-2">
                <a class="nav-link text-white" href="logout-admin"><i class="bi bi-box-arrow-right"></i> Deconnexion</a>
            </li>
        </ul>
    </nav>
    <!-- Main content -->
    <div class="flex-grow-1 p-4">
        <h2>Bienvenue, administrateur !</h2>
        <!-- Ici tu mets le contenu dynamique selon la page -->
    </div>
</div>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</body>
</html>