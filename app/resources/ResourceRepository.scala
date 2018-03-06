package resources

import javax.inject.{Inject, Singleton}
import model.Tables
import model.Tables.ResourcesRow
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

  private val resources = Tables.Resources
  private val addresses = Tables.Addresses
  private val collectorPreferences = Tables.CollectorPreferences

  def getByCollector(collectorId: Int): Future[Seq[(Int, String, Int)]] = {
    val query = for {
      ((r, a), _) <-
        resources
          .filter(_.collectedTs.isEmpty)
          .filter(_.deletedTs.isEmpty) join
        addresses on (_.addressId === _.id) join
        collectorPreferences
          .filter(_.collectorId === collectorId) on (_._1.resourceTypeId === _.resourceTypeId)
    } yield (a.id, r.pricePerKg, r.weight)

    db.run {
      query.result
    }
  }

  def getByCollectorByCity(collectorId: Int, cityId: Int): Future[Seq[(Int, String, Int)]] = {
    val query = for {
      ((r, _), _) <-
        resources
          .filter(_.collectedTs.isEmpty)
          .filter(_.deletedTs.isEmpty) join
        addresses
          .filter(_.id === cityId) on (_.addressId === _.id) join
        collectorPreferences
          .filter(_.collectorId === collectorId) on (_._1.resourceTypeId === _.resourceTypeId) sortBy(_._2.priority)
    } yield (r.id, r.pricePerKg, r.weight)

    db.run {
      query.result
    }
  }

  def list(): Future[Seq[ResourcesRow]] = db.run {
    resources.result
  }

  def get(id: Int): Future[ResourcesRow] = db.run {
    resources.filter(_.id === id).result.map(r =>
      if (r.isEmpty) null
      else r.head
    )
  }

  def delete(id: Int): Future[Int] = db.run {
    resources.filter(_.id === id).delete
  }

  //  def save(input: ResourcesRow): Future[ResourcesRow] = db.run {
  //    (resources.map(r => (r.name, r.symbol))
  //      returning resources.map(_.id)
  //
  //      into ((res, id) => ResourcesRow(id, res._1, res._2))
  //      ) += (input.name, input.symbol)
  //  }
  //
  //  def update(obj: ResourcesRow): Future[Int] = db.run {
  //    resources.filter(_.id === obj.id)
  //      .map(r => (r.name, r.symbol))
  //      .update((obj.name, obj.symbol))
  //  }
}
