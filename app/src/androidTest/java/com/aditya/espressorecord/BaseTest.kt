package com.aditya.espressorecord

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Rule

abstract class BaseTest<A : Activity>(clazz: Class<A>) {

    @Rule
    @JvmField
    var activityScenarioRule = ActivityScenarioRule(clazz)

    protected lateinit var scenario: ActivityScenario<A>
    protected lateinit var appContext: Context

    @Before
    fun initiation() {
        scenario = activityScenarioRule.scenario
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        setUp()
    }

    abstract fun setUp();

    fun manuallyLaunchActivity(clazz: Class<*>) {
        val intent = Intent(
            ApplicationProvider.getApplicationContext(), clazz
        )
        manuallyLaunchActivity(intent)
    }

    fun manuallyLaunchActivity(intent: Intent) {
        scenario.onActivity { activity: A -> activity.startActivity(intent) }
    }

}