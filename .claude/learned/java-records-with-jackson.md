# Java records avec Jackson / Spring WebClient

Spring WebClient désérialise nativement les records Java via Jackson (supporté depuis Jackson 2.12).

- Pas de configuration supplémentaire nécessaire
- Jackson utilise le constructeur canonique du record
- Les noms de composants doivent correspondre aux clés JSON
- Utiliser `@JsonProperty("snake_case_key")` uniquement quand le nom JSON diffère du nom du composant
- Les enums sont désérialisés automatiquement par valeur (ex: `"primary"` → `ImageType.primary`)
- Un champ absent du JSON sera `null` (pour les types nullable) ou 0/false (pour les primitifs)
