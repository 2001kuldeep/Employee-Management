# Employee-Management
 It is an Api tool for the management of in and out time of employees in order to track the total time spent in the office.
 
 # URLs to run project
 
Employee Controller URL:

//to add employee(POST):
http://localhost:8080/employee/add

//to get list of all employees(GET):
http://localhost:8080/employee/list

//to get details of a particular employee by empid(GET):
http://localhost:8080/employee/{empid}

//to get employees by department(GET):
http://localhost:8080/employee/{department}/list

//to delete a particular employee by empid(DELETE):
http://localhost:8080/employee/{empid}/delete

//to update a particular employee by empid(PUT):
http://localhost:8080/employee/{empid}/update

---------------------------------------------------------------------------------------------------------------------------

Timeinfo Controller URL:


//to do entry in office(POST):
http://localhost:8080/employee/intime/record

//to do exit from office(PUT):
http://localhost:8080/employee/outtime/{empid}/record

//to update update employeeid using a tid(PUT):
http://localhost:8080/employee/outtime/{tid}/update

//to get record of all employees for the current day(GET):
http://localhost:8080/employee/currentdate/reports

//to get record of all employees between two days(GET):
http://localhost:8080/employee/datebetween/reports?startDate=&endDate=

//to get record of all employees for a particular date(GET):
http://localhost:8080/employee/report?date=

//to get record of a particular employee for the current day(GET):
http://localhost:8080/employee/{empid}/currentdate/reports

//to get record of a particular employee between two days(GET):
http://localhost:8080/employee/{empid}/datebetween/reports?startDate=&endDate=

//to get record of a particular employee for a particular date(GET):
http://localhost:8080/employee/{empid}/anydate/reports?date=
