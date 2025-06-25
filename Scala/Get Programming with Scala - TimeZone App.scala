import java.time.format.DateTimeFormatter
import java.time.{ZoneId, ZonedDateTime}

class TimePrinter(formatter: DateTimeFormatter) {
  def now(timezone: String): String = {
    val dateTime = currentDateTime(timezone)
    dateTimeToString(dateTime)
  }

  private def currentDateTime(timezone: String): ZonedDateTime = {
    val zoneId = ZoneId.of(timezone)
    ZonedDateTime.now(zoneId)
  }

  private def dateTimeToString(dateTime: ZonedDateTime): String = formatter.format(dateTime)
}

object Main {
  def main(args: Array[String]) = {
    val timePrinter = new TimePrinter(DateTimeFormatter.RFC_1123_DATE_TIME)
    println("New York : " + timePrinter.now("America/New_York"))
    println("London   : " + timePrinter.now("Europe/London"))
    println("Berlin   : " + timePrinter.now("Europe/Berlin"))
    println("Tokyo    : " + timePrinter.now("Asia/Tokyo"))
  }
}
