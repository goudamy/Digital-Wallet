package web_Login
import scala.collection.mutable.HashMap;
import hello.User_details;
import hello.helper;
import java.util.{List,ArrayList}
import hello._
import com.mongodb.casbah.Imports._
object Web_Login_helper {  
  
 val db = MongoConnection("ds045970.mongolab.com",45970)("things")
  db.authenticate("goudamy", "gg")
  val coll = db("web_login")  
  
  
  def postmeth3(id :String,request :Web_Login):Web_Login ={  
   var login_id :Int = 0;
    val fetch = MongoDBObject("user_id" -> id)
    val u1= coll.findOne( fetch )
    println("u1"+u1)
    u1 match{
    case None => login_id = 1
    case Some(message) => 
         val doc = coll.find(fetch).sort(Map("login2" -> -1)).limit(1)
    
    for( doc1 <- doc) {
       login_id =doc1.as[Int]("login2") 
       println("login_id"+login_id)
     }
        
         login_id = login_id +1; 
          println(login_id)
    }
    val login=login_id.toString()
    val login1 = "I-"+login
   request.login_id = login1;
    
   val user_info = MongoDBObject("login2" -> login_id,"login_id" -> login1,
       "url" -> request.url,
       "login" ->request.login,
       "password" -> request.password,
       "user_id" -> id)
        coll.insert( user_info )
        println("Saved")
       return request;  
  }  
  
  
  def getmeth3(id :String,request :Web_Login)={ 
 
  var list:List[DBObject] = new ArrayList()
    println("Display all docs")
    val user1 = MongoDBObject("user_id"->id)    
    val user = MongoDBObject("user_id" -> 0,"_id" -> 0,"login"->0)
    val allDocs = coll.find(user1,user)
    for(doc <- allDocs) {
      list.add(doc)
          }
   println(list)
      list  
  }  
  
  def delmeth3(id :String,card_id :String)={ 
  println("deleting.....")  
  val user = MongoDBObject("user_id" -> id,"login_id" ->card_id)
  val result1 = coll.remove( user)
  }  
 
}