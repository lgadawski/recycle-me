package controllers

import scala.concurrent.ExecutionContext.Implicits.global
import models.{Resource, ResourceRepository}
import org.scalatest.mockito.MockitoSugar
import org.mockito.Mockito.when
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.Helpers._
import play.api.test._
import play.api.libs.json._

import scala.concurrent.Future

class ResourcesControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting with MockitoSugar {

  "ResourcesController GET" should {

    "return list of elements" in {
      // given

      val firstResource = Resource(1, "AA", "Aaa")
      val secondResource = Resource(2, "AB", "Abb")

      val repo = mock[ResourceRepository]
      val controller = new ResourcesController(repo, stubControllerComponents())

      // when

      when(repo.list())
        .thenReturn(Future {
          List(firstResource, secondResource)
        })

      val home = controller.get().apply(FakeRequest(GET, "/api/resources"))

      // then

      status(home) mustBe OK
      contentType(home) mustBe Some("application/json")

      val result: List[Resource] = Json.fromJson[List[Resource]](contentAsJson(home)).get

      result.size mustBe 2
      result.head mustBe firstResource
      result.apply(1) mustBe secondResource
    }

    "return element requested by id" in {
      // given

      val firstResource = Resource(1, "AA", "Aaa")
      val secondResource = Resource(2, "AB", "Abb")

      val repo = mock[ResourceRepository]
      val controller = new ResourcesController(repo, stubControllerComponents())

      // when

      val home = controller.get().apply(FakeRequest(GET, "/api/resources/" + firstResource.id))

      // then

      status(home) mustBe OK
      contentType(home) mustBe Some("application/json")

      val result: Resource = Json.fromJson[Resource](contentAsJson(home)).get

      result mustBe firstResource
    }

    "return element requested by id not found" in {
      // given

      val firstResource = Resource(1, "AA", "Aaa")
      val secondResource = Resource(2, "AB", "Abb")
      val id = 3

      val repo = mock[ResourceRepository]
      val controller = new ResourcesController(repo, stubControllerComponents())

      // when

      val home = controller.get().apply(FakeRequest(GET, "/api/resources/" + id))

      // then

      status(home) mustBe NOT_FOUND
    }
  }
}