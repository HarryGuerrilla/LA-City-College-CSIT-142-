/*

  Tony Pelaez
  CSIT 142
  Chapter 11

  LAB HOMEWORK
  ===========================================================================

  (a) 11.20 Write a program that shows a constructor passing information 
  about constructor failure to an exception handler.  Define class SomeClass,
  which throws an Exception in the constructor.  Your program should try to 
  create an object of type SomeClass and catch the exception that is thrown
  from the constructor.

*/

class SomeClass {
  public SomeClass() throws Exception{
    throw new Exception("Exception Thrown in Constructor!");
  }

  public static void main(String[] args) {
    try {
      SomeClass test = new SomeClass();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
