# QontoDemo

* Architecture of the app:
- The demo follows an MVP pattern where each screen has a view , a presenter and a screen interface
to support communication from the presenter to the view, the presenter never uses any android specific
api so that it can be testable.

* Explain your choice of libraries
- Usage of dagger to simplify providing dependencies and facilitate testing and decouple components.
- RxJava to facilitate networking and handling asynchronuous requests.
- Retrofit for network calls to the api and it's rx adapter because it makes calling the api simple
  and allows later for testing either with functional tests by providing a mock retrofit or even for
  unit tests by providing a mocked interface for the api.

* What was the most difficult part of the challenge ?
- Finishing all i would've liked done in the required time.

* Estimate your percentage of completion and how much time you would need to finish
- Almost 85% , an extra 40 to an hour will suffice to finish it all i think.
