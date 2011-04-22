public class Factorial {
  static int fact(int n){
    if ((n==1) || (n==0)) {
      return 1;
    } else if (n<0) {
       return -1;
    } else {
      return fact(n-1)*n;
    }
  }

  public static void main (String [] args){
    System.out.println("Factorial of 5 is " + Factorial.fact(45));
    //    System.out.println("Factorial of 0 is " + Factorial.fact(0));
  }
}
