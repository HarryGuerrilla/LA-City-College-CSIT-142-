import org.junit.*;
import static org.junit.Assert.*;

public class TestCheckingAccount {

  private CheckingAccount newCheckingAccount(int deposit, int overdraft) {
    CheckingAccount newAccount = new CheckingAccount();
    newAccount.deposit(deposit);
    newAccount.setOverDraftAmount(overdraft);
    return newAccount;
  }

  @Test 
  public void testThatCheckingAccountAcceptsDeposits() {
    System.out.println("Checking Account Accepts Deposits");
    CheckingAccount c = newCheckingAccount(0, 0);
    c.deposit(10);
    assertTrue(c.getBalance() == 10);
  }

  @Test
  public void testThatCheckingAccountAcceptsWithdrawals() {
    System.out.println("Checking Account Accepts Withdrawals");
    CheckingAccount c = newCheckingAccount(10, 0);
    c.withdraw(5);
    assertTrue(c.getBalance() == 5);
  }

  @Test
  public void testThatOverDraftProtectionWorks(){
    System.out.println("Overdraft Protection Works");
    CheckingAccount c = newCheckingAccount(0,10);
    c.withdraw(5);
    assertTrue(c.getBalance() == 0);
    assertTrue(c.getOverDraftAmount() == 5);
  }

  /*
  @Test(expected=OverDraftException.class)
  public void testThatOverDraftExceptionIsThrown(){
    System.out.println("Overdraft Exception is Thrown");
    CheckingAccount c = newCheckingAccount(0,0);
    c.checkOverdraft(10);
    }*/
}
