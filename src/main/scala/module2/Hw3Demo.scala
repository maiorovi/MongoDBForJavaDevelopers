package module2

import java.util

import com.mongodb.MongoClient
import com.mongodb.client.MongoCollection
import org.bson.Document
import org.bson.conversions.Bson

object Hw3Demo extends App {
  val client = new MongoClient()
  val db = client.getDatabase("students")

  val coll:MongoCollection[Document] = db.getCollection("grades")

  val filter:Bson = new Document("type","homework")

  val sort = new Document().append("student_id",1).append("score",-1)

  val selectFilter:Bson = new Document("type","homework")

  val list:util.ArrayList[Document] = coll.find(selectFilter).sort(sort).into(new util.ArrayList[Document]())

//  coll.deleteMany(filter)

//  val list:util.ArrayList[Document] = coll.find().into(new util.ArrayList[Document]())

  val iter = list.listIterator()
  var oldId = 0
  var oldValue:Document = null

  while (iter.hasNext) {
//    println(iter.next().toJson)
    val next = iter.next()
//
    if (next.getInteger("student_id") != oldId) {
      coll.deleteOne(oldValue)
    }

    if (iter.hasNext() == false) {
      coll.deleteOne(next)
    }

    println(next.toJson)
    oldId = next.getInteger("student_id")
    oldValue = next
  }

  println(coll.count())
}
