package ru.alfalab.gradle

import nebula.test.PluginProjectSpec

/**
 * Created by aleksandr on 14.11.16.
 */
class CucumberReportingPluginSpec extends PluginProjectSpec {

    @Override
    String getPluginName() {
        'ru.alfalab.cucumber-reporting'
    }

    def 'add cucumber reporting task'() {
        when:
        project.apply plugin: pluginName

        then:
        project.tasks.getByName('generateCucumberReport')
        project.extensions.getByName('cucumberReporting')
    }

}