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
 rentaloToolClient = DaggerRepositoryToolIoC.builder().build().rentaloToolClient();


## Google Authenticator
# Authorization Flow
  Created on firebase https://console.firebase.google.com/ a new RentaloTool project.
1. Choice between https://console.firebase.google.com/ | https://console.cloud.google.com/?pli=1
https://medium.com/google-developers/whats-the-relationship-between-firebase-and-google-cloud-57e268a7ff6f

 https://firebase.google.com/docs/android/setup?authuser=0#console
 Step 1: Create firebase project
 Step 2: Linking the firebase project with the app
 Step 3: Next Sign in  tutorial https://firebase.google.com/docs/auth/android/google-signin?authuser=0

 Google sign in we have user id for each device. Based on this user id we will retrieve user data from our service.
------------------------------------------------------------------- User id for each

#Authentication Flow
https://stackoverflow.com/questions/53294357/using-spring-boot-app-as-resource-server-and-google-oauth2-as-authorization-serv

Authorizatino server - Resource Server - Client

 Issues:
 ## Remove in production the http(use https) and remove from manifest       android:usesCleartextTraffic="true"
  https://stackoverflow.com/questions/45940861/android-8-cleartext-http-traffic-not-permitted
 ## comptability between android dependencies
 ## butterknife
 ## dagger
 ## ui setup
 ## Retrofit
 ## Gson
 ## Picasso
 ## DatePicker
 ## Authorisation + Authentication
 ## OAuth2.0 + user id

TODO:
Add on click activity and show the available dates for the selected tool.
Add a chart and Paypal integration. See templates for Ecom projects.
