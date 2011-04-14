/*

  Tony Pelaez
  CSIT 142
  Chapter 11

  LAB HOMEWORK
  ===========================================================================

  (b) Create a CheckingAccount class. Have two instance variables. Balance 
  and OverdrafftAmount. Accounts may be setup with or without over draft 
  protection. Have two methods withdraw and deposit. If the Balance is 
  sufficient then allow the withdrawal to proceed. If the Balance is not 
  enough, check if there is enough in the OverdrafftAmount for the withdrawal 
  to proceed. Create an OverdraftException object that has a deficit field, 
  which keeps track of the extra amount needed. In your withdraw method throw 
  appropriate Exceptions.

 */

import java.util.Scanner;

public class CheckingAccount {
  private int balance = 0;
  private int overDraftAmount = 0;


  // Getters and Setters
  // ===================

  public int getBalance(){
    return balance;
  } // end getBalance()

  public void setBalance(int new_balance){
    balance = (new_balance > 0) ? new_balance : 0;
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

    try {
      if ( balance < withdrawAmount ) {
        checkOverdraft(withdrawAmount);
        setOverDraftAmount(overDraftAmount - (withdrawAmount - balance));
        setBalance(0);
      } else {
        setBalance(getBalance() - withdrawAmount);
      }
    }
    catch (OverDraftException e) {
      System.err.print("\n\n");      
      System.err.println("===================================================");
      System.err.println("#           !!!!!!!!!ATTENTION!!!!!!!!            #");
      System.err.println("#                                                 #");
      System.err.println("#  This transaction has been stopped! It would    #");
      System.err.println("#  have caused your account to be overdrawn.      #");
      System.err.println("#  Please make additional deposits into your,     #");
      System.err.println("#  or increase the amount of your overdraft       #");
      System.err.println("#  before proceding.                              #");
      System.err.println("#                                                 #");
      System.err.printf( "#      DEFICIT CAUSED BY TRANSACTION: $%1$5d      #\n", e.getDeficit());
      System.err.println("===================================================");
      System.err.print("\n\n");
    }
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
    
    Scanner input = new Scanner(System.in);

    System.out.print("Enter initial balance: ");
    int initialBalance = input.nextInt();

    System.out.print("Enter overdraft protection amount: ");
    int initialOverdraft = input.nextInt();

    CheckingAccount account = new CheckingAccount();
    account.setBalance(initialBalance);
    account.setOverDraftAmount(initialOverdraft);

    System.out.printf("\n\nNew account created!!\nBalance: %d\nOverdraft Protection: %d\n\n", account.getBalance(), account.getOverDraftAmount());

    boolean accountActivity = true;
    do {
      
      System.out.print("\n\n");
      System.out.println("Would you like to");
      System.out.println("=================");
      System.out.println("1. Make a deposit");
      System.out.println("2. Withdraw Funds");
      System.out.println("3. Exit          ");
      System.out.print("\n\n");
      int action = input.nextInt();

      switch (action) {
      case 1:
        System.out.print("Enter deposit amount: ");
        int deposit = input.nextInt();
        account.deposit(deposit);
        System.out.printf("\n\nYour new balance is: $%d\n\n", account.getBalance());
        break;
      case 2:
        System.out.print("Enter the amount you would like to withdraw: ");
        int withdrawal = input.nextInt();
        account.withdraw(withdrawal);
        System.out.printf("\n\nYour new balance is: $%d\n", account.getBalance());
        System.out.printf("Your new overdraft protection is: $%d\n\n", account.getOverDraftAmount());
        break;
      case 3:
        accountActivity = false;
        break;
      default:
        break;
      }

      
    } while (accountActivity);

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




// CODE AS RUN
// ===========

/*

[master][~/Documents/Class/CSIT-142/Lab/Chapter 11]$ javac CheckingAccount.java 
[master][~/Documents/Class/CSIT-142/Lab/Chapter 11]$ java CheckingAccount 
Enter initial balance: 25
Enter overdraft protection amount: 100


New account created!!
Balance: 25
Overdraft Protection: 100



Would you like to
=================
1. Make a deposit
2. Withdraw Funds
3. Exit          


1
Enter deposit amount: 50


Your new balance is: $75



Would you like to
=================
1. Make a deposit
2. Withdraw Funds
3. Exit          


2
Enter the amount you would like to withdraw: 25


Your new balance is: $50
Your new overdraft protection is: $100



Would you like to
=================
1. Make a deposit
2. Withdraw Funds
3. Exit          


2
Enter the amount you would like to withdraw: 100


Your new balance is: $0
Your new overdraft protection is: $50



Would you like to
=================
1. Make a deposit
2. Withdraw Funds
3. Exit          


2
Enter the amount you would like to withdraw: 100


===================================================
#           !!!!!!!!!ATTENTION!!!!!!!!            #
#                                                 #
#  This transaction has been stopped! It would    #
#  have caused your account to be overdrawn.      #
#  Please make additional deposits into your,     #
#  or increase the amount of your overdraft       #
#  before proceding.                              #
#                                                 #
#      DEFICIT CAUSED BY TRANSACTION: $  -50      #
===================================================




Your new balance is: $0
Your new overdraft protection is: $50



Would you like to
=================
1. Make a deposit
2. Withdraw Funds
3. Exit          


3
[master][~/Documents/Class/CSIT-142/Lab/Chapter 11]$ 

*/
