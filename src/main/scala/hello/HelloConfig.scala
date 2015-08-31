package hello

import org.springframework.stereotype.Controller
import org.springframework.boot.SpringApplication
import org.springframework.context.annotation.Configuration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http._
import org.springframework.http.ResponseEntity
import javax.validation.Valid
import java.lang
import org.springframework.web.bind.annotation.PathVariable
import scala.collection.Iterable.iterate
import java.util.{List,ArrayList}
import org.springframework.web.context.request.WebRequest
import java.sql.Timestamp
import org.springframework.web.context.request.ServletWebRequest
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.TimeZone;
import java.util.Date;
import id_Cards._;
import bank_Account._;
import web_Login._;
import java.util.ArrayList


/**
 * This config class will trigger Spring @annotation scanning and auto configure Spring context.
 * Request Mapping is used to establish HTTP connection
 */

@Configuration
@EnableWebMvc
@Controller
@EnableAutoConfiguration
@ComponentScan
@ControllerAdvice
@RequestMapping(value= Array("/api/v1"))
 class HelloConfig {
         var req: User_details = null;
        var lastModifiedTime : Long = _;
        @RequestMapping(value= Array("/users"),method=Array(RequestMethod.POST),headers = Array("Content-Type=application/json"))
        @ResponseStatus( HttpStatus.CREATED )
        @ResponseBody
        def home(@RequestBody @Valid request : User_details): User_details ={             
        println(request.email);
        println(request.password);
        var emp =Hash_map.post(request);  
        emp
        }

        var lastModified: Long = -1;
        @RequestMapping(value= Array("/users/{id}"),method=Array(RequestMethod.GET),headers = Array("Content-Type=application/json"))
        @ResponseBody
        def getmeth(@PathVariable("id") id: String,request : User_details, webRequest:ServletWebRequest):User_details={             
           
        val dateFormat:DateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"))
         var updated_at = helper.date(); 
         println("update"+updated_at)
        var date: Date = dateFormat.parse(updated_at)
        var longDate = date.getTime()
        lastModified = longDate 		
        println((webRequest.checkNotModified(lastModified)));
        if (webRequest.checkNotModified(lastModified)) {         
        null
        }else{
        println("passing"+id)        
         var emp = Hash_map.get(id,request);
         emp  
         
         }
        }

          
        @RequestMapping(value= Array("/users/{id}"),method=Array(RequestMethod.PUT),headers = Array("Content-Type=application/json"))         
        @ResponseStatus( HttpStatus.CREATED )
        @ResponseBody  
        def putmeth(@PathVariable("id") id: String,@RequestBody request : User_details):User_details={             
      // var emp = new user_info(request.email,request.password); 
          println("id"+id);
        var emp = Hash_map.put(id,request);   
         return emp;
        }
               
            
        @RequestMapping(value= Array("/users/{id}/idcards"),method=Array(RequestMethod.POST),headers = Array("Content-Type=application/json"))
        @ResponseStatus( HttpStatus.CREATED )
        @ResponseBody           
        def postmeth2(@PathVariable("id") id: String,@RequestBody request : ID_cards):ID_cards={             
        var emp = Id_Cards_helper.postmeth2(id,request);   
        return emp;
        }
        
              
        @RequestMapping(value= Array("/users/{id}/idcards"),method=Array(RequestMethod.GET),headers = Array("Content-Type=application/json"))
        @ResponseBody           
        def getmeth2(@PathVariable("id") id: String, request : ID_cards)={             
      // var emp = new user_info(request.email,request.password); 
        println("id"+id);
  
       var emp = Id_Cards_helper.getmeth2(id)  
        println("empp"+emp)
        emp
        }
       @RequestMapping(value= Array("/users/{user_id}/idcards/{card_id}"),method=Array(RequestMethod.DELETE),headers = Array("Content-Type=application/json"))       
       @ResponseStatus( HttpStatus.NO_CONTENT )
       @ResponseBody 
       def delmeth2(@PathVariable("user_id") id: String,@PathVariable ("card_id") card_id :String)={             
       println("delete");
       var emp = Id_Cards_helper.delmeth2(id,card_id); 
       println("completeed.....")
     
        }

       
        @RequestMapping(value= Array("/users/{id}/weblogins"),method=Array(RequestMethod.POST),headers = Array("Content-Type=application/json"))
        @ResponseStatus( HttpStatus.CREATED )
        @ResponseBody           
        def postmeth3(@PathVariable("id") id: String,@RequestBody request : Web_Login):Web_Login={             
        var emp = Web_Login_helper.postmeth3(id,request);   
        return emp;
        }
                     
        @RequestMapping(value= Array("/users/{id}/weblogins"),method=Array(RequestMethod.GET),headers = Array("Content-Type=application/json"))
        @ResponseBody           
        def getmeth3(@PathVariable("id") id: String, request : Web_Login)={             
        println("id"+id);  
        var emp = Web_Login_helper.getmeth3(id,request)  
        println("empp"+emp)
        emp
        }
        
        @RequestMapping(value= Array("/users/{id}/weblogins/{login_id}"),method=Array(RequestMethod.DELETE),headers = Array("Content-Type=application/json"))       
        @ResponseStatus( HttpStatus.NO_CONTENT )
        @ResponseBody 
        def delmeth3(@PathVariable("id") id: String,@PathVariable ("login_id") login_id :String)={             
        println("delete");
        var emp = Web_Login_helper.delmeth3(id,login_id); 
        println("completeed.....")
     
        }
        
        @RequestMapping(value= Array("/users/{id}/bankaccounts"),method=Array(RequestMethod.POST),headers = Array("Content-Type=application/json"))
        @ResponseStatus( HttpStatus.CREATED )
        @ResponseBody           
        def postmeth4(@PathVariable("id") id: String,@RequestBody request : Bank_Account)={             
        var emp = Bank_Account_helper.postmeth4(id,request);   
        println(emp)
        emp;
        }
        
      
        @RequestMapping(value= Array("/users/{id}/bankaccounts"),method=Array(RequestMethod.GET),headers = Array("Content-Type=application/json"))
        @ResponseBody           
        def getmeth4(@PathVariable("id") id: String, request : Bank_Account)={             
        println("id"+id);  
        var emp = Bank_Account_helper.getmeth4(id)  
        emp
        }
        
       @RequestMapping(value= Array("/users/{id}/bankaccounts/{ba_id}"),method=Array(RequestMethod.DELETE),headers = Array("Content-Type=application/json"))       
       @ResponseStatus( HttpStatus.NO_CONTENT )
       @ResponseBody 
       def delmeth4(@PathVariable("id") id: String,@PathVariable ("ba_id") card_id :String)={             
       println("delete");
       var emp = Bank_Account_helper.delmeth4(id,card_id); 
       println("completeed.....")
     
        }
 
}