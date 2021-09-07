# FILM QUERY APPLICATION README

### Description
This project ended up being difficult in ways I didn't anticipate! The biggest issues I had actually revolved around understanding how the information was being passed from Java to SQL and back to Java. I didn't have too much trouble with the SQL syntax itself, but trying to take in input using "?" and trying to juggle joining tables, realizing that wasn't going to work for me, then trying to understand how to search the results with Java instead, was a struggle. Overall, I think I came up with a pretty acceptable way to implement the functions.

### How it works
The app itself is contained through FilmQueryApp. This displays a menu, takes in an input for which of the three options the user would like, then calls methods accordingly. I use a switch to check which of the three options I need to return - using try catch blocks or if statements to ensure that the user is giving the input we need and looking for a movie that can be found. After whichever option is performed, the user is returned to the main menu.

The methods that actually access the database and return information are stored in the DatabaseAccessorObject. This allows us to create different functions that contain different SQL queries, which will return the information requested. This information is then used to create Objects of the Film and Actor classes which is then returned to the user, based on what they'd asked for.

### Things I learned/Thoughts on the project
Awesome project, as always. This project pushed me a bit further in terms of frustration than previous projects, honestly. I was struggling with the issue of getting input for FilmId for so, so long and could not find the help I needed online, which is an issue I usually don't have. All the resources that I normally would use to figure out an issue like that kind of fell short this time, which isn't something I'm really used to at this point. Normally I'm one google search and a stack overflow click away from knowing what the issue is, but the issues I faced in this project just seemed harder to put to words and when I did, they didn't seem to turn up helpful results.

I will say, my favorite part was seeing how much my knowledge of Java has grown. I didn't have any issue at all, whatsoever, understanding the interface and the abstract methods, which is honestly something I probably couldn't have comprehended very well two or three weeks ago.
