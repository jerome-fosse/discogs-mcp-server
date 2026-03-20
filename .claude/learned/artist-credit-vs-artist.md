# ArtistCredit vs Artist

Discogs retourne deux représentations différentes d'un artiste selon le contexte :

- **`ArtistCredit`** : artiste tel que crédité sur une release ou un master. Contient `id`, `anv` (name variation), `join`, `name`, `role`, `tracks`, `resourceUrl`. Utilisé dans `Release.artists`, `Release.extraartists`, `Master.artists`, `Track.extraartists`.

- **`Artist`** : fiche complète d'un artiste. Contient en plus `profile`, `namevariations`, `urls`, `images`, `members`, `releasesUrl`.

Toujours utiliser `ArtistCredit` pour les artistes embarqués dans une release/master/track, et `Artist` uniquement pour la ressource `/artists/{id}`.
