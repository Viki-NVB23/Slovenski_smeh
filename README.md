<!DOCTYPE html>
<html lang="sl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Slovenski Smeh</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background: #0f0b1f;
            color: #ffffff;
            line-height: 1.6;
        }
        header {
            background: linear-gradient(90deg, #ff8c00, #ff5e00);
            padding: 20px;
            text-align: center;
        }
        h1, h2, h3 {
            color: #ff8c00;
        }
        .container {
            padding: 20px;
            max-width: 900px;
            margin: auto;
        }
        .card {
            background: #1a1333;
            padding: 15px;
            border-radius: 10px;
            margin-bottom: 15px;
        }
        ul {
            padding-left: 20px;
        }
        .team {
            display: flex;
            gap: 20px;
            flex-wrap: wrap;
        }
        .member {
            background: #1a1333;
            padding: 15px;
            border-radius: 10px;
            flex: 1;
            min-width: 200px;
        }
        footer {
            text-align: center;
            padding: 15px;
            background: #1a1333;
            margin-top: 30px;
            font-size: 14px;
        }
        a {
            color: #ff8c00;
            text-decoration: none;
        }
    </style>
</head>
<body>

<header>
    <h1>🎤 Slovenski Smeh</h1>
    <p>Mobilna aplikacija za raziskovanje slovenskega stand-up sveta 🇸🇮</p>
</header>

<div class="container">

    <section class="card">
        <h2>📱 Opis projekta</h2>
        <p>
            Slovenski Smeh je mobilna aplikacija, ki na enem mestu združuje informacije o slovenskih stand-up komikih,
            njihovih nastopih in izvoru. Namenjena je vsem ljubiteljem stand-up komedije.
        </p>
    </section>

    <section class="card">
        <h2>🎯 Cilj aplikacije</h2>
        <ul>
            <li>Pregled vseh slovenskih stand-up komikov</li>
            <li>Prikaz biografij in slogov komedije</li>
            <li>Vpogled v prihajajoče nastope</li>
            <li>Prikaz geografskega izvora komikov</li>
        </ul>
    </section>

    <section class="card">
        <h2>🚀 Funkcionalnosti</h2>
        <ul>
            <li>👤 Seznam komikov</li>
            <li>📄 Profil komika</li>
            <li>📅 Dogodki</li>
            <li>📍 Podrobnosti dogodka z zemljevidom</li>
            <li>🗺️ Zemljevid komikov</li>
            <li>🔍 Iskanje in filtriranje</li>
        </ul>
    </section>

    <section class="card">
        <h2>🧩 Struktura aplikacije</h2>
        <ul>
            <li>Domov – seznam komikov</li>
            <li>Komik – profil komika</li>
            <li>Dogodki – prihajajoči nastopi</li>
            <li>Dogodek – podrobnosti dogodka</li>
            <li>Zemljevid – regijski pregled</li>
        </ul>
    </section>

    <section class="card">
        <h2>🛠️ Tehnologije</h2>
        <ul>
            <li>Figma (UI/UX)</li>
            <li>API integracija</li>
            <li>Google Maps API</li>
        </ul>
    </section>

    <section class="card">
        <h2>🎨 Dizajn</h2>
        <p>
            Uporabniški vmesnik je zasnovan v Figmi z modernim temnim izgledom, poudarkom na berljivosti
            in enostavni navigaciji.
        </p>
    </section>

    <section class="card">
        <h2>🧠 Analiza (HTA)</h2>
        <p><strong>Glavni cilj:</strong> Najti komika ali dogodek</p>
        <ol>
            <li>Odpri aplikacijo</li>
            <li>Izberi komika / dogodek / regijo</li>
            <li>Preglej informacije</li>
            <li>(opcijsko) preveri lokacijo ali termin</li>
        </ol>
    </section>

    <section class="card">
        <h2>📌 Status</h2>
        <p>🚧 Projekt je v fazi razvoja / prototipa</p>
    </section>

</div>

<footer>
    <p>© 2026 Slovenski Smeh | Študijski projekt</p>
</footer>

</body>
</html>
