package hw3

import java.util

import com.mongodb.MongoClient
import com.mongodb.client.MongoCollection
import org.bson.Document

object Hw31 extends App {
  val client = new MongoClient()
  val db = client.getDatabase("school")

  val coll:MongoCollection[Document] = db.getCollection("students")

  val doc  = coll.find()

  val list:util.ArrayList[Document] = coll.find().into(new util.ArrayList[Document]())

  val it = list.listIterator()

  while(it.hasNext) {
    val elem = it.next();
    val docs:util.ArrayList[Document] = elem.get("scores").asInstanceOf[util.ArrayList[Document]]
    val minimalScoreDoc = new Document("$pull", new Document("scores" ,findMin(docs)));

    coll.updateOne(elem, minimalScoreDoc)
  }


  def findMin(docs: util.ArrayList[Document]): Document = {
    val it = docs.iterator()
    var min = Double.MaxValue
    var minDoc:Document = null

    while(it.hasNext) {
      val elem:Document = it.next()
      val `type` = elem.getString("type")
      val score = elem.getDouble("score")
      if (`type` == "homework" && min > score) {
        min = score
        minDoc = elem
      }
    }
    minDoc
  }


}
