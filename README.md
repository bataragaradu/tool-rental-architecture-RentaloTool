# tool-rental-architecture-RentaloTool
Android app that can be used for renting



## Dagger notes:
Those familiar with Spring may have noticed some parallels between the two frameworks.

Dagger's @Module annotation makes the container aware of a class in a very similar fashion as any of Spring's stereotype annotations (for example, @Service, @Controller…). Likewise, @Provides and @Component are almost equivalent to Spring's @Bean and @Lookup respectively.

Spring also has its @Scope annotation, correlating to @Singleton, though note here already another difference in that Spring assumes a singleton scope by default while Dagger defaults to what Spring developers might refer to as the prototype scope, invoking the provider method each time a dependency is required.


Classes that wants dependenecies injection have to mention Dagger they are the owners via
@Component Interface AppComponenet{
    void inject(OwnerClass class);
} and  ((MainApplication) getApplicationContext()).appComponent.inject(this), where appComponent is an instance of the generated DaggerOwnerClass.
or via
 toolService = DaggerRepositoryToolIoC.builder().build().toolService();


 Issues:

 ## comptability between android dependencies
 ## butterknife
 ## dagger
 ## ui setup
