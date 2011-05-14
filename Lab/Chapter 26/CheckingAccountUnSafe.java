/*

  Tony Pelaez
  CSIT 142
  Chapter 11

  LAB HOMEWORK
  ===========================================================================

  (a) Write a BankAccount class that has withdraw and deposit methods. 
  Implement the class as 'not thread safe'. Exhibit the unsafe behavior, by 
  running two threads making random withdrawals and deposits. Upload the java 
  source, class and sample output.

 */

//import java.util.Scanner;
import java.util.Random;

public class CheckingAccountUnSafe {
  private int balance = 0;
  private int overDraftAmount = 0;


  // Getters and Setters
  // ===================

  public int getBalance(){
    return balance;
  } // end getBalance()

  public void setBalance(int new_balance){
    // balance = (new_balance > 0) ? new_balance : 0;
    balance = new_balance;
  }

  public int getOverDraftAmount(){
    return overDraftAmount;
  } // end getOverDraftAmount()

  public void setOverDraftAmount(int overdraft){
    overDraftAmount = (overdraft > 0) ? overdraft : 0;
  } // end setOverDraftAmount()


  // Other Methods
  // =============

  public void withdraw( int withdrawAmount ){
    
    if (withdrawAmount < 0) { // ensure that withdrawal is not negative
      System.out.println("You cannot withdraw a negative amount");
      withdrawAmount = 0;
    }

    // try {
      // if ( balance < withdrawAmount ) {
      //   checkOverdraft(withdrawAmount);
      //   setOverDraftAmount(overDraftAmount - (withdrawAmount - balance));
      //   setBalance(0);
      // } else {
        setBalance(getBalance() - withdrawAmount);
      // }
    // }
    // catch (OverDraftException e) {
    //   System.err.print("\n\n");      
    //   System.err.println("===================================================");
    //   System.err.println("#           !!!!!!!!!ATTENTION!!!!!!!!            #");
    //   System.err.println("#                                                 #");
    //   System.err.println("#  This transaction has been stopped! It would    #");
    //   System.err.println("#  have caused your account to be overdrawn.      #");
    //   System.err.println("#  Please make additional deposits into your,     #");
    //   System.err.println("#  or increase the amount of your overdraft       #");
    //   System.err.println("#  before proceding.                              #");
    //   System.err.println("#                                                 #");
    //   System.err.printf( "#      DEFICIT CAUSED BY TRANSACTION: $%1$5d      #\n", e.getDeficit());
    //   System.err.println("===================================================");
    //   System.err.print("\n\n");
    // }
  } // end withdraw()

  public void deposit( int depositAmount ){
    balance += depositAmount;
  } // end deposit()

  private void checkOverdraft(int withdrawAmount)
    throws OverDraftException
  {
    if ( balance + overDraftAmount < withdrawAmount) {
      OverDraftException e = new OverDraftException();
      e.setDeficit(balance + overDraftAmount - withdrawAmount);
      throw e;
    }
  } // end checkOverdraft()



  // Main Method
  // ===========

  public static void main( String[] args ){
    
    int initialBalance = 0;
    int initialOverdraft = 0;

    CheckingAccountUnSafe account = new CheckingAccountUnSafe();
    account.setBalance(initialBalance);
    account.setOverDraftAmount(initialOverdraft);

    System.out.printf("\n\nNew account created!!\nBalance: %d\nOverdraft Protection: %d\n\n", account.getBalance(), account.getOverDraftAmount());

    Thread deposit = new Thread( new Deposit(account) );
    Thread withdraw = new Thread( new Withdraw(account) );    
    
    deposit.start();
    withdraw.start();

  } // end main()

}


// Class OverDraftException is an exception raised when the Overdraft Amount 
// is not enough to cover the withdrawal amount.  The Class also stores an
// instance variable of the deficit amount.
class OverDraftException extends Exception {
  private int deficit = 0;

  public OverDraftException(String msg, Throwable e){
    super(msg,e);
  }

  public OverDraftException(String msg){
    super(msg);
  }

  public OverDraftException(){
    super();
  }

  public int getDeficit(){
    return deficit;
  }
  public void setDeficit(int adjustment){
    deficit += adjustment;
  }
}

class Withdraw extends CheckingAccountUnSafe implements Runnable {
  CheckingAccountUnSafe account;
  Random generator = new Random();
  public Withdraw(CheckingAccountUnSafe act){
    account = act;
  }

  public void run(){
    for (int count = 1; count <= 10; count++){
      try{
        Thread.sleep(generator.nextInt(3000));
        int withdrawAmount = generator.nextInt(100);
        System.out.println("Withdrawing $" + withdrawAmount);
        account.withdraw(withdrawAmount);
        System.out.println("Balance is $: " + account.getBalance());
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

class Deposit extends CheckingAccountUnSafe implements Runnable {
  CheckingAccountUnSafe account;
  Random generator = new Random();
  public Deposit(CheckingAccountUnSafe act){
    account = act;
  }
  public void run(){
    for (int count = 1; count <= 10; count++){
      try{
        Thread.sleep(generator.nextInt(3000));
        int depositAmount = generator.nextInt(100);
        System.out.println("Depositing $" + depositAmount);
        account.deposit(depositAmount);
        System.out.println("Balance is: $" + account.getBalance());
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }  
  }
}

// CODE AS RUN
// ===========
