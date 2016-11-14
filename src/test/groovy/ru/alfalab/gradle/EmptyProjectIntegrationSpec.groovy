package ru.alfalab.gradle

import nebula.test.IntegrationSpec
import nebula.test.functional.ExecutionResult

/**
 * Created by aleksandr on 14.11.16.
 */
class EmptyProjectIntegrationSpec extends IntegrationSpec {
    def 'runs build'() {
        given:
        buildFile << """
            apply plugin: 'java'
            apply plugin: 'ru.alfalab.cucumber-reporting'

            repositories {
              mavenLocal()
              jcenter()
            }

            cucumberReporting {
            }
        """.stripIndent()

        when:
        ExecutionResult result = runTasks('generateCucumberReport')

        then:
        println result.standardOutput
        println result.standardError

        result.failure == null
    }
}
