package com.rbinnovative.rentalotool.config;

import com.rbinnovative.rentalotool.service.RentaloToolClient;
import com.rbinnovative.rentalotool.ui.activity.DetailedToolActivity;
import com.rbinnovative.rentalotool.ui.activity.LandingScrollingActivity;
import com.rbinnovative.rentalotool.ui.recicleview.HorizotalToolsRecyclerAdapter;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component
public interface RepositoryToolIoC {
    /*
    This will be used to generate at compile time a builder for DaggerToolRepositoryIoC.
    Pre:requirement:
        ToolRepository contrustor to be annotated with @Inject(And also it's dependencies).
 */
    RentaloToolClient toolRepository();

    // This tells Dagger that LoginActivity requests injection so the graph needs to
    // satisfy all the dependencies of the fields that LoginActivity is injecting
    void inject(LandingScrollingActivity landingScrollingActivity);
    void inject(DetailedToolActivity landingScrollingActivity);
    void inject(HorizotalToolsRecyclerAdapter landingScrollingActivity);
}
