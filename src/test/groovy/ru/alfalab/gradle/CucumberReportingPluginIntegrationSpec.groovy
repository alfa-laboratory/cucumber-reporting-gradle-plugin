package ru.alfalab.gradle

import nebula.test.IntegrationSpec
import nebula.test.functional.ExecutionResult

/**
 * Created by aleksandr on 14.11.16.
 */
class CucumberReportingPluginIntegrationSpec extends IntegrationSpec {
    def 'run build'() {
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

        def cucumberJsonResult = createFile('build/cucumber/cucumber.json')
        cucumberJsonResult.text = """[
  {
    "comments": [
      {
        "line": 1,
        "value": "# language: en"
      }
    ],
    "line": 3,
    "elements": [
      {
        "line": 6,
        "name": "Test scenario",
        "description": "",
        "id": "test",
        "after": [
          {
            "result": {
              "duration": 78362717,
              "status": "passed"
            },
            "match": {
              "location": "BaseSteps.CloseDriver()"
            }
          }
        ],
        "type": "scenario",
        "keyword": "scenario",
        "steps": [
          {
            "result": {
              "duration": 4947929648,
              "status": "passed"
            },
            "line": 8,
            "name": "first step",
            "match": {
              "location": "BaseSteps.hello()"
            },
            "keyword": "Hello "
          }
        ],
        "tags": [
          {
            "line": 15,
            "name": "@testTag"
          }
        ]
      }
    ],
    "name": "Test",
    "description": "Some description",
    "id": "test",
    "keyword": "keyword",
    "uri": "Test.feature"
  }
]""".stripIndent()

        when:
        ExecutionResult result = runTasks('generateCucumberReport')

        then:
        println result.standardOutput
        println result.standardError

        result.failure == null
        !result.wasUpToDate('generateCucumberReport')
        result.wasExecuted('generateCucumberReport')
        result.success
    }

    def 'run corrupted files build'() {
        given:
        buildFile << """
            apply plugin: 'java'
            apply plugin: 'ru.alfalab.cucumber-reporting'

            repositories {
              mavenLocal()
              jcenter()
            }
        """.stripIndent()

        def corrupted = createFile('build/cucumber/cucumber.json')
        corrupted.text = "".stripIndent()

        when:
        ExecutionResult result = runTasks('generateCucumberReport')

        then:
        println result.standardOutput
        println result.standardError

        result.failure
    }
}
