# Discogs MCP Server

![Java](https://img.shields.io/badge/Java-25-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.4-6DB33F?logo=springboot&logoColor=white)
![Spring AI](https://img.shields.io/badge/Spring%20AI-2.0.0--M3-6DB33F?logo=spring&logoColor=white)

A [Model Context Protocol (MCP)](https://modelcontextprotocol.io/) server that gives AI agents the ability to interact with the [Discogs](https://www.discogs.com/) music database.

It exposes the Discogs API as MCP tools, allowing any MCP-compatible AI client (Claude Desktop, Cursor, etc.) to search for music releases, artists, and labels, retrieve detailed information about them, and manage a user's collection.

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

### MCP Tools

#### Database

| Tool | Description |
|---|---|
| `discogs_database_search` | Search for releases, masters, artists, or labels with rich filtering criteria |
| `discogs_database_release` | Fetch a specific release by its Discogs ID |
| `discogs_database_master_release` | Fetch a master release by its Discogs ID |
| `discogs_database_master_release_versions` | List all release versions of a master |
| `discogs_database_artist` | Fetch an artist by their Discogs ID |
| `discogs_database_label_releases` | List releases associated with a label, with pagination support |

#### Marketplace

| Tool | Description |
|---|---|
| `discogs_marketplace_price_suggestions` | Get Discogs price suggestions for a release, by condition |

#### Collection

| Tool | Description |
|---|---|
| `discogs_collection_folders` | List folders in a user's collection |
| `discogs_collection_folder` | Get metadata about a specific folder |
| `discogs_collection_folder_create` | Create a new folder |
| `discogs_collection_folder_delete` | Delete an empty folder (folders 0 and 1 cannot be deleted) |
| `discogs_collection_items_by_release` | List collection folders containing a specific release |
| `discogs_collection_items_by_folder` | List items in a folder, with filtering, sorting and pagination support |
| `discogs_collection_add_to_folder` | Add a release to a folder |
| `discogs_collection_release_change_rating` | Change the rating of a release instance |
| `discogs_delete_release_instance_from_folder` | Remove a release instance from a folder |
| `discogs_remove_release_instance_from_folder` | Move a release instance to the "Uncategorized" folder |
| `discogs_list_custom_collection_fields` | List user-defined collection notes fields |
| `discogs_edit_custom_collection_field_instance` | Edit a custom notes field on a release instance |
| `discogs_collection_value` | Get the minimum, median, and maximum value of a user's collection |

### MCP Resources

| Resource URI | Description |
|---|---|
| `reference://grading-guidelines` | Goldmine grading standard guidelines for vinyl, CD and cassette |
| `reference://price-codes/fr` | Price codes on French pressings (1969–mid-1980s) |
| `reference://pressing-identification` | Guidelines for identifying and dating vinyl pressings |
| `reference://rights-societies` | Reference data for all supported rights management societies (JSON) |
| `reference://rights-societies/{societyName}/logo` | Visual logo reference for a specific rights society as it appears on labels |

Supported societies: ASCAP, BMI, MCPS, PRS, SACEM, SABAM, GEMA, BIEM, STEMRA, SGAE, SIAE, SUISA.

URI completion is provided for `{societyName}` on the logo resource.

## Configuration

| Environment variable | Required | Default | Description |
|---|---|---|---|
| `DISCOGS_TOKEN` | Yes | — | Discogs API personal access token |
| `DISCOGS_USERNAME` | Yes | — | Discogs username (required for collection tools) |
| `DISCOGS_BASEURL` | No | `https://api.discogs.com` | Discogs API base URL |
| `DISCOGS_TIMEOUT` | No | `5000` | HTTP timeout in milliseconds |
| `DISCOGS_PAGESIZE` | No | `30` | Default page size for paginated results |

## Running the server

The server requires the `DISCOGS_TOKEN` and `DISCOGS_USERNAME` environment variables to be set.

Two Spring profiles are available, selecting the MCP transport mode:

| Profile | Transport | Use case |
|---|---|---|
| `stdio` | Standard input/output | Integration with MCP clients (Claude Desktop, etc.) |
| `http` | HTTP on port `8080` | Development, testing, or HTTP-based MCP clients |

stdio is the default spring profile.

### stdio mode

```bash
export DISCOGS_TOKEN=your_token
export DISCOGS_USERNAME=your_username
mvn spring-boot:run
```

### HTTP mode

```bash
export DISCOGS_TOKEN=your_token
export DISCOGS_USERNAME=your_username
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
        "-jar",
        "/path/to/discogs-mcp-server-0.2.0.jar"
      ],
      "env": {
        "DISCOGS_TOKEN": "your_token",
        "DISCOGS_USERNAME": "your_username"
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
- "What releases do I have in my Discogs collection?"
- "How much is my Discogs collection worth?"
- "Add the release 12345 to my collection"
- "What's the rating I gave to Dark Side of the Moon in my collection?"
