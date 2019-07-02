## Social-community is a small social openion sharing application aimed at fulfilling the following requirements.
1. Site user can register and login to the applicaiton.
2. Once logged in, he can share a status to public or private selecting a location (Using dropdown).
3. Status can be public or private.
4. Public status will be visible to all other users within the selected location. However, private status will be visible to only the user who posted.
5. Owner of a shared status can edit or delete it if he wishes.

### Setup Process:
1. The application is a Maven project. Therefore, Maven must have to present in the dev environment.
2. The application is developed with Intellij Idea. Therefore, it is preferred to use intellij for importing easily.
3. The development environment JDK version is JDK 8
5. Database is MySQL. DB username is **root** and password is **''** [Empty]. If the connection is properly configured, database will be created and initialized with seed data automatically.


## Completed checkpoints:
1. Register and login
![alt text](md-resources/registration.PNG "Registration Page")

![alt text](md-resources/login.PNG "Login Page")
2. Create, update location
![alt text](md-resources/add-view-update-location.PNG "Add, View and update locations")
3. Create, update, delete status
![alt text](md-resources/profile.PNG "Create status page")

![alt text](md-resources/profile-2.PNG "Created status list")

![alt text](md-resources/profile-3-after-status-update.PNG "Status after update")
4. Public status are visible to everyone, private are only visible to owner.

Example:
Public page:
![alt text](md-resources/index-page.PNG "Only public status are shown")

Same page accessed by logged in user:
![alt text](md-resources/index-page-after-login.PNG "Both public and private are shown to logged in user.")

5. Form fields are validated both in client and server side.
Example:
Registration page:
![alt text](md-resources/registration.PNG "Validation in registration page")

## Could not perform:
1. Updating oneToMany with HQL

## Further enhancements:
1. Updating one to many
2. Allowing to upload attachments
3. Comment and reactions.
