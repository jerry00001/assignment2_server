
Identification:  
NAME: Jiarui.xiong  
EMAIL: Jiarui.xiong@studenti.unitn.it
HEROKU Server: https://introsde-asgn2-server.herokuapp.com  
Server Git ripo: https://github.com/jerry00001/assignment2_server
Client Git riop: https://github.com/jerry00001/assignment2_client

Project:
The University wants to understand better the preferences of their students, so they need a system that help them register Users and their preferred activities. Ideally they need to register firstname, lastname, birthdate of the students. About the activities they need to register name, description, type of activity, place, start date/time.The University needs to add, read, modify and delete the information via Web (REST Services). Additionally the results should be save in a database.

Dao class is used to connect to reomte database hosted by heroku app with the properties defined in persistence.xml.  
Activity and person classes contains the initialization of different parameters and and JPA and XML annotations in order to persist the data to database and move around the data via JAXB.
Data Initialization class which is execute once at the deploy time by the webservice implementation class. in the end i defined rest resources which which defined different CRUD opperations.  

Execution:

`Request #0:` at deployment DataBaseINIT class create 5 new persons to initialise the database and return the newly created persons including at least one activity preference per person.  
`Request #1: GET /person` return all person in database.  
`Request #2: GET /person/{id}` give all the personal information of {id}.  
`Request #3: PUT /person/{id}` updates the personal information of {id}.  
`Request #4: POST /person` creates a new person and return the newly created person with its assigned id.  
`Request #5: DELETE /person/{id}` delete the person of {id} from the system.  
`Request #6: GET /activity_types` return the list of activity_types.  
`Request #7: GET /person/{id}/{activity_type}` should return the list of activities for person identified by {id}.  
`Request #8: GET /person/{id}/{activity_type}/{activity_id}` returns the activities of {activity_type} identified by {activity_id} for person identified by {id}.  
`Request #9: POST /person/{id}/{activity_type}` saves a new value for the {activity_type} of person identified by {id} and archive the old value in the history.  

`Request #10 (Extra): PUT /person/{id}/{activity_type}/{activity_id}` updates the value for the {activity_type} identified by {activity_id}, related to the person identified by {id}.  
`Request #11 (Extra): GET /person/{id}/{activity_type}?before={beforeDate}&after={afterDate}` returns the activities of {activity_type} for person {id} which {start_date} is in the specified range of date.  



Execution:  
git init
git add .
git commit -am "initial commit"
heroku create NAME-OF-HEROKU-APP
git push heroku master
