package me.redstoner2019;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class TTFReader {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\Redstoner_2019\\Projects\\MeshRenderer\\src\\main\\resources\\Arial.ttf");

        // Read the TrueType font file header
        byte[] header = new byte[12];
        fis.read(header);

        // Parse the header
        int sfntVersion = ByteBuffer.wrap(header).getInt(0);
        int numTables = ByteBuffer.wrap(header).getShort(4);
        int searchRange = ByteBuffer.wrap(header).getShort(6);
        int entrySelector = ByteBuffer.wrap(header).getShort(8);
        int rangeShift = ByteBuffer.wrap(header).getShort(10);

        // Iterate over the tables
        for (int i = 0; i < numTables; i++) {
            byte[] tableHeader = new byte[16];
            fis.read(tableHeader);

            // Parse the table header
            int tag = ByteBuffer.wrap(tableHeader).getInt(0);
            int checksum = ByteBuffer.wrap(tableHeader).getInt(4);
            int offset = ByteBuffer.wrap(tableHeader).getInt(8);
            int length = ByteBuffer.wrap(tableHeader).getInt(12);

            // Check if this is the glyf table
            if (tag == 0x676C7966) { // 'glyf'
                // Read the glyf table data
                byte[] glyfData = new byte[length];
                fis.read(glyfData);

                // Iterate over the glyphs
                for (int j = 0; j < glyfData.length; j += 2) {
                    int glyphIndex = ByteBuffer.wrap(glyfData).getShort(j);

                    // Read the glyph data
                    byte[] glyphData = new byte[glyfData.length - j];
                    fis.read(glyphData);

                    // Parse the glyph outline data
                    // (this is where you'll need to implement the Bezier curve parsing)
                    parseGlyphOutline(glyphData);
                }
            }
        }
    }

    private static void parseGlyphOutline(byte[] glyphData) {
        // TO DO: implement Bezier curve parsing
        System.out.println("Glyph outline data: " + Arrays.toString(glyphData));
    }
}
