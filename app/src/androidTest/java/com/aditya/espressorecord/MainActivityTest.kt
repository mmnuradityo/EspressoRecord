package com.aditya.espressorecord

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.aditya.espressorecord.ui.Main.MainActivity
import com.aditya.espressorecord.data.SharedLogin
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest: BaseTest<MainActivity>(MainActivity::class.java) {

    private lateinit var sharedLogin: SharedLogin

    override fun setUp() {
        sharedLogin = SharedLogin(appContext)
    }
    
    @Test
    fun runningMainActivityTests() {
        val expectation = "Expectation"
        sharedLogin.setLogin(expectation, expectation)
        
        manuallyLaunchActivity(
            MainActivity.generateIntent(appContext, expectation, expectation)
        )
        
        val materialButton = onView(
            allOf(
                withId(R.id.btnFirst), withText("First"),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.tvUserName), withText(expectation),
                ViewMatchers.withParent(
                    allOf(
                        withId(R.id.containerBackground),
                        ViewMatchers.withParent(withId(R.id.container))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText(expectation)))

        val materialButton2 = onView(
            allOf(
                withId(R.id.btnSecond), withText("Second"),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val textView2 = onView(
            allOf(
                withId(R.id.tvUserName), withText(expectation),
                ViewMatchers.withParent(
                    allOf(
                        withId(R.id.containerBackground),
                        ViewMatchers.withParent(withId(R.id.container))
                    )
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText(expectation)))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
