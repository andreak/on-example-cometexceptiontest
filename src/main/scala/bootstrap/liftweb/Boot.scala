package bootstrap.liftweb

import net.liftweb._
import http._
import util.Helpers._
import net.liftweb.common.{Full, Logger}
import net.liftweb.http.js.jquery.JQuery14Artifacts


class Boot {
	def boot() {
		// Do nothing. We don't want Lift to try to mess up our logging. Having log4j.xml in classpath is sufficient
		Logger.setup = Full(() => ())

		LiftRules.addToPackages("no.officenet.example.cometexceptiontest")
		LiftRules.ajaxRetryCount = Full(0)
		LiftRules.ajaxPostTimeout = (1 minute).toInt // Longer than cometProcessingTimeout (set to 30 sek), to show effect

		LiftRules.ajaxStart =
			Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

		// Make the spinny image go away when it ends
		LiftRules.ajaxEnd =
			Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

		LiftRules.jsArtifacts = JQuery14Artifacts

	}

}
