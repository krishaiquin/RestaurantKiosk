# Restaurant Food Kiosk

### CPSC 210 Personal Project

For my CPSC 210 Personal Project, I decided to create a Restaurant Food Kiosk. This program will be responsible for
the following:
- Greeting and directing guests to their table
- Taking orders and sending it directly to the kitchen
- Presenting the bill to the guests when they are ready to pay.

The program will be used by the restaurant's guests. For the sake of simplicity, 
the project can only have one session at a time. 


I currently work at a small restaurant where we have to write down guests' orders and have to
ring it in on our POS system. I have witnessed numerous times that errors present themselves due to these multiple hand 
offs of information. I realize that we are able to eliminate these errors by sending the orders directly from the table 
to the kitchen. This program does not completely eliminate the need for servers but aims to eliminate potential errors
for ringing in orders.


# User Stories

As a **guest**, I want to be able to:

- Select a table based on our group size
- Add dish to our order
- Add special instruction to a dish
- Edit our order
    - Remove dish from our order
    - Edit the quantity of a dish in our order
    - Edit the special instructions of a dish in our order
- See the bill

As an **Admin**, I want to be able to:

- Add food to the menu
- Remove food from the menu
- Update information about the food on the menu
- Mark food on the menu as "Out of Stock"
- Add table to the restaurant
- Remove table from the restaurant
- Update information about a table

#Phase 4: Task 2

I have chosen:
- Include a type hierarchy in your code other than the one that uses the Saveable interface introduced in Phase 2.  
  You must have more than one subclass and your subclasses must have distinct functionality.  
  They must therefore override at least one method inherited from a super type and override it in different ways in 
  each of the subclasses.

**Supertype and its' subclasses**:
- Supertype: **Frame**
- Subclasses:
    - AddDishFrame
    - RemoveDishFrame
    - ViewMenuFrame
    - WelcomeFrame
    
Each of these subclasses have overridden the method **drawPanel** as each Frame needs to implement their own unique
panel.

#Phase 4: Task 3
Right off the bat, the first thing I noticed in my UML is the whole program has high coupling, and some association 
doesn't make any sense. If I had more time, I would enforce Single Responsibility Principle and spend more time on
paper to plan the relationship/association between classes. If I had more time, I would also refactor more often 
to maintain cohesion in the whole program. Overall, I wish I could clean up the whole project by introducing inheritance
and apply appropriate design principles for easy maintenance and expandability. The UML says it all, the project 
is looking like a mess.