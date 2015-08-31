package hello
import scala.collection.mutable.HashMap
import java.util.{List,ArrayList}
import com.mongodb.casbah.Imports._
import com.mongodb.util.JSON.serialize
import scala.collection.JavaConversions._
import com.mongodb.casbah.MongoURI
object Hash_map {  


  val db = MongoConnection("ds045970.mongolab.com",45970)("things")
  db.authenticate("goudamy", "gg")
   val coll = db("things")
  
  def post(details:User_details):User_details ={    
  
    var create_id :Int = 0;
    val fetch = MongoDBObject("user" -> 1)
    val u1= coll.findOne( fetch )
    u1 match{
    case None => create_id = 1
    case Some(message) => 
         val doc = coll.find().sort(Map("user" -> -1)).limit(1)  

    for( doc1 <- doc) {
       create_id =doc1.as[Int]("user")     
     }
     println(create_id)     
     create_id = create_id +1; 
    }
    val user1 = create_id.toString()
     val user = "u-"+ user1
     println("create_id" + create_id)
     println("user" +user)
     val created_at = helper.date()
     val updated_at = helper.date()
     val user_info = MongoDBObject("user" -> create_id,"user_id" ->user, "email" -> details.email,"password" ->details.password,
         "name" -> details.name, "created_at" -> created_at,"updated_at" -> "null")
     coll.insert( user_info )
     println("Saved")
     details.user_id = user
     details.created_at = created_at;
    //details.updated_at = updated_at;
  
       return details;
  }
  
  def get(id :String,request :User_details):User_details ={  
 
    val fetch_user = MongoDBObject("user_id" -> id)
    val user_get= coll.findOne( fetch_user )
      var name1:String = null
      var update1:String = null
    //val user_profile:User_details = null;
    println (id)
    request.user_id = id
    for( info_details <- user_get) {
     request.email = info_details.as[String]("email") 
     request.password = info_details.as[String]("password")
      val name = info_details.getAs[String]("name") 
       name match{
       case None => name1 = null
       case Some(message) => name1 = message
      }
  
    request.name = name1
     //request.name = info_details.as[String]("name") 
     request.created_at = info_details.as[String]("created_at") 
      val update = info_details.getAs[String]("updated_at") 
       update match{
       case None =>  request.updated_at = null
       case Some(message) => request.updated_at = info_details.as[String]("updated_at")
      }
         }
 request
  }  
  
  def put(id :String,request :User_details):User_details ={  

 // var user_update :User_details = null;
 // val user_profile:User_details = null;
  var name1:String = null
  val updated_at:String = helper.date();
  val query = MongoDBObject("user_id" -> id)
  val update = MongoDBObject("updated_at" -> updated_at)
 // val result = coll.update( query, update )
 val result = coll.update(MongoDBObject("user_id"->id),$set("name" ->request.name,"password" ->request.password,"updated_at"->updated_at) )

 request.user_id = id
  val fetch_user = MongoDBObject("user_id" -> id)
  val user_get= coll.findOne( fetch_user )
 
    for( info_details <-user_get ) {
     request.email = info_details.as[String]("email") 
    request.password = info_details.as[String]("password")
    
     val name = info_details.getAs[String]("name") 
       name match{
       case None => name1 = null
       case Some(message) => name1 = message
      }
  
    request.name = name1
    request.name = name1
     request.created_at = info_details.as[String]("created_at") 
    request.updated_at = info_details.as[String]("updated_at") 
    }
request
   
  }  
  

  
 
  
}