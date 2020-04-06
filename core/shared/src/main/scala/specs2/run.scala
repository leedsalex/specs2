package specs2

import org.specs2.main.Arguments
import org.specs2.runner._
import org.specs2.fp.syntax._
import org.specs2.specification.core._

/**
 * Run a specification from the command-line with specs2.run <specification name> <arguments>
 */
object run extends ClassRunnerMain {

  /**
   * Run one or more specifications with `specs2.run(spec1, spec2)` from a terminal
   */
  def apply(specifications: SpecificationStructure*)(implicit arguments: Arguments = Arguments()) = {
    val env = EnvDefault.create(arguments)

    val action = for {
      classRunner <- ClassRunner.createClassRunner(env).toAction
      result <- specifications.toList.traverse(s => classRunner.run(s)).map(_.suml)
    } yield result

    try Runner.execute(action, env, exit = false)
    finally env.shutdown()
  }

  /** main method for the command line */
  def main(args: Array[String]) =
    run(args, exit = true)
}
