package module2

import java.util.function.Consumer

import com.mongodb.MongoClient
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Filters._
import org.bson.Document

object ReplacementDemo extends App {
  val client = new MongoClient()

  val db = client.getDatabase("courser")
  val collection = db.getCollection("findWithFilterTest")

  collection.drop()

  for (x <- 1 to 10) collection.insertOne(new Document("x",x).append("_id", x))

//  val filter = or(and(Filters.eq("x" , 5)), Filters.eq("x", 12))

  import java.util._

  var documents:List[Document] = collection.find().into(new ArrayList[Document]())



  documents.forEach( new Consumer[Document] {
    override def accept(t: Document): Unit = println(t.toJson)
  })

  println("\n\n")

  collection.replaceOne(Filters.eq("x", 5), new Document("x", 12).append("appended", true).append("_id",5))

  documents = collection.find().into(new ArrayList[Document]())

  documents.forEach( new Consumer[Document] {
    override def accept(t: Document): Unit = println(t.toJson)
  })
}
