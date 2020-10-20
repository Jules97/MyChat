# MyChat
Android mock chat/messaging application that echos any message sent using Kotlin. The data is persisted locally using Room.

* This android application built using Kotlin echos twice any message sent to any user after a randomized delay up to 0.5 seconds. 
* It consists of two acitivities:
    - AllChatsActivity: views all the users with their profile picture, last message sent, and the timestamp of the latter.
    - SingleChatActivity: views all the messages sent and echoed by that specific user.
* The messages sent are saved locally using Room.
* Messages and users are sorted depending on the time of the last message sent.
* If no messages are originally sent, no message and timestamp would be visible.

* Minimum SDK 21 
* Target SDK 29
* The splash screen’s background is the activity’s theme background.

* 200 random users are created using the random string generator function.
* Time is generated using the SimpleDateFormat function with compatibility constratint check the Build Version.
  SimpleDateFormat is used for Build Versions > Build.VERSION_CODES.O (Oreo).
* Random time is generated using ThreadLocalRandom.current().nextDouble(start, end) .

* For android versions < 6, an intent flag is added to ove from an adapter to an activity.

* A view model is used to provide data to the user interface and to be launched in the background thread.
* A view model factory is used to send an argument to the database and retreive the data on application startup.
* Instances in the database respositories are volatile to make them visible to other threads.
