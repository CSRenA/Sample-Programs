package edu.gmu.lander16.gmuquotegenerator;

import junit.framework.TestCase;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class KeywordTest extends TestCase {
  
  /**
   * A test method.
   * (Replace "X" with a name describing the test.  You may write as
   * many "testSomething" methods in this class as you wish, and each
   * one will be called when running JUnit over this class.)
   */
  public void testKeywordExists() {
    Quotes quote = new Quotes("No.", "Rosa Parks", " ");
    assertFalse(quote.getKeyword().equals(""));
                }
  public void testKeywordIsNotEmptyString(){
      Quotes quote = new Quotes("No.", "Rosa Parks", "Segragation");
      assertFalse(quote.getKeyword().equals(""));
  }
  public void testSearchByKeyword() {
      Quotes quote = new Quotes("Rosa Parks.", "No.", "Segregation");
      Quotes expected = new Quotes("Martin Luther King Jr.", "I have a dream ", "Civil Rights");
      QuoteList quotes = new QuoteList();
      quotes.setQuote(quote);
      quotes.setQuote(expected);
      QuoteList quotes2 = quotes.searchByKeyword("Civil Rights");
      Quotes actual = quotes2.getQuotes().get(0);
      assertEquals("Quote should be MLK quote", expected, actual);


  }
  
}
