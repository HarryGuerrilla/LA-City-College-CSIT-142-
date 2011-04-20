class JavaBat{
  
  public int count7(int n) {
    if (n == 0) {
      return 0;
    } else {
      if (n%10==7) {
        return 1 + count7(n/10);
      } else {
        return 0 + count7(n/10);
      }
    }
  }

  public int count8(int n) {
    if (n == 0) {
      return 0;
    } else {
      if (n%10==8) {
        if (n%100==88) {
          return 2 + count8(n/10);
        } else {
          return 1 + count8(n/10);
        }
      } else {
        return 0 + count8(n/10);
      }
    }
  }

  public static void main(String[] args){
    JavaBat test = new JavaBat();

    System.out.println(test.count7(717));
    System.out.println(test.count7(7))
    System.out.println(test.count7(123));

    System.out.println(test.count8(8));
    System.out.println(test.count8(818))
    System.out.println(test.count8(8818));
  }
}
