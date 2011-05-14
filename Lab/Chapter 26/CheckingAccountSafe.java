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

public class CheckingAccountSafe {
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

  public synchronized void withdraw( int withdrawAmount ) throws InterruptedException{
    while (getBalance()<withdrawAmount) {
      System.out.println("Waiting for Funds to be available");
      wait();
    }

    if (withdrawAmount < 0) { // ensure that withdrawal is not negative
      System.out.println("You cannot withdraw a negative amount");
      withdrawAmount = 0;
    }
    balance -= withdrawAmount;
  } // end withdraw()

  public synchronized void deposit( int depositAmount ) throws InterruptedException{
    balance += depositAmount;
    notifyAll();
  } // end deposit()

  // Main Method
  // ===========

  public static void main( String[] args ){
    
    int initialBalance = 0;
    int initialOverdraft = 0;

    CheckingAccountSafe account = new CheckingAccountSafe();
    account.setBalance(initialBalance);
    account.setOverDraftAmount(initialOverdraft);

    System.out.printf("\n\nNew account created!!\nBalance: %d\nOverdraft Protection: %d\n\n", account.getBalance(), account.getOverDraftAmount());

    Thread deposit = new Thread( new SafeDeposit(account) );
    Thread withdraw = new Thread( new SafeWithdraw(account) );    
    
    deposit.start();
    withdraw.start();

  } // end main()

}

class SafeWithdraw extends CheckingAccountSafe implements Runnable {
  CheckingAccountSafe account;
  Random generator = new Random();
  public SafeWithdraw(CheckingAccountSafe act){
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

class SafeDeposit extends CheckingAccountSafe implements Runnable {
  CheckingAccountSafe account;
  Random generator = new Random();
  public SafeDeposit(CheckingAccountSafe act){
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

Depositing $55
Balance is: $55
Depositing $29
Balance is: $84
Depositing $32
Balance is: $116
Withdrawing $94
Balance is $: 22
Withdrawing $81
Waiting for Funds to be available
Depositing $90
Balance is: $112
Balance is $: 31
Withdrawing $47
Waiting for Funds to be available
Depositing $99
Balance is: $130
Balance is $: 83
Withdrawing $11
Balance is $: 72
Withdrawing $77
Waiting for Funds to be available
Depositing $98
Balance is: $170
Balance is $: 93
Withdrawing $11
Balance is $: 82
Depositing $23
Balance is: $105
Depositing $7
Balance is: $112
Withdrawing $73
Balance is $: 39
Depositing $92
Balance is: $131
Withdrawing $23
Balance is $: 108
Withdrawing $67
Balance is $: 41
Depositing $50
Balance is: $91
Withdrawing $30
Balance is $: 61

 */
