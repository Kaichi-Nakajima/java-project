# My Personal Project

## TV Show and Movie Watchlist

### What this application does
This application allows users to **track all the TV shows and Movies they 
have watched or are planning to watch**. For each show they have watched, 
they will be able to record a rating out from 0 to 10, the genres, the director, 
number of episodes watched and a list of their favorite characters. Users will be able to view their list and see the 
details of each show.


### Who will use it
This app is directed towards individuals who are struggling to keep track of all
the movies and TV shows they have watched. With this app, users will be 
able to keep an organized list of what they have watched and help them 
remember which shows they found most enjoyable. Furthermore, users could 
share their list with friends to talk about and recommend some of their favorite
shows.

### Why this project is of interest to me
This project is of interest to me since I often struggle to remember all the 
shows I have watched and some of the characters that I liked. This 
application could help people, like myself, easily keep record of their 
favorite shows in an organized manner.

### User Stories

- As a user, I want to be able to add a show to my watch list
- As a user, I want to be able to select a show and add a rating from 1 
to 10, a list of genres,the director, number of episodes and a list of favorite 
characters
- As a user, I want to be able to view the list of shows in my watchlist
- As a user, I want to be able to remove a show from my watch list
- As a user, I want to be able to view the number of total, short (1-25 
- episodes), medium (26-65 episodes) and long(66+ episode) shows in 
my watchlist
- As a user, I want to be able to select a show and view its details
- As a user, I want to be able to save my watchlist to file
- As a user, I want to be able to load my watchlist from file

### Instructions for Grader

- You can generate the first required action related to the user 
story "adding multiple Xs to a Y" by filling the text box that says 
"Add Show Name" with the name of the show and entering the
number of episodes in the text box that says "Add Episodes". 
Then click on "Add Show".
- You can generate the second required action related to the user 
story "adding multiple Xs to a Y" by entering the name of the 
show you want to remove then click "Remove Show".
- To view the current state of the watchlist, click on "View List" 
which will display the names of the shows currently in the 
watchlist
- You can locate my visual component by loading the application 
and look at the top of the window.
- You can save the state of my application by clicking on "Save"
- You can reload the state of my application by clicking on "Load"

### Phase 4: Task 2
- Sat Apr 06 22:01:53 PDT 2024 \
Show Added: Breaking Bad
- Sat Apr 06 22:02:01 PDT 2024 \
Show Added: Game Of Thrones
- Sat Apr 06 22:02:09 PDT 2024 \
Show Added: One Piece
- Sat Apr 06 22:02:22 PDT 2024 \
Show Removed: Game Of Thrones
- Sat Apr 06 22:02:24 PDT 2024 \
Watchlist Viewed

### Phase 4: Task 3
- If given more time, I would refactor the WatchList into abstract 
class and create separate classes for short, medium and long 
show watch lists. This is to reduce redundancy in the WatchList 
class that share common functionality such as the methods: 
numOfShortShows, numOfMediumShows and 
numOfLongShows.