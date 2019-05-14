package com.galaxy.notes;

import com.galaxy.notes.GalaxyNotesParser;
import junit.framework.TestCase;
import org.junit.Before;

/**
 */
public class GalaxyNotesParserTest extends TestCase {

    GalaxyNotesParser galaxyNotesParser;

    @Before
    protected void setUp() throws Exception {
        super.setUp();
        galaxyNotesParser = new GalaxyNotesParser();
        galaxyNotesParser.parseSymbol("glob is I");
        galaxyNotesParser.parseSymbol("prok is V");
        galaxyNotesParser.parseSymbol("pish is X");
        galaxyNotesParser.parseSymbol("tegj is L");
        galaxyNotesParser.parseCredit("glob prok Gold is 57800 Credits");
    }

    @org.junit.Test
    public void testIsInfo() throws Exception {
        String symbol = "glob is I";
        assertEquals(galaxyNotesParser.isInfo(symbol), true);

        symbol = "glob glob Silver is 34 Credits";
        assertEquals(galaxyNotesParser.isInfo(symbol), true);

        symbol = "how much is pish tegj glob glob ?";
        assertEquals(galaxyNotesParser.isInfo(symbol), false);

    }

    @org.junit.Test
    public void testIsHowQuestion() throws Exception {
        String symbol = "glob is I";
        assertEquals(galaxyNotesParser.isInfo(symbol), true);

        symbol = "how many Credits is glob prok Iron ?";
        assertEquals(galaxyNotesParser.isInfo(symbol), false);

        symbol = "how much is pish tegj glob glob ?";
        assertEquals(galaxyNotesParser.isInfo(symbol), false);

    }

    @org.junit.Test
    public void testProcessNotesLine() throws Exception {
        String question = "how many Credits is glob prok Gold ?";
        assertEquals(galaxyNotesParser.processNotesLine(question), "glob prok Gold is 57800 Credits");
    }

    @org.junit.Test
    public void testAnswerHowQuestion() throws Exception {
        String question = "how many Credits is glob prok Gold ?";
        assertEquals(galaxyNotesParser.processNotesLine(question), "glob prok Gold is 57800 Credits");
    }
}