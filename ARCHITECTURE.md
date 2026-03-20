# Architecture & Design

## Objectif

Serveur MCP exposant des tools et resources pour permettre à Claude Desktop d'identifier des éditions de disques vinyles via l'API publique Discogs. Cas d'usage principal : photographier un vinyle (pochette avant, arrière, labels) et laisser Claude identifier l'édition exacte.

## Comment Claude Desktop fait le lien entre les photos et les tools

Claude Desktop est multimodal : il analyse lui-même les images. **Les tools ne reçoivent pas de photos** — Claude en extrait les informations textuelles pertinentes, puis appelle les tools avec ces données.

```
Photos (user) → Claude analyse visuellement → extrait des données → appelle MCP tools → API Discogs
```

### Ce que Claude extrait visuellement d'un vinyle

- **Pochette avant** : artwork, titre, artiste
- **Pochette arrière** : tracklist, code-barres, price code
- **Labels** :
  - Nom et design du label (permet de dater le pressage)
  - Numéro de catalogue (ex: `2C 064-85390`)
  - Logos des sociétés de droits d'auteurs (SACEM, BIEM, STEMRA, GEMA...)
  - Pays de fabrication
  - **Matrix/runout** : texte gravé dans la zone morte (ex: `YEX 189-1`) — identifiant le plus précis d'un pressage

## Tools MCP à exposer

Les tools appellent l'API Discogs avec des données textuelles :

```
search_releases(artist, title, label, catalog_number)
get_master_release(master_id)                          # toutes les éditions d'un album
get_release_versions(master_id, country, year, format) # filtrer les versions
get_release(release_id)                                # détails complets d'une édition
search_by_barcode(barcode)
```

## Resources MCP à exposer

Les Resources sont différentes des Tools : elles fournissent une **base de connaissance** injectée dans le contexte de Claude. C'est essentiel car la connaissance de Claude sur les détails fins de la discophilie est lacunaire.

```
resources/
├── pressing-indicators         # Logos droits d'auteurs → pays/époque
├── price-codes/{label}         # Price codes par label (EMI, CBS, Polydor...)
├── matrix-formats/{label}      # Comment lire les matrices par label
├── label-designs/{label}       # Évolutions graphiques des labels (permettent de dater)
└── country-codes               # Indicateurs pays sur les vinyles
```

### Exemple : pressing-indicators

Les logos de sociétés de droits d'auteurs sont des indicateurs clés du pays et de l'époque de pressage :

| Logo(s) | Pays | Période |
|---|---|---|
| BIEM seul | Europe (générique) | avant ~1982 |
| BIEM + MCPS | Royaume-Uni | post-1982 |
| SACEM + BIEM | France | — |
| STEMRA | Pays-Bas | — |
| GEMA | Allemagne | — |

## Flux complet d'identification

```
User → [photo pochette] [photo label A] [photo label B]

Claude (vision) :
  → lit "SACEM + BIEM" sur le label → pressage France
  → lit numéro de catalogue "2C 064-85390"
  → lit matrix "2C 064-85390 A"

Claude (resources MCP) :
  → consulte pressing-indicators : SACEM+BIEM = France confirmé
  → consulte price-codes/CBS : préfixe "2C" = CBS France

Claude (tools MCP) :
  → search_releases(label="CBS", country="France", catalog="2C 064-85390")
  → get_release(release_id=XXXXX)

Claude → "Pressage original français, CBS orange, 1973."
```

## Points clés

- Claude gère toute la partie visuelle, les tools n'ont besoin que de texte/JSON
- Les Resources permettent de construire une base de connaissance vinyle indépendante de ce que Claude sait nativement, enrichissable au fil du temps
- Discogs contient lui-même beaucoup de ces métadonnées dans les notes de releases — les tools pourront les rapatrier dynamiquement pour enrichir les Resources
