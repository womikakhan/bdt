import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD

object wordcount {
  def main(args: Array[String]) {
    val pathToFile = "log.txt"
    val conf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
    val sc = new SparkContext(conf)
    
    val rdd1 = sc.textFile(pathToFile).flatMap(_.split(" "))
    val rdd2 = rdd1.map(word => (word,1))
    val rdd3 = rdd2.reduceByKey((v1,v2) => v1 + v2)
    val highFreq = rdd3.filter(x => x._2 > 2)
    highFreq.saveAsTextFile("wordcountsDir")
  }
}
