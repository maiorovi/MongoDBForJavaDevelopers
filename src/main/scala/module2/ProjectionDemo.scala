package module2

import java.util.function.Consumer

import com.mongodb.MongoClient
import com.mongodb.client.model.{Projections, Filters}
import com.mongodb.client.model.Filters._
import org.bson.Document

object ProjectionDemo {
  val client = new MongoClient()

  val db = client.getDatabase("courser")
  val collection = db.getCollection("findWithFilterTest")

  collection.drop()

  for (x <- 1 to 10; y <- 1 to 10) collection.insertOne(new Document("x",x).append("y",y))

  val filter = and(or(Filters.eq("x" , 5), Filters.eq("x",6)), gt("y", 7))
  val projection = Projections.excludeId()

  import java.util._

  val documents:List[Document] = collection.find(filter).projection(Projections.excludeId()).into(new ArrayList[Document]())

  documents.forEach( new Consumer[Document] {
    override def accept(t: Document): Unit = println(t.toString)
  })

}
