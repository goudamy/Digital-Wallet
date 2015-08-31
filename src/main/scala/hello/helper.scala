package hello
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.TimeZone;
import java.util.Date;

object helper{
  var id_updated :Map[String,String] = Map(); 
def random_generate():String={
  var id = new scala.util.Random;
  var create_id1 = id.toString();
  val parts = create_id1 split "@";
  val create_id :String = parts(1);
  println("Create_ID"+ create_id);  
  create_id;
  }

 def set_updated_at(id:String):String ={   
    val dateFormat:DateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"))
    var updated_at: String = dateFormat.format(new Date)
    id_updated += id -> updated_at
    updated_at
  }
 
  def date():String ={   
    val dateFormat:DateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"))
    var updated_at: String = dateFormat.format(new Date)
   updated_at
  }
 
def get_updated_at(id:String):String={
  println("updatedddd"+id_updated)
 var get_update:String = null;
   id_updated.keys.foreach{i=>                         
                           if(i == id){
                           get_update = id_updated(i)} }
     get_update;

   
  }  
 
  def set_created_at(id:String):String ={   
    val dateFormat:DateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"))
    var created_at: String = dateFormat.format(new Date)
    id_updated += id -> created_at
    created_at
  }
}