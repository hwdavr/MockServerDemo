# SPWeather

SPWeather is a simple app to check the weather. It uses Kotlin MVVM architecture and Dagger as dependency injection. 

## Architecture 

Below is the whole architecture. Activity and ViewModel use android databinding to bind together. The other layers use callbacks to return results to upper layers. 

![architecture](https://raw.githubusercontent.com/hwdavr/hwdavr.github.io/master/assets/images/44525736-e9e7b700-a71c-11e8-8045-42c4478dd67e.png)

Room is used to store persistent data. Inside CityDao.kt, `latest` is used to return latest 10 cities based on updated timestamp of the inserted rows. `upsert` is used to insert an new city row, if the city name exists, it will just update the timestamp only.  

Kotlin coroutine is used for Room database access, to make sure the database access does not block the main thread execution. All API services are also executed in Coroutine IO threads. 

API services use 2 different ways for json deserialization, one is using Kotlin Serializable, another one using jackson library because we don't need to deserialized all the server responsed data. 

View layers are tested by instrumentation test using esspresso. 
Other layers are tested by JUnit tests. `kotlinx-coroutines-test` library is used to test the Kotlin coroutine.

## Test Coverage

A Jacoco test coverage report is provided in the "reports" folder at root of this repository. 

Run command to test the test coverage report at folder:
"app/build/reports/jacoco/testDebugUnitTestCoverage/html".

```
gradle clean testDebugUnitTestCoverage
```
