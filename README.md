
# Assignment 2 COSC2391 Further Programming

# Nabil Rusafee s3842767


##HotDesk
-------------------------------------------------------------------------------------
HotDesk is a program which manages seating arrangements at an office. It allows employees to login and book any available desk to sit in for a period of time. Administrators 
can also manage seating arrangements  by rejecting bookings made by employees or by locking down specific seats to adhere to COVID-19 social distancing protocols.


For my implementation of Assignment 2 I have used the MVC design pattern to develop the program. HotDesk consists of 
the model, view and controller.

The controller has the following classes:
- `AdminLandingController` - Controller for the Admin home page from where they can access Desk Manager and employee manager.
- `DeskBookingController` - Controller for the Desk Booking page from where employees can book their desks and admins can reject bookings, lockdown desks or export data to CSV.
- `EmployeeLandingController` - Controller for the Employee homepage from where they can access the booking page or sign out.
- `EmployeeManagerController` - Controller for the Employee manager from where Admins can view all the employees in the database and edit or delete information.
- `HomeController` - The homepage of the program, users can login or register here.
- `LoginController` - Login page from where users can login or go to reset their password.
- `RegisterController` - Controller for the Register page from where users can register their account.
- `ResetPasswordController` - Controller for the Reset password page from where users can reset their password in case they forget their password.

The model has the following classes:
- `DeskBookModel` - Model which handles the functions required to book a desk, communicates with the database.
- `DeskModel` - Stores information about the desks like status.
- `EmployeeManagerModel` - Handles the functions relating to employee management such as viewing and editing employee information and deleting employees.
- `InitializaApplication` - Initializes functions such as buttons for desk booking.
- `LoginModel` - Handles login functions such as verifying login information.
- `RegisterModel` - Handles registering a new user
- `ResetPasswordModel` - Handles resetting a user's password 
- `UserModel` - Stores information about a user
- `UserSession` - Temporarily stores information about a logged-in user.

##Design
-----------------------------
Some design choices in this assignment are:
- _Storing a user's session in a UserSession class_ - When a user logs in, the user's details are temporarily stored inside static variables inside
   of the UserSession class. They are made static so that they can be easily accessed from anywhere they are needed such as desk booking so that the program
  knows which user is making the booking. When a user logs out, the details are cleared.
- _Adhering to the MVC design pattern_ - all pages/features have their own controller and model (with the exception of a couple of models). This allows the controller to handle user input and send information to the view and model while the model
handles core functionality such as handling and processing data.
  

## Lessons learnt
---------------------
In developing this program I have learnt how easy to use and accessible program development in Java is thanks to JavaFX and
assistive programs such as Scene Builder. In addition, developing this program has taught me how to properly use many of Java's libraries
and programming techniques to increase the efficiency of programming.



