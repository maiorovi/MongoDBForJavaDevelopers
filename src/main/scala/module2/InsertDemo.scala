package module2

import com.mongodb.MongoClient
import com.mongodb.client.MongoCollection
import org.bson.Document

object InsertDemo extends App {
  val client = new MongoClient()
  val db = client.getDatabase("test")

  val coll:MongoCollection[Document] = db.getCollection("course");

  val doc = new Document().append("name","Smith").append("age", 30).append("profession","cleaner")

  coll.insertOne(doc)

}
