package id_Cards
import scala.collection.mutable.HashMap;
import hello.User_details;
import hello.helper;
import java.util.{List,ArrayList}
import com.mongodb.casbah.Imports._
import hello._

  
object Id_Cards_helper {
  /*val mongoClient = MongoClient("localhost", 27017)
  val db = mongoClient("things")
  val coll = db("id") */
   val db = MongoConnection("ds045970.mongolab.com",45970)("things")
  db.authenticate("goudamy", "gg")
   val coll = db("id")
  
def postmeth2(id :String,request :ID_cards):ID_cards ={  
 
    var card_id :Int = 0;
    val fetch = MongoDBObject("user_id" -> id)
    val u1= coll.findOne( fetch )
    println("u1"+u1)
    u1 match{
    case None => card_id = 1
    case Some(message) => 
         val doc = coll.find(fetch).sort(Map("card" -> -1)).limit(1)
    
    for( doc1 <- doc) {
       card_id =doc1.as[Int]("card")     
     }
        
      card_id = card_id +1; 
      println(card_id)
    }
    val card1 = card_id.toString()
    val card = "c-"+card1
   request.card_id = card;
   val user_info = MongoDBObject("card" -> card_id,"card_id" -> card,
       "card_name" -> request.card_name,
       "card_number" ->request.card_number,
       "expiration_date" -> request.expiration_date,
       "user_id" -> id)
        coll.insert( user_info )
        println("Saved")
       return request;   
  } 
  
  
   def getmeth2(id :String)={ 
   var list:List[DBObject] = new ArrayList()
    println("Display all docs")
    val user1 = MongoDBObject("user_id"->id)    
    val user = MongoDBObject("user_id" -> 0,"_id" -> 0,"card"->0)
    val allDocs = coll.find(user1,user)
    for(doc <- allDocs) {
      list.add(doc)
          }
   println(list)
      list
  }  
  
  def delmeth2(id :String,card_id :String)={ 
  println("deleting.....")  
  val user = MongoDBObject("user_id" -> id,"card_id" ->card_id)
  val result1 = coll.remove( user)
  
  }
  
}