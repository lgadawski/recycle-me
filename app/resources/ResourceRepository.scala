package resources

import javax.inject.{Inject, Singleton}

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
protected class ResourceRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ex: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  // These imports are important, the first one brings db into scope, which will let you do the actual db operations.
  // The second one brings the Slick DSL into scope, which lets you define the table and other queries.
  import dbConfig._
  import profile.api._

  private class ResourcesTable(tag: Tag) extends Table[Resource](tag, "RESOURCES") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def symbol = column[String]("symbol")

    def * = (id, name, symbol) <> ((Resource.apply _).tupled, Resource.unapply)
  }

  private val resources = TableQuery[ResourcesTable]

  def list(): Future[Seq[Resource]] = db.run {
    resources.result
  }

  def get(id: Long): Future[Resource] = db.run {
    resources.filter(_.id === id).result.head
  }

  def save(input: Resource): Future[Resource] = db.run {
    (resources.map(r => (r.name, r.symbol))
      returning resources.map(_.id)

      into ((res, id) => Resource(id, res._1, res._2))
      ) += (input.name, input.symbol)
  }

  def update(obj: Resource): Future[Int] = db.run {
    resources.filter(_.id === obj.id)
      .map(r => (r.name, r.symbol))
      .update((obj.name, obj.symbol))
  }
}
