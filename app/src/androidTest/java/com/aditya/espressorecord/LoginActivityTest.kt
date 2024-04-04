package com.aditya.espressorecord

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.aditya.espressorecord.data.SharedLogin
import com.aditya.espressorecord.ui.LoginActivity
import org.hamcrest.Matchers.allOf
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginActivityTest() : BaseTest<LoginActivity>(LoginActivity::class.java) {

    private lateinit var sharedLogin: SharedLogin

    override fun setUp() {
        sharedLogin = SharedLogin(appContext)
    }

    @Test
    fun loginActivityBeforeLoginTest() {
        sharedLogin.setLogin("", "")
        manuallyLaunchActivity(LoginActivity::class.java)

        val appCompatEditText = onView(
            allOf(
                withId(R.id.etUserName), isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("test"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.etPassword), isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("test"), closeSoftKeyboard())

        val materialButton = onView(
            allOf(
                withId(R.id.btnLogin), withText("Login"), isDisplayed()
            )
        )
        materialButton.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.tvUserName), withText("test"), withParent(
                    allOf(
                        withId(R.id.main), withParent(withId(android.R.id.content))
                    )
                ), isDisplayed()
            )
        )
        textView.check(matches(withText("test")))
    }

    @Test
    fun loginActivityAfterLoginTest() {
        val expectation = "Expectation"
        sharedLogin.setLogin(expectation, expectation)
        manuallyLaunchActivity(LoginActivity::class.java)

        val textView = onView(
            allOf(
                withId(R.id.tvUserName), withText(expectation), withParent(
                    allOf(
                        withId(R.id.main), withParent(withId(android.R.id.content))
                    )
                ), isDisplayed()
            )
        )
        textView.check(matches(withText(expectation)))
    }

}
