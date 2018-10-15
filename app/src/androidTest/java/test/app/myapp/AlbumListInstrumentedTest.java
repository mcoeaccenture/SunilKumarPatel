package test.app.myapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.AssertionFailedError;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import test.app.myapp.albums.AlbumsActivity;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class AlbumListInstrumentedTest {

    @Rule
    public ActivityTestRule<AlbumsActivity> mActivityRule =
            new ActivityTestRule<>(AlbumsActivity.class);

    @Test
    public void checkGetAlbumButtonVisible() {
        Espresso.onView(withText("GET ALBUMS")).check(matches(isDisplayed()));
    }

    /**
     * Check if album list is displayed.
     */
    @Test
    public void checkListIsDisplayed() {
        Espresso.onView(withText("GET ALBUMS"))
                .perform(click());
        try {
            Espresso.onView(withId(R.id.albumsList))
                    .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        } catch (AssertionFailedError error) {
            Espresso.onView(withId(R.id.emptyView))
                    .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        }
    }
}
