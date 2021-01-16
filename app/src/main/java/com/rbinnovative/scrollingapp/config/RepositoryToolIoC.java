package com.rbinnovative.scrollingapp.config;

import com.rbinnovative.scrollingapp.service.ToolService;
import com.rbinnovative.scrollingapp.ui.ScrollingActivity;

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
    ToolService toolRepository();

    // This tells Dagger that LoginActivity requests injection so the graph needs to
    // satisfy all the dependencies of the fields that LoginActivity is injecting
    void inject(ScrollingActivity scrollingActivity);
}
