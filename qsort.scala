object quick {
  def sort(a:List[Int]) : List[Int]={
    if(a.length < 2) a
    else {
      val p = a(a.length/2)
      sort(a.filter(_<p)):::
      a.filter(_==p):::
      sort(a.filter(_>p))
    }
  }
  
  def sort1(a:Array[Int]) {
    def swap(l:Int, r: Int) {
      val t = a(l); a(l) = a(r); a(r) = t
    }
  def sort2(l:Int, r:Int) {
    val p = a((l+r)/2)
    var i = l
    var j = r
    
    while(i<=j) {
      while(a(i) < p)
        i += 1
      while(a(j) > p)
        j -= 1
        
      if(i<=j) {
        swap(i,j)
        i += 1
        j -= 1
      }
    }
    if(l < j) sort2(l,j)
    if(j < r) sort2(i,r)
  }
  if(a.length > 0)
    sort2(0,a.length-1)
  }
  def main(args: Array[String]){
    val arr = List(2,84,30,21,34)
    println(arr)
    println(sort(arr))
    val a2 = Array(88,90,32,12,43)
    println(a2.mkString(" , "))
    sort1(a2)
    for(ele <- a2)
      print(" "+ele+" " )
  }
}
