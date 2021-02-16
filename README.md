# Flixter
Android Dev Unit 1

Flix is an app that allows users to browse movies from the [The Movie Database API](http://docs.themoviedb.apiary.io/#).

---

## Flix Part 1

### User Stories

#### REQUIRED (10pts)
- [x] (10pts) User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API.

#### BONUS
- [x] (2pts) Views should be responsive for both landscape/portrait mode.
   - [x] (1pt) In portrait mode, the poster image, title, and movie overview is shown.
   - [x] (1pt) In landscape mode, the rotated alternate layout should use the backdrop image instead and show the title and movie overview to the right of it.

- [ ] (2pts) Display a nice default [placeholder graphic](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#advanced-usage) for each image during loading
- [x] (2pts) Improved the user interface by experimenting with styling and coloring.
   - Added spacing between the view holders
   - Added ellipses for when text exceeded a certain number of lines (to make view holders look more uniform)
- [ ] (2pts) For popular movies (i.e. a movie voted for more than 5 stars), the full backdrop image is displayed. Otherwise, a poster image, the movie title, and overview is listed. Use Heterogenous RecyclerViews and use different ViewHolder layout files for popular movies and less popular ones.

### App Walkthough GIF

<img src="https://github.com/mrmikeyc/Flixter/blob/master/flix_walkthrough_week1.gif" width=250><br>

### Notes
Describe any challenges encountered while building the app.
- Becoming more fluent with the Android studio short cuts in order to write faster, generate more code, and spend less time using the mouse
- It took a bit of time to understand how the components of the RecyclerView work together in order to go from data to viewholder to recyclerview
- Ensuring that I was editing the correct resource in an xml file.  Occasionally I would accidentally make an edit to the Layout file, which would then make it difficult to edit the components within the file.

### Open-source libraries used

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Androids
