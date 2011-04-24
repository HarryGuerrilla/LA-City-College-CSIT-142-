/*

  Tony Pelaez
  CSIT 142
  Chapter 18

  LAB Homework
  ============

  (b) pg 808: #18.15 (eight Queens). Upload the java, class and sample run(s).

 */
 
import java.util.ArrayList; 

class EightQueens {

  public boolean placeQueens(int availableQueens, int x, int y, ArrayList<int[]> placedQueens){

    if (spaceAvailable(x,y,placedQueens)) {
      int[] q = {x,y};
      placedQueens.add(q);
      System.out.printf("Placed queen on: %d,%d\n", q[0], q[1]);
      availableQueens -= 1;
      if (x<8) {
        if (placeQueens(availableQueens,x+1,1,placedQueens)) return true; 
      } else if (availableQueens > 0){
        int[] qx = placedQueens.get(placedQueens.size()-1);
        System.out.printf("Removed Queen: %d,%d\n", qx[0], qx[1]);
        placedQueens.remove(placedQueens.size()-1);
        availableQueens += 1;
        if (qx[1]<8) { q[1] = q[1]+1; }
        if (placeQueens(availableQueens,q[0],q[1],placedQueens)) return true;        
      }      
    } else {
      if (y<8) {
        if (placeQueens(availableQueens,x,y+1,placedQueens)) return true; 
      } else if (availableQueens > 0) {
        int[] q = placedQueens.get(placedQueens.size()-1);
        System.out.printf("Removed Queen: %d,%d\n", q[0], q[1]);
        placedQueens.remove(placedQueens.size()-1);
        availableQueens += 1;
        if (q[1]==8) { q[0] += 1; q[1] = 0; }
        if (placeQueens(availableQueens,q[0],q[1]+1,placedQueens)) return true;
      }
    }

    if (availableQueens == 0) {
      printBoard(placedQueens);
      return true; 
    } else {
      return false;
    }
  }
  
  public boolean spaceAvailable(int x, int y, ArrayList<int[]> placedQueens){
    for (int[] i : placedQueens) {
      if (i[0]==x || i[1]==y) return false;
      
      if (diagonalCheck(x,y,i)) return false;
    }
    return true;
  }

  public boolean diagonalCheck(int x, int y, int[] i){
    for (int tx=i[0], ty=i[1]; tx<=8 && ty<=8; tx++, ty++) {
      if (tx==x && ty==y) return true;
    }
    for (int tx=i[0], ty=i[1]; tx>=1 && ty>=1; tx--, ty--) {
      if (tx==x && ty==y) return true;
    }
    for (int tx=i[0], ty=i[1]; tx<=8 && ty>=1; tx++, ty--) {
      if (tx==x && ty==y) return true;
    }
    for (int tx=i[0], ty=i[1]; tx>=1 && ty<=8; tx--, ty++) {
      if (tx==x && ty==y) return true;
    }
    return false;
  }

  public void printBoard(ArrayList<int[]> q){
    System.out.println("\n\n");
    System.out.println("    SOLUTION   ");
    System.out.println("===============");
    
    for (int l=1; l<=8; l++) {
      for(int c=1; c<=8; c++){
        if (isQueen(new int[] {c,l}, q)) {
          System.out.print("Q ");
        } else {
          System.out.print("# ");
        }
        if (c==8) System.out.println("");
      }
    }
  }

  public boolean isQueen(int[] a, ArrayList<int[]> queens){
    boolean test = false;
    for (int[] q : queens) {
      if (a[0]==q[0] && a[1]==q[1]) {
        test = true;
      } else {
        if (!test) { test = false; }
      }
    }
    return test;
  }

  public static void main(String[] args){
    ArrayList<int[]> placedQueens = new ArrayList<int[]>();
    int availableQueens = 8;
    int x = 1;
    int y = 1;
    EightQueens board = new EightQueens();
    board.placeQueens(availableQueens, x, y, placedQueens);
  }
}


