package module2

import java.util.function.Consumer

import  com.mongodb.client.model.Filters._
import com.mongodb.MongoClient
import com.mongodb.client.model.Filters
import org.bson.Document
import org.bson.conversions.Bson


object CriteriaDemo extends App {

  val client = new MongoClient()

  val db = client.getDatabase("courser")
  val collection = db.getCollection("findWithFilterTest")

  collection.drop()

  for (x <- 1 to 10; y <- 1 to 10) collection.insertOne(new Document("x",x).append("y",y))

  val filter = and(Filters.eq("x" , 5), gt("y", 7))

  import java.util._

  val documents:List[Document] = collection.find(filter).into(new ArrayList[Document]())

  documents.forEach( new Consumer[Document] {
    override def accept(t: Document): Unit = println(t.toJson)
  })
}
