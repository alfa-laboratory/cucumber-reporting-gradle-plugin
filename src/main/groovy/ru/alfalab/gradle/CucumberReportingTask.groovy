package ru.alfalab.gradle

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import net.masterthought.cucumber.Configuration
import net.masterthought.cucumber.ReportBuilder
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

/**
 * Created by aleksandr on 14.11.16.
 */
@Slf4j
@CompileStatic
class CucumberReportingTask extends DefaultTask {
    @Input
    File cucumberResultsDirectory

    @OutputDirectory
    File outputDirectory

    @TaskAction
    protected void generateReport() {
        logger.debug 'CucumberReporting: +dir {}', cucumberResultsDirectory

        if (!cucumberResultsDirectory.exists() || cucumberResultsDirectory.list().size() == 0) {
            logger.info 'Cucumber results dir is empty. Nothing to generate'
            return
        }

        def cucumberResults = []
        cucumberResultsDirectory.eachFileMatch(~/.*\.json/) { file ->
            cucumberResults.add(file.absolutePath)
        }

        def configuration = new Configuration(outputDirectory, project.name)
        def reportBuilder = new ReportBuilder(cucumberResults, configuration)

        def result = reportBuilder.generateReports()
        if (null == result) {
            throw new GradleException('One of cucumber report files was corrupted')
        }
    }
}
