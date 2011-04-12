import java.lang.*;
import java.util.*;

public class CheckingAccount {
  int balance;
  int overDraftAmount;

  public void withdraw( int withdrawAmount ){
    try {
      if ( balance < withdrawAmount ) {
        checkOverdraft(withdrawAmount);
        overDraftAmount = overDraftAmount - (withdrawAmount - balance);
        balance = 0;
      } else {
        balance -= withdrawAmount;
      }
    }
    catch (OverDraftException e) {}
  }

  public void deposit( int depositAmount ){
    balance += depositAmount;
  }

  private void checkOverdraft(int withdrawAmount)
    throws OverDraftException
  {
    if ( balance + overDraftAmount < withdrawAmount)
      throw new OverDraftException();
  }

  public static void main( String[] args ){
  
  }
}

class OverDraftException extends Exception {
  
}
