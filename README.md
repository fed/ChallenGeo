# ChallenGeo

This is a simple, minimalistic geography related app. ChallenGeo has two core components, first one aiming at providing information on countries (flags, population, capital city, currency, languages spoken there and so on) whereas the second is some sort of quiz that presents users a flag / capital city / language / etc. so that they answer back which country they correspond to.

<a href="https://play.google.com/store/apps/details?id=com.fknussel.challengeo&hl=en">
    <img src="/docs/google-play.png" alt="Get the App on Google Play" />
</a>

## UX Wireframes and Comps

<table style="text-align: center;">
	<tr>
		<td><img src="/docs/ux_1.jpg" /></td>
		<td><img src="/docs/ux_2.jpg" /></td>
	</tr>
</table>

## Topics Covered

Services.
ListViews and Custom Adapters.
Using Shared Preferences to store user settings.
Flexible UI: Landscape and portrait custom layouts.
Handling configuration changes using headless fragments.
Networking: Consuming RESTful services using Retrofit.
Handling network responses using GSON to parse JSON objects into POJOs.
Handling collections using Guava.
UI Design Patterns: Navigation Drawer, Action Bar, View Pager, Swipe to Refresh.
Material Design: Animations and Transitions.
Robotium and Robolectric.

## Data Source

This app doesn't have its own backend since it makes use of the [restcountries.eu RESTful API](http://restcountries.eu) for country information, as well as [GeoNames' bank of flags](http://www.geonames.org) instead. Please reach out to either website to get more info on the endpoints and data provided.

## Contributing

If you wish to contribute to this app, please fork it on GitHub, push your change to a named branch, then send a pull request. If it is a big feature, you might want to start an Issue first to make sure it's something that will be accepted. If it involves new code, please also write tests for it.