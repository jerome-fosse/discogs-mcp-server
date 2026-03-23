# Discogs MCP Server

![Java](https://img.shields.io/badge/Java-25-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.4-6DB33F?logo=springboot&logoColor=white)
![Spring AI](https://img.shields.io/badge/Spring%20AI-2.0.0--M3-6DB33F?logo=spring&logoColor=white)

A [Model Context Protocol (MCP)](https://modelcontextprotocol.io/) server that gives AI agents the ability to interact with the [Discogs](https://www.discogs.com/) music database.

It exposes the Discogs database API as MCP tools, allowing any MCP-compatible AI client (Claude Desktop, Cursor, etc.) to search for music releases, artists, and labels, and retrieve detailed information about them.

Two transport modes are supported: **stdio** for direct integration with MCP clients, and **HTTP** for development or HTTP-based clients.

## Prerequisites

- Java 25+
- Maven 3.9+
- A [Discogs personal access token](https://www.discogs.com/settings/developers)

## Build

```bash
# Compile
mvn compile

# Build JAR
mvn package

# Clean
mvn clean
```

## Features

Exposes 4 MCP tools:

| Tool | Description |
|---|---|
| `discogs_database_search` | Search for releases, masters, artists, or labels with rich filtering criteria |
| `discogs_database_release` | Fetch a specific release by its Discogs ID |
| `discogs_database_master_release` | Fetch a master release by its Discogs ID |
| `discogs_database_master_release_versions` | List all release versions of a master |

## MCP Resources

Exposes 5 MCP resources:

| Resource URI | Description |
|---|---|
| `reference://how-to-grade-records` | Goldmine grading standard guidelines for vinyl, CD and cassette |
| `reference://price-codes` | Price codes on French pressings (1969–mid-1980s) |
| `reference://how-to-identify-records` | Guidelines for identifying and dating vinyl pressings |
| `reference://rights-societies` | Reference data for all supported rights management societies (JSON) |
| `reference://rights-societies/{societyName}/logo` | Visual logo reference for a specific rights society as it appears on labels |

Supported societies: ASCAP, BMI, MCPS, PRS, SACEM, SABAM, GEMA, BIEM, STEMRA, SGAE, SIAE, SUISA.

URI completion is provided for `{societyName}` on the logo resource.

## Configuration

| Environment variable | Required | Default | Description |
|---|---|---|---|
| `DISCOGS_TOKEN` | Yes | — | Discogs API personal access token |
| `DISCOGS_BASEURL` | No | `https://api.discogs.com` | Discogs API base URL |
| `DISCOGS_TIMEOUT` | No | `5000` | HTTP timeout in milliseconds |
| `DISCOGS_PAGESIZE` | No | `30` | Default page size for paginated results |

## Running the server

The server requires the `DISCOGS_TOKEN` environment variable to be set with your Discogs personal access token.

Two Spring profiles are available, selecting the MCP transport mode:

| Profile | Transport | Use case |
|---|---|---|
| `stdio` | Standard input/output | Integration with MCP clients (Claude Desktop, etc.) |
| `http` | HTTP on port `8080` | Development, testing, or HTTP-based MCP clients |

### stdio mode

```bash
export DISCOGS_TOKEN=your_token
mvn spring-boot:run -Dspring-boot.run.profiles=stdio
```

### HTTP mode

```bash
export DISCOGS_TOKEN=your_token
mvn spring-boot:run -Dspring-boot.run.profiles=http
```

## MCP client configuration

### Claude Desktop (stdio)

Build the JAR first:

```bash
mvn package -DskipTests
```

Then add to your `claude_desktop_config.json`:

```json
{
  "mcpServers": {
    "discogs": {
      "command": "java",
      "args": [
        "-Dspring.profiles.active=stdio",
        "-jar",
        "/path/to/discogs-mcp-server-0.0.1-SNAPSHOT.jar"
      ],
      "env": {
        "DISCOGS_TOKEN": "your_token"
      }
    }
  }
}
```

### HTTP transport

Point your MCP client at `http://localhost:8080/mcp`.

## Example prompts

- "Find all vinyl pressings of Master of Puppets released in the USA"
- "What albums did Chet Baker record on the Blue Note label?"
- "Search for jazz releases by Miles Davis from the 1960s"
- "Find all versions of Dark Side of the Moon — which countries were they pressed in?"
- "What are the different formats available for Daft Punk's Random Access Memories?"
- "Search for 12\" singles by New Order on Factory Records"
- "Which releases credit Bill Evans as a sideman?"
- "I have a vinyl with a SACEM logo on the label — what does that tell me about its origin?"
- "What are the different grades for vinyl condition and what do they mean?"
- "I see a price code 'Y' on the back of this French pressing — when was it released?"
- "How can I use the matrix number to identify a pressing?"
