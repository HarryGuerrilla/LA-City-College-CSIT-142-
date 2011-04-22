public class ReverseArray{
  static void swap(int[] array, int size){
    if (size==0) {
      System.out.println();
    } else {
      System.out.print(array[size-1] + " ");
      swap(array, size-1);
    }
  }

  public static void main (String[] args){
    int array[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    swap(array, array.length);
  }
}
