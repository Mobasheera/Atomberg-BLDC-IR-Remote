package com.moba.atombergremote;

import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.InputStream;
import java.util.Arrays;
import javax.xml.parsers.DocumentBuilderFactory;
import static org.junit.Assert.*;

public class LedSignalTest {
    @Test
    public void publishedCaptureIsBitReversedNotByteSwapped() {
        assertEquals(Integer.toUnsignedLong(Integer.reverse(0x00CFE916)), MainActivity.LED);
        assertEquals(0xF300, MainActivity.LED & 0xFFFF);
        assertNotEquals(0xE916CF00L, MainActivity.LED);
        int[] signal = MainActivity.buildNecSignal(MainActivity.LED);
        // Decode the emitted bit spaces as the published MSB-first capture.
        long capture = 0;
        for (int bit = 0; bit < 32; bit++) {
            capture = (capture << 1) | (signal[3 + bit * 2] == 1690 ? 1 : 0);
        }
        assertEquals(0x00CFE916L, capture);
        assertEquals(67, signal.length);
        assertTrue(Arrays.stream(signal).sum() < 100_000);
    }

    @Test
    public void irplusLedMatchesTheTransmittedSignal() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        try (InputStream input = getClass().getResourceAsStream("/Atomberg_Fan.irplus")) {
            assertNotNull(input);
            NodeList buttons = factory.newDocumentBuilder().parse(input).getElementsByTagName("button");
            for (int i = 0; i < buttons.getLength(); i++) {
                Element button = (Element) buttons.item(i);
                if (button.getAttribute("label").endsWith(" LED")) {
                    int[] raw = Arrays.stream(button.getTextContent().trim().split("\\s+"))
                            .mapToInt(Integer::parseInt).toArray();
                    assertArrayEquals(raw, MainActivity.buildNecSignal(MainActivity.LED));
                    return;
                }
            }
            fail("IRplus profile must contain LED");
        }
    }
}
