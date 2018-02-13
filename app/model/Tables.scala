package model
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.PostgresProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(Addresses.schema, CollectorPreferences.schema, Collectors.schema, Owners.schema, PlayEvolutions.schema, Resources.schema, ResourceTypes.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Addresses
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param cityName Database column city_name SqlType(varchar), Length(40,true) */
  final case class AddressesRow(id: Int, cityName: String)
  /** GetResult implicit for fetching AddressesRow objects using plain SQL queries */
  implicit def GetResultAddressesRow(implicit e0: GR[Int], e1: GR[String]): GR[AddressesRow] = GR{
    prs => import prs._
    AddressesRow.tupled((<<[Int], <<[String]))
  }
  /** Table description of table addresses. Objects of this class serve as prototypes for rows in queries. */
  class Addresses(_tableTag: Tag) extends profile.api.Table[AddressesRow](_tableTag, "addresses") {
    def * = (id, cityName) <> (AddressesRow.tupled, AddressesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(cityName)).shaped.<>({r=>import r._; _1.map(_=> AddressesRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column city_name SqlType(varchar), Length(40,true) */
    val cityName: Rep[String] = column[String]("city_name", O.Length(40,varying=true))
  }
  /** Collection-like TableQuery object for table Addresses */
  lazy val Addresses = new TableQuery(tag => new Addresses(tag))

  /** Entity class storing rows of table CollectorPreferences
   *  @param collectorId Database column collector_id SqlType(int4)
   *  @param resourceTypeId Database column resource_type_id SqlType(int4)
   *  @param priority Database column priority SqlType(int4) */
  final case class CollectorPreferencesRow(collectorId: Int, resourceTypeId: Int, priority: Int)
  /** GetResult implicit for fetching CollectorPreferencesRow objects using plain SQL queries */
  implicit def GetResultCollectorPreferencesRow(implicit e0: GR[Int]): GR[CollectorPreferencesRow] = GR{
    prs => import prs._
    CollectorPreferencesRow.tupled((<<[Int], <<[Int], <<[Int]))
  }
  /** Table description of table collector_preferences. Objects of this class serve as prototypes for rows in queries. */
  class CollectorPreferences(_tableTag: Tag) extends profile.api.Table[CollectorPreferencesRow](_tableTag, "collector_preferences") {
    def * = (collectorId, resourceTypeId, priority) <> (CollectorPreferencesRow.tupled, CollectorPreferencesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(collectorId), Rep.Some(resourceTypeId), Rep.Some(priority)).shaped.<>({r=>import r._; _1.map(_=> CollectorPreferencesRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column collector_id SqlType(int4) */
    val collectorId: Rep[Int] = column[Int]("collector_id")
    /** Database column resource_type_id SqlType(int4) */
    val resourceTypeId: Rep[Int] = column[Int]("resource_type_id")
    /** Database column priority SqlType(int4) */
    val priority: Rep[Int] = column[Int]("priority")

    /** Primary key of CollectorPreferences (database name collector_preferences_pkey) */
    val pk = primaryKey("collector_preferences_pkey", (collectorId, resourceTypeId))

    /** Foreign key referencing Collectors (database name collector_preferences_collector_id_fkey) */
    lazy val collectorsFk = foreignKey("collector_preferences_collector_id_fkey", collectorId, Collectors)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing ResourceTypes (database name collector_preferences_resource_type_id_fkey) */
    lazy val resourceTypesFk = foreignKey("collector_preferences_resource_type_id_fkey", resourceTypeId, ResourceTypes)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table CollectorPreferences */
  lazy val CollectorPreferences = new TableQuery(tag => new CollectorPreferences(tag))

  /** Entity class storing rows of table Collectors
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(varchar), Length(40,true)
   *  @param minCapacity Database column min_capacity SqlType(int4), Default(None)
   *  @param maxCapacity Database column max_capacity SqlType(int4), Default(None)
   *  @param createdTs Database column created_ts SqlType(timestamp) */
  final case class CollectorsRow(id: Int, name: String, minCapacity: Option[Int] = None, maxCapacity: Option[Int] = None, createdTs: java.sql.Timestamp)
  /** GetResult implicit for fetching CollectorsRow objects using plain SQL queries */
  implicit def GetResultCollectorsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[Int]], e3: GR[java.sql.Timestamp]): GR[CollectorsRow] = GR{
    prs => import prs._
    CollectorsRow.tupled((<<[Int], <<[String], <<?[Int], <<?[Int], <<[java.sql.Timestamp]))
  }
  /** Table description of table collectors. Objects of this class serve as prototypes for rows in queries. */
  class Collectors(_tableTag: Tag) extends profile.api.Table[CollectorsRow](_tableTag, "collectors") {
    def * = (id, name, minCapacity, maxCapacity, createdTs) <> (CollectorsRow.tupled, CollectorsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name), minCapacity, maxCapacity, Rep.Some(createdTs)).shaped.<>({r=>import r._; _1.map(_=> CollectorsRow.tupled((_1.get, _2.get, _3, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(varchar), Length(40,true) */
    val name: Rep[String] = column[String]("name", O.Length(40,varying=true))
    /** Database column min_capacity SqlType(int4), Default(None) */
    val minCapacity: Rep[Option[Int]] = column[Option[Int]]("min_capacity", O.Default(None))
    /** Database column max_capacity SqlType(int4), Default(None) */
    val maxCapacity: Rep[Option[Int]] = column[Option[Int]]("max_capacity", O.Default(None))
    /** Database column created_ts SqlType(timestamp) */
    val createdTs: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_ts")
  }
  /** Collection-like TableQuery object for table Collectors */
  lazy val Collectors = new TableQuery(tag => new Collectors(tag))

  /** Entity class storing rows of table Owners
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(varchar), Length(40,true)
   *  @param createdTs Database column created_ts SqlType(timestamp) */
  final case class OwnersRow(id: Int, name: String, createdTs: java.sql.Timestamp)
  /** GetResult implicit for fetching OwnersRow objects using plain SQL queries */
  implicit def GetResultOwnersRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp]): GR[OwnersRow] = GR{
    prs => import prs._
    OwnersRow.tupled((<<[Int], <<[String], <<[java.sql.Timestamp]))
  }
  /** Table description of table owners. Objects of this class serve as prototypes for rows in queries. */
  class Owners(_tableTag: Tag) extends profile.api.Table[OwnersRow](_tableTag, "owners") {
    def * = (id, name, createdTs) <> (OwnersRow.tupled, OwnersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name), Rep.Some(createdTs)).shaped.<>({r=>import r._; _1.map(_=> OwnersRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(varchar), Length(40,true) */
    val name: Rep[String] = column[String]("name", O.Length(40,varying=true))
    /** Database column created_ts SqlType(timestamp) */
    val createdTs: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_ts")
  }
  /** Collection-like TableQuery object for table Owners */
  lazy val Owners = new TableQuery(tag => new Owners(tag))

  /** Entity class storing rows of table PlayEvolutions
   *  @param id Database column id SqlType(int4), PrimaryKey
   *  @param hash Database column hash SqlType(varchar), Length(255,true)
   *  @param appliedAt Database column applied_at SqlType(timestamp)
   *  @param applyScript Database column apply_script SqlType(text), Default(None)
   *  @param revertScript Database column revert_script SqlType(text), Default(None)
   *  @param state Database column state SqlType(varchar), Length(255,true), Default(None)
   *  @param lastProblem Database column last_problem SqlType(text), Default(None) */
  final case class PlayEvolutionsRow(id: Int, hash: String, appliedAt: java.sql.Timestamp, applyScript: Option[String] = None, revertScript: Option[String] = None, state: Option[String] = None, lastProblem: Option[String] = None)
  /** GetResult implicit for fetching PlayEvolutionsRow objects using plain SQL queries */
  implicit def GetResultPlayEvolutionsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[String]]): GR[PlayEvolutionsRow] = GR{
    prs => import prs._
    PlayEvolutionsRow.tupled((<<[Int], <<[String], <<[java.sql.Timestamp], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table play_evolutions. Objects of this class serve as prototypes for rows in queries. */
  class PlayEvolutions(_tableTag: Tag) extends profile.api.Table[PlayEvolutionsRow](_tableTag, "play_evolutions") {
    def * = (id, hash, appliedAt, applyScript, revertScript, state, lastProblem) <> (PlayEvolutionsRow.tupled, PlayEvolutionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(hash), Rep.Some(appliedAt), applyScript, revertScript, state, lastProblem).shaped.<>({r=>import r._; _1.map(_=> PlayEvolutionsRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(int4), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column hash SqlType(varchar), Length(255,true) */
    val hash: Rep[String] = column[String]("hash", O.Length(255,varying=true))
    /** Database column applied_at SqlType(timestamp) */
    val appliedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("applied_at")
    /** Database column apply_script SqlType(text), Default(None) */
    val applyScript: Rep[Option[String]] = column[Option[String]]("apply_script", O.Default(None))
    /** Database column revert_script SqlType(text), Default(None) */
    val revertScript: Rep[Option[String]] = column[Option[String]]("revert_script", O.Default(None))
    /** Database column state SqlType(varchar), Length(255,true), Default(None) */
    val state: Rep[Option[String]] = column[Option[String]]("state", O.Length(255,varying=true), O.Default(None))
    /** Database column last_problem SqlType(text), Default(None) */
    val lastProblem: Rep[Option[String]] = column[Option[String]]("last_problem", O.Default(None))
  }
  /** Collection-like TableQuery object for table PlayEvolutions */
  lazy val PlayEvolutions = new TableQuery(tag => new PlayEvolutions(tag))

  /** Entity class storing rows of table Resources
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param resourceTypeId Database column resource_type_id SqlType(int4)
   *  @param pricePerKg Database column price_per_kg SqlType(varchar), Length(10,true)
   *  @param weight Database column weight SqlType(int4)
   *  @param addressId Database column address_id SqlType(int4)
   *  @param ownerId Database column owner_id SqlType(int4)
   *  @param createTs Database column create_ts SqlType(timestamp)
   *  @param collectedTs Database column collected_ts SqlType(timestamp), Default(None)
   *  @param deletedTs Database column deleted_ts SqlType(timestamp), Default(None) */
  final case class ResourcesRow(id: Int, resourceTypeId: Int, pricePerKg: String, weight: Int, addressId: Int, ownerId: Int, createTs: java.sql.Timestamp, collectedTs: Option[java.sql.Timestamp] = None, deletedTs: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching ResourcesRow objects using plain SQL queries */
  implicit def GetResultResourcesRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[java.sql.Timestamp]]): GR[ResourcesRow] = GR{
    prs => import prs._
    ResourcesRow.tupled((<<[Int], <<[Int], <<[String], <<[Int], <<[Int], <<[Int], <<[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table resources. Objects of this class serve as prototypes for rows in queries. */
  class Resources(_tableTag: Tag) extends profile.api.Table[ResourcesRow](_tableTag, "resources") {
    def * = (id, resourceTypeId, pricePerKg, weight, addressId, ownerId, createTs, collectedTs, deletedTs) <> (ResourcesRow.tupled, ResourcesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(resourceTypeId), Rep.Some(pricePerKg), Rep.Some(weight), Rep.Some(addressId), Rep.Some(ownerId), Rep.Some(createTs), collectedTs, deletedTs).shaped.<>({r=>import r._; _1.map(_=> ResourcesRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8, _9)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column resource_type_id SqlType(int4) */
    val resourceTypeId: Rep[Int] = column[Int]("resource_type_id")
    /** Database column price_per_kg SqlType(varchar), Length(10,true) */
    val pricePerKg: Rep[String] = column[String]("price_per_kg", O.Length(10,varying=true))
    /** Database column weight SqlType(int4) */
    val weight: Rep[Int] = column[Int]("weight")
    /** Database column address_id SqlType(int4) */
    val addressId: Rep[Int] = column[Int]("address_id")
    /** Database column owner_id SqlType(int4) */
    val ownerId: Rep[Int] = column[Int]("owner_id")
    /** Database column create_ts SqlType(timestamp) */
    val createTs: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("create_ts")
    /** Database column collected_ts SqlType(timestamp), Default(None) */
    val collectedTs: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("collected_ts", O.Default(None))
    /** Database column deleted_ts SqlType(timestamp), Default(None) */
    val deletedTs: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("deleted_ts", O.Default(None))

    /** Foreign key referencing Addresses (database name resources_address_id_fkey) */
    lazy val addressesFk = foreignKey("resources_address_id_fkey", addressId, Addresses)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Owners (database name resources_owner_id_fkey) */
    lazy val ownersFk = foreignKey("resources_owner_id_fkey", ownerId, Owners)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing ResourceTypes (database name resources_resource_type_id_fkey) */
    lazy val resourceTypesFk = foreignKey("resources_resource_type_id_fkey", resourceTypeId, ResourceTypes)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Resources */
  lazy val Resources = new TableQuery(tag => new Resources(tag))

  /** Entity class storing rows of table ResourceTypes
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(varchar), Length(40,true) */
  final case class ResourceTypesRow(id: Int, name: String)
  /** GetResult implicit for fetching ResourceTypesRow objects using plain SQL queries */
  implicit def GetResultResourceTypesRow(implicit e0: GR[Int], e1: GR[String]): GR[ResourceTypesRow] = GR{
    prs => import prs._
    ResourceTypesRow.tupled((<<[Int], <<[String]))
  }
  /** Table description of table resource_types. Objects of this class serve as prototypes for rows in queries. */
  class ResourceTypes(_tableTag: Tag) extends profile.api.Table[ResourceTypesRow](_tableTag, "resource_types") {
    def * = (id, name) <> (ResourceTypesRow.tupled, ResourceTypesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name)).shaped.<>({r=>import r._; _1.map(_=> ResourceTypesRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(varchar), Length(40,true) */
    val name: Rep[String] = column[String]("name", O.Length(40,varying=true))
  }
  /** Collection-like TableQuery object for table ResourceTypes */
  lazy val ResourceTypes = new TableQuery(tag => new ResourceTypes(tag))
}
