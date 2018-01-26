package models

import javax.inject.{Inject, Singleton}

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ResourceRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ex: ExecutionContext) {
  // we want jdbc provider for this
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  // These imports are important, the first one brings db into scope, which will let you do the actual db operations.
  // The second one brings the Slick DSL into scope, which lets you define the table and other queries.
  import dbConfig._
  import profile.api._

  /**
    * Here we define the table. It will have a name of people
    */
  private class ResourcesTable(tag: Tag) extends Table[Resource](tag, "RESOURCES") {

    /** The ID column, which is the primary key, and auto incremented */
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    /** The name column */
    def name = column[String]("name")

    /** The age column */
    def symbol = column[String]("symbol")

    /**
      * This is the tables default "projection".
      *
      * It defines how the columns are converted to and from the Person object.
      *
      * In this case, we are simply passing the id, name and page parameters to the Person case classes
      * apply and unapply methods.
      */
    def * = (id, name, symbol) <> ((Resource.apply _).tupled, Resource.unapply)
  }

  /**
    * The starting point for all queries on the people table.
    */
  private val resources = TableQuery[ResourcesTable]

  def list(): Future[Seq[Resource]] = db.run {
    resources.result
  }

}
