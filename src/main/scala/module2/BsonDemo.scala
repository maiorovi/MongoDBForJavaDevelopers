package module2

import java.util.Date

import org.bson.Document
import org.bson.types.ObjectId

object BsonDemo {

  def main(args:Array[String]): Unit = {
    val document = new Document()
                        .append("str", "Hello, MongoDb")
                        .append("i", 45)
                        .append("double", 1.1)
                        .append("b", false)
                        .append("date", new Date())
                        .append("objId", new ObjectId())
                        .append("null", null)
                        .append("embeddedDoc", new Document("x",0))
                        .append("list", List(1,2,3,4,5))

    val str:String = document.get("str").asInstanceOf[String] // you need to cast to string when you use get
    val str1:String = document.getString("str") // there no need to cast
    val i = document.getInteger("i")
  }
}
