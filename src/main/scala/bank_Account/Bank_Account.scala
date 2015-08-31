package bank_Account
import org.springframework.boot.SpringApplication;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import javax.persistence.Entity
import scala.beans._;
import javax.validation.constraints.NotNull;


@Entity
class Bank_Account {
@BeanProperty  var ba_id :String =_;
@BeanProperty  var account_name :String =_
@NotNull
@BeanProperty  var routing_number :String =_
@NotNull
@BeanProperty  var account_number :String =_
}