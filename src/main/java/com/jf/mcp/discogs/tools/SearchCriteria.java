package com.jf.mcp.discogs.tools;

import org.springframework.ai.mcp.annotation.McpToolParam;

import java.util.Optional;

public class SearchCriteria {
    @McpToolParam(description = "Your search query. Example: Nirvana", required = false)
    private final String query;
    @McpToolParam(description = "The type of items you're searching. One of release, master, artist, label.", required = false)
    private final SearchType type;
    @McpToolParam(description = "Search by combined “Artist Name - Release Title” title field. Example: nirvana - nevermind", required = false)
    private final String title;
    @McpToolParam(description = "Search release titles. Example: nevermind", required = false)
    private final String releaseTitle;
    @McpToolParam(description = "Search release credits. Example: Kurt Cobain", required = false)
    private final String credit;
    @McpToolParam(description = "Search artist names. Example: Nirvana", required = false)
    private final String artist;
    @McpToolParam(description = "Search artist ANV (Artist Name Variation). Example: Nirvana", required = false)
    private final String anv;
    @McpToolParam(description = "Search label names. Example: Sub Pop", required = false)
    private final String label;
    @McpToolParam(description = "Search genres. Example; rock", required = false)
    private final String genre;
    @McpToolParam(description = "Search styles. Example: grunge", required = false)
    private final String style;
    @McpToolParam(description = "Search release country. Examples: USA, UK, France...", required = false)
    private final String country;
    @McpToolParam(description = "Search release year. Example: 1991", required = false)
    private final Integer year;
    @McpToolParam(description = "Search formats. Examples: album, single, CD, vinyl, EP...", required = false)
    private final String format;
    @McpToolParam(description = "Search catalog number. Example: DGCD-24425", required = false)
    private final String catalogNumber;
    @McpToolParam(description = "Search barcode. Example: 7 2064-24425-2 4", required = false)
    private final String barCode;
    @McpToolParam(description = "Search track titles. Example: Smells Like Teen Spirit", required = false)
    private final String track;
    @McpToolParam(description = "Search submitter username. Example: milKt", required = false)
    private final String submitter;
    @McpToolParam(description = "Search contributor username. Example: jerome99", required = false)
    private final String contributor;

    public SearchCriteria(String query, SearchType type, String title, String releaseTitle, String credit, 
                          String artist, String anv, String label, String genre, String style, String country, 
                          Integer year, String format, String catalogNumber, String barCode, String track, 
                          String submitter, String contributor) {
        this.query = query;
        this.type = type;
        this.title = title;
        this.releaseTitle = releaseTitle;
        this.credit = credit;
        this.artist = artist;
        this.anv = anv;
        this.label = label;
        this.genre = genre;
        this.style = style;
        this.country = country;
        this.year = year;
        this.format = format;
        this.catalogNumber = catalogNumber;
        this.barCode = barCode;
        this.track = track;
        this.submitter = submitter;
        this.contributor = contributor;
    }

    public Optional<String> getQuery() {
        return Optional.ofNullable(query);
    }

    public Optional<SearchType> getType() {
        return Optional.ofNullable(type);
    }

    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    public Optional<String> getReleaseTitle() {
        return Optional.ofNullable(releaseTitle);
    }

    public Optional<String> getCredit() {
        return Optional.ofNullable(credit);
    }

    public Optional<String> getArtist() {
        return Optional.ofNullable(artist);
    }

    public Optional<String> getAnv() {
        return Optional.ofNullable(anv);
    }

    public Optional<String> getLabel() {
        return Optional.ofNullable(label);
    }

    public Optional<String> getGenre() {
        return Optional.ofNullable(genre);
    }

    public Optional<String> getStyle() {
        return Optional.ofNullable(style);
    }

    public Optional<String> getCountry() {
        return Optional.ofNullable(country);
    }

    public Optional<Integer> getYear() {
        return Optional.ofNullable(year);
    }

    public Optional<String> getFormat() {
        return Optional.ofNullable(format);
    }

    public Optional<String> getCatalogNumber() {
        return Optional.ofNullable(catalogNumber);
    }

    public Optional<String> getBarCode() {
        return Optional.ofNullable(barCode);
    }

    public Optional<String> getTrack() {
        return Optional.ofNullable(track);
    }

    public Optional<String> getSubmitter() {
        return Optional.ofNullable(submitter);
    }

    public Optional<String> getContributor() {
        return Optional.ofNullable(contributor);
    }


    @Override
    public String toString() {
        return "SearchCriteria{" +
                "query='" + query + '\'' +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", releaseTitle='" + releaseTitle + '\'' +
                ", credit='" + credit + '\'' +
                ", artist='" + artist + '\'' +
                ", anv='" + anv + '\'' +
                ", label='" + label + '\'' +
                ", genre='" + genre + '\'' +
                ", style='" + style + '\'' +
                ", country='" + country + '\'' +
                ", year=" + year +
                ", format='" + format + '\'' +
                ", catalogNumber='" + catalogNumber + '\'' +
                ", barCode='" + barCode + '\'' +
                ", track='" + track + '\'' +
                ", submitter='" + submitter + '\'' +
                ", contributor='" + contributor + '\'' +
                '}';
    }
}
