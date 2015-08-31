package web_Login
import javax.persistence.Entity
import scala.beans._;
import javax.validation.constraints.NotNull;

@Entity
class Web_Login {
@BeanProperty  var login_id :String =_;
@NotNull
@BeanProperty  var url :String =_;
@NotNull
@BeanProperty  var login :String =_;
@NotNull
@BeanProperty  var password :String =_;
}