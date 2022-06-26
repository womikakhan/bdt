abstract class Notification

case class SMS(number:String, message: String) extends Notification
case class Email(emailAddr: String, subject: String, body: String) extends Notification

object test {
  def showNotification(notification : Notification): String = {
    notification match { 
      case Email(emailAddr,subject,_) => s"You recieved an Email from $emailAddr with subject = $subject"
      case SMS(number,msg) => s"You recieved a message from $number with message = $msg"
    }
  }
  def main(agrs : Array[String]) : Unit = {
    val sms = SMS("3768346845","Hello")
    val email = Email("abc@gmail.com","test","hello")
    
    println(showNotification(sms))
    println(showNotification(email))
  }
}
