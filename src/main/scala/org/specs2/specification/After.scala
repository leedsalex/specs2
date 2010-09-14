package org.specs2
package specification
import execute._
import control.Exceptions._

trait After {
  def after: Any
  def apply[T <% Result](a: =>T): Result = {
	try {
	  return a
	} finally {
	  after	
	}
  }  
}

