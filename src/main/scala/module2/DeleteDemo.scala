package module2

import java.util

import com.mongodb.MongoClient
import com.mongodb.client.MongoCollection
import org.bson.Document
import org.bson.conversions.Bson

object DeleteDemo extends App {
  val client = new MongoClient()
  val db = client.getDatabase("course")

  val coll:MongoCollection[Document] = db.getCollection("test")

  coll.drop()

  for(i <- 0 to 10) {
    coll.insertOne(new Document("_id", i))
  }

  val filter:Bson =  new Document("_id", new Document("$gt",4));

  coll.deleteMany(filter)

  val list:util.ArrayList[Document] = coll.find().into(new util.ArrayList[Document]())

  val iter = list.listIterator()

  while (iter.hasNext)
    println(iter.next().toJson)


}
