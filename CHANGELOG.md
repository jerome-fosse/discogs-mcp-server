# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.1.0] - 2026-03-23

### Added

#### MCP Tools
- `discogs_database_artist` — retrieve an artist by ID (name, profile, members, URLs)
- `discogs_database_label_releases` — list releases for a given label, with pagination support
- `discogs_marketplace_price_suggestions` — get Discogs price suggestions for a release, broken down by condition

#### MCP Resources
- `discogs://reference/grading-guidelines` — vinyl and sleeve grading guidelines (Mint to Poor)
- `discogs://reference/price-codes/fr` — French price codes reference
- `discogs://reference/pressing-identification` — pressing identification guide
- `discogs://reference/rights-societies` — list of rights societies

## [0.0.1] - 2026-03-22

### Added

#### MCP Tools
- `discogs_database_master_release` — retrieve a master release by ID (a master groups similar releases from the same artist)
- `discogs_database_release` — retrieve a specific release by ID (a physical or digital object released by one or more artists)
- `discogs_database_master_release_versions` — list all release versions of a master, with filtering and pagination support
- `discogs_database_search` — search the Discogs database for releases, masters, labels, or artists, with filtering and pagination support

#### Server
- Dual transport mode: **stdio** (for integration with AI clients like Claude Desktop) and **HTTP Streamable** (for direct HTTP connections via MCP Inspector or similar tools)
- Spring Boot 4.0.4 with Spring AI 2.0.0-M3 (`spring-ai-starter-mcp-server-webmvc`)
- Virtual threads enabled for non-blocking Discogs API calls
- Configurable Discogs API token, base URL, timeout, and page size via environment variables
