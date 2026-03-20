# Package com.jf.mcp.discogs.model

Tous les modèles sont des Java records. Voici les relations entre eux :

```
Release
├── List<ArtistCredit>     artists / extraartists
├── List<Track>            tracklist
│   └── List<ArtistCredit> extraartists (nullable)
├── List<Image>            images (ImageType enum: primary/secondary)
├── List<Video>            videos
├── List<Label>            labels
├── List<Company>          companies
├── List<Format>           formats
├── List<Identifier>       identifiers
└── Community
    ├── Rating
    └── List<Contributor>  contributors + submitter

MasterRelease
├── List<ArtistCredit>     artists
├── List<Track>            tracklist
├── List<Image>            images
└── List<Video>            videos

Artist
├── List<Image>            images
└── List<ArtistMember>     members
```
