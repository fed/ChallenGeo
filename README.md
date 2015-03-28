# ChallenGeo

This is a simple, minimalistic geography related app. ChallenGeo has two core components, first one aiming at providing information on countries (flags, population, capital city, currency, languages spoken there and so on) whereas the second is some sort of quiz that presents users a flag / capital city / language / etc. so that they answer back which country they correspond to.

## UX Wireframes and Comps

<table style="text-align: center;">
	<tr>
		<td><img src="/docs/ux_1.jpg" /></td>
		<td><img src="/docs/ux_2.jpg" /></td>
	</tr>
</table>

## Screenshots

<table style="text-align: center;">
	<tr>
		<td><img src="/docs/screenshot_1.png" /></td>
		<td><img src="/docs/screenshot_2.png" /></td>
		<td><img src="/docs/screenshot_3.png" /></td>
	</tr>
</table>

## Data Source

This app doesn't have its own backend since it makes use of the [restcountries.eu RESTful API](http://restcountries.eu) for country information, as well as [GeoNames' bank of flags](http://www.geonames.org) instead. Please reach out to either website to get more info on endpoints and data provided.

## Topics Covered

* ListViews and custom adapters
* Shared Preferences
* Handling configuration changes using headless fragments
* Networking: consuming RESTful services using Retrofit
* Handling network responses using GSON to parse JSON objects into POJOs
* Handling collections using Guava
* Flexible UI: single and multi pane layouts
* UI Design Patterns: Navigation Drawer, Action Bar, View Pager
* Material Design: animations and transitions
* Unit testing using JUnit.
* Robotium and Robolectric