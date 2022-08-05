name := "tweetmining"
version := "0.1"
scalaVersion := "2.12.10"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.1.2"
libraryDependencies += "com.google.code.gson" % "gson" % "2.3.1"


import org.apache.spark.{SparkContext, SparkConf}
import scala.Ordering
import org.apache.spark.rdd._

object tweetmining {
	val conf = new SparkConf().setAppName("User mining").setMaster("local[*]")
 	val sc = new SparkContext(conf)
	
	var pathToFile = ""
 	def main(args: Array[String]) {
   		if (args.length != 1) 
     		System.exit(1)
    	
   		pathToFile = args(0)

   		val tweets = sc.textFile(pathToFile).mapPartitions(TweetUtils.parseFromJson(_))

    	val tweetsByUser = tweets.map(x => (x.user, x)).groupByKey()

	   	val numTweetsByUser = tweetsByUser.map(x => (x._1, x._2.size))
    	val sortedUsersByNumTweets = numTweetsByUser.sortBy(_._2, ascending=false)
    	sortedUsersByNumTweets.take(10).foreach(println)
 	 }
}

import com.google.gson._
object TweetUtils {
	case class Tweet (
		id : String,
		user : String,
		userName : String,
		text : String,
		place : String,
		country : String,
		lang : String
	)

	def parseFromJson(lines:Iterator[String]):Iterator[Tweet] = {
		val gson = new Gson
		lines.map(line => gson.fromJson(line, classOf[Tweet]))
	}
}
