object SlickCodeGenerator {
  def main(args: Array[String]): Unit = {
    slick.codegen.SourceCodeGenerator.main(
      Array(
        "slick.jdbc.PostgresProfile",
        "org.postgresql.Driver",
        "jdbc:postgresql://localhost:5432/rme",
        "app/",
        "model",
        "postgres",
        "postgres")
    )
  }

}

