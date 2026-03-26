# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Working rules

- Always verify the actual filesystem state (Glob/Read) before making any claim about the existence, name, or content of a file. Memory and conversation context may be outdated.
- In classes serialized/deserialized by Jackson (records, POJOs), always use wrapper types (`Integer`, `Boolean`, `Long`, etc.) instead of primitives (`int`, `boolean`, `long`, etc.), since primitives cannot be `null` and will cause deserialization failures when a JSON field is absent or null.
- All Discogs model types in `com.jf.mcp.discogs.model` must be Java records (not classes). Exception: enums remain enums.
- For JSON structures with dynamic keys, prefer `Map<String, T>` over typed records with named fields. Named fields are fragile if the API adds or removes keys.
- For list endpoints, always create a dedicated record (e.g. `ArtistRelease`, `LabelRelease`). Do not reuse detailed models (e.g. `Release`) — list endpoints return simplified objects with a different structure.
- `@McpTool` methods must return blocking types. Spring AI MCP does not handle `Mono<T>` — it serializes the `Mono` object itself. Always call `.block()` explicitly. Virtual threads (`spring.threads.virtual.enabled: true`) mitigate the cost.
- `*Criteria` classes in `com.jf.mcp.discogs.tools` group parameters for tool methods to avoid long parameter lists. Two forms depending on field optionality:
  - All fields required → use a record (e.g. `ChangeRatingOfReleaseCriteria`)
  - Some fields optional → use a class with a constructor, required fields return their type directly, optional fields return `Optional<T>` (e.g. `CollectionItemsCriteria`)
  - No builder pattern — Jackson instantiates these directly via the constructor.

## Project Overview

A Spring Boot MCP (Model Context Protocol) server that enables AI agents to interact with the Discogs music database. Built with Spring AI's MCP server framework over WebMVC.

- **Group/Artifact:** `com.jf.mcp` / `discogs-mcp-server`
- **Root package:** `com.jf.mcp.discogs`
- **Java version:** 25
- **Spring Boot:** 4.0.4
- **Spring AI MCP:** 2.0.0-M3 (`spring-ai-starter-mcp-server-webmvc`)

## Build & Run Commands

```bash
# Compile
mvn compile

# Build JAR
mvn package

# Run the application
mvn spring-boot:run

# Clean build artifacts
mvn clean
```

No test or lint plugins are configured yet.

## Architecture

### Packages

- `com.jf.mcp.discogs` — entry point (`DiscogsMcpServer.java`, `@SpringBootApplication`)
- `com.jf.mcp.discogs.api` — Discogs API clients (WebClient-based, reactive): `DiscogsDatabaseApi`, `DiscogsMarketplaceApi`, `DiscogsCollectionApi`, `DiscogsApiException`
- `com.jf.mcp.discogs.config` — configuration (`DiscogsApiConfig`, `WebClientConfig`, `ServerConfig`)
- `com.jf.mcp.discogs.model` — Java records mapping Discogs API JSON responses
- `com.jf.mcp.discogs.tools` — MCP tools: `DiscogsDatabaseTools`, `DiscogsMarketplaceTools`, `DiscogsCollectionTools`
- `com.jf.mcp.discogs.resources` — MCP resources (`ReferenceResources`): static reference data (grading guidelines, French price codes, pressing identification, rights societies)
- `com.jf.mcp.discogs.completion` — MCP completions (`ReferenceCompletions`): URI template autocompletion for resource URIs
