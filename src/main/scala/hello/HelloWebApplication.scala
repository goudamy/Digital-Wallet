package hello

import org.springframework.boot.SpringApplication
 
/**
 * This object bootstraps Spring Boot web application.
 * Via Gradle: gradle bootRun
 */
object HelloWebApplication {
	def main(args: Array[String]) {
       val user_details : Array[Object] = Array(classOf[HelloConfig]);
	   SpringApplication.run(user_details,args);
	
}
}