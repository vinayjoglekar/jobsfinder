package com.jovinz.jobsfindingapp.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.jovinz.jobsfindingapp.R
import com.jovinz.jobsfindingapp.di.viewmodels.ViewModelsProviderFactory
import com.jovinz.jobsfindingapp.ui.EspressoDaggerMockRule
import com.jovinz.jobsfindingapp.ui.adapter.CategoriesRecyclerViewAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock


@RunWith(AndroidJUnit4ClassRunner::class)
class CategorySelectionFragmentTest {

    @get:Rule
    var rule = EspressoDaggerMockRule()

    @Test
    fun testNavigation() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        navController.setGraph(R.navigation.nav_graph)

        // Create a graphical FragmentScenario for the TitleScreen
        val categoriesScenario = launchFragment()

        // Set the NavController property on the fragment
        categoriesScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(ViewMatchers.withId(R.id.recyclerviewCategories)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }

    var categoriesRecyclerViewAdapter: CategoriesRecyclerViewAdapter = mock(CategoriesRecyclerViewAdapter::class.java)
    var factory: ViewModelsProviderFactory = mock(ViewModelsProviderFactory::class.java)

    private fun launchFragment(): FragmentScenario<CategorySelectionFragment> {
        return launchFragmentInContainer(factory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                return CategorySelectionFragment().apply {
                    viewModelsProviderFactory = factory
                    adapter = categoriesRecyclerViewAdapter
                }
            }
        })
    }


}