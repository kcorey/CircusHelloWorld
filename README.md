# CircusHelloWorld

This is a simple hello world demonstration of the Circus architecture.

Since Circus is aimed towards full-size apps, it's rather a lot of overkill for Hello World.  As such, I've 'jazzed it up' a bit with a splash screen and a later state in the hello world activity.

The point is that the front end is a transient and disposable thing, where the state is maintained and treasured in the back end.

Obviously, a Hello World doesn't need a database, or network connections, or even a singleton to maintain authorisation state.  These are meant to be kept in Plug-ins.

You'll have to [read my article](http://www.kencorey.com/flippin-bits/circus-in-motion "Circus in Motion") to see the higher-level detail of how this is meant to work in a real app.  

Just remember that Acts aren't tied to Activities.  They sit on top of Plug-ins.  For extra flexibility, you might want to make sure that they use a broker class similar to CircusAct to sit between the presenter and the plug-ins.

You could have all the Activites of your whole app controlled by a single Act if that makes sense.  

In a larger app, it would make sense to split the different user hournies into the Acts as there's usually a synergy.  A login-journey, an add-an-item journey, a payment-journey, etc.

This version now *does* demonstrate backgroundn processing in the class 'LongRunningPlugin'.  Note that it's called from an Act, which means we are already on a new background thread.  That means that we can block all we want, and we won't cause any issues.  Further, the counter information is saved, so that when the UI needs to be redrawn, the information is readily available.


Note to self: Update this to show the proper back-end thread handling, along with a long-running dummy back end service to show why.
