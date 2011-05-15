import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

public class LinearArray
{
   int[] data; // array of values
   private static final Random generator = new Random();

   // create array of given size and fill with random integers
   public LinearArray( int size )
   {
      data = new int[ size ]; // create space for array

      // fill array with random ints in range 10-99
      for ( int i = 0; i < size; i++ )
         data[ i ] = 10 + generator.nextInt( 90 );
   } // end LinearArray constructor

   // perform a linear search on the data
   public int linearSearch( int searchKey, int index )
   {
      // loop through array sequentially
     if (data[index] == searchKey) {
       return index;
     } else if (index == 0) {
       return (data[index]==searchKey) ? index : -1;
     }
     return linearSearch( searchKey, index-1);

   } // end method linearSearch

   // method to output values in array
   public String toString()
   {
      return Arrays.toString( data );
   } // end method toString

   public static void main( String[] args )
   {
      // create Scanner object to input data
      Scanner input = new Scanner( System.in );

      int searchInt; // search key
      int position; // location of search key in array

      // create array and output it
      LinearArray searchArray = new LinearArray( 10 );
      System.out.println( searchArray + "\n" ); // print array

      // get input from user
      System.out.print( 
         "Please enter an integer value (-1 to quit): " );
      searchInt = input.nextInt(); // read first int from user

      // repeatedly input an integer; -1 terminates the program
      while ( searchInt != -1 )
      {
         // perform linear search
        position = searchArray.linearSearch( searchInt, searchArray.data.length-1);

         if ( position == -1 ) // integer was not found
            System.out.println( "The integer " + searchInt + 
               " was not found.\n" );
         else // integer was found
            System.out.println( "The integer " + searchInt + 
               " was found in position " + position + ".\n" );

         // get input from user
         System.out.print( 
            "Please enter an integer value (-1 to quit): " );
         searchInt = input.nextInt(); // read next int from user
      } // end while
   } // end main
} // end class LinearArray

/*
[51, 73, 98, 60, 20, 57, 65, 95, 21, 35]

Please enter an integer value (-1 to quit): 61
The integer 61 was not found.

Please enter an integer value (-1 to quit): 35
The integer 35 was found in position 9.

Please enter an integer value (-1 to quit): 60
The integer 60 was found in position 3.

Please enter an integer value (-1 to quit): -1
*/
