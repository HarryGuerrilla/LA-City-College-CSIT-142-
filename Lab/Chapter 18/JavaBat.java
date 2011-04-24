/*

  Tony Pelaez
  CSIT 142
  Chapter 18

  LAB Homework for Javabat
  ========================

 */
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

  public int countHi(String str){
    if (str.length() < 2) {
      return 0;
    } else {
      if (str.substring(0,2).equals("hi")) {
        return 1 + countHi(str.substring(1));
      } else {
        return 0 + countHi(str.substring(1));
      }
    }
  }

  public int countX(String str){
    if (str.length() < 1) {
      return 0;
    } else {
      if (str.charAt(0) == 'x') {
        return 1 + countX(str.substring(1));
      } else {
        return 0 + countX(str.substring(1));
      }
    }
  }

  public boolean groupSum5(int start, int[] nums, int target) {
    if (start >= nums.length) {
      return (target == 0);
    }
    if (nums[start]%5==0) {
      if (nums.length >= start+2 && nums[start+1]==1) {
        if (groupSum5(start + 2, nums, target - nums[start])) return true;
      } else {
        if (groupSum5(start + 1, nums, target - nums[start])) return true;
      }
    } else {
      if (groupSum5(start + 1, nums, target - nums[start])) return true;
      if (groupSum5(start + 1, nums, target)) return true;
    }
    return false;
  }

  public boolean groupSum6(int start, int[] nums, int target) {
    if (start >= nums.length) {
      return (target == 0);
    }
    if (nums[start]%6==0) {
      if (groupSum6(start + 1, nums, target - nums[start])) return true;
    } else {
      if (groupSum6(start + 1, nums, target - nums[start])) return true;
      if (groupSum6(start + 1, nums, target)) return true;
    }
    return false;
  }

  public boolean groupNoAdj(int start, int[] nums, int target) {
    if (start >= nums.length) return (target == 0);
    if (groupNoAdj(start + 2, nums, target - nums[start])) return true;
    if (groupNoAdj(start + 1, nums, target)) return true;
    return false;
  }

  public static void main(String[] args){
    JavaBat test = new JavaBat();

    System.out.printf("count7(717) → 2 => %d \n", test.count7(717));
    System.out.printf("count7(7) → 1 => %d \n", test.count7(7));
    System.out.printf("count7(123) → 0 => %d \n", test.count7(123));

    System.out.printf("count8(8) → 1 => %d \n", test.count8(8));
    System.out.printf("count8(818) → 2 => %d \n", test.count8(818));
    System.out.printf("count8(8818) → 4 => %d \n", test.count8(8818));

    System.out.printf("countHi(\"xxhixx\") → 1 => %d \n", test.countHi("xxhixx"));
    System.out.printf("countHi(\"xhixhix\") → 2 => %d \n", test.countHi("xhixhix"));
    System.out.printf("countHi(\"hi\") → 1 => %d \n", test.countHi("hi"));

    System.out.printf("countX(\"xxhixx\") → 4 => %d \n", test.countX("xxhixx"));
    System.out.printf("countX(\"xhixhix\") → 3 => %d \n", test.countX("xhixhix"));
    System.out.printf("countX(\"hi\") → 0 => %d \n", test.countX("hi"));

    int[] ar = {2,5,10,4};
    System.out.printf("groupSum5(0, {2, 5, 10, 4}, 19) → true => %b \n", test.groupSum5(0, ar, 19));
    System.out.printf("groupSum5(0, {2, 5, 10, 4}, 17) → true => %b \n", test.groupSum5(0, ar, 17));
    System.out.printf("groupSum5(0, {2, 5, 10, 4}, 12) → false => %b \n", test.groupSum5(0, ar, 12));
    
    ar = new int[] {5,6,2};
    System.out.printf("groupSum6(0, {5, 6, 2}, 8) → true => %b \n", test.groupSum6(0, ar, 8));
    System.out.printf("groupSum6(0, {5, 6, 2}, 9) → false => %b \n", test.groupSum6(0, ar, 9));
    System.out.printf("groupSum6(0, {5, 6, 2}, 7) → false => %b \n", test.groupSum6(0, ar, 7));
    
    ar = new int[] {2,5,10,4};
    System.out.printf("groupNoAdj(0, {2, 5, 10, 4}, 12) → true => %b \n", test.groupNoAdj(0, ar, 12));
    System.out.printf("groupNoAdj(0, {2, 5, 10, 4}, 14) → false => %b \n", test.groupNoAdj(0, ar, 14));
    System.out.printf("groupNoAdj(0, {2, 5, 10, 4}, 7) → false => %b \n", test.groupNoAdj(0, ar, 7));
  }
}

/*
cd ~/Documents/Class/CSIT-142/Lab/Chapter 18/
/System/Library/Frameworks/JavaVM.framework/Versions/CurrentJDK/Home/bin/java JavaBat

count7(717) → 2 => 2 
count7(7) → 1 => 1 
count7(123) → 0 => 0 
count8(8) → 1 => 1 
count8(818) → 2 => 2 
count8(8818) → 4 => 4 
countHi("xxhixx") → 1 => 1 
countHi("xhixhix") → 2 => 2 
countHi("hi") → 1 => 1 
countX("xxhixx") → 4 => 4 
countX("xhixhix") → 3 => 3 
countX("hi") → 0 => 0 
groupSum5(0, {2, 5, 10, 4}, 19) → true => true 
groupSum5(0, {2, 5, 10, 4}, 17) → true => true 
groupSum5(0, {2, 5, 10, 4}, 12) → false => false 
groupSum6(0, {5, 6, 2}, 8) → true => true 
groupSum6(0, {5, 6, 2}, 9) → false => false 
groupSum6(0, {5, 6, 2}, 7) → false => false 
groupNoAdj(0, {2, 5, 10, 4}, 12) → true => true 
groupNoAdj(0, {2, 5, 10, 4}, 14) → false => false 
groupNoAdj(0, {2, 5, 10, 4}, 7) → false => false 

Process JavaBat finished
*/
