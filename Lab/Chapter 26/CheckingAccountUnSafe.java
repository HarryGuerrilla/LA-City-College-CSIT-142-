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
    balance -= withdrawAmount;
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
/*
New account created!!
Balance: 0
Overdraft Protection: 0

Depositing $24
Balance is: $24
Withdrawing $91
Balance is $: -67
Withdrawing $31
Balance is $: -98
Depositing $71
Balance is: $-27
Depositing $18
Balance is: $-9
Depositing $61
Balance is: $52
Withdrawing $84
Balance is $: -32
Depositing $31
Balance is: $-1
Withdrawing $38
Balance is $: -39
Depositing $35
Balance is: $-4
Depositing $64
Balance is: $60
Withdrawing $0
Balance is $: 60
Withdrawing $16
Balance is $: 44
Withdrawing $16
Balance is $: 28
Depositing $24
Balance is: $52
Depositing $50
Balance is: $102
Withdrawing $14
Balance is $: 88
Withdrawing $66
Balance is $: 22
Depositing $14
Balance is: $36
Withdrawing $30
Balance is $: 6

 */
