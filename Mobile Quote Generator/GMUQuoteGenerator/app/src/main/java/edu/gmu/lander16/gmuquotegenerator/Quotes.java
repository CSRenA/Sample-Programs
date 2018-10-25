package edu.gmu.lander16.gmuquotegenerator;
/**
 * Quote data object.
 * @author Mongkoldech Rajapakdee & Jeff offutt
 *         Date: Nov 2009
 * A quote has two parts, an author and a quoteText.
 * This bean class provides getters and setters for both, plus a toString()
 */
public class Quotes
{
    private String author;
    private String quoteText;
    private String keyword;

    // Default constructor does nothing
    public Quotes ()
    {
    }

    // Constructor that assigns both strings
    public Quotes (String author, String quoteText, String keyword)
    {
        this.author = author;
        this.quoteText = quoteText;
        this.keyword = keyword;
    }

    // Getter and setter for author
    public String getAuthor ()
    {
        return author;
    }
    public void setAuthor (String author)
    {
        this.author = author;
    }

    // Getter and setter for quoteText
    public String getQuoteText ()
    {
        return quoteText;
    }
    public void setQuoteText (String quoteText)
    {
        this.quoteText = quoteText;
    }
    
    //Getter and setter for keyword
    public String getKeyword ()
    {
      return keyword;
    }
    public void setKeyword (String keyword)
    {
      this.keyword = keyword;
    }

    @Override
    public String toString ()
    {
        return "Quote {" + "author='" + author + '\'' + ", quoteText='" + quoteText + '\'' + ", keyword='"+ keyword + '\'' + '}';
    }
}

