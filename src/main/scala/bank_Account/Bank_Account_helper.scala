package bank_Account
import scala.collection.mutable.HashMap;
import hello.User_details;
import hello.helper;
import java.util.{List,ArrayList}
import hello._
import com.mongodb.casbah.Imports._
import com.google.gson._;

object Bank_Account_helper { 
  
 val db = MongoConnection("ds045970.mongolab.com",45970)("things")
  db.authenticate("goudamy", "Ramanathan@1988")
  val coll = db("bank")  
  
  
  def postmeth4(id :String,request :Bank_Account) ={  
   var ba_id :Int = 0;
    val fetch = MongoDBObject("user_id" -> id)
    val u1= coll.findOne( fetch )
    println("u1"+u1)
    u1 match{
    case None => ba_id = 1
    case Some(message) => 
         val doc = coll.find(fetch).sort(Map("bank" -> -1)).limit(1)
    
    for( doc1 <- doc) {
       ba_id =doc1.as[Int]("bank")     
     }
        
         ba_id = ba_id +1; 
          println(ba_id)
    }
      var routing = request.routing_number
      if (routing == null){
        routing = "0000000"
          
      }
      var account_name :String = null
      val url:String = "http://www.routingnumbers.info/api/data.json?rn="+ routing;
      def get(url: String) = scala.io.Source.fromURL(url).mkString
      val get_value= get(url)
      println("get_value")
    //  val gson = new Gson
    //  val jsonString = gson.toJson(get_value)
      val jsonParser: JsonParser  = new JsonParser();
      val  rn = jsonParser.parse(get_value).getAsJsonObject().get("rn")
      val  customer_name = jsonParser.parse(get_value).getAsJsonObject().get("customer_name")
      val  code = jsonParser.parse(get_value).getAsJsonObject().get("code")
      if (code.toString() == "400")
      {
        println("rn"+rn)
        println("400")
        account_name = null;
        var error = IncorrectRounting(routing)
        println(error)
        throw new IncorrectRounting(routing)
      }
        if (code.toString() == "404")
      {
        println("rn"+rn)
        println("404")
        account_name = null;
        var error = Invalid(routing)
        println(error)
        throw new Invalid(routing)
      }
      if (code.toString() == "200"){
      println("rn"+rn)
      println("customer_name"+customer_name) 
      account_name = customer_name.toString()
      account_name=account_name.replace("\"","")
       
    val bank =  ba_id.toString()
    val bank1 = "b-" + bank
   request.account_name = account_name
   println("accounting_name"+request.account_name)
   println("account_name"+account_name)
   request.ba_id = bank1;
   val user_info = MongoDBObject("bank"-> ba_id,"bank_id" -> bank1,
       "account_name" -> account_name,
       "routing_number" ->request.routing_number,
       "account_number" -> request.account_number,
       "user_id" -> id)
        coll.insert( user_info )
        println("Saved")
       request; 
         } 
          
  
  }
  
  def getmeth4(id :String)={ 
  var list:List[DBObject] = new ArrayList()
    println("Display all docs")
    val user1 = MongoDBObject("user_id"->id)    
    val user = MongoDBObject("user_id" -> 0,"_id" -> 0,"bank"->0)
    val allDocs = coll.find(user1,user)
    for(doc <- allDocs) {
      list.add(doc)
          }
   println(list)
      list
  }  
  
  def delmeth4(id :String,card_id :String)={ 
  println("deleting.....")  
  val user = MongoDBObject("user_id" -> id,"bank_id" ->card_id)
  val result1 = coll.remove( user)
  }  
  
}