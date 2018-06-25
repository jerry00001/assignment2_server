# introsde-2017-assignment-2-server

## Identification:  
NAME: Cheema Danish Asghar  
EMAIL: danishasghar.cheema@studenti.unitn.it  

Client Code Done by:  
NAME: Jan Main muhammad faheem  
EMAIL: main.jan@unitn.it  

Server heroku Base ULR: https://introsde-asgn2-server.herokuapp.com  
Server Git ripo: https://github.com/danishc/introsde-2017-assignment-2-server  

Client Git riop: https://github.com/faheemjan5000/introsde-2017-assignment-2-client  


## Project Requirements:
Here i am implementing a server which is used by a University who wants to manage their student's data and their relative activities, each student is consist of firstname, lastname and dateofbirth. 
on the other hand the students can have multiple activities and each activity is consist of name, type, description, place and start date.

The project is consisting of different classes where Dao class is used to connect to reomte database hosted by heroku app with the properties defined in persistence.xml. then there are Activity and person classes which contains the initialization of different parameters and and JPA and XML annotations in order to persist the data to database and move around the data via JAXB. also there is Data Initialization class which is execute once at the deploy time by the webservice implementation class. in the end i defined rest resources which which defined different CRUD opperations.  

## Implementation:  
All Getter's requests supports both XML and JSON.  
`Request #0:` at deployment DataBaseINIT class create 5 new persons to initialise the database and return the newly created persons including at least one activity preference per person.  
`Request #1: GET /person` return all person in DB.  
`Request #2: GET /person/{id}` give all the personal information plus related information.  
`Request #3: PUT /person/{id}` updates the personal information of the person identified by {id}.  
`Request #4: POST /person` creates a new person and return the newly created person with its assigned id.  
`Request #5: DELETE /person/{id}` delete the person identified by {id} from the system.  
`Request #6: GET /activity_types` return the list of activity_types.  
`Request #7: GET /person/{id}/{activity_type}` should return the list of activities for person identified by {id}.  
`Request #8: GET /person/{id}/{activity_type}/{activity_id}` returns the activities of {activity_type} identified by {activity_id} for person identified by {id}.  
`Request #9: POST /person/{id}/{activity_type}` saves a new value for the {activity_type} of person identified by {id} and archive the old value in the history.  

`Request #10 (Extra): PUT /person/{id}/{activity_type}/{activity_id}` updates the value for the {activity_type} identified by {activity_id}, related to the person identified by {id}.  
`Request #11 (Extra): GET /person/{id}/{activity_type}?before={beforeDate}&after={afterDate}` returns the activities of {activity_type} for person {id} which {start_date} is in the specified range of date.  

NOTE: i used heroku-postgresql database which is created after the application is deployed on heroku.  

## Execution:  
1: make sure you have git and heroku installed on your system.  
1: clone or download the code from server git repo.  
2: run following commands on command line   
```
git init
git add .
git commit -am "initial commit"
heroku create NAME-OF-HEROKU-APP
heroku addons:create heroku-postgresql
git push heroku master
```
