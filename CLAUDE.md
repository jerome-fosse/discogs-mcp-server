# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Working rules

- Always verify the actual filesystem state (Glob/Read) before making any claim about the existence, name, or content of a file. Memory and conversation context may be outdated.
- In classes serialized/deserialized by Jackson (records, POJOs), always use wrapper types (`Integer`, `Boolean`, `Long`, etc.) instead of primitives (`int`, `boolean`, `long`, etc.), since primitives cannot be `null` and will cause deserialization failures when a JSON field is absent or null.

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
- `com.jf.mcp.discogs.api` — Discogs API client (`DiscogsApi.java`, WebClient-based, reactive)
- `com.jf.mcp.discogs.config` — configuration (`DiscogsApiConfig`, `WebClientConfig`, `ServerConfig`)
- `com.jf.mcp.discogs.model` — Java records mapping Discogs API JSON responses
- `com.jf.mcp.discogs.tools` — MCP tools (`DiscogsDatabaseTools.java`)
- `com.jf.mcp.discogs.resources` — MCP resources (`ReferenceResources.java`): static reference data (grading guidelines, French price codes, pressing identification, rights societies)
- `com.jf.mcp.discogs.completion` — MCP completions (`ReferenceCompletions.java`): URI template autocompletion for resource URIs
