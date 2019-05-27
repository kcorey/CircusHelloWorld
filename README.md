# CircusHelloWorld

This is a simple hello world demonstration of the Circus architecture.

Since Circus is aimed towards full-size apps, it's rather a lot of overkill for Hello World.  As such, I've 'jazzed it up' a bit with a splash screen and a later state in the hello world activity.

The point is that the front end is a transient and disposable thing, where the state is maintained and treasured in the back end.

Obviously, a Hello World doesn't need a database, or network connections, or even a singleton to maintain authorisation state.  These are meant to be kept in Plug-ins.

You'll have to [read my article] (http://www.kencorey.com/flippin-bits/circus-in-motion "Circus in Motion") to see the higher-level detail of how this is meant to work in a real app.  

Just remember that Acts aren't tied to Activities.  They sit on top of Plug-ins.  For extra flexibility, you might want to make sure that they use a broker class similar to CircusAct to sit between the presenter and the plug-ins.

You could have all the Activites of your whole app controlled by a single Act if that makes sense.  

In a larger app, it would make sense to split the different user hournies into the Acts as there's usually a synergy.  A login-journey, an add-an-item journey, a payment-journey, etc.
