package no.officenet.example.cometexceptiontest.comet

import net.liftweb._
import http.js.JsCmd
import http.{SHtml, CometActor}
import util.Helpers._
import http.js.JsCmds._
import xml.Text

class SnabelComet extends CometActor {

	private def throwException: () => JsCmd = () => {
		if (true) throw new RuntimeException("BOOM!")
		Noop
	}

	def render = {
		".click" #> SHtml.a(throwException, Text("Click here"))
	}

	override def cometProcessingTimeout = 30 seconds

	override protected def exceptionHandler = {
		case ex => {
			partialUpdate(Alert("Exception caught: " + ex.getMessage))
		}
	}
}