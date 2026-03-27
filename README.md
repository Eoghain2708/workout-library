# workout-library 

**workout-library** is a small library that hopes to make building workout-tracking apps easier by providing core logic for cardio and weight-lifting style workouts and a shorthand methods which provide
the user with useful information regarding their performance and potential. This library is a work in progress and, while its core is unlikely to change, will hopefully continue to expand in terms of options
and utility.

## Usage
This is a simple library to use, centred around taking user input and using Builders to promote clean, idiomatic object-creation along with optional attributes. The library is focussed on providing flexibility
to the client in terms of the complexity of their own application, trying to force as little as possible whilst presenting the greatest number of options, which will again, continue to increase (as I think of them :))

### Exercises
Exercises are the fundamental piece of the API and are the most basic of immutable objects. They are simply created with a name via the 'create' factory method, like so:
```
Exercise ex = Exercise.create("bench press");
```
Exercises are cached and sanitised at creation, meaning that regardless of leading or trailing white-space and differing capitalisation, the same object will be returned if it exists already. However, this does not hold true for differing spaces 
within the names themselves.

```
Exercise.create("pullup"); // ex1
Exercise.create("    pullUP"); // this will return ex1

Exercise.create("pull up"); // this will not
```

### ExerciseSets
ExerciseSets are abstract representations of a physical activity, built with a given Exercise. For further explanation, see the below paragraphs on CardioSets and WeightSets


### CardioSets
CardioSets are, thus far, the simplest of the two types of ExerciseSet. They are built with an Exercise and Duration, both of which cannot be null/absent, like so.
```
CardioSet cs = new CardioSet.Builder()
                .exercise(Exercise.create("jogging"))
                .duration(Duration.ofMinutes(30))
                .build();
```
Using Duration, the respective attribute is therefore eligible for the built-in Duration methods, depending on how you want to present Time and how a user has input it. The Duration of a CardioSet can, unsurprisingly, be retrieved by calling the
method getDuration(), and converted in the following way, as an example.

```
CardioSet cs = new CardioSet.Builder()
                .exercise(Exercise.create("jogging"))
                .duration(Duration.ofHours(2))
                .build();
long minutes = cs.getDuration().toMinutes();
```

### WeightSets
WeightSets can (at current) hold four attributes, of which two are necessary
**Necessary**
1. Exercise
2. Reps

**Optional**
1. Reps in Reserve (RIR)
2. Weight

Weight is an object which can be left at 0 in order to simulate bodyweight exercises (or, if one would prefer, they can create a User with a bodyweight and use that), and RIR can be left unassigned. RIR is an Integer so as to differentiate 
between unassigned (null) and a very difficult set, or one-rep maximum (0 RIR). WeightSets follow the same, predictable Builder pattern as CardioSets. Here is one in its full form.
```
WeightSet ws = new WeightSet.Builder()
              .exercise(Exercise.create("barbell curl")
              .reps(12)
              .weight(Weight.ofKg(15))
              .build();
```
***
#### Weight
*A side note about Weight.* Weight is an Object which can be instantiated with two separate factory methods:
```
ofKg(double val)
ofLbs(double val)
```
The default is kilograms, but conversion is made very easy with the following methods applied to Weight w:
```
double kgs = w.toKg();
double lbs = w.toLbs();
```
***
If Weight is not assigned, it will be transformed from null to 0 in construction and assumed to be a bodyweight exercise, although it will thusly result in 0 volume. For this reason, it is preferable to create a **User** with a given bodyweight and
pass that in instead.

***
#### User
User only serves one purpose in this library, and that is to have a bodyweight. At current, the only use for the User object is that its weight be passed into exercises if chosen.
```
User user = User.ofBodyWeight(Weight.ofKg(80));
WeightSet ws = new WeightSet.Builder()
              .exercise(Exercise.create("barbell curl")
              .reps(12)
              .weight(user.getBodyWeight())
              .build();
```
***
### StrengthEstimator
The StrengthEstimator class has methods that either take in a WeightSet and are called in a WeightSet class method, or are standalone methods that take in individual parameters. 
***
**predictOneRepMax()** <br>
*predictOneRepMax()* is both a static utility method and a class method. Its utility version takes in a reps parameter and a weight parameter, like so:
```
double oneRepMax = predictOneRepMax(8, 100); // predict one rep max from a set of 100kg/lbs for 8 reps
```
The class method is protected in the StrengthEstimator class, and takes in a WeightSet object. It is called in the WeightSet class, with the parameter being *this*, meaning it can be called in the following way:
```
WeightSet ws = new WeightSet.Builder()
              .exercise(Exercise.create("barbell curl")
              .reps(12)
              .weight(user.getBodyWeight())
              .build();
double oneRepMax = ws.predictOneRepMax();
```
Both methods do the same thing, which is to apply the Epley and Brzycki formulas to the weight/reps of the set, and then average the two.

Epley's formula:
**1RM = weight(1 + reps / 30)**

Brzycki's formula:
**1RM = weight * 36 / (37 - reps)**

Epley is cited as being optimistic over certain repitition amounts, whereas Brzycki is cited as conservative - I thought it pertinent to average them to get a happy medium, although this may be split into individual methods depending on 
the client's/user's preferences.
***
**getWeightFromOneRepMax()** <br>
This method takes a weight parameter and a target reps parameter. The *weight* is said to be the user's one rep maximum for an unspecified exercise, and the *target reps* is how many repititions they want to aim for in a given set. Different 
rep-ranges are preferred for different goals and this method aims to find the weight which would make *n* number of reps challenging, so that the user will achieve sufficient intensity in their desired rep-range. It used another Epley formula:
** Weight = 1RM / (1 + 0.03333 * targetRepititions)

