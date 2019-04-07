# **TFL Api Example**

This app is an example use of TFL API (`https://api-portal.tfl.gov.uk/`) with MVI approach.
For MVI I used tutorial by Hannes Dorfmann (http://hannesdorfmann.com/android/mosby3-mvi-1)
and adapted it to what I was trying to implement. I used minimum Android API version as 19

## **Building the app**
In order to build the app you need to do the following:
* Download it or clone
* Open in your Android Studio
* Make changes to validate API requests (see section below for instructions)
* Run the app

## **Validating API requests**
In order to make sure API requests are valid you need to have a valid TFL `App ID` and a `Key` from developer portal at `https://api-portal.tfl.gov.uk/`
Replace values for `TFL_APP_ID` and `TFL_KEY` in `app/credentials.gradle` file with your own.

## **Testing the app**
I only added a couple of tests for Interactor just to show approach.
To run tests you can either use `./gradlew test` or open `InteractorTest` class and run it from Android Studio