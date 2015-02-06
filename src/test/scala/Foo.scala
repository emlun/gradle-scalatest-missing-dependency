import org.scalatest._

class FooSpec extends FunSpec with Matchers {
  describe("The Foo object") {
    describe("has a getZero method which") {
      it("returns 0") {
        Foo.getZero should be (0)
      }
    }
  }
}
