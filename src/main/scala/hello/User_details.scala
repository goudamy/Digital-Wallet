package hello
import org.springframework.boot.SpringApplication;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import javax.persistence.Entity
import scala.beans._;
import scala.collection.mutable.HashMap;
import java.lang.Long;
import java.lang.annotation._;
import java.sql.Timestamp;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.TimeZone;
import java.util.Date;

@Entity
class User_details{
  @BeanProperty var user_id : String = _;
  @NotNull
  @BeanProperty var email : String = _;
  @NotNull
  @BeanProperty var password : String = _;
  @BeanProperty var name :String = _;
  @BeanProperty var created_at : String = _;
  @BeanProperty var updated_at :String = _; 
 
 
   //created_at = new java.sql.Timestamp(System.currentTimeMillis()).toString();
   //updated_at = new java.sql.Timestamp(System.currentTimeMillis()).toString();


} 






 