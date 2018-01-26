## Used Versions

* [Play Framework: 2.6.7](https://www.playframework.com/documentation/2.6.x/Home)
* [Angular: 5.0.0](https://angular.io/)
* [Angular CLI: 1.6.0](https://cli.angular.io/)

## How to use it?

### Let's get started,

* This application is not using any of the scala play views and all the views are served by the [Angular](https://angular.io/) code base which is inside the `ui` folder.

* Used any of the sbt commands listed in the below according to the requirement which are working fine with this application.(To see more details of [sbt](http://www.scala-sbt.org/))

``` 
    sbt clean           # Clear existing build files
    
    sbt stage           # Build your application from your project’s source directory
    
    sbt run             # Run both backend and frontend builds in watch mode
    
    sbt dist            # Build both backend and frontend sources into a single distribution
    
    sbt test            # Run both backend and frontend unit tests 
```  

**Note: _On production build all the front end Angular build artifacts will be copied to the `public/ui` folder._**

## Can be used to implement any front end/ui build!

* Simply replace the ui directory with the build of your choice
* Make output directory ROOT/public/
* Implement a proxy to localhost:9000

## Project seed based on a9e5dc4a2468a877d84b1d8609272d58ea47e835 from repo https://github.com/yohangz/scala-play-angular-seed 
Contributed by:

[yohan-profile]: https://github.com/yohangz
[lahiru-profile]: https://github.com/lahiruz
[gayan-profile]: https://github.com/Arty26
[anuradha-profile]: https://github.com/sanuradhag
