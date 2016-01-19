package module2

import java.util

import com.mongodb.MongoClient
import com.mongodb.client.MongoCollection
import org.bson.Document

object FindDemo extends App {
  val client = new MongoClient()
  val db = client.getDatabase("test")

  val coll:MongoCollection[Document] = db.getCollection("findDemo")

  coll.drop()

  for (i <- 1 to 10) {
    coll.insertOne(new Document("x", i))
  }

  println("Find one:")
  println(coll.find().first().toJson)

  println("Find all with intro:")
  val list:util.ArrayList[Document] = coll.find().into(new util.ArrayList[Document]())

  val iter = list.listIterator()

  while (iter.hasNext)
    println(iter.next().toJson)
}
