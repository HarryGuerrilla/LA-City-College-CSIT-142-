import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class BinaryArray
{
   private int[] data; // array of values
   private static final Random generator = new Random();

   // create array of given size and fill with random integers
   public BinaryArray( int size )
   {
      data = new int[ size ]; // create space for array

      // fill array with random ints in range 10-99
      for ( int i = 0; i < size; i++ )
         data[ i ] = 10 + generator.nextInt( 90 );

      Arrays.sort( data );
   } // end BinaryArray constructor

   // perform a binary search on the data
  public int binarySearch( int searchKey, int startingIndex, int endingIndex )
   {
     int middle = (endingIndex - startingIndex + 1)/2 + startingIndex;

     // print remaining elements of array
     System.out.print( remainingElements( startingIndex, endingIndex ) );

     // output spaces for alignment
     for ( int i = 0; i < middle; i++ )
       System.out.print( "   " );
     System.out.println( " * " ); // indicate current middle

     if (data[middle]==searchKey) {
       return middle;
     } else if (endingIndex-startingIndex==0) {
       return -1;
     } else if (searchKey>data[middle]) {
       return binarySearch(searchKey, middle + 1, endingIndex);
     } else if (searchKey<data[middle]) {
       return binarySearch(searchKey, startingIndex, middle - 1);
     }
     
     return -1;
     
   } // end method binarySearch

   // method to output certain values in array
   public String remainingElements( int low, int high )
   {
      StringBuilder temporary = new StringBuilder();

      // output spaces for alignment
      for ( int i = 0; i < low; i++ )
         temporary.append( "   " );

      // output elements left in array
      for ( int i = low; i <= high; i++ )
         temporary.append( data[ i ] + " " );

      temporary.append( "\n" );
      return temporary.toString();
   } // end method remainingElements

   // method to output values in array
   public String toString()
   {
      return remainingElements( 0, data.length - 1 );
   } // end method toString   

   public static void main( String[] args )
   {
      // create Scanner object to input data
      Scanner input = new Scanner( System.in );
      
      int searchInt; // search key
      int position; // location of search key in array
   
      // create array and output it
      BinaryArray searchArray = new BinaryArray( 15 );
      System.out.println( searchArray );

      // get input from user
      System.out.print( 
         "Please enter an integer value (-1 to quit): " );
      searchInt = input.nextInt(); // read an int from user
      System.out.println();

      // repeatedly input an integer; -1 terminates the program
      while ( searchInt != -1 )
      {
         // use binary search to try to find integer
        position = searchArray.binarySearch( searchInt, 0, 14 );

         // return value of -1 indicates integer was not found
         if ( position == -1 )
            System.out.println( "The integer " + searchInt + 
               " was not found.\n" );
         else
            System.out.println( "The integer " + searchInt + 
               " was found in position " + position + ".\n" );

         // get input from user
         System.out.print( 
            "Please enter an integer value (-1 to quit): " );
         searchInt = input.nextInt(); // read an int from user
         System.out.println();
      } // end while
   } // end main
} // end class BinaryArray
 
/*
11 13 15 16 17 26 35 57 59 61 61 67 74 89 91 

Please enter an integer value (-1 to quit): 27

11 13 15 16 17 26 35 57 59 61 61 67 74 89 91 
                      * 
11 13 15 16 17 26 35 
          * 
            17 26 35 
                * 
                  35 
                   * 
The integer 27 was not found.

Please enter an integer value (-1 to quit): 59

11 13 15 16 17 26 35 57 59 61 61 67 74 89 91 
                      * 
                        59 61 61 67 74 89 91 
                                  * 
                        59 61 61 
                            * 
                        59 
                         * 
The integer 59 was found in position 8.

Please enter an integer value (-1 to quit): -1
*/
