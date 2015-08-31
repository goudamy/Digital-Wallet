package id_Cards
import org.springframework.boot.SpringApplication;
import javax.persistence.Entity
import scala.beans._;
import javax.validation.constraints.NotNull;
@Entity
class ID_cards{ 
@BeanProperty var card_id :String =_;
@NotNull
@BeanProperty var card_name :String =_;
@NotNull
@BeanProperty var card_number :String =_;
@BeanProperty var expiration_date :String =_;
}