package price

import play.api.libs.json.{Json, OFormat}

case class Price(value: BigDecimal) {

  def multiply(multiplier: Int) = Price(value.*(multiplier))

  override def toString: String = value.toString()
}

object Price {

  implicit val resourceFormat: OFormat[Price] = Json.format[Price]

  implicit val priceNumeric: Numeric[Price] = new Numeric[Price] {

    override def plus(x: Price, y: Price): Price = Price(x.value + y.value)

    override def minus(x: Price, y: Price): Price = Price(x.value - y.value)

    override def times(x: Price, y: Price): Price = Price(x.value * y.value)

    override def negate(x: Price): Price = Price(-x.value)

    override def fromInt(x: Int): Price = Price(x)

    override def toInt(x: Price): Int = x.value.toInt

    override def toLong(x: Price): Long = x.value.toLong

    override def toFloat(x: Price): Float = x.value.toFloat

    override def toDouble(x: Price): Double = x.value.toDouble

    override def compare(x: Price, y: Price): Int = x.value.compare(y.value)
  }
}
