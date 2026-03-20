# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

A Spring Boot MCP (Model Context Protocol) server that enables AI agents to interact with the Discogs music database. Built with Spring AI's MCP server framework over WebFlux (reactive).

- **Group/Artifact:** `com.jf.ai` / `discogs-mcp-server`
- **Java version:** 25
- **Spring Boot:** 4.0.3
- **Spring AI MCP:** 2.0.0-M3 (`spring-ai-starter-mcp-server-webflux`)

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

The project is in early scaffolding stage. The only source file is:

- `src/main/java/com/jf/ai/discogs/DiscogsMcpServer.java` — standard `@SpringBootApplication` entry point

New MCP tools should be added as `@Bean`-annotated methods returning Spring AI `McpServerTool` (or equivalent) components within the `com.jf.ai.discogs` package. The WebFlux transport means handlers should be non-blocking/reactive where possible.