/*
*[master][~/Documents/Class/CSIT-142/Lab/Chapter 18]$ java EightQueens 
Placed queen on: 1,1
Placed queen on: 2,3
Placed queen on: 3,5
Placed queen on: 4,2
Placed queen on: 5,4
Removed Queen: 5,4
Placed queen on: 5,8
Removed Queen: 5,8
Removed Queen: 4,2
Placed queen on: 4,7
Placed queen on: 5,2
Placed queen on: 6,4
Placed queen on: 7,6
Removed Queen: 7,6
Removed Queen: 6,4
Removed Queen: 5,2
Placed queen on: 5,4
Removed Queen: 5,4
Removed Queen: 4,7
Placed queen on: 4,8
Placed queen on: 5,2
Placed queen on: 6,4
Placed queen on: 7,6
Removed Queen: 7,6
Removed Queen: 6,4
Removed Queen: 5,2
Placed queen on: 5,4
Removed Queen: 5,4
Removed Queen: 4,8
Placed queen on: 5,2
Placed queen on: 6,4
Placed queen on: 7,6
Removed Queen: 7,6
Removed Queen: 6,4
Removed Queen: 5,2
Placed queen on: 5,4
Removed Queen: 5,4
Placed queen on: 5,8
Placed queen on: 6,4
Placed queen on: 7,2
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,2
Removed Queen: 6,4
Removed Queen: 5,8
Placed queen on: 6,4
Placed queen on: 7,2
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,2
Placed queen on: 7,6
Removed Queen: 7,6
Removed Queen: 6,4
Removed Queen: 3,5
Placed queen on: 3,6
Placed queen on: 4,2
Placed queen on: 5,7
Placed queen on: 6,5
Removed Queen: 6,5
Removed Queen: 5,7
Removed Queen: 4,2
Placed queen on: 4,8
Placed queen on: 5,2
Placed queen on: 6,4
Removed Queen: 6,4
Placed queen on: 6,5
Removed Queen: 6,5
Removed Queen: 5,2
Removed Queen: 4,8
Placed queen on: 5,2
Placed queen on: 6,4
Removed Queen: 6,4
Placed queen on: 6,5
Removed Queen: 6,5
Placed queen on: 6,8
Placed queen on: 7,5
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,5
Removed Queen: 6,8
Placed queen on: 7,5
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,5
Removed Queen: 5,2
Placed queen on: 5,7
Placed queen on: 6,2
Placed queen on: 7,4
Removed Queen: 7,4
Removed Queen: 6,2
Placed queen on: 6,4
Removed Queen: 6,4
Placed queen on: 6,5
Removed Queen: 6,5
Removed Queen: 5,7
Removed Queen: 3,6
Placed queen on: 3,7
Placed queen on: 4,2
Placed queen on: 5,4
Placed queen on: 6,8
Removed Queen: 6,8
Removed Queen: 5,4
Placed queen on: 5,8
Placed queen on: 6,5
Removed Queen: 6,5
Removed Queen: 5,8
Placed queen on: 6,5
Removed Queen: 6,5
Placed queen on: 6,8
Placed queen on: 7,4
Removed Queen: 7,4
Placed queen on: 7,6
Placed queen on: 8,4
Removed Queen: 8,4
Removed Queen: 7,6
Removed Queen: 6,8
Placed queen on: 7,4
Removed Queen: 7,4
Placed queen on: 7,6
Placed queen on: 8,4
Removed Queen: 8,4
Removed Queen: 7,6
Removed Queen: 4,2
Removed Queen: 3,7
Placed queen on: 3,8
Placed queen on: 4,2
Placed queen on: 5,4
Removed Queen: 5,4
Placed queen on: 5,7
Removed Queen: 5,7
Removed Queen: 4,2
Placed queen on: 4,6
Placed queen on: 5,2
Removed Queen: 5,2
Placed queen on: 5,4
Placed queen on: 6,2
Placed queen on: 7,5
Removed Queen: 7,5
Removed Queen: 6,2
Removed Queen: 5,4
Removed Queen: 4,6
Removed Queen: 3,8
Placed queen on: 4,2
Placed queen on: 5,4
Placed queen on: 6,8
Removed Queen: 6,8
Removed Queen: 5,4
Placed queen on: 5,7
Placed queen on: 6,5
Removed Queen: 6,5
Removed Queen: 5,7
Placed queen on: 5,8
Placed queen on: 6,5
Removed Queen: 6,5
Removed Queen: 5,8
Placed queen on: 6,5
Removed Queen: 6,5
Placed queen on: 6,8
Placed queen on: 7,4
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,4
Placed queen on: 7,6
Placed queen on: 8,4
Removed Queen: 8,4
Removed Queen: 7,6
Removed Queen: 6,8
Placed queen on: 7,4
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,4
Placed queen on: 7,6
Placed queen on: 8,4
Removed Queen: 8,4
Removed Queen: 7,6
Removed Queen: 4,2
Placed queen on: 4,6
Placed queen on: 5,2
Placed queen on: 6,5
Removed Queen: 6,5
Removed Queen: 5,2
Placed queen on: 5,4
Placed queen on: 6,2
Placed queen on: 7,5
Removed Queen: 7,5
Removed Queen: 6,2
Removed Queen: 5,4
Placed queen on: 5,8
Placed queen on: 6,2
Placed queen on: 7,4
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,4
Placed queen on: 7,5
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,5
Removed Queen: 6,2
Placed queen on: 6,5
Placed queen on: 7,2
Placed queen on: 8,4
Removed Queen: 8,4
Removed Queen: 7,2
Removed Queen: 6,5
Removed Queen: 5,8
Placed queen on: 6,2
Placed queen on: 7,4
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,4
Placed queen on: 7,5
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,5
Removed Queen: 6,2
Placed queen on: 6,5
Placed queen on: 7,2
Placed queen on: 8,4
Removed Queen: 8,4
Removed Queen: 7,2
Removed Queen: 6,5
Removed Queen: 4,6
Placed queen on: 4,7
Placed queen on: 5,2
Placed queen on: 6,4
Placed queen on: 7,6
Removed Queen: 7,6
Removed Queen: 6,4
Placed queen on: 6,8
Placed queen on: 7,5
Removed Queen: 7,5
Placed queen on: 7,6
Placed queen on: 8,4
Removed Queen: 8,4
Removed Queen: 7,6
Removed Queen: 6,8
Placed queen on: 7,5
Removed Queen: 7,5
Placed queen on: 7,6
Placed queen on: 8,4
Removed Queen: 8,4
Removed Queen: 7,6
Removed Queen: 5,2
Placed queen on: 5,4
Placed queen on: 6,2
Placed queen on: 7,5
Removed Queen: 7,5
Removed Queen: 6,2
Placed queen on: 6,8
Placed queen on: 7,5
Placed queen on: 8,2
Removed Queen: 8,2
Removed Queen: 7,5
Removed Queen: 6,8
Placed queen on: 7,5
Placed queen on: 8,2
Removed Queen: 8,2
Removed Queen: 7,5
Removed Queen: 5,4
Removed Queen: 4,7
Placed queen on: 4,8
Placed queen on: 5,2
Placed queen on: 6,4
Placed queen on: 7,6
Removed Queen: 7,6
Removed Queen: 6,4
Placed queen on: 6,5
Removed Queen: 6,5
Removed Queen: 5,2
Placed queen on: 5,4
Placed queen on: 6,2
Removed Queen: 6,2
Removed Queen: 5,4
Removed Queen: 4,8
Placed queen on: 5,2
Placed queen on: 6,4
Placed queen on: 7,6
Removed Queen: 7,6
Removed Queen: 6,4
Placed queen on: 6,5
Removed Queen: 6,5
Placed queen on: 6,8
Placed queen on: 7,5
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,5
Placed queen on: 7,6
Placed queen on: 8,4
Removed Queen: 8,4
Removed Queen: 7,6
Removed Queen: 6,8
Placed queen on: 7,5
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,5
Placed queen on: 7,6
Placed queen on: 8,4
Removed Queen: 8,4
Removed Queen: 7,6
Removed Queen: 5,2
Placed queen on: 5,4
Placed queen on: 6,2
Placed queen on: 7,5
Removed Queen: 7,5
Removed Queen: 6,2
Placed queen on: 6,8
Placed queen on: 7,5
Placed queen on: 8,2
Removed Queen: 8,2
Removed Queen: 7,5
Removed Queen: 6,8
Placed queen on: 7,5
Placed queen on: 8,2
Removed Queen: 8,2
Removed Queen: 7,5
Removed Queen: 5,4
Placed queen on: 5,7
Placed queen on: 6,2
Placed queen on: 7,4
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,4
Placed queen on: 7,6
Removed Queen: 7,6
Removed Queen: 6,2
Placed queen on: 6,4
Placed queen on: 7,2
Placed queen on: 8,5
Removed Queen: 8,5
Removed Queen: 7,2
Placed queen on: 7,6
Removed Queen: 7,6
Removed Queen: 6,4
Placed queen on: 6,5
Placed queen on: 7,2
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,2
Removed Queen: 6,5
Removed Queen: 5,7
Placed queen on: 5,8
Placed queen on: 6,2
Placed queen on: 7,4
Placed queen on: 8,6
Removed Queen: 8,6
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,4
Placed queen on: 7,5
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,5
Removed Queen: 6,2
Placed queen on: 6,4
Placed queen on: 7,2
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,2
Removed Queen: 6,4
Placed queen on: 6,5
Placed queen on: 7,2
Placed queen on: 8,4
Removed Queen: 8,4
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,2
Removed Queen: 6,5
Removed Queen: 5,8
Placed queen on: 6,2
Placed queen on: 7,4
Placed queen on: 8,6
Removed Queen: 8,6
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,4
Placed queen on: 7,5
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,5
Placed queen on: 7,6
Removed Queen: 7,6
Removed Queen: 6,2
Placed queen on: 6,4
Placed queen on: 7,2
Placed queen on: 8,5
Removed Queen: 8,5
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,2
Placed queen on: 7,6
Removed Queen: 7,6
Removed Queen: 6,4
Placed queen on: 6,5
Placed queen on: 7,2
Placed queen on: 8,4
Removed Queen: 8,4
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,2
Removed Queen: 6,5
Placed queen on: 6,8
Placed queen on: 7,2
Placed queen on: 8,4
Removed Queen: 8,4
Placed queen on: 8,5
Removed Queen: 8,5
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,2
Placed queen on: 7,4
Placed queen on: 8,2
Removed Queen: 8,2
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,4
Placed queen on: 7,5
Placed queen on: 8,2
Removed Queen: 8,2
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,5
Placed queen on: 7,6
Placed queen on: 8,2
Removed Queen: 8,2
Placed queen on: 8,4
Removed Queen: 8,4
Removed Queen: 7,6
Removed Queen: 6,8
Placed queen on: 7,2
Placed queen on: 8,4
Removed Queen: 8,4
Placed queen on: 8,5
Removed Queen: 8,5
Placed queen on: 8,6
Removed Queen: 8,6
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,2
Placed queen on: 7,4
Placed queen on: 8,2
Removed Queen: 8,2
Placed queen on: 8,6
Removed Queen: 8,6
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,4
Placed queen on: 7,5
Placed queen on: 8,2
Removed Queen: 8,2
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,5
Placed queen on: 7,6
Placed queen on: 8,2
Removed Queen: 8,2
Placed queen on: 8,4
Removed Queen: 8,4
Removed Queen: 7,6
Removed Queen: 2,3
Placed queen on: 2,4
Placed queen on: 3,2
Placed queen on: 4,5
Placed queen on: 5,3
Removed Queen: 5,3
Placed queen on: 5,8
Removed Queen: 5,8
Removed Queen: 4,5
Placed queen on: 4,7
Placed queen on: 5,3
Removed Queen: 5,3
Removed Queen: 4,7
Placed queen on: 4,8
Placed queen on: 5,3
Placed queen on: 6,7
Removed Queen: 6,7
Removed Queen: 5,3
Placed queen on: 5,6
Placed queen on: 6,3
Removed Queen: 6,3
Removed Queen: 5,6
Removed Queen: 4,8
Placed queen on: 5,3
Placed queen on: 6,7
Removed Queen: 6,7
Removed Queen: 5,3
Placed queen on: 5,6
Placed queen on: 6,3
Placed queen on: 7,5
Removed Queen: 7,5
Removed Queen: 6,3
Removed Queen: 5,6
Placed queen on: 5,8
Placed queen on: 6,3
Placed queen on: 7,5
Removed Queen: 7,5
Removed Queen: 6,3
Removed Queen: 5,8
Placed queen on: 6,3
Placed queen on: 7,5
Removed Queen: 7,5
Placed queen on: 7,8
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,8
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 6,3
Placed queen on: 6,7
Placed queen on: 7,3
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,3
Placed queen on: 7,5
Placed queen on: 8,3
Removed Queen: 8,3
Removed Queen: 7,5
Removed Queen: 6,7
Removed Queen: 3,2
Placed queen on: 3,6
Placed queen on: 4,3
Removed Queen: 4,3
Placed queen on: 4,8
Placed queen on: 5,2
Placed queen on: 6,5
Placed queen on: 7,3
Removed Queen: 7,3
Removed Queen: 6,5
Placed queen on: 6,7
Placed queen on: 7,3
Removed Queen: 7,3
Removed Queen: 6,7
Removed Queen: 5,2
Placed queen on: 5,3
Placed queen on: 6,5
Removed Queen: 6,5
Placed queen on: 6,7
Removed Queen: 6,7
Removed Queen: 5,3
Removed Queen: 4,8
Placed queen on: 5,2
Placed queen on: 6,5
Placed queen on: 7,3
Removed Queen: 7,3
Placed queen on: 7,8
Removed Queen: 7,8
Removed Queen: 6,5
Placed queen on: 6,7
Placed queen on: 7,3
Removed Queen: 7,3
Placed queen on: 7,5
Placed queen on: 8,3
Removed Queen: 8,3
Removed Queen: 7,5
Removed Queen: 6,7
Removed Queen: 5,2
Placed queen on: 5,3
Placed queen on: 6,5
Placed queen on: 7,8
Placed queen on: 8,2
Removed Queen: 8,2
Removed Queen: 7,8
Placed queen on: 8,2
Removed Queen: 8,2
Removed Queen: 6,5
Placed queen on: 6,7
Removed Queen: 6,7
Removed Queen: 5,3
Removed Queen: 3,6
Placed queen on: 3,7
Placed queen on: 4,3
Placed queen on: 5,6
Placed queen on: 6,2
Placed queen on: 7,5
Removed Queen: 7,5
Removed Queen: 6,2
Removed Queen: 5,6
Placed queen on: 5,8
Placed queen on: 6,2
Placed queen on: 7,5
Removed Queen: 7,5
Removed Queen: 6,2
Removed Queen: 5,8
Placed queen on: 6,2
Placed queen on: 7,5
Removed Queen: 7,5
Placed queen on: 7,8
Placed queen on: 8,5
Removed Queen: 8,5
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,8
Placed queen on: 8,5
Removed Queen: 8,5
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 6,2
Removed Queen: 4,3
Placed queen on: 4,5
Placed queen on: 5,2
Removed Queen: 5,2
Placed queen on: 5,3
Removed Queen: 5,3
Placed queen on: 5,8
Placed queen on: 6,2
Removed Queen: 6,2
Removed Queen: 5,8
Placed queen on: 6,2
Placed queen on: 7,6
Placed queen on: 8,3
Removed Queen: 8,3
Removed Queen: 7,6
Removed Queen: 6,2
Removed Queen: 4,5
Removed Queen: 3,7
Placed queen on: 3,8
Placed queen on: 4,3
Removed Queen: 4,3
Placed queen on: 4,5
Placed queen on: 5,2
Removed Queen: 5,2
Placed queen on: 5,3
Removed Queen: 5,3
Removed Queen: 4,5
Removed Queen: 3,8
Placed queen on: 4,3
Placed queen on: 5,6
Placed queen on: 6,2
Placed queen on: 7,5
Removed Queen: 7,5
Removed Queen: 6,2
Removed Queen: 5,6
Placed queen on: 5,8
Placed queen on: 6,2
Placed queen on: 7,5
Removed Queen: 7,5
Removed Queen: 6,2
Removed Queen: 5,8
Placed queen on: 6,2
Placed queen on: 7,5
Removed Queen: 7,5
Placed queen on: 7,8
Placed queen on: 8,5
Removed Queen: 8,5
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,8
Placed queen on: 8,5
Removed Queen: 8,5
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 6,2
Placed queen on: 6,7
Placed queen on: 7,2
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,2
Placed queen on: 7,5
Placed queen on: 8,2
Removed Queen: 8,2
Removed Queen: 7,5
Removed Queen: 6,7
Removed Queen: 4,3
Placed queen on: 4,5
Placed queen on: 5,2
Removed Queen: 5,2
Placed queen on: 5,3
Removed Queen: 5,3
Placed queen on: 5,8
Placed queen on: 6,2
Removed Queen: 6,2
Removed Queen: 5,8
Placed queen on: 6,2
Placed queen on: 7,6
Placed queen on: 8,3
Removed Queen: 8,3
Removed Queen: 7,6
Removed Queen: 6,2
Removed Queen: 4,5
Placed queen on: 4,7
Placed queen on: 5,2
Removed Queen: 5,2
Placed queen on: 5,3
Removed Queen: 5,3
Removed Queen: 4,7
Placed queen on: 4,8
Placed queen on: 5,2
Placed queen on: 6,5
Placed queen on: 7,3
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,3
Removed Queen: 6,5
Placed queen on: 6,7
Placed queen on: 7,3
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,3
Removed Queen: 6,7
Removed Queen: 5,2
Placed queen on: 5,3
Placed queen on: 6,5
Placed queen on: 7,2
Removed Queen: 7,2
Removed Queen: 6,5
Placed queen on: 6,7
Placed queen on: 7,2
Removed Queen: 7,2
Removed Queen: 6,7
Removed Queen: 5,3
Placed queen on: 5,6
Placed queen on: 6,2
Removed Queen: 6,2
Placed queen on: 6,3
Removed Queen: 6,3
Removed Queen: 5,6
Removed Queen: 4,8
Placed queen on: 5,2
Placed queen on: 6,5
Placed queen on: 7,3
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,3
Placed queen on: 7,8
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,8
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 6,5
Placed queen on: 6,7
Placed queen on: 7,3
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,3
Placed queen on: 7,5
Placed queen on: 8,3
Removed Queen: 8,3
Removed Queen: 7,5
Removed Queen: 6,7
Removed Queen: 5,2
Placed queen on: 5,3
Placed queen on: 6,5
Placed queen on: 7,2
Removed Queen: 7,2
Placed queen on: 7,8
Placed queen on: 8,2
Removed Queen: 8,2
Removed Queen: 7,8
Placed queen on: 8,2
Removed Queen: 8,2
Removed Queen: 6,5
Placed queen on: 6,7
Placed queen on: 7,2
Removed Queen: 7,2
Removed Queen: 6,7
Removed Queen: 5,3
Placed queen on: 5,6
Placed queen on: 6,2
Placed queen on: 7,5
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,5
Removed Queen: 6,2
Placed queen on: 6,3
Placed queen on: 7,5
Placed queen on: 8,2
Removed Queen: 8,2
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,5
Removed Queen: 6,3
Removed Queen: 5,6
Placed queen on: 5,8
Placed queen on: 6,2
Placed queen on: 7,5
Placed queen on: 8,3
Removed Queen: 8,3
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,5
Removed Queen: 6,2
Placed queen on: 6,3
Placed queen on: 7,5
Placed queen on: 8,2
Removed Queen: 8,2
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,5
Removed Queen: 6,3
Placed queen on: 6,5
Placed queen on: 7,2
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,2
Placed queen on: 7,3
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,3
Removed Queen: 6,5
Removed Queen: 5,8
Placed queen on: 6,2
Placed queen on: 7,5
Placed queen on: 8,3
Removed Queen: 8,3
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,5
Placed queen on: 7,6
Placed queen on: 8,3
Removed Queen: 8,3
Removed Queen: 7,6
Placed queen on: 7,8
Placed queen on: 8,3
Removed Queen: 8,3
Placed queen on: 8,5
Removed Queen: 8,5
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,8
Placed queen on: 8,3
Removed Queen: 8,3
Placed queen on: 8,5
Removed Queen: 8,5
Placed queen on: 8,6
Removed Queen: 8,6
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 6,2
Placed queen on: 6,3
Placed queen on: 7,5
Placed queen on: 8,2
Removed Queen: 8,2
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 7,5
Placed queen on: 7,6
Placed queen on: 8,2
Removed Queen: 8,2
Removed Queen: 7,6
Placed queen on: 7,8
Placed queen on: 8,2
Removed Queen: 8,2
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,8
Placed queen on: 8,2
Removed Queen: 8,2
Placed queen on: 8,6
Removed Queen: 8,6
Placed queen on: 8,7
Removed Queen: 8,7
Removed Queen: 6,3
Placed queen on: 6,5
Placed queen on: 7,2
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,2
Placed queen on: 7,3
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,3
Placed queen on: 7,8
Placed queen on: 8,2
Removed Queen: 8,2
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,8
Placed queen on: 8,2
Removed Queen: 8,2
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 6,5
Placed queen on: 6,7
Placed queen on: 7,2
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,2
Placed queen on: 7,3
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,3
Placed queen on: 7,5
Placed queen on: 8,2
Removed Queen: 8,2
Placed queen on: 8,3
Removed Queen: 8,3
Removed Queen: 7,5
Removed Queen: 6,7
Removed Queen: 2,4
Placed queen on: 2,5
Placed queen on: 3,2
Placed queen on: 4,6
Placed queen on: 5,3
Placed queen on: 6,7
Placed queen on: 7,4
Removed Queen: 7,4
Removed Queen: 6,7
Removed Queen: 5,3
Removed Queen: 4,6
Placed queen on: 4,8
Placed queen on: 5,3
Placed queen on: 6,7
Placed queen on: 7,4
Removed Queen: 7,4
Removed Queen: 6,7
Removed Queen: 5,3
Placed queen on: 5,6
Placed queen on: 6,3
Removed Queen: 6,3
Placed queen on: 6,4
Removed Queen: 6,4
Removed Queen: 5,6
Removed Queen: 4,8
Placed queen on: 5,3
Placed queen on: 6,7
Placed queen on: 7,4
Removed Queen: 7,4
Removed Queen: 6,7
Placed queen on: 6,8
Placed queen on: 7,4
Removed Queen: 7,4
Removed Queen: 6,8
Placed queen on: 7,4
Removed Queen: 7,4
Placed queen on: 7,8
Placed queen on: 8,4
Removed Queen: 8,4
Removed Queen: 7,8
Placed queen on: 8,4
Removed Queen: 8,4
Removed Queen: 5,3
Placed queen on: 5,6
Placed queen on: 6,3
Removed Queen: 6,3
Placed queen on: 6,4
Removed Queen: 6,4
Placed queen on: 6,8
Placed queen on: 7,3
Removed Queen: 7,3
Removed Queen: 6,8
Placed queen on: 7,3
Removed Queen: 7,3
Removed Queen: 5,6
Placed queen on: 5,7
Placed queen on: 6,3
Placed queen on: 7,8
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 7,8
Placed queen on: 8,6
Removed Queen: 8,6
Removed Queen: 6,3
Placed queen on: 6,4
Placed queen on: 7,8
Placed queen on: 8,3
Removed Queen: 8,3
Removed Queen: 7,8
Placed queen on: 8,3
Removed Queen: 8,3
Removed Queen: 6,4
Removed Queen: 5,7
Removed Queen: 3,2
Placed queen on: 3,7
Placed queen on: 4,2
Placed queen on: 5,4
Placed queen on: 6,8
Removed Queen: 6,8
Placed queen on: 7,8
Placed queen on: 8,3
Removed Queen: 8,3
Removed Queen: 7,8
Placed queen on: 8,3
Removed Queen: 8,3
Removed Queen: 5,4
Placed queen on: 5,6
Placed queen on: 6,3
Removed Queen: 6,3
Placed queen on: 6,8
Removed Queen: 6,8
Removed Queen: 5,6
Removed Queen: 4,2
Removed Queen: 3,7
Placed queen on: 3,8
Placed queen on: 4,2
Placed queen on: 5,4
Placed queen on: 6,7
Placed queen on: 7,3
Removed Queen: 7,3
Removed Queen: 6,7
Removed Queen: 5,4
Placed queen on: 5,7
Placed queen on: 6,3
Placed queen on: 7,6
Removed Queen: 7,6
Removed Queen: 6,3
Removed Queen: 5,7
Removed Queen: 4,2
Placed queen on: 4,6
Placed queen on: 5,3
Placed queen on: 6,7
Placed queen on: 7,2
Placed queen on: 8,4



    SOLUTION   
===============
Q # # # # # # # 
# # # # # # Q # 
# # # # Q # # # 
# # # # # # # Q 
# Q # # # # # # 
# # # Q # # # # 
# # # # # Q # # 
# # Q # # # # # 
*[master][~/Documents/Class/CSIT-142/Lab/Chapter 18]$ 

 */
