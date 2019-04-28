# Sample
This quick exercise will require you to create a class that:
- Will make a REST call to a service and return its results as a JSON String
- Will take a JSON String, process the JSON String and return the processed JSON as a JSON String

JSON input (sample)
```
  [
   { email: 'aetomala@us.ibm.com', roles: ['Viewer'] },
   { email: 'aetomala@us.ibm.com', roles: ['Viewer'] },
   { email: 'aetomala@us.ibm.com', roles: ['Reader'] },
   { email: 'dademarc@us.ibm.com', roles: ['Viewer'] },
   { email: 'dademarc@us.ibm.com', roles: ['Reader'] },
   {
     email: 'daljit.sharma@ibm.com',
     roles: ['Administrator', 'Manager']
   }
  ]
```
JSON output (sample)
```
  {
   "aetomala@us.ibm.com":{roles:["Viewer","Reader"]},
   "dademarco@us.ibm.com":{roles:["Viewer","Reader"]},
   "daljit.sharma@ibm.com":{roles: ["Administrator", "Manager"] }
  }
```
- Will handle a JSON Parsing exception and return a custom exception.

The JUnit test in this execise has been designed to evaluate your implementation of each task above.
