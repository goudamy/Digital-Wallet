package bank_Account
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST , reason = "No such Routing No.")
case class IncorrectRounting (rout:String) extends 
RuntimeException ("Incorrect Routing number: "+rout)
@ResponseStatus(value = HttpStatus.NOT_FOUND , reason = "Doesnt contain 9 digits")
case class Invalid (rout:String) extends 
RuntimeException ("Routing number is not 9 digits: "+rout)
