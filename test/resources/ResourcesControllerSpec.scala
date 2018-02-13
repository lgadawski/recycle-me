package resources

import org.mockito.Mockito.when
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.libs.json._
import play.api.test.Helpers._
import play.api.test._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ResourcesControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting with MockitoSugar {

  "ResourcesController GET" should {

    "return list of elements" in {
      // given

      val firstResource = ResourceJsonUtils(1, "AA", "Aaa")
      val secondResource = ResourceJsonUtils(2, "AB", "Abb")

      val repo = mock[ResourceRepository]
      val controller = new ResourcesController(repo, stubControllerComponents())

      // when

      when(repo.list())
        .thenReturn(Future {
          List(firstResource, secondResource)
        })

      val home = controller.getAll().apply(FakeRequest(GET, "/api/resources"))

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

      val givenResource = ResourceJsonUtils(1, "AA", "Aaa")

      val repo = mock[ResourceRepository]
      val controller = new ResourcesController(repo, stubControllerComponents())

      // when

      when(repo.get(givenResource.id))
        .thenReturn(Future {
          givenResource
        })

      val home = controller.get(givenResource.id).apply(FakeRequest(GET, "/api/resources/" + givenResource.id))

      // then

      status(home) mustBe OK
      contentType(home) mustBe Some("application/json")

      val result: Resource = Json.fromJson[Resource](contentAsJson(home)).get

      result mustBe givenResource
    }

    "return element requested by id not found" in {
      // given

      val givenResource = ResourceJsonUtils(1, "AA", "Aaa")
      val id = 3

      val repo = mock[ResourceRepository]
      val controller = new ResourcesController(repo, stubControllerComponents())

      // when

      when(repo.get(id))
        .thenReturn(Future {
          givenResource
        })

      val home = controller.get(id).apply(FakeRequest(GET, "/api/resources/" + id))

      // then

      status(home) mustBe NOT_FOUND
    }

    "save new element requested by POST" in {
      // given

      // when

      // then

    }
  }
}