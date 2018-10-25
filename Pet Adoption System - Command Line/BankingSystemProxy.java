/**The BankingSystemProxy class verifies that a user's credit card number and expiration date are real before allowing them to create an adoption request and then charges the $10 adoption fee
  * @author Lauren Anderson
  * */
public class BankingSystemProxy{
  
  /** Specifies whether the credit card number and expiration date are valid after runnint the verify method.*/
  private boolean isValidPymt;
  
  /**The banking system proxy constructor, contains isValidPymt, a boolean field, which specifies whether the credit card number and the expiration date are valid after running the verify method.
    */
  public BankingSystemProxy(){
    isValidPymt = false;
  }
  /**This method checks the isValidPymt field and returns true if the payment was verified and valid
    * @return true if isValidPymt is true and false if isValidPymt is false*/
  public boolean checkIfValid(){
    return isValidPymt;
  }
  
  /**This method verifies that the credit card number and expiration date provided from the banking system proxy are valid and modifies the isValidPymt field based on its findings.
    * @param creditCardNumber String representation of a 16 digit credit card number
    * @param expDate String representation of a credit card expiration date in mm/yy format */
  public void verify(String creditCardNumber, String expDate){
    //First verify that the credit card number is of a correct length
    int correctLength = 16;
    String numberNoSpaces = creditCardNumber.replace(" ", "");
    int actualLength = numberNoSpaces.length();
    if(actualLength != correctLength){
      isValidPymt = false;
      return;
    }
    //Next check that the credit card is not expired (really only checking the year)
    String[]monthYear = expDate.split("/");
    int month = Integer.parseInt(monthYear[0]);
    int year = Integer.parseInt(monthYear[1]);
    if(month>12 || month < 1){
      isValidPymt = false;
      return;
    }
    else if(year < 17){
      isValidPymt = false;
      return;
    }
    else{
      isValidPymt = true;
    }
  }
}
  
  
  
  