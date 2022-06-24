import scala.io.StdIn
import scala.collection.mutable.ArrayBuffer

object MinMax {

	def main (args: Array[String] ): Unit = {
		var numArray = new ArrayBuffer[Int]()
		println("Enter number of elements")
		val n = scala.io.StdIn.readInt()		
		println("Enter elements")
		for (i <- 1 to n)				
			numArray += scala.io.StdIn.readInt()
		println(numArray)				
	
	val t = minmax(numArray) 				
	println("Maximum number is ", t._1)			
	println("Minimum number is ", t._2)			
	
	def minmax(numArray:ArrayBuffer[Int]): (Int,Int) = {

		var mi:Int = 999				
		var mx:Int = -999
		for ( i <- numArray)
		{
			if (i < mi)
				mi = i
			else if (i > mx)
				mx = i
		}
		(mx,mi)						
	}	
}
