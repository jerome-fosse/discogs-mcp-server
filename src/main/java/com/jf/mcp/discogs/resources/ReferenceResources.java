package com.jf.mcp.discogs.resources;

import io.modelcontextprotocol.spec.McpSchema;
import io.modelcontextprotocol.spec.McpSchema.BlobResourceContents;
import io.modelcontextprotocol.spec.McpSchema.ReadResourceResult;
import io.modelcontextprotocol.spec.McpSchema.TextResourceContents;
import org.slf4j.Logger;
import org.springframework.ai.mcp.annotation.McpResource;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ImportRuntimeHints(ReferenceResources.Hints.class)
public class ReferenceResources {

    static class Hints implements RuntimeHintsRegistrar {
        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
            hints.resources().registerPattern("mcp/logos/*.txt");
            hints.resources().registerPattern("mcp/right-societies/*.json");
        }
    }

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ReferenceResources.class);
    private final ResourcePatternResolver resourceLoader;

    public ReferenceResources(ResourcePatternResolver resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @McpResource(
            uri = "reference://grading-guidelines",
            name = "How to grade records",
            description = "Guidelines for grading records",
            mimeType = "text/plain"
    )
    public String howToGradeRecords() {
        return """
                Discogs uses the Goldmine Standard for grading the condition of items listed in the Marketplace.
                
                Mint (M)
                Vinyl
                Absolutely perfect in every way. Certainly never been played, possibly even still sealed. Should be used sparingly as a grade, if at all. \s
                
                CD
                Perfect. No scuffs/scratches, unplayed - possibly still sealed.
                Insert/Inlay/Booklet/Sleeve/Digipak: Perfect. No wear, marks, or any other imperfections - possibly still sealed.\s
                
                Cassette
                J-Card is crisp, clean and perfect in every way. Likely sealed. Cassette is brand new, and professionally produced. Used sparingly as a grade, should be free of even the slightest blemishes and/or defects. This grade should be used sparingly, if at all.
                
                
                Near Mint (NM or M-)
                Vinyl
                A nearly perfect record. A NM or M- record has more than likely never been played, and the vinyl will play perfectly, with no imperfections during playback. Many dealers won't give a grade higher than this implying (perhaps correctly) that no record is ever truly perfect. The record should show no obvious signs of wear. A 45 RPM or EP sleeve should have no more than the most minor defects, such as any sign of slight handling. An LP cover should have no creases, folds, seam splits, cut-out holes, or other noticeable similar defects. The same should be true of any other inserts, such as posters, lyric sleeves, etc. \s
                
                CD
                Near perfect. No obvious signs of use, it may have been played - but it has been handled very carefully.
                Insert/Inlay/Booklet/Sleeve/Digipak: Near Perfect. No obvious wear, it may have only the slightest of marks from handling.\s
                
                Cassette
                Sleeve should be totally crisp and clean with only the slightest evidence of handling. Tape is likely new, free of any wear or damage.
                
                
                Very Good Plus (VG+)
                Vinyl
                Generally worth 50% of the Near Mint value. A Very Good Plus record will show some signs that it was played and otherwise handled by a previous owner who took good care of it. Defects should be more of a cosmetic nature, not affecting the actual playback as a whole. Record surfaces may show some signs of wear and may have slight scuffs or very light scratches that don't affect one's listening experiences. Slight warps that do not affect the sound are "OK". The label may have some ring wear or discoloration, but it should be barely noticeable. Spindle marks may be present. Picture sleeves and inner sleeves will have some slight wear, slightly turned-up corners, or a slight seam split. An LP cover may have slight signs of wear, and may be marred by a cut-out hole, indentation, or cut corner. In general, if not for a couple of minor things wrong with it, this would be Near Mint. \s
                
                CD
                A few minor scuffs/scratches. This has been played, but handled with good care - and certainly not abused.
                Insert/Inlay/Booklet/Sleeve/Digipak: Slight wear, marks, indentations, it may possibly have a cut-out hole (or similar).
                
                Cassette
                Sleeve has slight wear, marks, indentations, and/or may possibly have a cut-out hole (or similar). Tape has been taken very good care of and may have light marks or spindle wear. Should play cleanly with minimal noise or degradation.
                
                
                Very Good (VG)
                Vinyl
                Generally worth 25% of Near Mint value. Many of the defects found in a VG+ record will be more pronounced in a VG disc. Surface noise will be evident upon playing, especially in soft passages and during a song's intro and fade, but will not overpower the music otherwise. Groove wear will start to be noticeable, as with light scratches (deep enough to feel with a fingernail) that will affect the sound. Labels may be marred by writing, or have tape or stickers (or their residue) attached. The same will be true of picture sleeves or LP covers. However, it will not have all of these problems at the same time. Goldmine price guides with more than one price will list Very Good as the lowest price. \s
                
                CD
                Quite a few light scuffs/scratches, or several more-pronounced scratches. This has obviously been played, but not handled as carefully as a VG+.\s
                Insert/Inlay/Booklet/Sleeve/Digipak: More wear, marks, indentations than a VG+. May have slight fading, a small tear/rip, or some writing.
                
                Cassette
                Sleeve will contain more wear, marks, and/or indentations than a VG+. May have slight fading, a small tear/rip, or some writing. Shell may have heavier marks and wear than VG including plastic discoloration. Should play with some stronger hiss or degradation, but not enough to overpower music.

                
                Good (G), Good Plus (G+)
                Vinyl
                Generally worth 10-15% of the Near Mint value. A record in Good or Good Plus condition can be played through without skipping. But it will have significant surface noise, scratches, and visible groove wear. A cover or sleeve will have seam splits, especially at the bottom or on the spine. Tape, writing, ring wear, or other defects will be present. While the record will be playable without skipping, noticeable surface noise and "ticks" will almost certainly accompany the playback. \s
                
                CD
                There are a lot of scuffs/scratches. However it will still play through without problems. This has not been handled with much care at all.\s
                Insert/Inlay/Booklet/Sleeve/Digipak: Well worn, marked, more obvious indentations, fading, writing, than a VG - possibly a more significant tear/rip.
                
                Cassette
                Sleeve will be well worn, marked, and contain obvious indentations, fading, and/or writing, more so than a VG grade - possibly a more significant tear/rip. Tape will have heavy wear on shell. Felt stopper may be missing. Tape may have minor creasing, but not broken. Must play through, may have heavier degradation that will overpower music.

                
                Poor (P), Fair (F)
                Vinyl
                Generally worth 0-5% of the Near Mint price. The record is cracked, badly warped, and won't play through without skipping or repeating. The picture sleeve is water damaged, split on all three seams and heavily marred by wear and writing. The LP cover barely keeps the LP inside it. Inner sleeves are fully split, crinkled, and written upon. \s
                
                CD
                The CD may or may not play some or all of the tracks. See the seller's comments for details.
                Insert/Inlay/Booklet/Sleeve/Digipak: Very worn. It may have obvious writing on it, it may be ripped/torn, or significantly faded, or water damaged.
                
                Cassette
                Sleeve will be torn, heavily stained, showing general heavy damage, or will be partially missing. Likewise, tape will be heavily damaged, showing complete fading on the face, crinkled tape, missing screws or teeth, staining, and other heavy wear. Cassette will more than likely not play through. \s
                """;
    }

    @McpResource(
            uri = "reference://price-codes/fr",
            name = "Price codes on french pressings",
            description = "Price codes used on french records from 1969 to the mid 80s",
            mimeType = "text/plain"
    )
    public String getPriceCodes() {
        return """
                The price code is a feature specific to French records that appeared from 1969 until the mid-1980s, and allows dating a French pressing.
                It consisted of an uppercase letter, usually printed at the top right of the back of the record sleeve, corresponding to a maximum retail price the record dealer was not allowed to exceed.
                Generally, the price codes were successively: T (1968–1970), U (1970–1972), B (1972–1974), Y (1974–1976), A (1976–1978), O or SE (1978–1979).
                The single letter was later replaced by three digits (varying by edition) preceded by the first two or three letters of the distributor (e.g. BA for BARCLAY, CB for CBS, PG for POLYGRAM, POL for POLYDOR...).

                Finally, price codes gave way to barcodes during the 1980s.
                """;
    }

    @McpResource(
            uri = "reference://pressing-identification",
            name = "How to identify records",
            description = "Guidelines for identifying records",
            mimeType = "text/plain"
    )
    public String howToIdentifyRecords() {
        return """
                Several elements help identify a record and find the correct pressing.
                
                1. The label
                The central label of the vinyl record is the first visual element used to identify a pressing. It contains crucial information such as the artist name, album or single title, record label, and often the catalog number — a true identity card for each vinyl.
                The label can vary between pressings: legal notices may differ, text layout may change, illustrations or colors may vary. Early Vertigo labels featured the famous swirl design; from around 1974–1975, they switched to a spaceship motif.
                Labels also frequently display the logo of the relevant copyright society (SACEM, GEMA, BIEM, STEMRA...), which indicates the country of origin of the pressing. Some countries, such as the USA and the UK, do not use these logos — instead, look for "Made in USA", "Made in England", or "Made in Great Britain".
                
                2. The record label / publisher
                Some records were released by different publishers over time or across territories. Correctly identifying the label (CBS, Warner, Polydor, EMI, RCA, Vertigo, etc.) is an important step in pinpointing a specific pressing.
                
                3. The catalog number
                Usually found on the label and the sleeve, the catalog number is essential for identifying a specific version of an album.
                
                4. The price code
                French records from 1969 to the mid-1980s carry a price code, typically visible at the top right of the back sleeve, which can be used to date the pressing.
                
                5. The barcode
                The presence of a barcode on the back of the record indicates the pressing dates from at least the mid-1980s, as barcodes appeared around 1984.
                
                6. The sleeve
                The sleeve is another key identifier and holds a great deal of information. Depending on the country and era, sleeves can differ — from small details (logo placement, credit wording...) to more significant differences such as color variations (Ozzy Osbourne's Bark at the Moon has blue lettering in Europe, red in the USA) or entirely different artwork (Black Sabbath's debut album has a different sleeve in Belgium).
                
                7. The matrix number
                Engraved on each side of the record between the groove and the label, in the area known as the "dead wax", the matrix number is a crucial identifier. Inscribed by the cutting engineer at the time of mastering, it allows precise determination of a pressing’s version and authenticity.
                
                8. Colored vinyl
                Special editions and colored pressings are often highly sought after by collectors. These limited editions stand out through unique visual elements such as colored vinyl, alternative sleeves, or limited pressing runs.\
                """;
    }

    @McpResource(
            uri = "reference://rights-societies",
            name = "Rights Management Societies",
            description = "Reference guide for identifying rights management society logos and markings on vinyl pressings. Use this to interpret logos and text visible on labels, sleeves and matrix.",
            mimeType = "application/json"
    )
    public ReadResourceResult getRightsSocieties() throws IOException {
        var resources = resourceLoader.getResources("classpath:mcp/right-societies/*.json");
        List<McpSchema.ResourceContents> data = Arrays.stream(resources)
                .map(resource -> {
                    try {
                        return new TextResourceContents(
                                "discogs://reference/rights-societies/" + resource.getFilename().replace(".json", ""),
                                "application/json",
                                resource.getContentAsString(StandardCharsets.UTF_8)
                        );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toUnmodifiableList());

        return new ReadResourceResult(data);
    }

    @McpResource(
            uri = "reference://rights-societies/{societyName}/logo",
            name = "Rights Society Logo",
            description = "Reference logo image for a specific rights management society as it appears on vinyl pressings",
            mimeType = "image/png"
    )
    public ReadResourceResult getRightsSocietyLogo(String societyName) throws IOException {
        LOGGER.info("Getting logo for society: " + societyName);

        var resource = new ClassPathResource("mcp/logos/" + societyName + ".txt");
        if (!resource.exists()) {
            throw new IllegalArgumentException("No logo available for society: " + societyName);
        }

        return new ReadResourceResult(List.of(
                new BlobResourceContents(
                        "discogs://reference/rights-societies/" + societyName + "/logo",
                        "image/png",
                        resource.getContentAsString(StandardCharsets.UTF_8)
                )
        ));
    }
}
