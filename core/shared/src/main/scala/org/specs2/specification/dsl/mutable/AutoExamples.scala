package org.specs2
package specification
package dsl
package mutable

import execute.AsResult
import org.specs2.specification.core.{Fragments}

/**
 * Auto-example creation for mutable specifications
 */
trait AutoExamples extends create.AutoExamples with FragmentBuilder with FragmentsFactory {
  override def createExample[T](expression: String, code: =>T, asResult: AsResult[T]): Fragments = {
    addFragment(makeExample(expression, code, asResult))
    addFragment(fragmentFactory.break)
    Fragments.empty
  }

}
