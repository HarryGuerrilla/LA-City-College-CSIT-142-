class CountPairs{

  public int countPairs(String str) {
    
    if (str.length()<=2) {
        return 0;
    } else {
      if (str.charAt(str.length()-1)==str.charAt(str.length()-3)) {
        return 1 + countPairs(str.substring(0,str.length()-1));
      } else {
        return 0 + countPairs(str.substring(0,str.length()-1));
      }
    }

  }

  public int count11(String str){
    
    if (str.length()<=1) {
      return 0;
    } else {
      if (str.substring(0, 2).equals("11")) {
        return 1 + count11(str.substring(2));
      } else {
        return 0 + count11(str.substring(1));
      }
    }
  }

  public int countAbc(String str){
    if (str.length()<=2) {
      return 0;
    } else {
      if (str.substring(0,3).equals("abc") || str.substring(0,3).equals("aba")) {
        return 1 + countAbc(str.substring(1));
      } else {
        return 0 + countAbc(str.substring(1));
      }
    }
  }

}
