# web-crawler
A Web crawler, extracts unique list of urls under the same domain including static content urls and external urls present on the provided url. 

## Technologies stack
 - Spring Boot Starter (v2.2.11.RELEASE)
 - jsoup (v1.12.1)
 - lombok (v1.18.16)
 - Junit5
 - Gradle (v6.6.1)
 
## How to build and run

```
$ ./gradlew clean build
$ ./gradlew bootRun -Dweb.domain=<domain_name>
Example: **./gradlew bootRun -Dweb.domain=wiprodigital.com**

or

> gradlew clean build
> gradlew bootRun -Dweb.domain=<domain_name>
Example: **gradlew bootRun -Dweb.domain=wiprodigital.com**

or

> java -jar <rootDirectory>/build/libs/web-crawler-1.0.jar -Dweb.domain=wiprodigital.com

```

## Why we choose this tech-stack

```
Spring-boot : To easily extend the application to expose the service end points to any consumers.
Jsoup: To parse the html documents in java and expand the urls in the document.
Lombok: To reduce boilerplate code for model/data objects used in the project.

```

## How can we improve
```
- Can make it as web application provides swagger to ease the testing.
- Can improve the scanning static content approach
```