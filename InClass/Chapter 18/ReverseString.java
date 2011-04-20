public class ReverseString{
  static void swap(String text){
    if (text.length()==0) {
      System.out.println();
    } else {
      //      System.out.print(text.charAt(text.length()-1));
      System.out.print(text.substring(text.length()-1, text.length()));
      swap(text.substring(0, text.length()-1));
    }
  }

  public static void main (String[] args){
    swap("Hello");
  }
}
