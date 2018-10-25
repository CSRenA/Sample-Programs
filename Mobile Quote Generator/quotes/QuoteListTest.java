import junit.framework.TestCase;
import junit.framework.*;
import java.util.*;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class QuoteListTest extends TestCase {
  
  //Constants from QuoteList
  static final int SearchAuthorVal = 0;
  static final int SearchTextVal   = 1;
  static final int SearchBothVal   = 2;
  //Create the QuoteList that will be searched
  QuoteList quotes = new QuoteList();
  Quote quote1 = new quote("Edgar Allan Poe", "I have great faith in fools; self-confidence my friends call it.");
  Quote quote2 = new quote("Maya Angelou", "I've learned that people will forget what you said, people will forget what you did, but people will never forget how you made them feel.");
  Quote quote3 = new quote("Rosa Parks", "I have learned over the years that when one's mind is made up, this diminishes fear; knowing what must be done does away with fear.");
  Quote quote4 = new quote("Stephen Hawking", "I have noticed even people who claim everything is predestined, and that we can do nothing to change it, look before they cross the road.");
  Quote quote5 = new quote("Alan Turing", "A computer would deserve to be called intelligent if it could deceive a human into believing that it was human.");
  Quote quote6 = new quote("Alexander Hamilton", "There is a certain enthusiasm in liberty, that makes human nature rise above itself, in acts of bravery and heroism.");
  Quote quote7 = new quote("Hellen Keller", "The best and most beautiful things in the world cannot be seen or even touched - they must be felt with the heart.");
  quotes.setQuote(quote1);
  quotes.setQuote(quote2);
  quotes.setQuote(quote3);
  quotes.setQuote(quote4);
  quotes.setQuote(quote5);
  quotes.setQuote(quote6);
  quotes.setQuote(quote7);
   
  
  /**
   * This method tests whether the search funtion works for the authors of the quotes.
   */
  public void testSearchAuthor() {
    QuoteList actual = quotes.search("Edgar",SearchAuthorVal);
    QuoteList expected = new QuoteList();
    expected.setQuote(quote1);
    AssertEquals("There should only be one quote by Edgar Allan Poe in the list.", expected, actual);
  }

  /**
   * This method tests whether the search function works for the text of the quotes.
   */
  public void testSearchText(){
    QuoteList actual = quotes.search("have", SearchTextVal);
    QuoteList expected = new QuoteList();
    expected.setQuote(quote1);
    expected.setQuote(quote3);
    expected.setQuote(quote4);
    AssertEquals("There are 3 quotes with the word 'have' in them.", expected, actual);
  }
  
  /**
   * This method tests whether the search function works for both the author and the text of the quotes.
   */
  public void testSearchBoth(){
    QuoteList actual = quotes.search("p", SearchBothVal);
    QuoteList expected = new QuoteList();
    expected.setQuote(quote1);
    expected.setQuote(quote2);
    expected.setQuote(quote3);
    expected.setQuote(quote4);
    expected.setQuote(quote5);
    AssertEquals("There are 5 quotes with the letter 'p' either in the Author or the Text.", expected, actual);
  }
  
  /**
   * This method tests what happens when the mode is wrong for the search function.
   */
  public void testSearchWrongMode(){
    QuoteList actual = quotes.search("Maya", 4);
    QuoteList expected = new QuoteList();
    AssertEquals("This is not a proper search mode. The list should be empty.", expected, actual);
  }
    /**
   *This method tests what happens when the term searched for is not in the author of the quotes.
   */
  public void testSearchTermNotInQuoteAuthor(){
    QuoteList actual = quotes.search("NotAnAuthor", SearchAuthorVal);
    QuoteList expected = new QuoteList();
    AssertEquals("This term is not contained in the author text of any of the quotes. The list should be empty.", expected, actual);
  }
      /**
   *This method tests what happens when the term searched for is not in the text of the quotes.
   */
  public void testSearchTermNotInQuoteText(){
    QuoteList actual = quotes.search("NotInTheText", SearchTextVal);
    QuoteList expected = new QuoteList();
    AssertEquals("This term is not contained in the text of any of the quotes. The list should be empty.", expected, actual);
  }
      /**
   *This method tests what happens when the term searched for is not in the author of the quote
   */
  public void testSearchTermNotInQuoteBoth(){
    QuoteList actual = quotes.search("NotAnAuthor", SearchBothVal);
    QuoteList expected = new QuoteList();
    AssertEquals("This term is not contained in the author or text of any of the quotes. The list should be empty.", expected, actual);
  }
  
}
